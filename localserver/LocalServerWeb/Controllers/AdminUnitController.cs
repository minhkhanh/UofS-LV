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
                        DonViTinh.TenDonViTinh = SharedString.NoInformation;
                    }

                }
                catch (Exception e)
                {
                    DonViTinh.TenDonViTinh = SharedString.NoInformation;
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
                TempData["errorNotFound"] = AdminUnitString.ErrorUnitNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!DonViTinhBUS.Xoa(objDonViTinh.MaDonViTinh))
            {
                TempData["errorCannotDelete"] = AdminUnitString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminUnitString.InfoDeleteSuccess;
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
                {
                    TempData["infoAddSuccess"] = AdminUnitString.InfoAddSuccess;
                    return RedirectToAction("Edit", new { id = donViTinh.MaDonViTinh });
                }
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
                TempData["errorNotFound"] = AdminUnitString.ErrorUnitNotFound;
                return RedirectToAction("Index", "Error");
            }

            ViewData["listNgonNguChuaCo"] = ChiTietDonViTinhDaNgonNguBUS.LayDanhSachNgonNguChuaCo(id ?? 0);
            List<ChiTietDonViTinhDaNgonNgu> listChiTietDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(id ?? 0);
            if (listChiTietDonViTinhDaNgonNgu == null || listChiTietDonViTinhDaNgonNgu.Count == 0)
            {
                TempData["warningNoLanguageDetail"] = AdminUnitString.WarningNoLanguageDetail;
            }
            ViewData["listChiTietDonViTinhDaNgonNgu"] = listChiTietDonViTinhDaNgonNgu;

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
                TempData["errorNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");
            }


            ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!ChiTietDonViTinhDaNgonNguBUS.Xoa(ct))
            {
                TempData["errorCannotDelete"] = AdminUnitString.ErrorCannotDelete;

            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminUnitString.InfoDeleteSuccess;
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
                TempData["errorNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
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

                if (ChiTietDonViTinhDaNgonNguBUS.Them(ctMoi))
                {
                    TempData["infoAddSuccess"] = AdminUnitString.InfoAddSuccess;
                }
                else
                {
                    TempData["errorCannotAdd"] = AdminUnitString.ErrorCannotAdd;
                }

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
                TempData["errorNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");
            }


            ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, ngonNgu.MaNgonNgu);
            if (ct == null)
            {
                TempData["errorNotFound"] = AdminUnitString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Index", "Error");

            }


            ct.TenDonViTinh = tenDonViTinh;

            if (ChiTietDonViTinhDaNgonNguBUS.CapNhat(ct))
            {
                TempData["infoEditSuccess"] = AdminUnitString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminUnitString.ErrorCannotEdit;
            }


            return RedirectToAction("Edit", new { id = maDonViTinh });
        }
    }
}