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
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminFoodController : ManagerBaseController
    {

        public ActionResult Index(string page)
        {
            ViewData["listDanhMuc"] = LayDanhSachDanhMucLevelThapNhat();

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<MonAn> pageListMonAn = LayDanhSachMonAn().AsQueryable().ToPagedList(_page, 10);
            ViewData["listMonAn"] = pageListMonAn;
            ViewData["_page"] = _page;

            return View(pageListMonAn);
        }

        public ActionResult Add()
        {
            ViewData["listDanhMuc"] = LayDanhSachDanhMucLevelThapNhat();
            return View();
        }

        [HttpPost]
        public ActionResult Add(int maDanhMuc, HttpPostedFileBase uploadFile)
        {
            TempData["maDanhMuc"] = maDanhMuc;
            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            
            if (uploadFile==null || uploadFile.ContentLength == 0 || danhMuc == null)
            {
                TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
                return RedirectToAction("Add");
            }

            string fileName = Guid.NewGuid() + Path.GetFileName(uploadFile.FileName);
            string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/FoodImages"), fileName);                           
            
            // When newly add a Food, there are no any language details and unit details. So that Food is not available
            // NgungBan = true
            MonAn monAn = new MonAn();
            monAn.DanhMuc = danhMuc;
            monAn.DiemDanhGia = 0;
            monAn.NgungBan = true;
            monAn.SoLuotDanhGia = 0;
            monAn.HinhAnh = "Uploads/FoodImages/" + fileName;

            if (MonAnBUS.Them(monAn))
            {
                TempData["infoAddSuccess"] = AdminFoodString.InfoAddSuccess;
                uploadFile.SaveAs(filePath);
                return RedirectToAction("Edit", new { id = monAn.MaMonAn });
            }
            else
            {
                TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
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

            MonAn monAn = MonAnBUS.LayMonAn(id ?? 0);
            if (monAn == null)
            {
                TempData["errorNotFound"] = AdminFoodString.ErrorFoodNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["maDanhMuc"] = (monAn.DanhMuc != null) ? monAn.DanhMuc.MaDanhMuc : 1;
                ViewData["status"] = monAn.NgungBan;
            }

            ViewData["listDanhMuc"] = LayDanhSachDanhMucLevelThapNhat();
            ViewData["monAn"] = monAn;

            // list Chi Tiet Mon An Don Vi Tinh theo Ngon Ngu hien tai
            var listChiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);

            if (listChiTietMonAnDonViTinh == null || listChiTietMonAnDonViTinh.Count == 0)
            {
                TempData["warningNoUnitDetail"] = AdminFoodString.WarningNoUnitDetail;
            }

            foreach (var donViTinh in listChiTietMonAnDonViTinh)
            {
                ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(donViTinh.DonViTinh.MaDonViTinh,
                                                                              SharedCode.GetCurrentLanguage(Session).MaNgonNgu);
                if(ctDonViTinhDaNgonNgu != null)
                    donViTinh.TenDonViTinh = ctDonViTinhDaNgonNgu.TenDonViTinh;
                else
                    donViTinh.TenDonViTinh = SharedString.NoInformation;
            }
            ViewData["listChiTietMonAnDonViTinh"] = listChiTietMonAnDonViTinh;

            // list Don Vi Tinh chua co
            var listDonViTinh = MonAnBUS.LayDanhSachDonViTinhChuaCoTheoNgonNgu(monAn, SharedCode.GetCurrentLanguage(Session));
            ViewData["listDonViTinh"] = listDonViTinh;

            // list Chi Tiet Mon An Da Ngon Ngu
            var listChiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayDanhSachChiTietMonAnDaNgonNguTheMonAn(monAn);
            // If there are no unit details, show warning
            if (listChiTietMonAnDaNgonNgu == null || listChiTietMonAnDaNgonNgu.Count == 0)
            {
                TempData["warningNoLanguageDetail"] = AdminFoodString.WarningNoLanguageDetail;
            }

            ViewData["listChiTietMonAnDaNgonNgu"] = listChiTietMonAnDaNgonNgu;

            // list Ngon Ngu Chua Co
            var listNgonNguChuaCo = MonAnBUS.LayDanhSachNgonNguMonAnChuaCo(monAn);
            ViewData["listNgonNguChuaCo"] = listNgonNguChuaCo;

            return View();
        }

        [HttpPost]
        public ActionResult EditPrice(int maMonAn, int maDonViTinh, int? gia)
        {
            if (maMonAn <= 0 || maDonViTinh <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null || gia == null || gia <= 0) 
                return RedirectToAction("Edit", new { id = maMonAn });

            var chiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, maDonViTinh);
            if (chiTietMonAnDonViTinh == null) 
                return RedirectToAction("Edit", new { id = maMonAn });

            chiTietMonAnDonViTinh.DonGia = gia??0;
            if (ChiTietMonAnDonViTinhBUS.CapNhat(chiTietMonAnDonViTinh))
            {
                TempData["infoEditSuccess"] = AdminFoodString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminFoodString.ErrorCannotEdit;
            }

            return RedirectToAction("Edit", new { id = maMonAn });
        }

        

        public ActionResult UpdateImageFood(int maMonAn, HttpPostedFileBase uploadFile)
        {            
            try
            {
                var monAn = MonAnBUS.LayMonAn(maMonAn);
                if (uploadFile == null || uploadFile.ContentLength == 0 || monAn == null) 
                    throw new Exception("Input wrong!");

                string fileName = Guid.NewGuid() + Path.GetFileName(uploadFile.FileName);
                string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/FoodImages"), fileName);
                string oldFilePath = Path.Combine(HttpContext.Server.MapPath("/"), monAn.HinhAnh);

                monAn.HinhAnh = "Uploads/FoodImages/" + fileName;
                if (MonAnBUS.CapNhat(monAn))
                {
                    TempData["infoEditSuccess"] = AdminFoodString.InfoEditSuccess;
                    uploadFile.SaveAs(filePath);
                    if (System.IO.File.Exists(oldFilePath)) 
                        System.IO.File.Delete(oldFilePath);
                }
                else
                {
                    TempData["errorCannotEdit"] = AdminFoodString.ErrorCannotEdit;
                }
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }

            return RedirectToAction("Edit", new { id = maMonAn });
        }

        [HttpPost]
        public ActionResult DeleteUnit(int maMonAn, int maDonViTinh)
        {
            if (maMonAn <= 0 || maDonViTinh <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            DonViTinh donViTinh = DonViTinhBUS.LayDonViTinhTheoMa(maDonViTinh);
            if (monAn == null || donViTinh == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            var chiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, maDonViTinh);
            if (chiTietMonAnDonViTinh == null)
            {
                TempData["errorUnitDetailNotFound"] = AdminFoodString.ErrorUnitDetailNotFound;
                return RedirectToAction("Edit", new { id = maMonAn });
            }

            if (ChiTietMonAnDonViTinhBUS.Xoa(chiTietMonAnDonViTinh))
            {
                TempData["infoDeleteSuccess"] = AdminFoodString.InfoDeleteSuccess;
                // If there are no units detail, the food is not available
                if (ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(maMonAn).Count == 0)
                {
                    monAn.NgungBan = true;
                    MonAnBUS.CapNhat(monAn);
                }
            }
            else
            {
                TempData["errorCannotDelete"] = AdminFoodString.ErrorCannotDelete;
            }

            return RedirectToAction("Edit", new { id = maMonAn });
        }

        [HttpPost]
        public ActionResult AddUnitPrice(int maDonViTinh, int maMonAn, int? price_new)
        {
            if (maMonAn <= 0 || maDonViTinh <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            DonViTinh donViTinh = DonViTinhBUS.LayDonViTinhTheoMa(maDonViTinh);
            if (monAn == null || donViTinh == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if(price_new == null || price_new <= 0)
            {
                return RedirectToAction("Edit", new { id = maMonAn });
            }

            var tmp = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, maDonViTinh);
            if (tmp != null)
            {
                TempData["errorUnitDetailExist"] = AdminFoodString.ErrorUnitDetailExist;
                return RedirectToAction("Edit", new { id = maMonAn });
            }


            var chiTietMonAnDonViTinh = new ChiTietMonAnDonViTinh();
            chiTietMonAnDonViTinh.DonViTinh = donViTinh;
            chiTietMonAnDonViTinh.MonAn = monAn;
            chiTietMonAnDonViTinh.DonGia = price_new??0;

            if (ChiTietMonAnDonViTinhBUS.ThemMoi(chiTietMonAnDonViTinh))
            {
                TempData["infoAddSuccess"] = AdminFoodString.InfoAddSuccess;
            }
            else
            {
                TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
            }
            

            return RedirectToAction("Edit", new { id = maMonAn });         
        }

        public ActionResult AddLanguageFood(int maMonAn, int maNgonNgu)
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);

            if (maMonAn <= 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);

            if (monAn==null || ngonNgu==null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            ViewData["ngonNgu"] = ngonNgu;
            return View();
        }

        [ValidateInput(false)]
        [HttpPost]
        public ActionResult AddLanguageFood(int maMonAn, int maNgonNgu, string tenMonAn, string moTaMonAn)
        {
            if (maMonAn <= 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TempData["tenMonAn"] = tenMonAn;
            TempData["moTaMonAn"] = moTaMonAn;
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);

            if (monAn == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }


            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);

            if (chiTietMonAnDaNgonNgu != null)
            {
                TempData["errorLanguageDetailExist"] = AdminFoodString.ErrorLanguageDetailExist;
                return RedirectToAction("Edit", new { id = maMonAn });  
            }
            
            if (tenMonAn == null || moTaMonAn == null || tenMonAn.Length < 1 || moTaMonAn.Length < 1)
            {
                TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
                return RedirectToAction("AddLanguageFood", new { maMonAn = monAn.MaMonAn, maNgonNgu = ngonNgu.MaNgonNgu });
            }

            chiTietMonAnDaNgonNgu = new ChiTietMonAnDaNgonNgu();
            chiTietMonAnDaNgonNgu.MonAn = monAn;
            chiTietMonAnDaNgonNgu.NgonNgu = ngonNgu;
            chiTietMonAnDaNgonNgu.TenMonAn = tenMonAn;
            chiTietMonAnDaNgonNgu.MoTaMonAn = moTaMonAn;

            if (ChiTietMonAnDaNgonNguBUS.Them(chiTietMonAnDaNgonNgu))
            {
                TempData["infoAddSuccess"] = AdminFoodString.InfoAddSuccess;
            }
            else
            {
                TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
            }
            

            return RedirectToAction("Edit", new { id = maMonAn });  
        }

        [HttpPost]
        public ActionResult DeleteLanguageFood(int maMonAn, int maNgonNgu)
        {
            if (maMonAn <= 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null )
                return RedirectToAction("Edit", new { id = maMonAn });  

            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            var listNgonNguMon = MonAnBUS.LayDanhSachNgonNguCuaMonAn(monAn);

            if (ngonNgu == null || !listNgonNguMon.Contains(ngonNgu))
                return RedirectToAction("Edit", new { id = maMonAn });  
            var chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);

            if (chiTietMonAnDaNgonNgu == null)
            {
                TempData["errorLanguageDetailNotFound"] = AdminFoodString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Edit", new { id = maMonAn });
            }

            if (ChiTietMonAnDaNgonNguBUS.Xoa(chiTietMonAnDaNgonNgu))
            {
                TempData["infoDeleteSuccess"] = AdminFoodString.InfoDeleteSuccess;
            }
            else
            {
                TempData["errorCannotDelete"] = AdminFoodString.ErrorCannotDelete;
            }

            return RedirectToAction("Edit", new { id = maMonAn });  
        }

        public ActionResult EditLanguageFood(int maMonAn, int maNgonNgu)
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, -1);

            if (maMonAn <= 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            
            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
            if (monAn == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (chiTietMonAnDaNgonNgu == null)
            {
                TempData["errorLanguageDetailNotFound"] = AdminFoodString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Edit", new { id = maMonAn });  
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
            if (maMonAn <= 0 || maNgonNgu <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TempData["tenMonAn"] = tenMonAn;
            TempData["moTaMonAn"] = moTaMonAn;

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            NgonNgu ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);

            if (monAn == null || ngonNgu == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);

            if (chiTietMonAnDaNgonNgu == null)
            {
                TempData["errorLanguageDetailNotFound"] = AdminFoodString.ErrorLanguageDetailNotFound;
                return RedirectToAction("Edit", new { id = maMonAn });
            }

            if (tenMonAn == null || moTaMonAn == null || tenMonAn.Length < 1 || moTaMonAn.Length < 1)
            {
                TempData["errorCannotEdit"] = AdminFoodString.ErrorCannotEdit;
                return RedirectToAction("EditLanguageFood", new { maMonAn = monAn.MaMonAn, maNgonNgu = ngonNgu.MaNgonNgu });
            }

            chiTietMonAnDaNgonNgu.TenMonAn = tenMonAn;
            chiTietMonAnDaNgonNgu.MoTaMonAn = moTaMonAn;
            if (ChiTietMonAnDaNgonNguBUS.CapNhat(chiTietMonAnDaNgonNgu))
            {
                TempData["infoEditSuccess"] = AdminFoodString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminFoodString.ErrorCannotEdit;
            }

            return RedirectToAction("Edit", new { id = maMonAn });
        }


        private List<MonAn> LayDanhSachMonAn()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            return MonAnBUS.LayDanhSachMonAnTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
        }

        private List<DanhMuc> LayDanhSachDanhMucLevelThapNhat()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
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

        [HttpPost]
        public ActionResult ChangeCategory(int maMonAn, int maDanhMuc, string previous_action)
        {
            if (maMonAn <= 0 || maDanhMuc <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null || danhMuc == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }


            monAn.DanhMuc = danhMuc;
            if (MonAnBUS.CapNhat(monAn))
            {
                TempData["infoChangeCategorySuccess"] = AdminFoodString.InfoChangeCategorySuccess;
            }
            else
            {
                TempData["errorCannotChangeCategory"] = AdminFoodString.ErrorCannotChangeCategory;
            }

            if (previous_action == "Index")
                return RedirectToAction("Index");
            return RedirectToAction(previous_action, new { id = maMonAn });

        }

        [HttpPost]
        public ActionResult ChangeStatus(int maMonAn, int status)
        {
            if (maMonAn <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn == null)
            {
                TempData["errorNotFound"] = AdminFoodString.ErrorFoodNotFound;
                return RedirectToAction("Index", "Error");
            }

            // If this food has no unit details, cannot change its status to Available
            if(ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn).Count == 0 && status == 1)
            {
                TempData["errorCannotChangeStatusToAvailable"] = AdminFoodString.ErrorCannotChangeStatusToAvaiable;
                return RedirectToAction("Edit", new { id = maMonAn });
            }

            monAn.NgungBan = (status == 0) ? true : false;
            if (MonAnBUS.CapNhat(monAn))
            {
                TempData["infoEditSuccess"] = AdminFoodString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminFoodString.ErrorCannotEdit;
            }


            return RedirectToAction("Edit", new { id = maMonAn });

        }

        public ActionResult RelatedFood(int? id)
        {
            // Check if id OK and Food not NULL
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(id ?? 0);
            if (monAn == null)
            {
                TempData["errorNotFound"] = AdminFoodString.ErrorFoodNotFound;
                return RedirectToAction("Index", "Error");
            }

            // Begin Related food
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);

            ViewData["tenMonAn"] = LayTenMonAn(monAn.MaMonAn);
            ViewData["listMonAn"] = LayDanhSachMonAn();
            ViewData["listMonLienQuan"] = LayDanhSachMonLienQuan(monAn.MaMonAn);

            return View();
        }

        [HttpPost]
        public ActionResult AddRelatedFood(int maMonAn, int maMonAnLienQuan)
        {
            // Check if id OK and khuyenMai not NULL
            if (maMonAn <= 0 || maMonAnLienQuan <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            MonAn monAnLienQuan = MonAnBUS.LayMonAn(maMonAnLienQuan);
            if (monAn == null || monAnLienQuan == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }


            // Begin Add related food
            bool bCheck = true;
            if (monAn == monAnLienQuan)
            {
                TempData["errorCannotAddItself"] = AdminFoodString.ErrorCannotAddItself;
                bCheck = false;
            }

            ChiTietMonLienQuan ctMonLienQuan = ChiTietMonLienQuanBUS.LayChiTietMonLienQuan(maMonAn, maMonAnLienQuan);
            if (ctMonLienQuan != null)
            {
                TempData["errorRelatedFoodExist"] = AdminFoodString.ErrorRelatedFoodExist;
                bCheck = false;
            }

            if (bCheck)
            {
                ChiTietMonLienQuan ctMoi = new ChiTietMonLienQuan();
                ctMoi.MonAn = monAn;
                ctMoi.MonAnLienQuan = monAnLienQuan;

                if (ChiTietMonLienQuanBUS.Them(ctMoi))
                {
                    TempData["infoAddSuccess"] = AdminFoodString.InfoAddSuccess;
                }
                else
                {
                    TempData["errorCannotAdd"] = AdminFoodString.ErrorCannotAdd;
                }
            }

            return RedirectToAction("RelatedFood", new { id = maMonAn });
        }

        [HttpPost]
        public ActionResult DeleteRelatedFood(int maMonAn, int maMonAnLienQuan)
        {
            // Check if id OK and khuyenMai not NULL
            if (maMonAn <= 0 || maMonAnLienQuan <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            MonAn monAnLienQuan = MonAnBUS.LayMonAn(maMonAnLienQuan);
            if (monAn == null || monAnLienQuan == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            // Begin delete related food
            bool bCheck = true;

            ChiTietMonLienQuan ctMonLienQuan = ChiTietMonLienQuanBUS.LayChiTietMonLienQuan(maMonAn, maMonAnLienQuan);
            if (ctMonLienQuan == null)
            {
                TempData["errorRelatedFoodNotFound"] = AdminFoodString.ErrorRelatedFoodNotFound;
                bCheck = false;
            }

            if (bCheck)
            {
                if (ChiTietMonLienQuanBUS.Xoa(ctMonLienQuan))
                {
                    TempData["infoDeleteSuccess"] = AdminFoodString.InfoDeleteSuccess;
                }
                else
                {
                    TempData["errorCannotDelete"] = AdminFoodString.ErrorCannotDelete;
                }
            }

            return RedirectToAction("RelatedFood", new { id = maMonAn });
        }

        private List<ChiTietMonLienQuan> LayDanhSachMonLienQuan(int maMonAn)
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<ChiTietMonLienQuan> listMonLienQuan = ChiTietMonLienQuanBUS.LayDanhSachChiTietMonLienQuan(maMonAn);
            if (listMonLienQuan != null && listMonLienQuan.Count > 0)
            {
                foreach (ChiTietMonLienQuan ct in listMonLienQuan)
                {
                    ChiTietMonAnDaNgonNgu ctDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(ct.MonAn.MaMonAn, maNgonNgu);
                    if (ctDaNgonNgu != null)
                    {
                        ct.MonAn.TenMonAn = ctDaNgonNgu.TenMonAn;
                    }
                    else
                    {
                        ct.MonAn.TenMonAn = SharedString.NoInformation;
                    }
                }
            }

            return listMonLienQuan;
        }


        private string LayTenMonAn(int maMonAn)
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            ChiTietMonAnDaNgonNgu ctDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            if (ctDaNgonNgu != null)
            {
                return ctDaNgonNgu.TenMonAn;
            }
            else
            {
                return SharedString.NoInformation;
            }
        }
    }
}
