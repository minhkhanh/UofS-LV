using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using LocalServerBUS;
using LocalServerWeb.Codes;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.Reports
{
    public class ReportManager
    {
        public static bool PrintBill(int maHoaDon, int maNgonNgu)
        {
            try
            {
                var hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
                if (hoaDon == null) return false;
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter==null || thamSoBillPrinter.GiaTri.Length<=0) return false;
                string deviceInfo =
                @"<DeviceInfo>
                    <OutputFormat>EMF</OutputFormat>
                    <PageWidth>8.5in</PageWidth>
                    <PageHeight>11in</PageHeight>
                    <MarginTop>0.25in</MarginTop>
                    <MarginLeft>0.25in</MarginLeft>
                    <MarginRight>0.25in</MarginRight>
                    <MarginBottom>0.25in</MarginBottom>
                </DeviceInfo>";
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

                var listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("MaHoaDon", hoaDon.MaHoaDon.ToString()));
                listParameter.Add(new ReportParameter("ThoiDiemLap", hoaDon.ThoiDiemLap.ToString("dd/MM/yyyy H:mm:ss")));
                listParameter.Add(new ReportParameter("TenNguoiLap", hoaDon.TaiKhoan.HoTen));
                listParameter.Add(new ReportParameter("TongTien", hoaDon.TongTien.ToString()));
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
    }
}