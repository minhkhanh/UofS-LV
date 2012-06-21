using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class KhuyenMaiBUS
    {
        public static List<KhuyenMai> LayDanhSachKhuyenMai()
        {
            return KhuyenMaiDAO.LayDanhSachKhuyenMai();
        }

        public static KhuyenMai LayKhuyenMai(int maKhuyenMai)
        {
            return KhuyenMaiDAO.LayKhuyenMai(maKhuyenMai);
        }

        public static bool Xoa(int maKhuyenMai)
        {
            return KhuyenMaiDAO.Xoa(maKhuyenMai);
        }

        public static bool Them(KhuyenMai khuyenMai)
        {
            return KhuyenMaiDAO.Them(khuyenMai);
        }

        public static bool CapNhat(KhuyenMai khuyenMai)
        {
            return KhuyenMaiDAO.CapNhat(khuyenMai);
        }

        public static List<KhuyenMai> LayDanhSachKhuyenMaiApDungJson(int maOrder)
        {
            return KhuyenMaiDAO.LayDanhSachKhuyenMaiApDung(maOrder);
        }
    }
}
