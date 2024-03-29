﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminOrder;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
using Webdiyer.WebControls.Mvc;
using LocalServerWeb.Resources.Views.Shared;

namespace LocalServerWeb.Controllers
{
    public class AdminBillController : ManagerBaseController
    {
        public ActionResult Index(string page)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<HoaDon> pageListHoaDon = HoaDonBUS.LayDanhSachHoaDon().AsQueryable().ToPagedList(_page, 10);
            ViewData["listHoaDon"] = pageListHoaDon;
            ViewData["_page"] = _page;

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            ViewData["maNgonNgu"] = maNgonNgu;

            return View(pageListHoaDon);
        }

        public ActionResult BillDetail(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);
            if (id == null || id < 1)
                return View();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            ViewData["maNgonNgu"] = maNgonNgu;
            ViewData["maHoaDon"] = id;

            List<ChiTietHoaDon> listChiTietHoaDOn = ChiTietHoaDonBUS.LayNhieuChiTietHoaDon(id ?? 1);

            foreach (ChiTietHoaDon ct in listChiTietHoaDOn)
            {
                ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(ct.MonAn.MaMonAn, maNgonNgu);
                ct.MonAn.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;

            }

            ViewData["listChiTietHoaDon"] = listChiTietHoaDOn;
            return View();
        }

        public ActionResult Print(int maHoaDon)
        {
            HoaDon hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
            if (hoaDon == null)
                return RedirectToAction("Index");

            if (!Reports.ReportManager.PrintBill(hoaDon.MaHoaDon, SharedCode.GetCurrentLanguage(Session).MaNgonNgu))
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
                
            }
            return RedirectToAction("Index");
            
        }

        public ActionResult Print2(int maHoaDon)
        {
            HoaDon hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
            if (hoaDon == null)
                return RedirectToAction("Index");

            if (!Reports.ReportManager.PrintBill(hoaDon.MaHoaDon, SharedCode.GetCurrentLanguage(Session).MaNgonNgu))
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            return RedirectToAction("BillDetail", new { id = maHoaDon });
            
        }
        
    }
}