using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminFoodController : BaseController
    {
        //
        // GET: /AdminHome/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 0);
            return View();
        }

        public ActionResult AddFood()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 1);
            ViewData["listDanhMuc"] = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoNgonNgu(SharedCode.GetCurrentLanguage(Session));
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }
            return View();
        }
    }
}
