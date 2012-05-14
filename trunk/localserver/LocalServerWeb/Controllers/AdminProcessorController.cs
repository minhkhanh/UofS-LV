using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminProcessor;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminProcessorController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            ViewData["listBoPhanCheBien"] = BoPhanCheBienBUS.LayDanhSachBoPhanCheBien();
            return View();
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            BoPhanCheBien boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(id ?? 0);
            if (boPhanCheBien == null)
            {
                TempData["errorNotFound"] = AdminProcessorString.ErrorProcessorNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!BoPhanCheBienBUS.Xoa(boPhanCheBien.MaBoPhanCheBien))
            {
                TempData["errorCannotDelete"] = AdminProcessorString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminProcessorString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
            ViewData["listTaiKhoan"] = TaiKhoanBUS.LayDanhSachTaiKhoan();
            if (TempData["checkDic"] == null)
            {
                //TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }
            return View();
        }

        [HttpPost]
        public ActionResult Add(string tenBoPhan, string moTa, int maTaiKhoan)
        {
            TempData["tenBoPhan"] = tenBoPhan;
            TempData["moTa"] = moTa;
            TempData["maTaiKhoan"] = maTaiKhoan;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            if (tenBoPhan == null || tenBoPhan.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenBoPhan", SharedString.InputWrong);
            }

            TaiKhoan taiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
            if (maTaiKhoan <= 0 || taiKhoan == null)
            {
                bCheckOk = false;
                checkDic.Add("maTaiKhoan", AdminProcessorString.ErrorAccountNotFound);
            }

            if (bCheckOk)
            {
                try
                {
                    BoPhanCheBien boPhanCheBien = new BoPhanCheBien();
                    boPhanCheBien.TenBoPhan = tenBoPhan;
                    boPhanCheBien.MoTa = moTa;
                    boPhanCheBien.TaiKhoan = taiKhoan;


                    // Need to clear TempData
                    if (BoPhanCheBienBUS.Them(boPhanCheBien))
                    {
                        TempData["infoAddSuccess"] = AdminProcessorString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminProcessor");
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminProcessorString.ErrorCannotAdd;
                    }
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
            ViewData["listTaiKhoan"] = TaiKhoanBUS.LayDanhSachTaiKhoan();

            if (TempData["checkDic"] == null)
            {
                //TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            BoPhanCheBien boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(id??0);
            if (boPhanCheBien == null)
            {
                TempData["errorNotFound"] = AdminProcessorString.ErrorProcessorNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["tenBoPhan"] = boPhanCheBien.TenBoPhan;
                TempData["moTa"] = boPhanCheBien.MoTa;
                TempData["maTaiKhoan"] = boPhanCheBien.TaiKhoan.MaTaiKhoan;

                ViewData["listDanhMuc"] = LayDanhSachDanhMucLevelThapNhat();
                List<ChiTietDanhMucBoPhanCheBien> listChiTietDanhMucBoPhanCheBien = LayDanhSachChiTietDanhMucBoPhanCheBien(boPhanCheBien.MaBoPhanCheBien);
                if (listChiTietDanhMucBoPhanCheBien == null || listChiTietDanhMucBoPhanCheBien.Count == 0)
                {
                    TempData["warningNoCategoryDetail"] = AdminProcessorString.WarningNoCategoryDetail;
                }

                ViewData["listChiTietDanhMucBoPhanCheBien"] = listChiTietDanhMucBoPhanCheBien;

            }

            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maBoPhanCheBien, string tenBoPhan, string moTa, int maTaiKhoan)
        {
            TempData["tenBoPhan"] = tenBoPhan;
            TempData["moTa"] = moTa;
            TempData["maTaiKhoan"] = maTaiKhoan;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            if (tenBoPhan == null || tenBoPhan.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenBoPhan", SharedString.InputWrong);
            }


            TaiKhoan objTaiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
            if (maTaiKhoan <= 0 || objTaiKhoan == null)
            {
                bCheckOk = false;
                checkDic.Add("maTaiKhoan", AdminProcessorString.ErrorAccountNotFound);
            }

            if (bCheckOk)
            {
                try
                {
                    BoPhanCheBien boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(maBoPhanCheBien);
                    boPhanCheBien.TenBoPhan = tenBoPhan;
                    boPhanCheBien.MoTa = moTa;
                    boPhanCheBien.TaiKhoan = objTaiKhoan;

                    // Need to clear TempData
                    if (BoPhanCheBienBUS.CapNhat(boPhanCheBien))
                    {
                        TempData["infoEditSuccess"] = AdminProcessorString.InfoEditSuccess;
                        return RedirectToAction("Index", "AdminProcessor");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminProcessorString.ErrorCannotEdit;
                    }
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Edit");

        }


        [HttpPost]
        public ActionResult AddCategoryProcessor(int maBoPhanCheBien, int maDanhMuc)
        {
            if (maBoPhanCheBien < 0 || maDanhMuc <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            BoPhanCheBien boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(maBoPhanCheBien);
            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            if (boPhanCheBien == null || danhMuc == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDanhMucBoPhanCheBien ctDanhMucBoPhanCheBien = ChiTietDanhMucBoPhanCheBienBUS.LayChiTiet(maBoPhanCheBien, maDanhMuc);
            if (ctDanhMucBoPhanCheBien != null)
            {
                TempData["errorCategoryDetailExist"] = AdminProcessorString.ErrorCategoryDetailExist;
                bCheck = false;
            }

            if (bCheck)
            {
                ChiTietDanhMucBoPhanCheBien ctMoi = new ChiTietDanhMucBoPhanCheBien();
                ctMoi.BoPhanCheBien = boPhanCheBien;
                ctMoi.DanhMuc = danhMuc;

                if (ChiTietDanhMucBoPhanCheBienBUS.Them(ctMoi))
                {
                    TempData["infoAddSuccess"] = AdminProcessorString.InfoAddSuccess;
                }
                else
                {
                    TempData["errorCannotAdd"] = AdminProcessorString.ErrorCannotAdd;
                }
            }

            return RedirectToAction("Edit", new { id = maBoPhanCheBien });
        }

        [HttpPost]
        public ActionResult DeleteCategoryProcessor(int maBoPhanCheBien, int maDanhMuc)
        {
            if (maBoPhanCheBien < 0 || maDanhMuc <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            BoPhanCheBien boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(maBoPhanCheBien);
            DanhMuc danhMuc = DanhMucBUS.LayDanhMuc(maDanhMuc);
            if (boPhanCheBien == null || danhMuc == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            bool bCheck = true;
            ChiTietDanhMucBoPhanCheBien ctDanhMucBoPhanCheBien = ChiTietDanhMucBoPhanCheBienBUS.LayChiTiet(maBoPhanCheBien, maDanhMuc);
            if (ctDanhMucBoPhanCheBien == null)
            {
                TempData["errorCategoryDetailNotFound"] = AdminProcessorString.ErrorCategoryDetailNotFound;
                bCheck = false;
            }

            if (bCheck)
            {
                ChiTietDanhMucBoPhanCheBien ct = ChiTietDanhMucBoPhanCheBienBUS.LayChiTiet(maBoPhanCheBien, maDanhMuc);

                if (ChiTietDanhMucBoPhanCheBienBUS.Xoa(ct))
                {
                    TempData["infoDeleteSuccess"] = AdminProcessorString.InfoDeleteSuccess;
                }
                else
                {
                    TempData["errorCannotDelete"] = AdminProcessorString.ErrorCannotDelete;
                }
            }

            return RedirectToAction("Edit", new { id = maBoPhanCheBien });
        }

        private List<DanhMuc> LayDanhSachDanhMucLevelThapNhat()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            return DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoMaNgonNgu(maNgonNgu, SharedString.NoInformation);
        }

        private List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBien(int maBoPhanCheBien)
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<ChiTietDanhMucBoPhanCheBien> listChiTietDanhMucBoPhanCheBien = ChiTietDanhMucBoPhanCheBienBUS.LayDanhSachChiTietDanhMucBoPhanCheBienTheoBoPhanCheBien(maBoPhanCheBien);
            foreach (ChiTietDanhMucBoPhanCheBien ct in listChiTietDanhMucBoPhanCheBien)
            {
                ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(ct.DanhMuc.MaDanhMuc, maNgonNgu);
                if (ctDanhMucDaNgonNgu != null)
                    ct.DanhMuc.TenDanhMuc = ctDanhMucDaNgonNgu.TenDanhMuc;
                else
                    ct.DanhMuc.TenDanhMuc = SharedString.NoInformation;
            }

            return listChiTietDanhMucBoPhanCheBien;
        }
    }
}