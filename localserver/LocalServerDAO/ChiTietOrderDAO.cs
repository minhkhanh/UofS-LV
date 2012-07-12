using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietOrderDAO
    {
        public static ChiTietOrder LayChiTietOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietOrder ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietOrder> LayNhieuChiTietOrder(int maOrder)
        {
            return ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.Order.MaOrder == maOrder).ToList();
        }

        public static List<ChiTietOrder> LayNhieuChiTietOrderChuaThanhToan(int maBan)
        {
            return ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.TinhTrang != 4 && m.Order.Ban.MaBan == maBan).ToList();
        }

        public static ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            ThucDonDienTu.DataContext.ChiTietOrders.InsertOnSubmit(_chiTietOrder);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
            }
            catch (Exception e)
            {
                _chiTietOrder = null;
            }

            return _chiTietOrder;
        }

        public static List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder)
        {
            ThucDonDienTu.DataContext.ChiTietOrders.InsertAllOnSubmit(_listChiTietOrder);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
            }
            catch (Exception e)
            {
                _listChiTietOrder = null;
            }

            return _listChiTietOrder;
        }

        public static bool SuaChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietOrders.Where(c => c.MaChiTietOrder == _chiTietOrder.MaChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietOrder ct = temp.First();
                ct._maBoPhanCheBien = _chiTietOrder._maBoPhanCheBien;
                ct._maMonAn = _chiTietOrder._maMonAn;
                ct._maOrder = _chiTietOrder._maOrder;
                ct._maDonViTinh = _chiTietOrder._maDonViTinh;

                ct.GhiChu = _chiTietOrder.GhiChu;
                ct.TinhTrang = _chiTietOrder.TinhTrang;
                ct.SoLuong = _chiTietOrder.SoLuong;
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

        public static List<ChiTietOrder> LayDanhSachChiTietOrderChuaThanhToan(int maOrder)
        {
            return ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.TinhTrang != 4 && m.Order.MaOrder == maOrder).ToList();
        }

        public static List<ChiTietOrder> LayDanhSachChiTietOrderJson(int maOrder)
        {
            return ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.SoLuong != 0 && m.Order.MaOrder == maOrder).ToList();
        }

        public static bool SuaChiTietOrderJson(ChiTietOrder holder)
        {
            var varChiTiet = ThucDonDienTu.DataContext.ChiTietOrders.Where(c => c.MaChiTietOrder == holder.MaChiTietOrder);
            if (varChiTiet.Count() == 0)
                return false;       // ct order nay khong ton tai

            ChiTietOrder ctOrder = varChiTiet.First();
            if (ctOrder.Order.TinhTrang == 4)
                return false;       // order nay da thanh toan

            ctOrder.GhiChu = holder.GhiChu;

            bool result = true;
            if (holder.SoLuong >= ctOrder.SoLuongDaCheBien + ctOrder.SoLuongDangCheBien)
                ctOrder.SoLuong = holder.SoLuong;
            else
                result = false;     // khong thay doi duoc so luong

            ThucDonDienTu.DataContext.SubmitChanges();

            return result;
        }

        public static bool CapNhatHuy(int maChiTietOrder, int soLuongYeuCauHuy, string ghiChu)
        {
            ChiTietOrder ctOrder = LayChiTietOrder(maChiTietOrder);
            // Khong ton tai
            // hoac da thanh toan
            if (ctOrder == null || ctOrder.TinhTrang == 4)
                return false;

            ctOrder.SoLuong -= soLuongYeuCauHuy;
            ctOrder.GhiChu = ghiChu;

            ThucDonDienTu.DataContext.SubmitChanges();

            return true;
        }

        public static bool CapNhatKhoa(int maChiTietOrder, int soLuongChuaDungToi)
        {
            ChiTietOrder ctOrder = LayChiTietOrder(maChiTietOrder);
            // Khong ton tai
            // hoac da thanh toan
            if (ctOrder == null || ctOrder.TinhTrang == 4)
                return false;

            ctOrder.SoLuong -= soLuongChuaDungToi;
            // Doi trang thai sang lam xong neu da xong
            ChiTietCheBienOrder ctCheBien = ChiTietCheBienOrderDAO.LayChiTietCheBienOrder(ctOrder.MaChiTietOrder);
            if (ctCheBien!=null && ctCheBien.SoLuongDangCheBien == 0)
            {
                ctOrder.TinhTrang = 3;
            }            

            ThucDonDienTu.DataContext.SubmitChanges();

            return true;
        }


        //public static int LaySoLuongChuaCheBien(int maChiTiet)
        //{
        //    var varChiTiet = ThucDonDienTu.DataContext.ChiTietOrders.Where(c => c.MaChiTietOrder == maChiTiet);
        //    if (varChiTiet.Count() == 0)
        //        return -1;       // ct order nay khong ton tai

        //    ChiTietOrder ctOrder = varChiTiet.First();
        //    if (ctOrder.Order.TinhTrang == 4)
        //        return -1;       // order nay da thanh toan
           
        //    return ctOrder.SoLuong - ctOrder.SoLuongDaCheBien - ctOrder.SoLuongDangCheBien;
        //}
    }
}
