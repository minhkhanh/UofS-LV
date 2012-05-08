using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class KhuVucBUS
    {
        public static List<KhuVuc> LayDanhSachKhuVuc()
        {
            return KhuVucDAO.LayDanhSachKhuVuc();
        }

        public static KhuVuc LayKhuVuc(int maKhuVuc)
        {
            return KhuVucDAO.LayKhuVuc(maKhuVuc);
        }

        public static bool Xoa(int maKhuVuc)
        {
            return KhuVucDAO.Xoa(maKhuVuc);
        }

        public static bool Them(KhuVuc khuVuc)
        {
            return KhuVucDAO.Them(khuVuc);
        }

        public static bool CapNhat(KhuVuc khuVuc)
        {
            return KhuVucDAO.CapNhat(khuVuc);
        }
    }
}
