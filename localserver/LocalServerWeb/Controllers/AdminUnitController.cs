using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminUnit;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;


namespace LocalServerWeb.Controllers
{
    public class AdminUnitController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            ViewData["listDonViTinh"] = LayDanhSachDonViTinh();
            return View();
        }

        private List<DonViTinh> LayDanhSachDonViTinh()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<DonViTinh> listDonViTinh = DonViTinhBUS.LayDanhSachDonViTinh();

            foreach (DonViTinh DonViTinh in listDonViTinh)
            {
                try
                {
                    ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, maNgonNgu);
                    if (ct != null)
                    {
                        DonViTinh.TenDonViTinh = ct.TenDonViTinh;
                    }
                    else
                    {
                        DonViTinh.TenDonViTinh = "";
                    }

                }
                catch (Exception e)
                {
                    DonViTinh.TenDonViTinh = "";
                    Console.WriteLine(e.Message);
                }
            }

            return listDonViTinh;
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh objDonViTinh = DonViTinhBUS.LayDonViTinhTheoMa(id ?? 0);
            if (objDonViTinh == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!DonViTinhBUS.Xoa(objDonViTinh.MaDonViTinh))
            {
                TempData["errorCannotDelete"] = AdminUnitString.ErrorCannotDelete;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 3);
            return View();
        }

        [HttpPost]
        public ActionResult AddBasic()
        {
            try
            {
                DonViTinh donViTinh = new DonViTinh();

                // If add successfully, 
                if (DonViTinhBUS.Them(donViTinh))
                    return RedirectToAction("Edit", new { id = donViTinh.MaDonViTinh });
                // If add failed
                else
                    TempData["errorCannotAdd"] = AdminUnitString.ErrorCannotAdd;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }

            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);

            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh donViTinh = DonViTinhBUS.LayDonViTinhTheoMa(id ?? 0);
            if (donViTinh == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            ViewData["listNgonNguChuaCo"] = ChiTietDonViTinhDaNgonNguBUS.LayDanhSachNgonNguChuaCo(id ?? 0);
            ViewData["listChiTietDonViTinhDaNgonNgu"] = ChiTietDonViTinhDaNgonNguBUS.LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(id ?? 0);

            return View();
        }

        [HttpPost]
        public ActionResult DeleteUnitLanguage(int maDonViTinh, int maNgonNgu)
        {
            if (maDonViTinh < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh DonViTinh = DonViTinhBUS.LayDonViTinhTheoMa(maDonViTinh);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (DonViTinh == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorLanguageDetailNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
                bCheck = false;
            }

            if (bCheck)
            {
                if (!ChiTietDonViTinhDaNgonNguBUS.Xoa(ct))
                {
                    TempData["errorCannotDelete"] = AdminUnitString.ErrorCannotDelete;

                }
            }

            return RedirectToAction("Edit", new { id = maDonViTinh });
        }

        [HttpPost]
        public ActionResult AddUnitLanguage(int maDonViTinh, int maNgonNgu, string tenDonViTinh)
        {
            if (maDonViTinh < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh DonViTinh = DonViTinhBUS.LayDonViTinhTheoMa(maDonViTinh);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (DonViTinh == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, ngonNgu.MaNgonNgu);
            if (ct != null)
            {
                TempData["errorLanguageDetailExist"] = AdminUnitString.ErrorLanguageDetailExist;
                bCheck = false;
            }

            if (bCheck)
            {
                ChiTietDonViTinhDaNgonNgu ctMoi = new ChiTietDonViTinhDaNgonNgu();
                ctMoi.DonViTinh = DonViTinh;
                ctMoi.NgonNgu = ngonNgu;
                ctMoi.TenDonViTinh = tenDonViTinh;

                ChiTietDonViTinhDaNgonNguBUS.Them(ctMoi);

            }

            return RedirectToAction("Edit", new { id = maDonViTinh });
        }

        [HttpPost]
        public ActionResult EditUnitLanguage(int maDonViTinh, int maNgonNgu, string tenDonViTinh, string tenNgonNgu)
        {
            if (maDonViTinh < 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh DonViTinh = DonViTinhBUS.LayDonViTinhTheoMa(maDonViTinh);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (DonViTinh == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorLanguageDetailExist"] = AdminUnitString.ErrorLanguageDetailNotFound;
                bCheck = false;
            }

            if (bCheck)
            {

                ct.TenDonViTinh = tenDonViTinh;

                ChiTietDonViTinhDaNgonNguBUS.CapNhat(ct);

            }

            return RedirectToAction("Edit", new { id = maDonViTinh });
        }
    }
}