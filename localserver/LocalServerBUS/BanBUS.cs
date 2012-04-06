using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class BanBUS
    {
        public static List<Ban> LayDanhSachBan()
        {
            return BanDAO.LayDanhSachBan();
        }

        public static List<Ban> LayDanhSachBan(int maKhuVuc)
        {
            return BanDAO.LayDanhSachBan(maKhuVuc);
        }
    }
}
