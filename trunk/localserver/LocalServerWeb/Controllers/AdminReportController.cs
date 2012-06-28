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
using LocalServerWeb.Resources.Views.AdminReport;
using LocalServerWeb.Resources.Views.Shared;

namespace LocalServerWeb.Controllers
{
    public class AdminReportController : ManagerBaseController
    {

        public ActionResult Index()
        {
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            ViewData["nguoiLap"] = nguoiLap;
            return View();
        }

        public ActionResult PrintRevenueDayReport(int ngay, int thang, int nam)
        {
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, ngay);
            }
            catch (Exception)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            if (!Reports.ReportManager.PrintRevenueDayReport(ngayLap, nguoiLap))
            {
                TempData["error"] = AdminReportString.ErrorCannotPrint;
                return RedirectToAction("Index", "Error");
            }

            return RedirectToAction("Index");

        }

        public ActionResult PrintRevenueMonthReport(int thang, int nam)
        {
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, 1);
            }
            catch (Exception)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            if(!Reports.ReportManager.PrintRevenueMonthReport(ngayLap, nguoiLap))
            {
                TempData["error"] = AdminReportString.ErrorCannotPrint;
                return RedirectToAction("Index", "Error");
            }

            return RedirectToAction("Index");
        }

        public ActionResult PrintRevenuePeriodReport(int ngayBatDau, int thangBatDau, int namBatDau, int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            DateTime tuNgay;
            DateTime denNgay;

            try
            {
                tuNgay = new DateTime(namBatDau, thangBatDau, ngayBatDau);
                denNgay = new DateTime(namKetThuc, thangKetThuc, ngayKetThuc);
            }
            catch (Exception)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            if(!Reports.ReportManager.PrintRevenuePeriodReport(tuNgay, denNgay, nguoiLap))
            {
                TempData["error"] = AdminReportString.ErrorCannotPrint;
                return RedirectToAction("Index", "Error");
            }

            return RedirectToAction("Index");
        }

    }
}
