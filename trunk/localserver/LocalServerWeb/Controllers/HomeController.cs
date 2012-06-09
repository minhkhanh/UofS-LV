using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    [HandleError]
    public class HomeController : BaseController
    {
        public ActionResult Index()
        {
            ViewData["Message"] = "Welcome to eMenu!";

            return View();
        }

        public ActionResult About()
        {
            return View();
        }
    }
}
