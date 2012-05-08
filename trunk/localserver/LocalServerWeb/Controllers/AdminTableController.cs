﻿using System;
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
    public class AdminTableController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
            ViewData["listBan"] = BanBUS.LayDanhSachBan();
            ViewData["listKhuVuc"] = KhuVucBUS.LayDanhSachKhuVuc();
            return View();
        }

        [HttpPost]
        public ActionResult ChangeArea(int maKhuVuc, int maBan)
        {
            if (maKhuVuc <= 0 || maBan <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(maKhuVuc);
            Ban objBan = BanBUS.LayBan(maBan);

            if (objKhuVuc == null || objBan == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            objBan.KhuVuc = objKhuVuc;

            if (!BanBUS.CapNhat(objBan))
            {
                TempData["errorCannotChangeArea"] = AdminTableString.ErrorCannotChangeArea;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            Ban objBan = BanBUS.LayBan(id ?? 0);
            if (objBan == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!BanBUS.Xoa(objBan.MaBan))
            {
                TempData["errorCannotDelete"] = AdminTableString.ErrorCannotDelete;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
            ViewData["listKhuVuc"] = KhuVucBUS.LayDanhSachKhuVuc();
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }
            return View();
        }

        [HttpPost]
        public ActionResult Add(string tenBan, int maKhuVuc, string ghiChu, string active)
        {
            TempData["tenBan"] = tenBan;
            TempData["maKhuVuc"] = maKhuVuc;
            TempData["ghiChu"] = ghiChu;
            TempData["active"] = (active == "true") ? true : false;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            if (tenBan == null || tenBan.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenBan", SharedString.InputWrong);
            }

            KhuVuc objKhuVuc = KhuVucBUS.LayKhuVuc(maKhuVuc);
            if (maKhuVuc == 0 || objKhuVuc == null)
            {
                bCheckOk = false;
                checkDic.Add("maKhuVuc", AdminTableString.ErrorAreaNotFound);
            }

            if (bCheckOk)
            {
                try
                {
                    Ban ban = new Ban();
                    ban.TenBan = tenBan;
                    ban.KhuVuc = objKhuVuc;
                    ban.GhiChu = ghiChu;
                    ban.Active = (active == "true") ? true : false;
                    ban.TinhTrang = false;
                    ban.BanChinh = null;


                    // Need to clear TempData
                    if (BanBUS.Them(ban))
                        return RedirectToAction("Index", "AdminTable");
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

    }
}