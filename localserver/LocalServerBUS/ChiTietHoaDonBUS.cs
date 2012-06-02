using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;


namespace LocalServerBUS
{
    public class ChiTietHoaDonBUS
    {
        public static ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon)
        {
            return ChiTietHoaDonDAO.LayChiTietHoaDon(maChiTietHoaDon);
        }

        public static List<ChiTietHoaDon> LayNhieuChiTietHoaDon(int maHoaDon)
        {
            return ChiTietHoaDonDAO.LayNhieuChiTietHoaDon(maHoaDon);
        }

        public static ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.ThemChiTietHoaDon(_chiTietHoaDon);
        }

        public static List<ChiTietHoaDon> ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            // Khi thanh toan xong, thay doi TinhTrang = 4 cua cac ct Order tuong ung
            if (ChiTietHoaDonDAO.ThemNhieuChiTietHoaDon(_listChiTietHoaDon) != null)
            {
                int maBan = 0;
                if(_listChiTietHoaDon.Count > 0)
                    maBan = _listChiTietHoaDon[0].HoaDon.Ban.MaBan;
                List<ChiTietOrder> listChiTietOrder = ChiTietOrderBUS.LayNhieuChiTietOrderChuaThanhToan(maBan);
                foreach (ChiTietOrder ctOrder in listChiTietOrder)
                {
                    ctOrder.TinhTrang = 4;
                    ChiTietOrderBUS.SuaChiTietOrder(ctOrder);
                }
            }

            return _listChiTietHoaDon;
        }

        public static bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.SuaChiTietHoaDon(_chiTietHoaDon);
        }
    }
}
