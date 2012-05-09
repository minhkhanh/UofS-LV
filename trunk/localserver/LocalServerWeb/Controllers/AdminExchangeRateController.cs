using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminTable;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminExchangeRateController : BaseController 
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
            ViewData["listTiGia"] = TiGiaBUS.LayDanhSachTiGia();
            return View();
        }
    }
}