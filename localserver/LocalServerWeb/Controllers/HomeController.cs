using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    [HandleError]
    public class HomeController : Controller
    {
        public  HomeController()
        {
            Console.Out.WriteLine("HomeController: ");
            SharedCode.KhoiTaoCSDL();
        }
        public ActionResult Index()
        {
            ViewData["Message"] = "Welcome to ASP.NET MVC!";

            SharedCode.FillCommonData2View(ViewData, HttpContext);

            return View();
        }

        public ActionResult About()
        {
            return View();
        }
    }
}
