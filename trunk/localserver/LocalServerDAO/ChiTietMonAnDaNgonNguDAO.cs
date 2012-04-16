using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonAnDaNgonNguDAO
    {
        public static ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.Where(c => c.MonAn.MaMonAn == maMonAn && c.NgonNgu.MaNgonNgu == maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietMonAnDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu()
        {
            return ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.ToList();
        }
    }
}
