using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietMonAnDaNgonNguBUS
    {
        public static ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu)
        {
            return ChiTietMonAnDaNgonNguDAO.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
        }

        public static List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu()
        {
            return ChiTietMonAnDaNgonNguDAO.LayDanhSachChiTietMonAnDaNgonNgu();
        }

        public static List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNguTheMonAn(MonAn monAn)
        {
            return ChiTietMonAnDaNgonNguDAO.LayDanhSachChiTietMonAnDaNgonNguTheMonAn(monAn);
        }

        public static bool Xoa(ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu)
        {
            return ChiTietMonAnDaNgonNguDAO.Xoa(chiTietMonAnDaNgonNgu);
        }
    }
}
