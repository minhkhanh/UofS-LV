using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Reports;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.Controllers
{
    public class AdminReportController : BaseController
    {
        //
        // GET: /AdminReport/

        public ActionResult Index()
        {
            int maHoaDon = 1; //du lieu gia
            int maNgonNgu = SharedCode.GetCurrentLanguage(Session).MaNgonNgu;
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);
            try
            {
                var hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
                if (hoaDon == null) return RedirectToAction("Index", "Error");
                var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
                if (thamSoBillPrinter == null || thamSoBillPrinter.GiaTri.Length <= 0) return RedirectToAction("Index", "Error");
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
                //var report = new PrintReport(@"LocalServerWeb.Reports.BillReport.rdlc", thamSoBillPrinter.GiaTri, deviceInfo);
                

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
                ViewData["reportData"] = new ReportDataSource("BillReportData", datas);

                var listParameter = new List<ReportParameter>();
                listParameter.Add(new ReportParameter("MaHoaDon", hoaDon.MaHoaDon.ToString()));
                listParameter.Add(new ReportParameter("ThoiDiemLap", hoaDon.ThoiDiemLap.ToString("dd/MM/yyyy H:mm:ss")));
                listParameter.Add(new ReportParameter("TenNguoiLap", hoaDon.TaiKhoan.HoTen));
                listParameter.Add(new ReportParameter("TongTien", hoaDon.TongTien.ToString()));
                listParameter.Add(new ReportParameter("TenBan", hoaDon.Ban.TenBan));
                listParameter.Add(new ReportParameter("PhuThu", hoaDon.PhuThu.TenPhuThu));

                ViewData["reportParameter"] = listParameter;


                ViewData["reportPath"] = "LocalServerWeb.Reports.BillReport.rdlc";// Path.Combine(HttpContext.Server.MapPath("/Reports"), "BillReport.rdlc");   
                return View();
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return RedirectToAction("Index", "Error");
        }

    }
}
