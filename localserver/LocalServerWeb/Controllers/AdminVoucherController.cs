using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminVoucher;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminVoucherController : BaseController
    {
        public ActionResult Index(string page)
        {
            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<Voucher> pageListVoucher = VoucherBUS.LayDanhSachVoucher().AsQueryable().ToPagedList(_page, 10);
            ViewData["listVoucher"] = pageListVoucher;
            ViewData["_page"] = _page;

            return View(pageListVoucher);
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            Voucher voucher = VoucherBUS.LayVoucher(id ?? 0);
            if (voucher == null)
            {
                TempData["errorNotFound"] = AdminVoucherString.ErrorVoucherNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!VoucherBUS.Xoa(voucher.MaVoucher))
            {
                TempData["errorCannotDelete"] = AdminVoucherString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminVoucherString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            if (TempData["checkDic"] == null)
            {
                //TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            // De tien su dung, pre populate voi ngay hien tai
            DateTime thoiDiemHienTai = DateTime.Now;
            TempData["ngayBatDau"] = thoiDiemHienTai.Day;
            TempData["thangBatDau"] = thoiDiemHienTai.Month;
            TempData["namBatDau"] = thoiDiemHienTai.Year;

            // ngay Ket thuc la ngay mai
            thoiDiemHienTai = thoiDiemHienTai.AddDays(1);
            TempData["ngayKetThuc"] = thoiDiemHienTai.Day;
            TempData["thangKetThuc"] = thoiDiemHienTai.Month;
            TempData["namKetThuc"] = thoiDiemHienTai.Year;

            return View();
        }

        [HttpPost]
        public ActionResult Add(string tenVoucher, string moTa, float? mucGiaApDung, float? giaTri, int ngayBatDau, int thangBatDau, int namBatDau,
                                int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            TempData["tenVoucher"] = tenVoucher;
            TempData["moTa"] = moTa;
            TempData["giaTri"] = giaTri;
            TempData["ngayBatDau"] = ngayBatDau;
            TempData["thangBatDau"] = thangBatDau;
            TempData["namBatDau"] = namBatDau;
            TempData["ngayKetThuc"] = ngayKetThuc;
            TempData["thangKetThuc"] = thangKetThuc;
            TempData["namKetThuc"] = namKetThuc;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            // Kiem tra ten Khuyen mai
            if (tenVoucher == null || tenVoucher.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenVoucher", SharedString.InputWrong);
            }

            // Kiem tra Gia tri co nhap chua
            if (giaTri == null || giaTri <=0)
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            // Kiem tra Muc gia ap dung co nhap chua
            if (mucGiaApDung == null || mucGiaApDung <=0)
            {
                bCheckOk = false;
                checkDic.Add("mucGiaApDung", SharedString.InputWrong);
            }


            // Kiem tra thoi diem bat dau
            DateTime thoiDiemBatDau = DateTime.Now;
            try
            {
                thoiDiemBatDau = new DateTime(namBatDau, thangBatDau, ngayBatDau);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiem", AdminVoucherString.ErrorDateWrong);
            }

            // Kiem tra thoi diem ket thuc
            DateTime thoiDiemKetThuc = DateTime.Now;
            try
            {
                thoiDiemKetThuc = new DateTime(namKetThuc, thangKetThuc, ngayKetThuc);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiem", AdminVoucherString.ErrorDateWrong);
            }


            // Kiem tra Thoi diem bat dau > Thoi diem ket thuc ?
            if (DateTime.Compare(thoiDiemKetThuc, thoiDiemBatDau) <= 0)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiemKetThucSau", AdminVoucherString.ErrorEndDateAfter);
            }

            if (bCheckOk)
            {
                try
                {
                    Voucher voucher = new Voucher();
                    voucher.TenVoucher = tenVoucher;
                    voucher.MoTaVoucher = moTa;
                    voucher.BatDau = thoiDiemBatDau;
                    voucher.KetThuc = thoiDiemKetThuc;
                    voucher.MucGiaApDung = mucGiaApDung ?? 0;
                    voucher.GiaGiam = giaTri ?? 0;

                    if (VoucherBUS.Them(voucher))
                    {
                        TempData["infoAddSuccess"] = AdminVoucherString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminVoucher");
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminVoucherString.ErrorCannotAdd;
                    }

                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
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

            Voucher voucher = VoucherBUS.LayVoucher(id ?? 0);
            if (voucher == null)
            {
                TempData["errorNotFound"] = AdminVoucherString.ErrorVoucherNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["tenVoucher"] = voucher.TenVoucher;
                TempData["moTa"] = voucher.MoTaVoucher;
                TempData["mucGiaApDung"] = voucher.MucGiaApDung;
                TempData["giaTri"] = voucher.GiaGiam;


                // De tien su dung, pre populate voi ngay hien tai
                DateTime thoiDiemHienTai = DateTime.Now;
                TempData["ngayBatDau"] = thoiDiemHienTai.Day;
                TempData["thangBatDau"] = thoiDiemHienTai.Month;
                TempData["namBatDau"] = thoiDiemHienTai.Year;

                // ngay Ket thuc la ngay mai
                thoiDiemHienTai = thoiDiemHienTai.AddDays(1);
                TempData["ngayKetThuc"] = thoiDiemHienTai.Day;
                TempData["thangKetThuc"] = thoiDiemHienTai.Month;
                TempData["namKetThuc"] = thoiDiemHienTai.Year;
            }



            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maVoucher, string tenVoucher, string moTa, float? mucGiaApDung, float? giaTri, int ngayBatDau, int thangBatDau, int namBatDau,
                                int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            TempData["tenVoucher"] = tenVoucher;
            TempData["moTa"] = moTa;
            TempData["mucGiaApDung"] = mucGiaApDung;
            TempData["giaTri"] = giaTri;
            TempData["ngayBatDau"] = ngayBatDau;
            TempData["thangBatDau"] = thangBatDau;
            TempData["namBatDau"] = namBatDau;
            TempData["ngayKetThuc"] = ngayKetThuc;
            TempData["thangKetThuc"] = thangKetThuc;
            TempData["namKetThuc"] = namKetThuc;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            // Kiem tra ten Khuyen mai
            if (tenVoucher == null || tenVoucher.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenVoucher", SharedString.InputWrong);
            }

            // Kiem tra Gia tri co nhap chua
            if (giaTri == null || giaTri <=0 )
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            // Kiem tra Muc gia ap dung co nhap chua
            if (mucGiaApDung == null || mucGiaApDung <= 0)
            {
                bCheckOk = false;
                checkDic.Add("mucGiaApDung", SharedString.InputWrong);
            }


            // Kiem tra thoi diem bat dau
            DateTime thoiDiemBatDau = DateTime.Now;
            try
            {
                thoiDiemBatDau = new DateTime(namBatDau, thangBatDau, ngayBatDau);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiem", AdminVoucherString.ErrorDateWrong);
            }

            // Kiem tra thoi diem ket thuc
            DateTime thoiDiemKetThuc = DateTime.Now;
            try
            {
                thoiDiemKetThuc = new DateTime(namKetThuc, thangKetThuc, ngayKetThuc);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiem", AdminVoucherString.ErrorDateWrong);
            }


            // Kiem tra Thoi diem bat dau > Thoi diem ket thuc ?
            if (DateTime.Compare(thoiDiemKetThuc, thoiDiemBatDau) <= 0)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiemKetThucSau", AdminVoucherString.ErrorEndDateAfter);
            }

            if (bCheckOk)
            {
                try
                {
                    Voucher voucher = VoucherBUS.LayVoucher(maVoucher);
                    voucher.TenVoucher = tenVoucher;
                    voucher.MoTaVoucher = moTa;
                    voucher.BatDau = thoiDiemBatDau;
                    voucher.KetThuc = thoiDiemKetThuc;
                    voucher.MucGiaApDung = mucGiaApDung ?? 0;
                    voucher.GiaGiam = giaTri ?? 0;


                    // Luon luon tao them Khuyen Mai Hoa Don voi gia tri ap dung = 0
                    if (VoucherBUS.CapNhat(voucher))
                    {
                        TempData["infoEditSuccess"] = AdminVoucherString.InfoEditSuccess;
                        return RedirectToAction("Index", "AdminVoucher");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminVoucherString.ErrorCannotEdit;
                    }

                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Edit");

        }

        public ActionResult VoucherDetail(int? id, string page)
        {
            // Check if id OK and khuyenMai not NULL
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            Voucher voucher = VoucherBUS.LayVoucher(id ?? 0);
            if (voucher == null)
            {
                TempData["errorNotFound"] = AdminVoucherString.ErrorVoucherNotFound;
                return RedirectToAction("Index", "Error");
            }

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<ChiTietVoucher> pageListCTVoucher = ChiTietVoucherBUS.LayNhieuChiTietVoucher(id??0).AsQueryable().ToPagedList(_page, 10);
            ViewData["listCTVoucher"] = pageListCTVoucher;
            ViewData["_page"] = _page;
            ViewData["tenVoucher"] = voucher.TenVoucher;

            return View(pageListCTVoucher);
        }

        public ActionResult DeleteVoucherDetail(int? id, int maVoucher)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            ChiTietVoucher ctVoucher = ChiTietVoucherBUS.LayChiTietVoucher(id ?? 0);
            if (ctVoucher == null)
            {
                TempData["errorNotFound"] = AdminVoucherString.ErrorVoucherDetailNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!ChiTietVoucherBUS.Xoa(ctVoucher.MaChiTietVoucher))
            {
                TempData["errorCannotDelete"] = AdminVoucherString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminVoucherString.InfoDeleteSuccess;
            }

            return RedirectToAction("VoucherDetail", new { id = maVoucher });
        }


        public ActionResult AddVoucherDetail(int maVoucher, string soPhieu)
        {
            if (ChiTietVoucherBUS.KiemTraTonTai(soPhieu))
            {
                TempData["errorVoucherDetailExist"] = AdminVoucherString.ErrorCodeExist;
            }
            else
            {
                ChiTietVoucher ctVoucher = new ChiTietVoucher();
                ctVoucher._maVoucher = maVoucher;
                ctVoucher.SoPhieu = soPhieu;
                ctVoucher.Active = true;
                if (ChiTietVoucherBUS.Them(ctVoucher))
                {
                    TempData["infoAddSuccess"] = AdminVoucherString.InfoAddSuccess;
                }
                else
                {
                    TempData["errorCannotAdd"] = AdminVoucherString.ErrorCannotAdd;
                }
            }

            return RedirectToAction("VoucherDetail", new { id = maVoucher });
        }

        public ActionResult AddManyVoucherDetail(int maVoucher, int soLuong)
        {
            Random rand = new Random();
            for (int i = 0; i < soLuong; ++i)
            {
                string soPhieu = GenerateCode(maVoucher, rand, 5);
                while (ChiTietVoucherBUS.KiemTraTonTai(soPhieu))
                {
                    soPhieu = GenerateCode(maVoucher, rand, 5);
                }

                ChiTietVoucher ctVoucher = new ChiTietVoucher();
                ctVoucher._maVoucher = maVoucher;
                ctVoucher.SoPhieu = soPhieu;
                ctVoucher.Active = true;
                if(!ChiTietVoucherBUS.Them(ctVoucher))
                {
                    TempData["errorCannotAdd"] = AdminVoucherString.ErrorCannotAdd;
                    break;
                }

            }

            return RedirectToAction("VoucherDetail", new { id = maVoucher });
        }

        private string GenerateCode(int maVoucher, Random rand, int size)
        {
            string strMaVoucher = maVoucher.ToString();
            string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            string result = new string(
                Enumerable.Repeat(chars, size)
                          .Select(s => s[rand.Next(s.Length)])
                          .ToArray());

            return strMaVoucher + result;
        }

        public ActionResult Print(int maChiTietVoucher, int maVoucher)
        {
            ChiTietVoucher ctVoucher = ChiTietVoucherBUS.LayChiTietVoucher(maChiTietVoucher);
            if (ctVoucher == null)
                return RedirectToAction("Index");

            if (!Reports.ReportManager.PrintVoucherReport(maChiTietVoucher))
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");

            }

            return RedirectToAction("VoucherDetail", new { id = maVoucher });

        }

    }
}