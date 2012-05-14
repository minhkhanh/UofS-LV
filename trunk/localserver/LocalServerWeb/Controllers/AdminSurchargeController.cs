using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminSurcharge;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{
    public class AdminSurchargeController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            ViewData["listPhuThu"] = PhuThuBUS.LayDanhSachPhuThu();
            return View();
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            PhuThu phuThu = PhuThuBUS.LayPhuThu(id ?? 0);
            if (phuThu == null)
            {
                TempData["errorNotFound"] = AdminSurchargeString.ErrorSurchargeNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!PhuThuBUS.Xoa(phuThu.MaPhuThu))
            {
                TempData["errorCannotDelete"] = AdminSurchargeString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminSurchargeString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 6);
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
        public ActionResult Add(string tenPhuThu, string moTa, int hinhThucTang, float? giaTri, int ngayBatDau, int thangBatDau, int namBatDau,
                                int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            TempData["tenPhuThu"] = tenPhuThu;
            TempData["moTa"] = moTa;
            TempData["hinhThucTang"] = hinhThucTang;
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
            if (tenPhuThu == null || tenPhuThu.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenPhuThu", SharedString.InputWrong);
            }

            // Kiem tra Gia tri co nhap chua
            if (giaTri == null)
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            // hinhThucTang 
            // 0: giaTriTang
            // 1: tiLeTang
            switch (hinhThucTang)
            {
                case 0:
                    if (giaTri <= 0)
                    {
                        bCheckOk = false;
                        checkDic.Add("giaTri", SharedString.InputWrong);
                    }
                    break;
                case 1:
                    if (giaTri <= 0 || giaTri > 100)
                    {
                        bCheckOk = false;
                        checkDic.Add("giaTri", SharedString.InputWrong);
                    }
                    break;
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
                checkDic.Add("thoiDiem", AdminSurchargeString.ErrorDateWrong);
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
                checkDic.Add("thoiDiem", AdminSurchargeString.ErrorDateWrong);
            }


            // Kiem tra Thoi diem bat dau > Thoi diem ket thuc ?
            if (DateTime.Compare(thoiDiemKetThuc, thoiDiemBatDau) <= 0)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiemKetThucSau", AdminSurchargeString.ErrorEndDateAfter);
            }

            if (bCheckOk)
            {
                try
                {
                    PhuThu phuThu = new PhuThu();
                    phuThu.TenPhuThu = tenPhuThu;
                    phuThu.MoTaPhuThu = moTa;
                    phuThu.BatDau = thoiDiemBatDau;
                    phuThu.KetThuc = thoiDiemKetThuc;

                    if (hinhThucTang == 0)
                    {
                        phuThu.GiaTang = giaTri ?? 0;
                        phuThu.TiLeTang = 0;
                    }
                    else if (hinhThucTang == 1)
                    {
                        phuThu.GiaTang = 0;
                        phuThu.TiLeTang = giaTri ?? 0;
                    }

                    // Luon luon tao them Khuyen Mai Hoa Don voi gia tri ap dung = 0
                    if (PhuThuBUS.Them(phuThu))
                    {
                        TempData["infoAddSuccess"] = AdminSurchargeString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminSurcharge");
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminSurchargeString.ErrorCannotAdd;
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

            PhuThu phuThu = PhuThuBUS.LayPhuThu(id ?? 0);
            if (phuThu == null)
            {
                TempData["errorNotFound"] = AdminSurchargeString.ErrorSurchargeNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["tenPhuThu"] = phuThu.TenPhuThu;
                TempData["moTa"] = phuThu.MoTaPhuThu;
                TempData["hinhThucTang"] = (phuThu.GiaTang != 0) ? 0 : 1;
                TempData["giaTri"] = (phuThu.GiaTang != 0) ? phuThu.GiaTang : phuThu.TiLeTang;


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
        public ActionResult Edit(int maPhuThu, string tenPhuThu, string moTa, int hinhThucTang, float? giaTri, int ngayBatDau, int thangBatDau, int namBatDau,
                                int ngayKetThuc, int thangKetThuc, int namKetThuc)
        {
            TempData["tenPhuThu"] = tenPhuThu;
            TempData["moTa"] = moTa;
            TempData["hinhThucTang"] = hinhThucTang;
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
            if (tenPhuThu == null || tenPhuThu.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("tenPhuThu", SharedString.InputWrong);
            }

            // Kiem tra Gia tri co nhap chua
            if (giaTri == null)
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            // hinhThucTang 
            // 0: giaTriTang
            // 1: tiLeTang
            switch (hinhThucTang)
            {
                case 0:
                    if (giaTri <= 0)
                    {
                        bCheckOk = false;
                        checkDic.Add("giaTri", SharedString.InputWrong);
                    }
                    break;
                case 1:
                    if (giaTri <= 0 || giaTri > 100)
                    {
                        bCheckOk = false;
                        checkDic.Add("giaTri", SharedString.InputWrong);
                    }
                    break;
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
                checkDic.Add("thoiDiem", AdminSurchargeString.ErrorDateWrong);
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
                checkDic.Add("thoiDiem", AdminSurchargeString.ErrorDateWrong);
            }


            // Kiem tra Thoi diem bat dau > Thoi diem ket thuc ?
            if (DateTime.Compare(thoiDiemKetThuc, thoiDiemBatDau) <= 0)
            {
                bCheckOk = false;
                checkDic.Add("thoiDiemKetThucSau", AdminSurchargeString.ErrorEndDateAfter);
            }

            if (bCheckOk)
            {
                try
                {
                    PhuThu phuThu = PhuThuBUS.LayPhuThu(maPhuThu);
                    phuThu.TenPhuThu = tenPhuThu;
                    phuThu.MoTaPhuThu = moTa;
                    phuThu.BatDau = thoiDiemBatDau;
                    phuThu.KetThuc = thoiDiemKetThuc;

                    if (hinhThucTang == 0)
                    {
                        phuThu.GiaTang = giaTri ?? 0;
                        phuThu.TiLeTang = 0;
                    }
                    else if (hinhThucTang == 1)
                    {
                        phuThu.GiaTang = 0;
                        phuThu.TiLeTang = giaTri ?? 0;
                    }

                    // Luon luon tao them Khuyen Mai Hoa Don voi gia tri ap dung = 0
                    if (PhuThuBUS.CapNhat(phuThu))
                    {
                        TempData["infoEditSuccess"] = AdminSurchargeString.InfoEditSuccess;
                        return RedirectToAction("Index", "AdminSurcharge");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminSurchargeString.ErrorCannotEdit;
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
    }
}