using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminLanguage;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminLanguageController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            ViewData["listNgonNgu"] = NgonNguBUS.LayDanhSachNgonNgu();
            return View();
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 3);

            return View();
        }

        public ActionResult Edit()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);

            return View();
        }

        public ActionResult Delete(int? id)
        {
            if (id == null ||id <= 0)
            {
                TempData["error"] = AdminLanguageString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            NgonNgu objNgonNgu = NgonNguBUS.LayNgonNguTheoMa(id ?? 1);
            if (objNgonNgu == null)
            {
                TempData["error"] = AdminLanguageString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!NgonNguBUS.XoaNgonNgu(objNgonNgu.MaNgonNgu))
            {
                TempData["error"] = AdminLanguageString.DeleteError;
                return RedirectToAction("Index");
            }

            return RedirectToAction("Index");
        }


    }
}