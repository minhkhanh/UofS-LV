using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
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
            if (hoaDon == null) return false;
            return Reports.ReportManager.PrintBill(hoaDon.MaHoaDon, SharedCode.GetCurrentLanguage(Session).MaNgonNgu);
        }
    }
}
