using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminRestaurant;
using LocalServerWeb.Resources.Views.Shared;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminRestaurantController : BaseController
    {
        public ActionResult Index()
        {
            return View();
        }
    }
}