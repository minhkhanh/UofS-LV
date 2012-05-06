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

        public static ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.ThemChiTietHoaDon(_chiTietHoaDon);
        }

        public static List<ChiTietHoaDon> ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            return ChiTietHoaDonDAO.ThemNhieuChiTietHoaDon(_listChiTietHoaDon);
        }

        public static bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.SuaChiTietHoaDon(_chiTietHoaDon);
        }
    }
}
