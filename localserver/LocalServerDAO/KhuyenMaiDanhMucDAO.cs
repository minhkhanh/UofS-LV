using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiDanhMucDAO
    {
        public static List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiDanhMucs.ToList();
        }

        public static List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiDanhMucs.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }
    }
}
