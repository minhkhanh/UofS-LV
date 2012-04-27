using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class KhuyenMaiDanhMucBUS
    {
        public static List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc()
        {
            return KhuyenMaiDanhMucDAO.LayDanhSachKhuyenMaiDanhMuc();
        }

        public static List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai)
        {
            return KhuyenMaiDanhMucDAO.LayDanhSachKhuyenMaiDanhMucTheoMa(maKhuyenMai);
        }
    }
}
