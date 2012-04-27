using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiKhuVucDAO
    {
        public static List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiKhuVucs.ToList();
        }

        public static List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiKhuVucs.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }
    }
}
