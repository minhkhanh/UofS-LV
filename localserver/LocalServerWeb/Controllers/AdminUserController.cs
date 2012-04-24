
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminUserController : BaseController
    {
        //
        // GET: /AdminUser/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 0, 0);
            ViewData["listTaiKhoan"] = TaiKhoanBUS.LayDanhSachTaiKhoan();
            ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
            return View();
        }

        [HttpPost]
        public ActionResult ChangeGroupUser()
        {
            return new EmptyResult();
        }
    }
}
