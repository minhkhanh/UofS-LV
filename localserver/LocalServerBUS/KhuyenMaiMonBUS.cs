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
    }
}
