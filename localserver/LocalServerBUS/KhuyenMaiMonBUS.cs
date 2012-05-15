using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class KhuyenMaiMonBUS
    {
        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon()
        {
            return KhuyenMaiMonDAO.LayDanhSachKhuyenMaiMon();
        }

        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai)
        {
            return KhuyenMaiMonDAO.LayDanhSachKhuyenMaiMonTheoMa(maKhuyenMai);
        }

        public static KhuyenMaiMon LayKhuyenMaiMon(int maKhuyenMai, int maMon)
        {
            return KhuyenMaiMonDAO.LayKhuyenMaiMon(maKhuyenMai, maMon);
        }

        public static bool Xoa(KhuyenMaiMon khuyenMaiMon)
        {
            return KhuyenMaiMonDAO.Xoa(khuyenMaiMon);
        }

        public static bool Them(KhuyenMaiMon khuyenMaiMon)
        {
            return KhuyenMaiMonDAO.Them(khuyenMaiMon);
        }
    }
}
