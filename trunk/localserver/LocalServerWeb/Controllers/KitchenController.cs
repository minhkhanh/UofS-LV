using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class KitchenController : Controller
    {
        //
        // GET: /Kitchen/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            return View();
        }

    }
}
