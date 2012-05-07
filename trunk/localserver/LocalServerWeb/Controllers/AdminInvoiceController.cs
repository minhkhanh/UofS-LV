using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminOrder;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
namespace LocalServerWeb.Controllers
{
    public class AdminInvoiceController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);
            ViewData["listHoaDon"] = HoaDonBUS.LayDanhSachHoaDon();
            return View();
        }

        public ActionResult InvoiceDetail(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);
            if (id == null || id < 1)
                return View();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<ChiTietHoaDon> listChiTietHoaDOn = ChiTietHoaDonBUS.LayNhieuChiTietHoaDon(id ?? 1);

            foreach (ChiTietHoaDon ct in listChiTietHoaDOn)
            {
                ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(ct.MonAn.MaMonAn, maNgonNgu);
                ct.MonAn.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;

            }

            ViewData["listChiTietHoaDon"] = listChiTietHoaDOn;
            return View();
        }
    }
}