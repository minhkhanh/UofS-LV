using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminRestaurant;
using LocalServerWeb.Resources.Views.Shared;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminRestaurantController : ManagerBaseController
    {
        public ActionResult Index()
        {
            var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
            var tsMoTaNhaHang = ThamSoBUS.LayThamSo("MoTaNhaHang");
            var tsDiaChiNhaHang = ThamSoBUS.LayThamSo("DiaChiNhaHang");
            var tsTelNhaHang = ThamSoBUS.LayThamSo("TelNhaHang");
            var tsFaxNhaHang = ThamSoBUS.LayThamSo("FaxNhaHang");
            var tsLogoNhaHang = ThamSoBUS.LayThamSo("LogoNhaHang");

            string tenNhaHang = (tsTenNhaHang != null) ? tsTenNhaHang.GiaTri : "";
            string moTaNhaHang = (tsMoTaNhaHang != null) ? tsMoTaNhaHang.GiaTri : "";
            string diaChiNhaHang = (tsDiaChiNhaHang != null) ? tsDiaChiNhaHang.GiaTri : "";
            string telNhaHang = (tsTelNhaHang != null) ? tsTelNhaHang.GiaTri : "";
            string faxNhaHang = (tsFaxNhaHang != null) ? tsFaxNhaHang.GiaTri : "";
            string logoNhaHang = (tsLogoNhaHang != null) ? tsLogoNhaHang.GiaTri : "";

            ViewData["tenNhaHang"] = tenNhaHang;
            ViewData["moTaNhaHang"] = moTaNhaHang;
            ViewData["diaChiNhaHang"] = diaChiNhaHang;
            ViewData["telNhaHang"] = telNhaHang;
            ViewData["faxNhaHang"] = faxNhaHang;
            ViewData["logoNhaHang"] = logoNhaHang;

            return View();
        }

        [HttpPost]
        public ActionResult UpdateInfo(string tenNhaHang, string motaNhaHang, string diaChiNhaHang, string telNhaHang, string faxNhaHang)
        {
            var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
            var tsMoTaNhaHang = ThamSoBUS.LayThamSo("MoTaNhaHang");
            var tsDiaChiNhaHang = ThamSoBUS.LayThamSo("DiaChiNhaHang");
            var tsTelNhaHang = ThamSoBUS.LayThamSo("TelNhaHang");
            var tsFaxNhaHang = ThamSoBUS.LayThamSo("FaxNhaHang");

            if (tsTenNhaHang == null || tsMoTaNhaHang == null || tsDiaChiNhaHang == null || tsTelNhaHang == null || tsFaxNhaHang == null)
            {
                TempData["errorCannotUpdate"] = AdminRestaurantString.ErrorCannotUpdate;
                return RedirectToAction("Index");
            }

            bool ketQua = true;
            
            tsTenNhaHang.GiaTri = tenNhaHang;
            tsDiaChiNhaHang.GiaTri = diaChiNhaHang;
            tsMoTaNhaHang.GiaTri = motaNhaHang;
            tsTelNhaHang.GiaTri = telNhaHang;
            tsFaxNhaHang.GiaTri = faxNhaHang;

            if (!ThamSoBUS.CapNhat(tsTenNhaHang))
                ketQua = false;
            if (!ThamSoBUS.CapNhat(tsDiaChiNhaHang))
                ketQua = false;
            if (!ThamSoBUS.CapNhat(tsMoTaNhaHang))
                ketQua = false;
            if (!ThamSoBUS.CapNhat(tsTelNhaHang))
                ketQua = false;
            if (!ThamSoBUS.CapNhat(tsFaxNhaHang))
                ketQua = false;

            if (ketQua == true)
            {
                TempData["infoUpdateSuccess"] = AdminRestaurantString.InfoUpdateSuccess;
            }
            else
            {
                TempData["errorCannotUpdate"] = AdminRestaurantString.ErrorCannotUpdate;
            }

            return RedirectToAction("Index");
        }

        public ActionResult UpdateLogo(HttpPostedFileBase uploadFile)
        {
            var tsLogoNhaHang = ThamSoBUS.LayThamSo("LogoNhaHang");

            if (uploadFile == null || uploadFile.ContentLength == 0 || tsLogoNhaHang == null)
            {
                TempData["errorCannotUpdate"] = AdminRestaurantString.ErrorCannotUpdate;
                return RedirectToAction("Index");
            }

            string fileName = Guid.NewGuid() + Path.GetFileName(uploadFile.FileName);
            string filePath = Path.Combine(HttpContext.Server.MapPath("../Uploads/RestaurantImages"), fileName);


            tsLogoNhaHang.GiaTri = "Uploads/RestaurantImages/" + fileName;
            if (ThamSoBUS.CapNhat(tsLogoNhaHang))
            {
                uploadFile.SaveAs(filePath);
                TempData["infoUpdateSuccess"] = AdminRestaurantString.InfoUpdateSuccess;
            }
            else
            {
                TempData["errorCannotUpdate"] = AdminRestaurantString.ErrorCannotUpdate;
            }

            return RedirectToAction("Index");
        }
    }
}