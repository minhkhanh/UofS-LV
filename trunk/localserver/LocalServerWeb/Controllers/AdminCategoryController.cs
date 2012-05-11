using System;
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

namespace LocalServerWeb.Controllers
{
    public class AdminCategoryController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();
            return View();
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
                TempData["error"] = SharedString.InputWrong;
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
            }
            
            // Should redirect to previous action
            if (previous_action == "Index")
                return RedirectToAction("Index");
            return RedirectToAction(previous_action, new { id = maDanhMuc });
        }

        private List<DanhMuc> LayDanhSachDanhMuc()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMuc();

            foreach (DanhMuc danhmuc in listDanhMuc)
            {
                try
                {
                    ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhmuc.MaDanhMuc, maNgonNgu);
                    if (ct != null)
                    {
                        danhmuc.TenDanhMuc = ct.TenDanhMuc;
                        danhmuc.MoTaDanhMuc = ct.MoTaDanhMuc;
                    }
                    else
                    {
                        danhmuc.TenDanhMuc = "";
                        danhmuc.MoTaDanhMuc = "";
                    }
                    
                }
                catch (Exception e)
                {
                    danhmuc.TenDanhMuc = "";
                    danhmuc.MoTaDanhMuc = "";

                    Console.WriteLine(e.Message);
                }
            }

            return listDanhMuc;
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
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!DanhMucBUS.Xoa(objDanhMuc.MaDanhMuc))
            {
                TempData["errorCannotDelete"] = AdminCategoryString.ErrorCannotDelete;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);
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
                        return RedirectToAction("Edit", new { id = danhMuc.MaDanhMuc });
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }


            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();
            

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(id ?? 0);
            if (id == null || danhMuc == null)
            {
                TempData["errorNotFound"] = AdminCategoryString.ErrorCategoryNotFound;
            }
            else
            {
                TempData["maDanhMucCha"] = (danhMuc.DanhMucCha!=null)?danhMuc.DanhMucCha.MaDanhMuc:1;
            }


            
            ViewData["listNgonNguChuaCo"] = ChiTietDanhMucDaNgonNguBUS.LayDanhSachNgonNguChuaCo(id ?? 0);
            ViewData["listChiTietDanhMucDaNgonNgu"] = ChiTietDanhMucDaNgonNguBUS.LayDanhSachChiTietDanhMucDaNgonNguTheoDanhMuc(id ?? 0);

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
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorLanguageDetailNotFound"] = AdminCategoryString.ErrorLanguageDetailNotFound;
                bCheck = false;
            }

            if (bCheck)
            {
                if (!ChiTietDanhMucDaNgonNguBUS.Xoa(ct))
                {
                    TempData["errorCannotDelete"] = AdminCategoryString.ErrorCannotDelete;

                }
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

                ChiTietDanhMucDaNgonNguBUS.Them(ctMoi);
  
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

            bool bCheck = true;
            ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorLanguageDetailExist"] = AdminCategoryString.ErrorLanguageDetailNotFound;
                bCheck = false;
            }

            if (bCheck)
            {

                ct.TenDanhMuc = tenDanhMuc;
                ct.MoTaDanhMuc = moTaDanhMuc;

                ChiTietDanhMucDaNgonNguBUS.CapNhat(ct);

            }

            return RedirectToAction("Edit", new { id = maDanhMuc });
        }
    }
}