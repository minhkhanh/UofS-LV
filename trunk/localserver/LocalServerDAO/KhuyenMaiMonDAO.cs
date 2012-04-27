using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiMonDAO
    {
        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiMons.ToList();
        }

        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiMons.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }
    }
}
