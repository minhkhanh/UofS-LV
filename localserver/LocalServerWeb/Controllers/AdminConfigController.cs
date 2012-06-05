using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminConfigController : BaseController
    {
        //
        // GET: /AdminConfig/

        public ActionResult Index()
        {
            var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
            var billPrinter = "";
            if (thamSoBillPrinter!=null) 
                billPrinter = thamSoBillPrinter.GiaTri;
            ViewData["billPrinter"] = billPrinter;
            return View();
        }

        [HttpPost]
        public bool ChangeBillPrinter(string billPrinter)
        {
            if (billPrinter == null || billPrinter.Length <= 0) 
                return false;
            var thamSoBillPrinter = ThamSoBUS.LayThamSo("BillPrinter");
            if (thamSoBillPrinter == null)
                return false;
            thamSoBillPrinter.GiaTri = billPrinter;

            return ThamSoBUS.CapNhat(thamSoBillPrinter);
        }
    }
}
