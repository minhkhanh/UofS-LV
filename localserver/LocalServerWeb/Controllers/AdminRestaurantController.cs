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
    public class AdminRestaurantController : BaseController
    {
        public ActionResult Index()
        {
            var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
            var tsMoTaNhaHang = ThamSoBUS.LayThamSo("MoTaNhaHang");
            var tsTelNhaHang = ThamSoBUS.LayThamSo("TelNhaHang");
            var tsFaxNhaHang = ThamSoBUS.LayThamSo("FaxNhaHang");
            var tsLogoNhaHang = ThamSoBUS.LayThamSo("LogoNhaHang");

            string tenNhaHang = (tsTenNhaHang != null) ? tsTenNhaHang.GiaTri : "";
            string moTaNhaHang = (tsMoTaNhaHang != null) ? tsMoTaNhaHang.GiaTri : "";
            string telNhaHang = (tsTelNhaHang != null) ? tsTelNhaHang.GiaTri : "";
            string faxNhaHang = (tsFaxNhaHang != null) ? tsFaxNhaHang.GiaTri : "";
            string logoNhaHang = (tsLogoNhaHang != null) ? tsLogoNhaHang.GiaTri : "";

            return View();
        }


        public ActionResult UpdateLogo()
        {
            return RedirectToAction("Index");
        }
    }
}