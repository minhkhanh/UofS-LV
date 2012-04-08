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

        public static bool TachBan(int maBan)
        {
            return BanDAO.TachBan(maBan);
        }

        public static bool GhepBan(RequestGhepBan request)
        {
            if (request.MaBanPhuList.Count() == 0) return false;
            return BanDAO.GhepBan(request);
        }
    }
}
