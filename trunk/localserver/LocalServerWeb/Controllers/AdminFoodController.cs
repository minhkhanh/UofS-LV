﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminFood;
using LocalServerWeb.Resources.Views.Shared;

namespace LocalServerWeb.Controllers
{
    public class AdminFoodController : BaseController
    {

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 0);
            ViewData["listMonAn"] = LayDanhSachMonAn();
            ViewData["listDanhMuc"] = LayDanhSachDanhMuc();
            return View();
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 1);
            ViewData["listDanhMuc"] = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoNgonNgu(SharedCode.GetCurrentLanguage(Session));
            return View();
        }

        [HttpPost]
        public ActionResult Add(int listDanhMuc, HttpPostedFileBase uploadFile)
        {
            TempData["listDanhMuc"] = listDanhMuc;
            var danhMuc = DanhMucBUS.LayDanhMuc(listDanhMuc);
            
            if (uploadFile==null || uploadFile.ContentLength == 0 || danhMuc==null)
            {
                return RedirectToAction("AddFood");
            }
            string fileName = Guid.NewGuid() + Path.GetFileName(uploadFile.FileName);
            string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/FoodImages"), fileName);                           
            
            MonAn monAn = new MonAn();
            monAn.DanhMuc = danhMuc;
            monAn.DiemDanhGia = 0;
            monAn.NgungBan = false;
            monAn.SoLuotDanhGia = 0;
            monAn.HinhAnh = "Uploads/FoodImages/" + fileName;
            if (MonAnBUS.Them(monAn))
            {
                uploadFile.SaveAs(filePath);
                return RedirectToAction("ViewDetailFood", new {maMonAn = monAn.MaMonAn});
            }
            return RedirectToAction("AddFood");
        }

        public ActionResult ViewDetailFood(int maMonAn)
        {
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn==null)
            {
                return RedirectToAction("Index");
            }
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);
            ViewData["listDanhMuc"] = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoNgonNgu(SharedCode.GetCurrentLanguage(Session));
            ViewData["monAn"] = monAn;

            var listChiTienMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);
            foreach (var donViTinh in listChiTienMonAnDonViTinh)
            {
                donViTinh.TenDonViTinh =
                    ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(donViTinh.DonViTinh.MaDonViTinh,
                                                                              SharedCode.GetCurrentLanguage(Session).
                                                                                  MaNgonNgu).TenDonViTinh;
            }
            ViewData["listChiTienMonAnDonViTinh"] = listChiTienMonAnDonViTinh;

            var listDonViTinh = MonAnBUS.LayDanhSachDonViTinhChuaCoTheoNgonNgu(monAn, SharedCode.GetCurrentLanguage(Session));
            ViewData["listDonViTinh"] = listDonViTinh;

            //var listNgonNguMonAn = MonAnBUS.LayDanhSachNgonNguCuaMonAn(monAn);
            //ViewData["listNgonNguMonAn"] = listNgonNguMonAn;

            var listChiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayDanhSachChiTietMonAnDaNgonNguTheMonAn(monAn);
            ViewData["listChiTietMonAnDaNgonNgu"] = listChiTietMonAnDaNgonNgu;

            var listNgonNguChuaCo = MonAnBUS.LayDanhSachNgonNguMonAnChuaCo(monAn);
            ViewData["listNgonNguChuaCo"] = listNgonNguChuaCo;

            return View();
        }

        [HttpPost]
        public ActionResult EditPrice(int maMonAn, int maDonViTinh, int gia)
        {            
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null || gia <= 0) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var chiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, maDonViTinh);
            if (chiTietMonAnDonViTinh == null) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            chiTietMonAnDonViTinh.DonGia = gia;
            ChiTietMonAnDonViTinhBUS.CapNhat(chiTietMonAnDonViTinh);
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn});
        }

        [HttpPost]
        public ActionResult UpdateCategory(int listDanhMuc, int maMonAn)
        {
            var danhMuc = DanhMucBUS.LayDanhMuc(listDanhMuc);
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (danhMuc != null && monAn != null)
            {
                monAn.DanhMuc = danhMuc;
                MonAnBUS.CapNhat(monAn);
            }
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
        }

        public ActionResult UpdateImageFood(int maMonAn, HttpPostedFileBase uploadFile)
        {            
            try
            {
                var monAn = MonAnBUS.LayMonAn(maMonAn);
                if (uploadFile == null || uploadFile.ContentLength == 0 || monAn == null) throw new Exception("Input wrong!");
                string fileName = Guid.NewGuid() + Path.GetFileName(uploadFile.FileName);
                string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/FoodImages"), fileName);
                string oldFilePath = Path.Combine(HttpContext.Server.MapPath("/"), monAn.HinhAnh);
                monAn.HinhAnh = "Uploads/FoodImages/" + fileName;
                if (MonAnBUS.CapNhat(monAn))
                {
                    uploadFile.SaveAs(filePath);
                    if (System.IO.File.Exists(oldFilePath)) System.IO.File.Delete(oldFilePath);
                }
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
        }

        [HttpPost]
        public ActionResult DeleteUnit(int maMonAn, int maDonViTinh)
        {
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var chiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, maDonViTinh);
            if (chiTietMonAnDonViTinh == null) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            bool b = ChiTietMonAnDonViTinhBUS.Xoa(chiTietMonAnDonViTinh);
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
        }

        [HttpPost]
        public ActionResult AddUnitPrice(int listDonViTinh, int maMonAn, int price_new)
        {
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null || price_new <= 0) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var tmp = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, listDonViTinh);
            if (tmp != null) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var donViTinh = DonViTinhBUS.LayDonViTinhTheoMa(listDonViTinh);
            var chiTietMonAnDonViTinh = new ChiTietMonAnDonViTinh();
            chiTietMonAnDonViTinh.DonViTinh = donViTinh;
            chiTietMonAnDonViTinh.MonAn = monAn;
            chiTietMonAnDonViTinh.DonGia = price_new;
            ChiTietMonAnDonViTinhBUS.ThemMoi(chiTietMonAnDonViTinh);
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });            
        }

        public ActionResult AddLanguageFood(int maMonAn, int listNgonNguChuaCo)
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(listNgonNguChuaCo);
            if (monAn==null || ngonNgu==null)
            {
                return RedirectToAction("Index", "AdminHome");
            }
            ViewData["ngonNgu"] = ngonNgu;
            return View();
        }

        [ValidateInput(false)]
        [HttpPost]
        public ActionResult AddLanguageFood(int maMonAn, int maNgonNgu, string tenMonAn, string moTaMonAn)
        {
            TempData["tenMonAn"] = tenMonAn;
            TempData["moTaMonAn"] = moTaMonAn;
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);

            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (monAn == null || ngonNgu == null)
            {                
                return RedirectToAction("Index", "AdminFood");
            }

            if (chiTietMonAnDaNgonNgu != null)
            {
                return RedirectToAction("ViewDetailFood", new { maMonAn = monAn.MaMonAn });
            }
            
            if (tenMonAn == null || moTaMonAn == null || tenMonAn.Length < 5 || moTaMonAn.Length < 5)
            {
                TempData["error"] = AdminFoodString.AddLanguageFoodError;
                return RedirectToAction("AddLanguageFood", new { maMonAn = monAn.MaMonAn, listNgonNguChuaCo = ngonNgu.MaNgonNgu });
            }
            chiTietMonAnDaNgonNgu = new ChiTietMonAnDaNgonNgu();
            chiTietMonAnDaNgonNgu.MonAn = monAn;
            chiTietMonAnDaNgonNgu.NgonNgu = ngonNgu;
            chiTietMonAnDaNgonNgu.TenMonAn = tenMonAn;
            chiTietMonAnDaNgonNgu.MoTaMonAn = moTaMonAn;
            ChiTietMonAnDaNgonNguBUS.Them(chiTietMonAnDaNgonNgu);
            return RedirectToAction("ViewDetailFood", new { maMonAn = monAn.MaMonAn });
        }

        [HttpPost]
        public ActionResult DeleteLanguageFood(int maMonAn, int maNgonNgu)
        {
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null ) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            var listNgonNguMon = MonAnBUS.LayDanhSachNgonNguCuaMonAn(monAn);
            if (ngonNgu == null || !listNgonNguMon.Contains(ngonNgu)) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            var chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (chiTietMonAnDaNgonNgu == null) return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
            ChiTietMonAnDaNgonNguBUS.Xoa(chiTietMonAnDaNgonNgu);
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn });
        }

        public ActionResult EditLanguageFood(int maMonAn, int maNgonNgu)
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (monAn == null || ngonNgu == null || chiTietMonAnDaNgonNgu==null)
            {
                return RedirectToAction("Index", "AdminHome");
            }
            ViewData["ngonNgu"] = ngonNgu;
            TempData["tenMonAn"] = chiTietMonAnDaNgonNgu.TenMonAn;
            TempData["moTaMonAn"] = chiTietMonAnDaNgonNgu.MoTaMonAn;
            return View();
        }

        [ValidateInput(false)]
        [HttpPost]
        public ActionResult EditLanguageFood(int maMonAn, int maNgonNgu, string tenMonAn, string moTaMonAn)
        {
            TempData["tenMonAn"] = tenMonAn;
            TempData["moTaMonAn"] = moTaMonAn;
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);

            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (monAn == null || ngonNgu == null)
            {
                return RedirectToAction("Index", "AdminFood");
            }

            if (chiTietMonAnDaNgonNgu == null)
            {
                return RedirectToAction("ViewDetailFood", new { maMonAn = monAn.MaMonAn });
            }

            if (tenMonAn == null || moTaMonAn == null || tenMonAn.Length < 5 || moTaMonAn.Length < 5)
            {
                TempData["error"] = AdminFoodString.AddLanguageFoodError;
                return RedirectToAction("EditLanguageFood", new { maMonAn = monAn.MaMonAn, maNgonNgu = ngonNgu.MaNgonNgu });
            }

            chiTietMonAnDaNgonNgu.TenMonAn = tenMonAn;
            chiTietMonAnDaNgonNgu.MoTaMonAn = moTaMonAn;
            ChiTietMonAnDaNgonNguBUS.CapNhat(chiTietMonAnDaNgonNgu);
            return RedirectToAction("ViewDetailFood", new { maMonAn = monAn.MaMonAn });
        }


        private List<MonAn> LayDanhSachMonAn()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            return MonAnBUS.LayDanhSachMonAnTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
        }

        private List<DanhMuc> LayDanhSachDanhMuc()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<DanhMuc> listDanhMuc =  DanhMucBUS.LayDanhSachDanhMucTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
            if (listDanhMuc != null && listDanhMuc.Count > 0)
            {
                foreach (DanhMuc danhMuc in listDanhMuc)
                {
                    if (danhMuc.MaDanhMuc == 1)
                    {
                        listDanhMuc.Remove(danhMuc);
                        break;
                    }
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

            MonAn objMonAn = MonAnBUS.LayMonAn(id ?? 0);
            if (objMonAn == null)
            {
                TempData["errorNotFound"] = AdminFoodString.ErrorFoodNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!MonAnBUS.Xoa(objMonAn.MaMonAn))
            {
                TempData["errorCannotDelete"] = AdminFoodString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminFoodString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }
    }
}
