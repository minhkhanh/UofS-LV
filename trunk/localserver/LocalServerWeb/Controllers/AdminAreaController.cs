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
using LocalServerWeb.Resources.Views.Shared;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminAreaController : BaseController
    {
        public ActionResult Index(string page)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 4);

            int _page = 1;
            int.TryParse(page, out _page);
            PagedList<KhuVuc> pageListKhuVuc = KhuVucBUS.LayDanhSachKhuVuc().AsQueryable().ToPagedList(_page, 2);
            ViewData["listKhuVuc"] = pageListKhuVuc;

            return View(pageListKhuVuc);
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 5);
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
                    {
                        TempData["infoAddSuccess"] = AdminAreaString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminArea");
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminAreaString.ErrorCannotAdd;
                    }
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 4);
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(id ?? 0);
            if (objKhuVuc == null)
            {
                TempData["errorNotFound"] = AdminAreaString.ErrorAreaNotFound;
                return RedirectToAction("Index", "Error");
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
                    {
                        TempData["infoEditSuccess"] = AdminAreaString.InfoEditSuccess;
                        return RedirectToAction("Index", "AdminArea");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminAreaString.ErrorCannotEdit;
                    }
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Edit");

        }


        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(id ?? 0);
            if (objKhuVuc == null)
            {
                TempData["errorNotFound"] = AdminAreaString.ErrorAreaNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!KhuVucBUS.Xoa(objKhuVuc.MaKhuVuc))
            {
                TempData["errorCannotDelete"] = AdminAreaString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminAreaString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }
    }
}