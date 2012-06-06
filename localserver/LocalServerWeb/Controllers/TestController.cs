using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class TestController : BaseController
    {
        //
        // GET: /Test/

        public bool Index()
        {
            return false;
        }


        public bool TestPrintBill(int maHoaDon)
        {
            var hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
            if (hoaDon == null) 
                return false;

            return Reports.ReportManager.PrintBill(hoaDon.MaHoaDon, SharedCode.GetCurrentLanguage(Session).MaNgonNgu);
        }

        public bool Test2()
        {
            DateTime ngay = new DateTime(2012, 4, 4);
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenueDayReport(ngay, nguoiLap);
        }

        public bool Test3()
        {
            DateTime ngay = new DateTime(2012, 4, 4);
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenueMonthReport(ngay, nguoiLap);
        }

        public bool Test4()
        {
            DateTime ngayBatDau = new DateTime(2012, 1, 1);
            DateTime ngayKetThuc = new DateTime(2012, 12, 30);
            TaiKhoan taiKhoan = (TaiKhoan)Session["taiKhoan"];
            string nguoiLap = (taiKhoan != null) ? taiKhoan.HoTen : " ";

            return Reports.ReportManager.PrintRevenuePeriodReport(ngayBatDau, ngayKetThuc, nguoiLap);
        }
    }
}
