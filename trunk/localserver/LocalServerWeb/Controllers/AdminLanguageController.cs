using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminLanguage;
using LocalServerWeb.Resources.Views.Shared;
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
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }   

            return View();
        }

        [HttpPost]
        public ActionResult Add(string tenNgonNgu, string kiHieu)
        {
            TempData["tenNgonNgu"] = tenNgonNgu;
            TempData["kiHieu"] = kiHieu;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;
            var regexTenNgonNgu = new Regex("[a-zA-Z]{2,20}");
            if (tenNgonNgu == null || !regexTenNgonNgu.IsMatch(tenNgonNgu))
            {
                bCheckOk = false;
                checkDic.Add("tenNgonNgu", SharedString.InputWrong);
            }

            var regexKiHieu1 = new Regex("[a-z]{2}");
            var regexKiHieu2 = new Regex("[a-z]{2}-[A-Z]{2}");
            var regexKiHieu3 = new Regex("[a-z]{2}-[A-Z]{2}-[A-Z]{2}");

            if (kiHieu == null || !(regexKiHieu1.IsMatch(kiHieu) || regexKiHieu2.IsMatch(kiHieu) || regexKiHieu3.IsMatch(kiHieu)))
            {
                bCheckOk = false;
                checkDic.Add("kiHieu", SharedString.InputWrong);
            }


            if (bCheckOk)
            {
                try
                {
                    NgonNgu ngonNgu = new NgonNgu();
                    ngonNgu.TenNgonNgu = tenNgonNgu;
                    ngonNgu.KiHieu = kiHieu;

                    // Need to clear TempData
                    if (NgonNguBUS.Them(ngonNgu))
                        return RedirectToAction("Index", "AdminLanguage");
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            NgonNgu objNgonNgu = NgonNguBUS.LayNgonNguTheoMa(id ?? 0);
            if (id == null || objNgonNgu == null)
            {
                TempData["error"] = AdminLanguageString.ErrorLanguageNotFound;
            }
            else
            {
                TempData["tenNgonNgu"] = objNgonNgu.TenNgonNgu;
                TempData["kiHieu"] = objNgonNgu.KiHieu;
            }

            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maNgonNgu, string tenNgonNgu, string kiHieu)
        {
            TempData["tenNgonNgu"] = tenNgonNgu;
            TempData["kiHieu"] = kiHieu;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            NgonNgu objNgonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (objNgonNgu == null)
            {
                return RedirectToAction("Index");
            }

            var regexTenNgonNgu = new Regex("[a-zA-Z]{2,20}");
            if (tenNgonNgu == null || !regexTenNgonNgu.IsMatch(tenNgonNgu))
            {
                bCheckOk = false;
                checkDic.Add("tenNgonNgu", SharedString.InputWrong);
            }

            var regexKiHieu1 = new Regex("[a-z]{2}");
            var regexKiHieu2 = new Regex("[a-z]{2}-[A-Z]{2}");
            var regexKiHieu3 = new Regex("[a-z]{2}-[A-Z]{2}-[A-Z]{2}");

            if (kiHieu == null || !(regexKiHieu1.IsMatch(kiHieu) || regexKiHieu2.IsMatch(kiHieu) || regexKiHieu3.IsMatch(kiHieu)))
            {
                bCheckOk = false;
                checkDic.Add("kiHieu", SharedString.InputWrong);
            }


            if (bCheckOk)
            {
                try
                {
                    NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
                    ngonNgu.TenNgonNgu = tenNgonNgu;
                    ngonNgu.KiHieu = kiHieu;

                    // Need to clear TempData
                    if (NgonNguBUS.CapNhat(ngonNgu))
                        return RedirectToAction("Index", "AdminLanguage");
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Edit");

        }

        public ActionResult Delete(int? id)
        {
            if (id == null ||id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            NgonNgu objNgonNgu = NgonNguBUS.LayNgonNguTheoMa(id ?? 0);
            if (objNgonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!NgonNguBUS.Xoa(objNgonNgu.MaNgonNgu))
            {
                TempData["errorCannotDelete"] = AdminLanguageString.ErrorCannotDelete;
            }

            return RedirectToAction("Index");
        }


    }
}