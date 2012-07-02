using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.ReportForms;
using LocalServerWeb.Reports;
using LocalServerWeb.Reports.RevenueDayReport;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.ReportsForm
{
    public partial class RevenueDayReportForm : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IsPostBack) return;
            try
            {
                // Lay input
                string nguoiLap = Request.QueryString["p"];
                int ngay = int.Parse(Request.QueryString["d"]);
                int thang = int.Parse(Request.QueryString["m"]);
                int nam = int.Parse(Request.QueryString["y"]);

     

                DateTime ngayLap = new DateTime(nam, thang, ngay);

                List<RevenueDayReportData> listData = new List<RevenueDayReportData>();
                List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoNgay(ngayLap);

                // Thong tin nha hang
                var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
                var tsDiaChiNhaHang = ThamSoBUS.LayThamSo("DiaChiNhaHang");
                string tenNhaHang = (tsTenNhaHang != null) ? tsTenNhaHang.GiaTri : " ";
                string diaChiNhaHang = (tsDiaChiNhaHang != null) ? tsDiaChiNhaHang.GiaTri : " ";

                float khuyenMai = 0;
                float phuThu = 0;
                float tongTien = 0;
                // Lap hoa don cho ngay nao
                string thoiDiemLap = "Ngày " + ngayLap.ToShortDateString();

                int iCount = 1;
                foreach (HoaDon hoaDon in listHoaDon)
                {
                    RevenueDayReportData data = new RevenueDayReportData();
                    data.Stt = iCount++;
                    data.MaHoaDon = hoaDon.MaHoaDon;
                    data.ThoiDiemLap = hoaDon.ThoiDiemLap.ToShortTimeString();
                    data.NguoiLap = hoaDon.TaiKhoan.TenTaiKhoan;
                    data.TongTien = hoaDon.TongTien;
                    data.BanChinh = hoaDon.Ban.TenBan;
                    data.BanGhep = hoaDon.MoTaBanGhep;
                    data.PhuThu = hoaDon.PhuThu.GiaTang;

                    tongTien += data.TongTien;
                    phuThu += data.PhuThu;
                    khuyenMai += HoaDonBUS.LayTongKhuyenMai(hoaDon.MaHoaDon);

                    listData.Add(data);
                }

                // Tham so
                List<ReportParameter> listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("ThoiDiemLap", thoiDiemLap));
                listParameter.Add(new ReportParameter("NguoiLap", nguoiLap));
                listParameter.Add(new ReportParameter("TongTien", tongTien.ToString()));
                listParameter.Add(new ReportParameter("KhuyenMai", khuyenMai.ToString()));
                listParameter.Add(new ReportParameter("PhuThu", phuThu.ToString()));
                listParameter.Add(new ReportParameter("TenNhaHang", tenNhaHang));
                listParameter.Add(new ReportParameter("DiaChiNhaHang", diaChiNhaHang));
                
                rvReport.Reset();
                rvReport.LocalReport.ReportPath = Path.Combine(MapPath("~/Reports/RevenueDayReport"), "RevenueDayReport.rdlc");
                rvReport.LocalReport.SetParameters(listParameter);
                rvReport.LocalReport.DataSources.Clear();
                rvReport.LocalReport.DataSources.Add(new ReportDataSource("RevenueDayReportData", listData));
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