using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminArea;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminAreaController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 4);
            ViewData["listKhuVuc"] = KhuVucBUS.LayDanhSachKhuVuc();
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
        public ActionResult Add(string tenKhuVuc, string moTa)
        {
            TempData["tenKhuVuc"] = tenKhuVuc;
            TempData["moTa"] = moTa;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;
            if (tenKhuVuc.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenKhuVuc", AdminAreaString.InputRequired);
            }

            if (bCheckOk)
            {
                try
                {
                    KhuVuc khuVuc = new KhuVuc();
                    khuVuc.TenKhuVuc = tenKhuVuc;
                    khuVuc.MoTa = moTa;

                    // Need to clear TempData
                    if (KhuVucBUS.Them(khuVuc))
                        return RedirectToAction("Index", "AdminArea");
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
            SharedCode.FillAdminMainMenu(ViewData, 3, 3);
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(id ?? 0);
            if (id == null || objKhuVuc == null)
            {
                TempData["error"] = AdminAreaString.ErrorAreaNotFound;
            }
            else
            {
                TempData["tenKhuVuc"] = objKhuVuc.TenKhuVuc;
                TempData["moTa"] = objKhuVuc.MoTa;
            }

            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maKhuVuc, string tenKhuVuc, string moTa)
        {
            TempData["tenKhuVuc"] = tenKhuVuc;
            TempData["moTa"] = moTa;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;
            if (tenKhuVuc.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenKhuVuc", AdminAreaString.InputRequired);
            }

            if (bCheckOk)
            {
                try
                {
                    KhuVuc khuVuc = KhuVucBUS.LayKhuVuc(maKhuVuc);
                    khuVuc.TenKhuVuc = tenKhuVuc;
                    khuVuc.MoTa = moTa;

                    // Need to clear TempData
                    if (KhuVucBUS.CapNhat(khuVuc))
                        return RedirectToAction("Index", "AdminArea");
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }


        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = AdminAreaString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(id ?? 0);
            if (objKhuVuc == null)
            {
                TempData["error"] = AdminAreaString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!KhuVucBUS.Xoa(objKhuVuc.MaKhuVuc))
            {
                TempData["errorCannotDelete"] = AdminAreaString.ErrorCannotDelete;
                return RedirectToAction("Index");
            }

            return RedirectToAction("Index");
        }
    }
}