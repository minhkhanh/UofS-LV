using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using System.IO;

namespace LocalServerDAO
{
    public class OrderDAO
    {
        public static List<Order> LayDanhSachOrder()
        {
            return ThucDonDienTu.DataContext.Orders.ToList();
        }

        public static Order LayOrder(int maOrder)
        {
            var temp = ThucDonDienTu.DataContext.Orders.Where(o => o.MaOrder == maOrder);
            if (temp.Count() > 0)
            {
                Order or = temp.First();
                return or;
            }
            return null;
        }

        public static List<Order> LayNhieuOrderChuaThanhToan(int maBan)
        {
            return ThucDonDienTu.DataContext.Orders.Where(o => o.Ban.MaBan == maBan && o.TinhTrang != 4).ToList();
        }

        public static Order ThemOrder(Order _order)
        {
            ThucDonDienTu.DataContext.Orders.InsertOnSubmit(_order);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();

            }
            catch(Exception e)
            {
                _order = null;
            }

            return _order;
        }

        public static bool SuaOrder(Order _order)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.Orders.Where(o => o.MaOrder == _order.MaOrder);
            if (temp.Count() > 0)
            {
                Order or = temp.First();
                or._maBan = _order._maBan;
                or._maTaiKhoan = _order._maTaiKhoan;
                or.TinhTrang = _order.TinhTrang;
            }

            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                result = true;
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }

        //public static bool KiemTraChuaCheBien(ChiTietOrder chiTietOrder)
        //{
        //    ThucDonDienTu.DataContext.c
        //}

        public static bool ChuyenBanJson(int maOrder, int maBanMoi)
        {
            var varOrder = ThucDonDienTu.DataContext.Orders.Where(o => o.TinhTrang != 4 && o.MaOrder == maOrder);
            var varBan = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBanMoi && b.TinhTrang == true);
            if (varOrder.Count() == 0 || varBan.Count() == 0)
                return false;

            Order order = varOrder.First();
            var varOrderBanCu = ThucDonDienTu.DataContext.Orders.Where(o => o.Ban.MaBan == order.Ban.MaBan && o.TinhTrang != 4);
            if (varOrderBanCu.Count() == 1)     // order chuyen di la order cuoi cung
            {
                var varBanCu = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh.MaBan == order.Ban.MaBan);
                if (varBanCu.Count() == 0)
                    return false;

                foreach (Ban b in varBanCu)
                {
                    b.Active = true;
                    b.BanChinh = null;
                }
            }

            Ban ban = varBan.First();
            ban.Active = false;
            if (ban.BanChinh == null)
                ban.BanChinh = ban;
                        
            order.Ban = ban.BanChinh;

            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static bool TachOrder(List<SplittingOrderItem> dsMaChiTiet)
        {
            List<ChiTietOrder> dsChiTietChuyen = new List<ChiTietOrder>();  // nhung chi tiet order chuyen toan bo
            List<ChiTietOrder> dsChiTietMoi = new List<ChiTietOrder>();    // nhung chi tiet order chuyen di, la mot phan cua chi tiet order cu
            List<ChiTietOrder> dsChiTietCu = new List<ChiTietOrder>();    // nhung chi tiet order goc, can update sau khi chuyen mot phan sang order khac
            foreach (SplittingOrderItem item in dsMaChiTiet)
            {
                int id = item.ItemId;
                var varChiTiet = ThucDonDienTu.DataContext.ChiTietOrders.Where(c => c.MaChiTietOrder == id);
                if (varChiTiet.Count() == 0)
                    return false;

                ChiTietOrder chiTietCu = varChiTiet.First();
                if (chiTietCu.SoLuong == item.QuantityToSplit)
                    dsChiTietChuyen.Add(varChiTiet.First());        // tach toan bo chi tiet order => tro chi tiet order sang mot order moi
                else if (chiTietCu.SoLuong > item.QuantityToSplit)
                {
                    // tach mot phan chi tiet order, di chuyen so luong

                    int slMoi = chiTietCu.SoLuong - item.QuantityToSplit;
                    int slChuyenDaCheBien = 0;
                    int slChuyenDangCheBien = 0;
                    int slCoDinh = chiTietCu.SoLuongDaCheBien + chiTietCu.SoLuongDangCheBien;
                    if (slCoDinh > slMoi)
                    {
                        if (chiTietCu.SoLuongDaCheBien >= slMoi)
                        {
                            // đưa số lượng đã chế biến vượt mức sang chi tiết order mới
                            slChuyenDaCheBien = chiTietCu.SoLuongDaCheBien - slMoi;
                            chiTietCu.SoLuongDaCheBien = slMoi;

                            // đưa toàn bộ số lượng đang chế biến sang chi tiết order mới
                            slChuyenDangCheBien = chiTietCu.SoLuongDangCheBien;
                            chiTietCu.SoLuongDangCheBien = 0;
                        }
                        else
                        {
                            chiTietCu.SoLuongDangCheBien = slMoi - chiTietCu.SoLuongDaCheBien;
                            slChuyenDangCheBien = slCoDinh - slMoi;

                            // chiTiet.SoLuongDaCheBien ko thay doi
                            // va slChuyenDaCheBien = 0
                        }
                    }

                    chiTietCu.SoLuong = slMoi;

                    // clone một số thuộc tính từ chi tiết cũ sang chi tiết mới
                    ChiTietOrder chiTietMoi = new ChiTietOrder();
                    chiTietMoi.BoPhanCheBien = chiTietCu.BoPhanCheBien;
                    chiTietMoi.DonViTinh = chiTietCu.DonViTinh;
                    chiTietMoi.GhiChu = chiTietCu.GhiChu;
                    chiTietMoi.MonAn = chiTietCu.MonAn;
                    chiTietMoi.SoLuong = item.QuantityToSplit;

                    chiTietMoi.SoLuongDaCheBien = slChuyenDaCheBien;
                    chiTietMoi.SoLuongDangCheBien = slChuyenDangCheBien;
                    chiTietMoi.TenTinhTrang = chiTietCu.TenTinhTrang;
                    chiTietMoi.TinhTrang = chiTietCu.TinhTrang;

                    dsChiTietMoi.Add(chiTietMoi);
                    dsChiTietCu.Add(chiTietCu);
                }
                else // (chiTiet.SoLuong < item.QuantityToSplit)
                    return false;       // ngoai le
            }

            if (dsChiTietChuyen.Count + dsChiTietCu.Count == 0)
                return false;

            MyLogger.Log("dsChiTietChuyen");
            foreach (ChiTietOrder ct in dsChiTietChuyen)
            {
                MyLogger.Log(ct.MaChiTietOrder + "," + ct.SoLuong);
            }
            MyLogger.Log("dsChiTietCu");
            foreach (ChiTietOrder ct in dsChiTietCu)
            {
                MyLogger.Log(ct.MaChiTietOrder + "," + ct.SoLuong);
            }
            MyLogger.Log("dsChiTietMoi");
            foreach (ChiTietOrder ct in dsChiTietMoi)
            {
                MyLogger.Log(ct.MaChiTietOrder + "," + ct.SoLuong);
            }

            Order orderCu;
            if (dsChiTietChuyen.Count > 0)
                orderCu = dsChiTietChuyen.ElementAt(0).Order;
            else
                orderCu = dsChiTietCu.ElementAt(0).Order;

            MyLogger.Log(orderCu.MaOrder.ToString());

            Order orderMoi = new Order();
            orderMoi.Ban = orderCu.Ban;
            orderMoi.TaiKhoan = orderCu.TaiKhoan;
            orderMoi.TinhTrang = orderCu.TinhTrang;

            orderMoi = OrderDAO.ThemOrder(orderMoi);
            if (orderMoi == null)
                return false;

            MyLogger.Log(orderMoi.MaOrder.ToString());

            foreach (ChiTietOrder ctc in dsChiTietChuyen)
                ctc.Order = orderMoi;

            ThucDonDienTu.DataContext.SubmitChanges();      // submit cho dsChiTietChuyen va dsChiTietCu

            foreach (ChiTietOrder ctt in dsChiTietMoi)
                ctt.Order = orderMoi;

            ChiTietOrderDAO.ThemNhieuChiTietOrder(dsChiTietMoi);

            return true;
        }
    }
}
