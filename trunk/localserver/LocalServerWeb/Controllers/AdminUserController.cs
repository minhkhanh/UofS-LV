using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminUser;
using LocalServerDTO;
using Webdiyer.WebControls.Mvc;
using LocalServerWeb.Resources.Views.Shared;
using System.IO;

namespace LocalServerWeb.Controllers
{
    public class AdminUserController : BaseController
    {
        //
        // GET: /AdminUser/

        public ActionResult Index(string page)
        {
            SharedCode.FillAdminMainMenu(ViewData, 1, 0);
            ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<TaiKhoan> pageListTaiKhoan = TaiKhoanBUS.LayDanhSachTaiKhoan().AsQueryable().ToPagedList(_page, 10);
            ViewData["listTaiKhoan"] = pageListTaiKhoan;
            ViewData["_page"] = _page;

            return View(pageListTaiKhoan);
        }

        [HttpGet]
        public ActionResult LockUnlock(int maTaiKhoan)
        {
            if (maTaiKhoan <= 0)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }            
            TaiKhoan objTaiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
            if (objTaiKhoan == null)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            if (objTaiKhoan.MaTaiKhoan == SharedCode.GetTaiKhoan(Session).MaTaiKhoan)
            {
                TempData["error"] = AdminUserString.ChangeYourself;
                return RedirectToAction("Index");
            }
            objTaiKhoan.Active = !objTaiKhoan.Active;

            if (TaiKhoanBUS.CapNhatTaiKhoan(objTaiKhoan))
            {
                TempData["infoLockUnlockSuccess"] = AdminUserString.LockUnLockSuccess;
            }
            else
            {
                TempData["errorCannotLockUnlock"] = AdminUserString.ErrorCannotLockUnlock;
            }

            return RedirectToAction("Index");
            
        }

        [HttpGet]
        public ActionResult Delete(int maTaiKhoan)
        {
            if (maTaiKhoan <= 0)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            TaiKhoan objTaiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
            if (objTaiKhoan == null)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            if (objTaiKhoan.MaTaiKhoan == SharedCode.GetTaiKhoan(Session).MaTaiKhoan)
            {
                TempData["error"] = AdminUserString.ChangeYourself;
                return RedirectToAction("Index");
            }
            objTaiKhoan.Active = !objTaiKhoan.Active;
            if (!TaiKhoanBUS.XoaTaiKhoan(objTaiKhoan))
            {
                TempData["errorCannotDelete"] = AdminUserString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminUserString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");

        }

        [HttpPost]
        public ActionResult ChangeGroupUser(int nhomTaiKhoan, int maTaiKhoan)
        {
            if (nhomTaiKhoan<=0 || maTaiKhoan<=0)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            NhomTaiKhoan objNhomTaiKhoan = NhomTaiKhoanBUS.LayNhomTaiKhoanTheoMa(nhomTaiKhoan);
            TaiKhoan objTaiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
            if (objNhomTaiKhoan==null || objTaiKhoan==null)
            {
                TempData["error"] = AdminUserString.InputWrong;
                return RedirectToAction("Index", "Error");
            }
            if (objTaiKhoan.MaTaiKhoan==SharedCode.GetTaiKhoan(Session).MaTaiKhoan)
            {
                TempData["error"] = AdminUserString.ChangeYourself;
                return RedirectToAction("Index");
            }
            objTaiKhoan.NhomTaiKhoan = objNhomTaiKhoan;

            if (TaiKhoanBUS.CapNhatTaiKhoan(objTaiKhoan))
            {
                TempData["infoEditSuccess"] = AdminUserString.InfoEditSuccess;
            }
            else
            {
                TempData["errorCannotEdit"] = AdminUserString.ErrorCannotEdit;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {

            ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
            TempData["tenTaiKhoan"] = "";
            if (TempData["checkDic"] == null)
            {
                TempData["checkDic"] = new Dictionary<string, string>();
            }                
            return View();
        }

        [HttpPost]
        public ActionResult Add(string tenTaiKhoan, string matKhau, string xacNhanMatKhau, string hoTen, int day, int month, int year, int gioiTinh, int nhomTaiKhoan, string cmnd, HttpPostedFileBase picture)
        {
            TempData["tenTaiKhoan"] = tenTaiKhoan;
            TempData["hoTen"] = hoTen;
            TempData["day"] = day;
            TempData["month"] = month;
            TempData["year"] = year;
            TempData["gioiTinh"] = gioiTinh;
            TempData["nhomTaiKhoan"] = nhomTaiKhoan;
            TempData["cmnd"] = cmnd;

            if (picture == null || picture.ContentLength == 0)
            {
                TempData["errorCannotAdd"] = AdminUserString.ErrorCannotAdd;
                return RedirectToAction("Add");
            }

            string fileName = Guid.NewGuid() + Path.GetFileName(picture.FileName);
            string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/UserImages"), fileName);       

            var checkDic = new Dictionary<string, string>();
            //HashSet<string> checkHash = new HashSet<string>();

            bool bCheckOk = true;
            var regexTenTaiKhoan = new Regex("[a-zA-Z0-9]{5,50}");
            if (tenTaiKhoan == null || !regexTenTaiKhoan.IsMatch(tenTaiKhoan))
            {
                bCheckOk = false;
                checkDic.Add("tenTaiKhoan", AdminUserString.Required);
            }
            var regexMatKhau = new Regex("^.{5,50}$");
            if (matKhau == null)
            {
                bCheckOk = false;
                checkDic.Add("matKhau", AdminUserString.Required);
            } else if (!regexMatKhau.IsMatch(matKhau))
            {
                bCheckOk = false;
                checkDic.Add("matKhau", AdminUserString.PasswordLength);
            } else if (matKhau!=xacNhanMatKhau)
            {
                bCheckOk = false;
                checkDic.Add("xacNhanMatKhau", AdminUserString.PasswordNotMatch);                
            }
            var regexHoTen = new Regex("^.{5,50}$");
            if (hoTen == null || !regexHoTen.IsMatch(hoTen))
            {
                bCheckOk = false;
                checkDic.Add("hoTen", AdminUserString.Required);
            }
            var regexCmnd = new Regex("[0-9]{9,10}");
            if (cmnd == null || !regexCmnd.IsMatch(cmnd))
            {
                bCheckOk = false;
                checkDic.Add("cmnd", AdminUserString.Required);
            }
            DateTime date = DateTime.Now;
            try
            {
                date = new DateTime(year, month, day);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("dateTime", AdminUserString.Required);
            }            
            if (bCheckOk)
            {
                try
                {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.Active = true;
                    taiKhoan.Avatar = "Uploads/UserImages/" + fileName;
                    taiKhoan.CMND = cmnd;
                    taiKhoan.GioiTinh = gioiTinh;
                    taiKhoan.HoTen = hoTen;
                    taiKhoan.MatKhau = SharedCode.MD5Hash(matKhau);
                    taiKhoan.NgaySinh = date;
                    taiKhoan.NhomTaiKhoan = NhomTaiKhoanBUS.LayNhomTaiKhoanTheoMa(nhomTaiKhoan);
                    taiKhoan.TenTaiKhoan = tenTaiKhoan;

                    if (TaiKhoanBUS.ThemTaiKhoan(taiKhoan))
                    {
                        picture.SaveAs(filePath);
                        TempData["infoAddSuccess"] = AdminUserString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminUser");
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
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TaiKhoan taiKhoan = TaiKhoanBUS.LayTaiKhoan(id ?? 0);
            if (taiKhoan == null)
            {
                TempData["errorNotFound"] = AdminUserString.Error;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["tenTaiKhoan"] = taiKhoan.TenTaiKhoan;
                TempData["matKhau"] = taiKhoan.MatKhau;
                TempData["hoTen"] = taiKhoan.HoTen;
                TempData["day"] = taiKhoan.NgaySinh.Day;
                TempData["month"] = taiKhoan.NgaySinh.Month;
                TempData["year"] = taiKhoan.NgaySinh.Year;
                TempData["gioiTinh"] = taiKhoan.GioiTinh;
                TempData["nhomTaiKhoan"] = taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan;
                TempData["cmnd"] = taiKhoan.CMND;

                ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
                if (TempData["checkDic"] == null)
                {
                    TempData["checkDic"] = new Dictionary<string, string>();
                }

            }

            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maTaiKhoan, string tenTaiKhoan, string matKhau, string xacNhanMatKhau, string hoTen, int day, int month, int year, int gioiTinh, int nhomTaiKhoan, string cmnd, HttpPostedFileBase picture)
        {
            TempData["tenTaiKhoan"] = tenTaiKhoan;
            TempData["hoTen"] = hoTen;
            TempData["day"] = day;
            TempData["month"] = month;
            TempData["year"] = year;
            TempData["gioiTinh"] = gioiTinh;
            TempData["nhomTaiKhoan"] = nhomTaiKhoan;
            TempData["cmnd"] = cmnd;

            if (picture == null || picture.ContentLength == 0)
            {
                TempData["errorCannotEdit"] = AdminUserString.ErrorCannotEdit;
                return RedirectToAction("Edit");
            }

            string fileName = Guid.NewGuid() + Path.GetFileName(picture.FileName);
            string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/UserImages"), fileName);   

            var checkDic = new Dictionary<string, string>();
            //HashSet<string> checkHash = new HashSet<string>();

            bool bCheckOk = true;
            var regexTenTaiKhoan = new Regex("[a-zA-Z0-9]{5,50}");
            if (tenTaiKhoan == null || !regexTenTaiKhoan.IsMatch(tenTaiKhoan))
            {
                bCheckOk = false;
                checkDic.Add("tenTaiKhoan", AdminUserString.Required);
            }
            var regexMatKhau = new Regex("^.{5,50}$");
            if (matKhau == null)
            {
                bCheckOk = false;
                checkDic.Add("matKhau", AdminUserString.Required);
            }
            else if (!regexMatKhau.IsMatch(matKhau))
            {
                bCheckOk = false;
                checkDic.Add("matKhau", AdminUserString.PasswordLength);
            }
            else if (matKhau != xacNhanMatKhau)
            {
                bCheckOk = false;
                checkDic.Add("xacNhanMatKhau", AdminUserString.PasswordNotMatch);
            }
            var regexHoTen = new Regex("^.{5,50}$");
            if (hoTen == null || !regexHoTen.IsMatch(hoTen))
            {
                bCheckOk = false;
                checkDic.Add("hoTen", AdminUserString.Required);
            }
            var regexCmnd = new Regex("[0-9]{9,10}");
            if (cmnd == null || !regexCmnd.IsMatch(cmnd))
            {
                bCheckOk = false;
                checkDic.Add("cmnd", AdminUserString.Required);
            }
            DateTime date = DateTime.Now;
            try
            {
                date = new DateTime(year, month, day);
            }
            catch (Exception)
            {
                bCheckOk = false;
                checkDic.Add("dateTime", AdminUserString.Required);
            }
            if (bCheckOk)
            {
                try
                {
                    TaiKhoan taiKhoan = TaiKhoanBUS.LayTaiKhoan(maTaiKhoan);
                    taiKhoan.Active = true;
                    taiKhoan.Avatar = "Uploads/UserImages/" + fileName;
                    taiKhoan.CMND = cmnd;
                    taiKhoan.GioiTinh = gioiTinh;
                    taiKhoan.HoTen = hoTen;
                    taiKhoan.MatKhau = SharedCode.MD5Hash(matKhau);
                    taiKhoan.NgaySinh = date;
                    taiKhoan.NhomTaiKhoan = NhomTaiKhoanBUS.LayNhomTaiKhoanTheoMa(nhomTaiKhoan);
                    taiKhoan.TenTaiKhoan = tenTaiKhoan;


                    if (TaiKhoanBUS.CapNhatTaiKhoan(taiKhoan))
                    {
                        TempData["infoEditSuccess"] = AdminUserString.InfoEditSuccess;
                        picture.SaveAs(filePath);
                        return RedirectToAction("Index", "AdminUser");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminUserString.ErrorCannotEdit;
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

    }


}
