using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminExchangeRate;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminCategoryController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            ViewData["listTiGia"] = TiGiaBUS.LayDanhSachTiGiaThatSu();
            return View();
        }
    }
}