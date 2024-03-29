﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminCategory;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminCategoryController : ManagerBaseController
    {
        public ActionResult Index(string page)
        {
            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<DanhMuc> pageListDanhMucThat = LayDanhSachDanhMucThat().AsQueryable().ToPagedList(_page, 10);
            ViewData["listDanhMucThat"] = pageListDanhMucThat;
            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();
            ViewData["_page"] = _page;

            return View(pageListDanhMucThat);
        }

        [HttpPost]
        public ActionResult ChangeParentCategory(int maDanhMuc, int maDanhMucCha, string previous_action)
        {        
            if (maDanhMuc <= 0 || maDanhMucCha <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            DanhMuc danhMucCha = DanhMucBUS.LayDanhMuc(maDanhMucCha);

            if (danhMuc == null || danhMucCha == null)
            {
                TempData["error"] = AdminCategoryString.ErrorCategoryNotFound;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;

            if(maDanhMuc == maDanhMucCha)
            {
                TempData["errorCannotChooseItself"] = AdminCategoryString.ErrorCannotChooseItself;
                bCheck = false;
            }

            if (DanhMucBUS.IsItsDescendant(maDanhMuc, maDanhMucCha))
            {
                TempData["errorCannotChooseItsDescendant"] = AdminCategoryString.ErrorCannotChooseItsDescendant;
                bCheck = false;
            }

            // If everything is OK, try updating Danh Muc
            if (bCheck)
            {
                danhMuc.DanhMucCha = danhMucCha;

                if (!DanhMucBUS.CapNhat(danhMuc))
                {
                    TempData["errorCannotChangeParentCategory"] = AdminCategoryString.ErrorCannotChangeParentCategory;
                }
                else
                {
                    TempData["infoChangeParentCategorySuccess"] = AdminCategoryString.InfoChangeParentCategorySuccess;
                }
            }
            
            // Should redirect to previous action
            if (previous_action == "Index")
                return RedirectToAction("Index");
            return RedirectToAction(previous_action, new { id = maDanhMuc });
        }

        // Lay tat cac danh muc, ko co danh muc None
        private List<DanhMuc> LayDanhSachDanhMucThat()
        {
            List<DanhMuc> listDanhMuc = LayDanhSachDanhMuc();
            foreach(DanhMuc dm in listDanhMuc)
            {
                // DanhMuc co ID = 1 la danh muc ao, Khong Co, nen khong show
                if (dm.MaDanhMuc == 1)
                {
                    listDanhMuc.Remove(dm);
                    break;
                }
            }

            return listDanhMuc;
        }

        // Lay tat ca danh muc
        private List<DanhMuc> LayDanhSachDanhMuc()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            return DanhMucBUS.LayDanhSachDanhMucTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc objDanhMuc = DanhMucBUS.LayDanhMuc(id ?? 0);
            if (objDanhMuc == null)
            {
                TempData["error"] = AdminCategoryString.ErrorCategoryNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!DanhMucBUS.Xoa(objDanhMuc.MaDanhMuc))
            {
                TempData["errorCannotDelete"] = AdminCategoryString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminCategoryString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();

            return View();
        }

        [HttpPost]
        public ActionResult Add(int maDanhMucCha)
        {
            TempData["maDanhMucCha"] = maDanhMucCha;

            bool bCheckOk = true;

            DanhMuc danhMucCha = DanhMucBUS.LayDanhMuc(maDanhMucCha);
            if (danhMucCha == null)
            {
                TempData["errorNotFound"] = AdminCategoryString.ErrorCategoryNotFound;
                bCheckOk = false;
            }

            if (bCheckOk)
            {
                try
                {
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.DanhMucCha = danhMucCha;

                    // If add successfully, 
                    if (DanhMucBUS.Them(danhMuc))
                    {
                        TempData["infoAddSuccess"] = AdminCategoryString.InfoAddSuccess;
                        return RedirectToAction("Edit", new { id = danhMuc.MaDanhMuc });
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminCategoryString.ErrorCannotAdd;
                    }
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }


            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {

            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(id ?? 0);
            if (danhMuc == null)
            {
                TempData["errorNotFound"] = AdminCategoryString.ErrorCategoryNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["maDanhMucCha"] = (danhMuc.DanhMucCha != null) ? danhMuc.DanhMucCha.MaDanhMuc : 1;
            }

            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();

            ViewData["listNgonNguChuaCo"] = ChiTietDanhMucDaNgonNguBUS.LayDanhSachNgonNguChuaCo(id ?? 0);

            List<ChiTietDanhMucDaNgonNgu> listChiTietDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayDanhSachChiTietDanhMucDaNgonNguTheoDanhMuc(id ?? 0);
            if (listChiTietDanhMucDaNgonNgu == null || listChiTietDanhMucDaNgonNgu.Count == 0)
            {
                TempData["warningNoLanguageDetail"] = AdminCategoryString.WarningNoLanguageDetail;
            }
            ViewData["listChiTietDanhMucDaNgonNgu"] = listChiTietDanhMucDaNgonNgu;

            return View();
        }

        [HttpPost]
        public ActionResult DeleteCategoryLanguage(int maDanhMuc, int maNgonNgu)
        {
            if (maDanhMuc < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (danhMuc == null || ngonNgu == null)
            {
                TempData["error"] = AdminCategoryString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");
            }


            ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorNotFound"] = AdminCategoryString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");

            }


            if (!ChiTietDanhMucDaNgonNguBUS.Xoa(ct))
            {
                TempData["errorCannotDelete"] = AdminCategoryString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminCategoryString.InfoDeleteSuccess;
            }


            return RedirectToAction("Edit", new { id = maDanhMuc });  
        }

        [HttpPost]
        public ActionResult AddCategoryLanguage(int maDanhMuc, int maNgonNgu, string tenDanhMuc, string moTaDanhMuc)
        {
            if (maDanhMuc < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (danhMuc == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, ngonNgu.MaNgonNgu);
            if (ct != null)
            {
                TempData["errorLanguageDetailExist"] = AdminCategoryString.ErrorLanguageDetailExist;
                bCheck = false;
            }

            if (bCheck)
            {
                ChiTietDanhMucDaNgonNgu ctMoi = new ChiTietDanhMucDaNgonNgu();
                ctMoi.DanhMuc = danhMuc;
                ctMoi.NgonNgu = ngonNgu;
                ctMoi.TenDanhMuc = tenDanhMuc;
                ctMoi.MoTaDanhMuc = moTaDanhMuc;

                if (ChiTietDanhMucDaNgonNguBUS.Them(ctMoi))
                {
                    TempData["infoAddSuccess"] = AdminCategoryString.InfoAddSuccess;
                }
                else
                {
                    TempData["errorCannotAdd"] = AdminCategoryString.ErrorCannotAdd;
                }
  
            }

            return RedirectToAction("Edit", new { id = maDanhMuc });  
        }

        [HttpPost]
        public ActionResult EditCategoryLanguage(int maDanhMuc, int maNgonNgu, string tenDanhMuc, string moTaDanhMuc, string tenNgonNgu)
        {
            if (maDanhMuc < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (danhMuc == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }


            ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorNotFound"] = AdminCategoryString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");
            }


            ct.TenDanhMuc = tenDanhMuc;
            ct.MoTaDanhMuc = moTaDanhMuc;

            if (ChiTietDanhMucDaNgonNguBUS.CapNhat(ct))
            {
                TempData["infoEditSuccess"] = AdminCategoryString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminCategoryString.ErrorCannotEdit;
            }


            return RedirectToAction("Edit", new { id = maDanhMuc });
        }
    }
}