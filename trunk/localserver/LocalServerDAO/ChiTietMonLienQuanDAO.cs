using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonLienQuanDAO
    {
        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan()
        {
            return ThucDonDienTu.DataContext.ChiTietMonLienQuans.ToList();
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(int maMonAn)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonLienQuans.Where(c => c.MonAn.MaMonAn == maMonAn);
            if (temp.Count() == 0)
                return null;
            return temp.ToList();
        }
    }
}
