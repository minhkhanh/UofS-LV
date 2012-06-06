using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using Microsoft.Reporting.WebForms;
using LocalServerWeb.Reports.RevenueDayReport;
using LocalServerWeb.Reports.RevenueMonthReport;
using LocalServerWeb.Reports.RevenuePeriodReport;
using System.Globalization;

namespace LocalServerWeb.Reports
{
    public class ReportManager
    {
        public static string deviceInfo = @"<DeviceInfo>
                    <OutputFormat>EMF</OutputFormat>
                    <PageWidth>8.5in</PageWidth>
                    <PageHeight>11in</PageHeight>
                    <MarginTop>0.25in</MarginTop>
                    <MarginLeft>0.25in</MarginLeft>
                    <MarginRight>0.25in</MarginRight>
                    <MarginBottom>0.25in</MarginBottom>
                </DeviceInfo>";

        public static bool PrintBill(int maHoaDon, int maNgonNgu)
        {
            try
            {
                var hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
                if (hoaDon == null) return false;
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter==null || thamSoBillPrinter.GiaTri.Length<=0) return false;

                var printReport = new PrintReport(@"LocalServerWeb.Reports.BillReport.rdlc", thamSoBillPrinter.GiaTri, deviceInfo);

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
                printReport.AddDataSoruce(new ReportDataSource("BillReportData", datas));

                string strTongTien = String.Format("{0:0,0}", hoaDon.TongTien).Replace('.', ',') + " VND";

                var listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("MaHoaDon", hoaDon.MaHoaDon.ToString()));
                listParameter.Add(new ReportParameter("ThoiDiemLap", hoaDon.ThoiDiemLap.ToString("dd/MM/yyyy H:mm:ss")));
                listParameter.Add(new ReportParameter("TenNguoiLap", hoaDon.TaiKhoan.HoTen));
                listParameter.Add(new ReportParameter("TongTien", strTongTien));
                listParameter.Add(new ReportParameter("TenBan", hoaDon.Ban.TenBan));
                listParameter.Add(new ReportParameter("PhuThu", hoaDon.PhuThu.TenPhuThu));
                printReport.SetParameters(listParameter.ToArray());

                printReport.Print();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool PrintRevenueDayReport(DateTime ngay, string nguoiLap)
        {
            try
            {
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter == null || thamSoBillPrinter.GiaTri.Length <= 0) 
                    return false;
   
                PrintReport printReport = new PrintReport(@"LocalServerWeb.Reports.RevenueDayReport.RevenueDayReport.rdlc", thamSoBillPrinter.GiaTri, deviceInfo);

                List<RevenueDayReportData> listData = new List<RevenueDayReportData>();
                List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoNgay(ngay);

                float khuyenMai = 0;
                float phuThu = 0;
                float tongTien = 0;
                // Lap hoa don cho ngay nao
                string thoiDiemLap = "Ngày " + ngay.ToShortDateString();

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

                printReport.AddDataSoruce(new ReportDataSource("RevenueDayReportData", listData));
           
                string strTongTien = String.Format("{0:0,0}", tongTien).Replace('.', ',') + " VND";
                string strKhuyenMai = String.Format("{0:0,0}", khuyenMai).Replace('.', ',') + " VND";
                string strPhuThu = String.Format("{0:0,0}", phuThu).Replace('.', ',') + " VND";

                List<ReportParameter> listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("ThoiDiemLap", thoiDiemLap));
                listParameter.Add(new ReportParameter("NguoiLap", nguoiLap));
                listParameter.Add(new ReportParameter("TongTien", strTongTien));
                listParameter.Add(new ReportParameter("KhuyenMai", strKhuyenMai));
                listParameter.Add(new ReportParameter("PhuThu", strPhuThu));

                printReport.SetParameters(listParameter.ToArray());

                // Print this report now
                printReport.Print();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool PrintRevenueMonthReport(DateTime ngay, string nguoiLap)
        {
            try
            {
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter == null || thamSoBillPrinter.GiaTri.Length <= 0)
                    return false;

                PrintReport printReport = new PrintReport(@"LocalServerWeb.Reports.RevenueMonthReport.RevenueMonthReport.rdlc", thamSoBillPrinter.GiaTri, deviceInfo);

                List<RevenueMonthReportData> listData = new List<RevenueMonthReportData>();
                List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoThang(ngay);

                // Report parameters
                float khuyenMai = 0;
                float phuThu = 0;
                float tongTien = 0;
                // Lap hoa don cho thang nao
                string thoiDiemLap = "Tháng " + ngay.Month + "/" + ngay.Year;

                int iCount = 1;

                // Danh sach Hoa Don da duoc sap xep tu Nho den Lon
                // Chia theo tung ngay
                DateTime ngayDangXet = new DateTime(2000, 1, 1);
                foreach (HoaDon hoaDon in listHoaDon)
                {
                    if (hoaDon.ThoiDiemLap.Date != ngayDangXet.Date)
                    {
                        ngayDangXet = hoaDon.ThoiDiemLap;
                        RevenueMonthReportData dataMoi = new RevenueMonthReportData();
                        dataMoi.Stt = iCount++;
                        dataMoi.Ngay = hoaDon.ThoiDiemLap.ToShortDateString();
                        listData.Add(dataMoi);
                    }

                    // Ap dung cho 1 ngay
                    RevenueMonthReportData data = listData[listData.Count - 1];
                    data.TongSoHoaDon++;
                    data.TongTien += hoaDon.TongTien;
                    data.PhuThu += hoaDon.PhuThu.GiaTang;
                    data.KhuyenMai += HoaDonBUS.LayTongKhuyenMai(hoaDon.MaHoaDon);

                    tongTien += data.TongTien;
                    phuThu += data.PhuThu;
                    khuyenMai += data.KhuyenMai;
                }

                printReport.AddDataSoruce(new ReportDataSource("RevenueMonthReportData", listData));

                string strTongTien = String.Format("{0:0,0}", tongTien).Replace('.', ',') + " VND";
                string strKhuyenMai = String.Format("{0:0,0}", khuyenMai).Replace('.', ',') + " VND";
                string strPhuThu = String.Format("{0:0,0}", phuThu).Replace('.', ',') + " VND";

                List<ReportParameter> listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("ThoiDiemLap", thoiDiemLap));
                listParameter.Add(new ReportParameter("NguoiLap", nguoiLap));
                listParameter.Add(new ReportParameter("TongTien", strTongTien));
                listParameter.Add(new ReportParameter("KhuyenMai", strKhuyenMai));
                listParameter.Add(new ReportParameter("PhuThu", strPhuThu));

                printReport.SetParameters(listParameter.ToArray());

                // Print this report now
                printReport.Print();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool PrintRevenuePeriodReport(DateTime ngayBatDau, DateTime ngayKetThuc, string nguoiLap)
        {
            try
            {
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter == null || thamSoBillPrinter.GiaTri.Length <= 0)
                    return false;

                PrintReport printReport = new PrintReport(@"LocalServerWeb.Reports.RevenuePeriodReport.RevenuePeriodReport.rdlc", thamSoBillPrinter.GiaTri, deviceInfo);

                List<RevenuePeriodReportData> listData = new List<RevenuePeriodReportData>();
                List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoThoiGian(ngayBatDau, ngayKetThuc);

                // Report parameters
                float khuyenMai = 0;
                float phuThu = 0;
                float tongTien = 0;
                // Lap hoa don cho khoang thoi gian nao
                string thoiDiemLap = "Từ " + ngayBatDau.ToShortDateString() + " đến " + ngayKetThuc.ToShortDateString();

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

                printReport.AddDataSoruce(new ReportDataSource("RevenuePeriodReportData", listData));

                string strTongTien = String.Format("{0:0,0}", tongTien).Replace('.', ',') + " VND";
                string strKhuyenMai = String.Format("{0:0,0}", khuyenMai).Replace('.', ',') + " VND";
                string strPhuThu = String.Format("{0:0,0}", phuThu).Replace('.', ',') + " VND";

                List<ReportParameter> listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("ThoiDiemLap", thoiDiemLap));
                listParameter.Add(new ReportParameter("NguoiLap", nguoiLap));
                listParameter.Add(new ReportParameter("TongTien", strTongTien));
                listParameter.Add(new ReportParameter("KhuyenMai", strKhuyenMai));
                listParameter.Add(new ReportParameter("PhuThu", strPhuThu));

                printReport.SetParameters(listParameter.ToArray());

                // Print this report now
                printReport.Print();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        
    }
}