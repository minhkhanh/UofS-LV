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
    }
}
