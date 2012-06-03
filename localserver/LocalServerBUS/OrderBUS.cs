using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class OrderBUS
    {
        public static List<Order> LayDanhSachOrder()
        {
            return OrderDAO.LayDanhSachOrder();
        }

        public static Order LayOrder(int maOrder)
        {
            return OrderDAO.LayOrder(maOrder);
        }

        public static Order ThemOrder(Order _order)
        {
            return OrderDAO.ThemOrder(_order);
        }

        public static bool SuaOrder(Order _order)
        {
            return OrderDAO.SuaOrder(_order);
        }

        public static List<ChiTietOrder> LapOrder(int maTaiKhoan, int maBan, List<ChiTietOrder> _listChiTietOrder)
        {
            Order order = new Order();
            order._maBan = maBan;
            order._maTaiKhoan = maTaiKhoan;
            order.TinhTrang = 0;

            // Dau tien, them Order moi
            if (OrderBUS.ThemOrder(order) == null)
            {
                return null;
            }

            // Chi tiet order co Ma order vua them
            // Cap nhat Bo Phan Che Bien cho ct order
            foreach (ChiTietOrder ct in _listChiTietOrder)
            {
                ct._maOrder = order.MaOrder;

                int maMonAn = ct._maMonAn ?? 0;
                MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
                if (monAn != null)
                {
                    BoPhanCheBien boPhanCheBien = ChiTietDanhMucBoPhanCheBienBUS.LayBoPhanCheBien(monAn.DanhMuc.MaDanhMuc);
                    ct._maBoPhanCheBien = (boPhanCheBien != null) ? boPhanCheBien.MaBoPhanCheBien : 0;
                }
            }

            if (ChiTietOrderBUS.ThemNhieuChiTietOrder(_listChiTietOrder) == null)
            {
                return null;
            }

            return _listChiTietOrder;


        }
    }
}
