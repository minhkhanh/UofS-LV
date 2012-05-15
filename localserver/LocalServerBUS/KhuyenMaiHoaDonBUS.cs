using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class KhuyenMaiHoaDonBUS
    {
        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon()
        {
            return KhuyenMaiHoaDonDAO.LayDanhSachKhuyenMaiHoaDon();
        }

        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai)
        {
            return KhuyenMaiHoaDonDAO.LayDanhSachKhuyenMaiHoaDonTheoMa(maKhuyenMai);
        }

        public static KhuyenMaiHoaDon LayKhuyenMaiHoaDon(int maKhuyenMai)
        {
            return KhuyenMaiHoaDonDAO.LayKhuyenMaiHoaDon(maKhuyenMai);
        }

        public static bool CapNhat(KhuyenMaiHoaDon khuyenMaiHoaDon)
        {
            return KhuyenMaiHoaDonDAO.CapNhat(khuyenMaiHoaDon);
        }

        public static bool Them(KhuyenMaiHoaDon khuyenMaiHoaDon)
        {
            return KhuyenMaiHoaDonDAO.Them(khuyenMaiHoaDon);
        }
    }
}
