using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.Reports;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.Controllers
{
    public class AdminReportController : BaseController
    {

        public ActionResult Index()
        {
            int maHoaDon = 1; //du lieu gia
            int maNgonNgu = SharedCode.GetCurrentLanguage(Session).MaNgonNgu;

            ViewData["maHoaDon"] = maHoaDon;
            ViewData["maNgonNgu"] = maNgonNgu;
            return View();
        }

        public bool PrintRevenueDayReport(int ngay, int thang, int nam)
        {
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, ngay);
            }
            catch (Exception e)
            {
                return false;
            }
            
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenueDayReport(ngayLap, nguoiLap);
        }

        public bool PrintRevenueMonthReport(int thang, int nam)
        {
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, 1);
            }
            catch (Exception e)
            {
                return false;
            }
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenueMonthReport(ngayLap, nguoiLap);
        }

        public bool PrintRevenuePeriodReport(int ngayBatDau, int thangBatDau, int namBatDau, int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            DateTime tuNgay;
            DateTime denNgay;

            try
            {
                tuNgay = new DateTime(namBatDau, thangBatDau, ngayBatDau);
                denNgay = new DateTime(namKetThuc, thangKetThuc, ngayKetThuc);
            }
            catch (Exception e)
            {
                return false;
            }

            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenuePeriodReport(tuNgay, denNgay, nguoiLap);
        }

    }
}
