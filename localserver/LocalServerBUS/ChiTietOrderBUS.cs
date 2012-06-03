using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietOrderBUS
    {
        public static ChiTietOrder LayChiTietOrder(int maChiTietOrder)
        {
            return ChiTietOrderDAO.LayChiTietOrder(maChiTietOrder);
        }

        //public static ChiTietOrder LayChiTietOrderDetail(int maChiTietOrder)
        //{
        //    var ct = ChiTietOrderDAO.LayChiTietOrder(maChiTietOrder);
        //    var cto = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(maChiTietOrder);
        //    if (ct!=null && cto!=null)
        //    {
        //        ct.SoLuongDaCheBien = cto.SoLuongDaCheBien;
        //        ct.SoLuongDangCheBien = cto.SoLuongDangCheBien;
        //    }
        //    return ct;
        //}

        public static List<ChiTietOrder> LayNhieuChiTietOrder(int maOrder)
        {
            return ChiTietOrderDAO.LayNhieuChiTietOrder(maOrder);
        }

        public static List<ChiTietOrder> LayNhieuChiTietOrderChuaThanhToan(int maBan)
        {
            return ChiTietOrderDAO.LayNhieuChiTietOrderChuaThanhToan(maBan);
        }

        public static ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            return ChiTietOrderDAO.ThemChiTietOrder(_chiTietOrder);
        }

        public static List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder)
        {
            return ChiTietOrderDAO.ThemNhieuChiTietOrder(_listChiTietOrder);
        }

        public static bool SuaChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            return ChiTietOrderDAO.SuaChiTietOrder(_chiTietOrder);
        }

        // Lay ra cac ct Order chua thanh toan cua Ban
        // Thay doi TinhTrang = 4, tuc la xac nhan da thanh toan
        public static bool ThayDoiTinhTrangDaThanhToan(int maBan)
        {
            bool ketQua = true;

            // Thay doi Tinh Trang cua cac ct Order tuong ung
            List<ChiTietOrder> listChiTietOrder = ChiTietOrderBUS.LayNhieuChiTietOrderChuaThanhToan(maBan);
            foreach (ChiTietOrder ctOrder in listChiTietOrder)
            {
                ctOrder.TinhTrang = 4;
                if (!ChiTietOrderBUS.SuaChiTietOrder(ctOrder))
                {
                    ketQua = false;
                }
            }

            // Tach ban
            BanBUS.TachBan(maBan);

            return ketQua;

        }
    }
}
