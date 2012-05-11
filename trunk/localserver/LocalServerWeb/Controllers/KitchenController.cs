using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class KitchenController : BaseController
    {
        //
        // GET: /Kitchen/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            return View();
        }

        public ActionResult GetKitchenOrder(int maBoPhanCheBien)
        {
            var boPhanCheBien = BoPhanCheBienBUS.LayBoPhanCheBienTheoMa(maBoPhanCheBien);
            if (boPhanCheBien==null) return RedirectToAction("Index", "Error");

            var listChiTietOrder = BoPhanCheBienBUS.LayDanhSachChiTietOrderCanCheBien(boPhanCheBien);
            if (listChiTietOrder == null) return RedirectToAction("Index", "Error");
            var listChiTietOrderKitchen = new List<ChiTietOrderKitchen>();
            foreach (var chiTietOrder in listChiTietOrder)
            {
                listChiTietOrderKitchen.Add(new ChiTietOrderKitchen
                                         {
                                             MaChiTietOrder = chiTietOrder.MaChiTietOrder,
                                             MaOrder = chiTietOrder.Order.MaOrder,
                                             TenKhuVuc = chiTietOrder.Order.Ban.KhuVuc.TenKhuVuc,
                                             TenBan = chiTietOrder.Order.Ban.TenBan,
                                             TenMonAn = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(chiTietOrder.MonAn.MaMonAn, SharedCode.GetCurrentLanguage(Session).MaNgonNgu).TenMonAn,
                                             GhiChu = chiTietOrder.GhiChu,
                                             TenDonViTinh = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(chiTietOrder.DonViTinh.MaDonViTinh, SharedCode.GetCurrentLanguage(Session).MaNgonNgu).TenDonViTinh,
                                             SoLuong = chiTietOrder.SoLuong,
                                             SoLuongDaCheBien = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).SoLuongDaCheBien,
                                             SoLuongDangCheBien = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).SoLuongDangCheBien,
                                             TenPhucVu = chiTietOrder.Order.TaiKhoan.TenTaiKhoan
                                         });
            }
            ViewData["listChiTietOrderKitchen"] = listChiTietOrderKitchen;
            return PartialView("KitchenOrder");
        }

        public ActionResult GetDialogCheBien(int maChiTietOrder)
        {
            if (!Request.IsAjaxRequest()) return RedirectToAction("Index", "Error");
            var chiTietOrder = ChiTietOrderBUS.LayChiTietOrder(maChiTietOrder);
            if (chiTietOrder==null) return new EmptyResult();
            int iSoLuongCheBienToiDa = chiTietOrder.SoLuong -
                                       ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).
                                           SoLuongDaCheBien -
                                       ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).
                                           SoLuongDangCheBien;
            ViewData["iSoLuongCheBienToiDa"] = iSoLuongCheBienToiDa;
            return PartialView("DialogCheBien");
        }

        [HttpPost]
        public bool PostCheBien(int maChiTietOrder, int soLuongCheBien)
        {
            if (!Request.IsAjaxRequest()) return false;
            var chiTietOrder = ChiTietOrderBUS.LayChiTietOrder(maChiTietOrder);
            if (chiTietOrder == null) return false;
            int iSoLuongCheBienToiDa = chiTietOrder.SoLuong -
                                       ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).
                                           SoLuongDaCheBien -
                                       ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).
                                           SoLuongDangCheBien;
            if (soLuongCheBien<=0 || soLuongCheBien>iSoLuongCheBienToiDa) return false;
            return true;
        }
    }
}
