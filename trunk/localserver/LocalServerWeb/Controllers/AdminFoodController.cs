using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminFoodController : BaseController
    {
        //
        // GET: /AdminHome/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 1, 1);
            return View();
        }

    }
}
