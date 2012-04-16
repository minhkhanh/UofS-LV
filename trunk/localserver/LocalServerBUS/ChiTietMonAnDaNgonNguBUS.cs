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
    }
}
