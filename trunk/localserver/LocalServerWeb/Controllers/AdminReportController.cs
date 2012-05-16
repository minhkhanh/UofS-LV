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
            ViewData["maHoaDon"] = maHoaDon;
            ViewData["maNgonNgu"] = maNgonNgu;
            return View();
        }

    }
}
