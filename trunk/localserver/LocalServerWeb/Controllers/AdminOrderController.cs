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
    public class AdminOrderController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            ViewData["listOrder"] = OrderBUS.LayDanhSachOrder();
            return View();
        }

        public ActionResult OrderDetail(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);
            if(id == null || id < 1)
                return View();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            List<OrderDetailViewModel> viewModels = new List<OrderDetailViewModel>();

            List<ChiTietOrder> listChiTietOrder = ChiTietOrderBUS.LayNhieuChiTietOrder(id??1);
            foreach (ChiTietOrder ct in listChiTietOrder)
            {
                try
                {
                    ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(ct.MonAn.MaMonAn, maNgonNgu);

                    OrderDetailViewModel viewModel = new OrderDetailViewModel();
                    viewModel.MaMonAn = ct.MonAn.MaMonAn;
                    viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
                    viewModel.SoLuong = ct.SoLuong;
                    viewModel.MaBoPhanCheBien = ct.BoPhanCheBien.MaBoPhanCheBien;
                    viewModel.TenBoPhanCheBien = ct.BoPhanCheBien.TenBoPhan;
                    viewModel.GhiChu = ct.GhiChu;
                    viewModel.CoThongTinHuy = false;

                    ChiTietHuyOrder ctHuyOrder = ChiTietHuyOrderBUS.LayChiTietHuyOrder(ct.MaChiTietOrder);
                    if (ctHuyOrder != null)
                    {
                        viewModel.CoThongTinHuy = true;
                        viewModel.SoLuongChoPhep = ctHuyOrder.SoLuongChoPhep;
                        viewModel.SoLuongYeuCau = ctHuyOrder.SoLuongYeuCau;
                        viewModel.TinhTrangHuy = ctHuyOrder.TinhTrangHuy ? AdminOrderString.CancelStatusYes : AdminOrderString.CancelStatusNo;
                    }

                    viewModels.Add(viewModel);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                }
                
            }

            ViewData["listChiTietOrderViewModel"] = viewModels;
            return View();
        }

       
    }
}