using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.ReportForms;
using LocalServerWeb.Reports;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.ReportsForm
{
    public partial class BillReportForm : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IsPostBack) return;
            try
            {
                // lay input
                int maHoaDon = int.Parse(Request.QueryString["maHoaDon"]);
                int maNgonNgu = int.Parse(Request.QueryString["maNgonNgu"]);
                //string reportPath = Request.QueryString["reportPath"];

                var hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
                if (hoaDon == null) Response.Write("<script> window.close();</script>");
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter == null || thamSoBillPrinter.GiaTri.Length <= 0) Response.Write("<script> window.close();</script>");

                var datas = new List<BillReportData>();
                var listChiTietHoaDon = ChiTietHoaDonBUS.LayNhieuChiTietHoaDon(hoaDon.MaHoaDon);
                int iCount = 0;
                foreach (var chiTietHoaDon in listChiTietHoaDon)
                {
                    datas.Add(new BillReportData
                    {
                        Stt = ++iCount,
                        DonGiaLuuTru = chiTietHoaDon.DonGiaLuuTru,
                        GiaTriKhuyenMaiLuuTru = chiTietHoaDon.GiaTriKhuyenMaiLuuTru,
                        SoLuong = chiTietHoaDon.SoLuong,
                        TenDonViTinh = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(chiTietHoaDon.DonViTinh.MaDonViTinh, maNgonNgu).TenDonViTinh,
                        TenMonAn = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(chiTietHoaDon.MonAn.MaMonAn, maNgonNgu).TenMonAn,
                        ThanhTien = chiTietHoaDon.ThanhTien
                    });
                }

                var listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("MaHoaDon", hoaDon.MaHoaDon.ToString()));
                listParameter.Add(new ReportParameter("ThoiDiemLap", hoaDon.ThoiDiemLap.ToString("dd/MM/yyyy H:mm:ss")));
                listParameter.Add(new ReportParameter("TenNguoiLap", hoaDon.TaiKhoan.HoTen));
                listParameter.Add(new ReportParameter("TongTien", hoaDon.TongTien.ToString()));
                listParameter.Add(new ReportParameter("TenBan", hoaDon.Ban.TenBan));
                listParameter.Add(new ReportParameter("PhuThu", hoaDon.PhuThu.TenPhuThu));


                rvReport.Reset();
                rvReport.LocalReport.ReportPath = Path.Combine(MapPath("/Reports"), "BillReport.rdlc");  //"LocalServerWeb.Reports.BillReport1.rdlc";//reportPath;
                rvReport.LocalReport.SetParameters(listParameter);
                rvReport.LocalReport.DataSources.Clear();
                rvReport.LocalReport.DataSources.Add(new ReportDataSource("BillReportData", datas));
                rvReport.DataBind();
                rvReport.LocalReport.Refresh();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.Write(ex.StackTrace);
                Response.Write("<script> window.close();</script>");
            }
        }
    }
}