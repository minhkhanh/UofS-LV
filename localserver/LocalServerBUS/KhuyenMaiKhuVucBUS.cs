using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class KhuyenMaiKhuVucBUS
    {
        public static List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc()
        {
            return KhuyenMaiKhuVucDAO.LayDanhSachKhuyenMaiKhuVuc();
        }

        public static List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai)
        {
            return KhuyenMaiKhuVucDAO.LayDanhSachKhuyenMaiKhuVucTheoMa(maKhuyenMai);
        }


    }
}
