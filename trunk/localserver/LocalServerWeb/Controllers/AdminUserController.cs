
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminUser;
using LocalServerDTO;

namespace LocalServerWeb.Controllers
{
    public class AdminUserController : AdminBaseController
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
    }
}
