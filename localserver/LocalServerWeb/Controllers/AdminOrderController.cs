﻿using System;
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
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class AdminOrderController : ManagerBaseController
    {
        public ActionResult Index(string page)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 0);

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<Order> pageListOrder = OrderBUS.LayDanhSachOrder().AsQueryable().ToPagedList(_page, 10);
            ViewData["listOrder"] = pageListOrder;
            ViewData["_page"] = _page;

            return View(pageListOrder);
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

                    if (ct.TinhTrang == 0)
                        viewModel.TenTinhTrang = AdminOrderString.WaitProcessing;
                    else if (ct.TinhTrang == 1)
                        viewModel.TenTinhTrang = AdminOrderString.Processing;
                    else if (ct.TinhTrang == 2)
                        viewModel.TenTinhTrang = AdminOrderString.LockProcessing;
                    else if (ct.TinhTrang == 3)
                        viewModel.TenTinhTrang = AdminOrderString.FinishProcessing;
                    else if (ct.TinhTrang == 4)
                        viewModel.TenTinhTrang = AdminOrderString.Paid;

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