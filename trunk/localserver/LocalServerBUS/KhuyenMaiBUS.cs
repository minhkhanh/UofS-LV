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
    }
}
