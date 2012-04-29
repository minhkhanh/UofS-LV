using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminFoodController : BaseController
    {
        //
        // GET: /AdminHome/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 0);
            return View();
        }

        public ActionResult AddFood()
        {
            SharedCode.FillAdminMainMenu(ViewData, 2, 1);
            ViewData["listDanhMuc"] = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoNgonNgu(SharedCode.GetCurrentLanguage(Session));
            return View();
        }

        [HttpPost]
        public ActionResult AddFood(int listDanhMuc, HttpPostedFileBase uploadFile)
        {
            TempData["listDanhMuc"] = listDanhMuc;
            var danhMuc = DanhMucBUS.LayDanhMuc(listDanhMuc);
            string strAppPath = string.Format("{0}://{1}{2}", Request.Url.Scheme, Request.Url.Authority, Request.ApplicationPath);
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
            if (MonAnBUS.ThemMonAn(monAn))
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
            var danhSachDanhMuc = DanhMucBUS.LayDanhSachDanhMucLevelThapNhatTheoNgonNgu(SharedCode.GetCurrentLanguage(Session));
            //            
            ViewData["listDanhMuc"] = new SelectList(danhSachDanhMuc, "MaDanhMuc", "TenDanhMuc", 5);
            //ViewData["maDanhMuc"] = monAn.DanhMuc.MaDanhMuc.ToString();
            

            return View();
        }

        public ActionResult UpdateCategory(int listDanhMuc, int maMonAn)
        {
            var danhMuc = DanhMucBUS.LayDanhMuc(listDanhMuc);
            var monAn = MonAnBUS.LayMonAn(maMonAn);
            if (danhMuc!=null && monAn!=null)
            {
                monAn.DanhMuc = danhMuc;
                MonAnBUS.CapNhatMonAn(monAn);
            }
            return RedirectToAction("ViewDetailFood", new { maMonAn = maMonAn});
        }
    }
}
