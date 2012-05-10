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
        public ActionResult ChangeArea(int maDanhMuc, int maDanhMucCha)
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

            danhMuc.DanhMucCha = danhMucCha;

            if (!DanhMucBUS.CapNhat(danhMuc))
            {
                TempData["errorCannotChangeParentCategory"] = AdminCategoryString.ErrorCannotChangeParentCategory;
            }

            return RedirectToAction("Index");
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
                    danhmuc.TenDanhMuc = ct.TenDanhMuc;
                    danhmuc.MoTaDanhMuc = ct.MoTaDanhMuc;
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                }
                

            }

            return listDanhMuc;
        }

    }
}