using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;


namespace LocalServerBUS
{
    public class HoaDonBUS
    {
        public static List<HoaDon> LayDanhSachHoaDon()
        {
            return HoaDonDAO.LayDanhSachHoaDon();
        }

        public static HoaDon LayHoaDon(int maHoaDon)
        {
            return HoaDonDAO.LayHoaDon(maHoaDon);
        }

        public static HoaDon ThemHoaDon(HoaDon _hoaDon)
        {
            return HoaDonDAO.ThemHoaDon(_hoaDon);
        }

        public static bool SuaHoaDon(HoaDon _hoaDon)
        {
            return HoaDonDAO.SuaHoaDon(_hoaDon);
        }
    }
}
