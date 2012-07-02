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
using LocalServerWeb.Reports.RevenuePeriodReport;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.ReportsForm
{
    public partial class RevenuePeriodReportForm : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IsPostBack) return;
            try
            {
                // Lay input
                string nguoiLap = Request.QueryString["p"];
                int ngayBatDau = int.Parse(Request.QueryString["sd"]);
                int thangBatDau = int.Parse(Request.QueryString["sm"]);
                int namBatDau = int.Parse(Request.QueryString["sy"]);
                int ngayKetThuc = int.Parse(Request.QueryString["ed"]);
                int thangKetThuc = int.Parse(Request.QueryString["em"]);
                int namKetThuc = int.Parse(Request.QueryString["ey"]);


                DateTime tuNgay = new DateTime(namBatDau, thangBatDau, ngayBatDau);
                DateTime denNgay = new DateTime(namKetThuc, thangKetThuc, ngayKetThuc);


                List<RevenuePeriodReportData> listData = new List<RevenuePeriodReportData>();
                List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoThoiGian(tuNgay, denNgay);

                // Thong tin nha hang
                var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
                var tsDiaChiNhaHang = ThamSoBUS.LayThamSo("DiaChiNhaHang");
                string tenNhaHang = (tsTenNhaHang != null) ? tsTenNhaHang.GiaTri : " ";
                string diaChiNhaHang = (tsDiaChiNhaHang != null) ? tsDiaChiNhaHang.GiaTri : " ";

                // Report parameters
                float khuyenMai = 0;
                float phuThu = 0;
                float tongTien = 0;
                // Lap hoa don cho khoang thoi gian nao
                string thoiDiemLap = "Từ " + tuNgay.ToShortDateString() + " đến " + denNgay.ToShortDateString();

                int iCount = 1;

                // Danh sach Hoa Don da duoc sap xep tu Nho den Lon
                // Chia theo tung ngay
                DateTime ngayDangXet = new DateTime(2000, 1, 1);
                foreach (HoaDon hoaDon in listHoaDon)
                {
                    if (hoaDon.ThoiDiemLap.Date != ngayDangXet.Date)
                    {
                        ngayDangXet = hoaDon.ThoiDiemLap;
                        RevenuePeriodReportData dataMoi = new RevenuePeriodReportData();
                        dataMoi.Stt = iCount++;
                        dataMoi.Ngay = hoaDon.ThoiDiemLap.ToShortDateString();
                        dataMoi.Thang = hoaDon.ThoiDiemLap.Month;
                        listData.Add(dataMoi);
                    }

                    // Ap dung cho 1 ngay
                    RevenuePeriodReportData data = listData[listData.Count - 1];
                    data.TongSoHoaDon++;
                    data.TongTien += hoaDon.TongTien;
                    data.PhuThu += hoaDon.PhuThu.GiaTang;
                    data.KhuyenMai += HoaDonBUS.LayTongKhuyenMai(hoaDon.MaHoaDon);

                    tongTien += data.TongTien;
                    phuThu += data.PhuThu;
                    khuyenMai += data.KhuyenMai;
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
                rvReport.LocalReport.ReportPath = Path.Combine(MapPath("~/Reports/RevenuePeriodReport"), "RevenuePeriodReport.rdlc");
                rvReport.LocalReport.SetParameters(listParameter);
                rvReport.LocalReport.DataSources.Clear();
                rvReport.LocalReport.DataSources.Add(new ReportDataSource("RevenuePeriodReportData", listData));
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