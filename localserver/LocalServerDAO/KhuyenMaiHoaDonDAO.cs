using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiHoaDonDAO
    {
        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiHoaDons.ToList();
        }

        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiHoaDons.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }
    }
}
