﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

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
    }
}
