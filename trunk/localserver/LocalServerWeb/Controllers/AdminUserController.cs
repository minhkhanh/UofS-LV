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

namespace LocalServerWeb.Controllers
{
    public class AdminUserController : BaseController
    {
        //
        // GET: /AdminUser/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 1, 0);
            ViewData["listTaiKhoan"] = TaiKhoanBUS.LayDanhSachTaiKhoan();
            ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
            return View();
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
            TaiKhoanBUS.CapNhatTaiKhoan(objTaiKhoan);

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
                TempData["error"] = AdminUserString.DeleteError;
                return RedirectToAction("Index");
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
            TaiKhoanBUS.CapNhatTaiKhoan(objTaiKhoan);

            return RedirectToAction("Index");
        }

        public ActionResult AddUser()
        {
            SharedCode.FillAdminMainMenu(ViewData, 1, 1);
            ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }                
            return View();
        }

        [HttpPost]
        public ActionResult AddUser(string tenTaiKhoan, string matKhau, string xacNhanMatKhau, string hoTen, int day, int month, int year, int gioiTinh, int nhomTaiKhoan, string cmnd, object picture)
        {
            TempData["tenTaiKhoan"] = tenTaiKhoan;
            TempData["hoTen"] = hoTen;
            TempData["day"] = day;
            TempData["month"] = month;
            TempData["year"] = year;
            TempData["gioiTinh"] = gioiTinh;
            TempData["nhomTaiKhoan"] = nhomTaiKhoan;
            TempData["cmnd"] = cmnd;

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
                    var taiKhoan = new TaiKhoan
                                            {
                                                Active = true,
                                                Avatar = null,
                                                CMND = cmnd,
                                                GioiTinh = gioiTinh,
                                                HoTen = hoTen,
                                                MatKhau = SharedCode.MD5Hash(matKhau),
                                                NgaySinh = date,
                                                NhomTaiKhoan = NhomTaiKhoanBUS.LayNhomTaiKhoanTheoMa(nhomTaiKhoan),
                                                TenTaiKhoan = tenTaiKhoan
                                            };
                    if (TaiKhoanBUS.ThemTaiKhoan(taiKhoan))
                        return RedirectToAction("Index", "AdminUser");
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }                
            }
            TempData["checkDic"] = checkDic;
            return RedirectToAction("AddUser");
        }
    }
}
