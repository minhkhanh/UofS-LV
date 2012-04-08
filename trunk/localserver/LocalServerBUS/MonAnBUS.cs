using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class MonAnBUS
    {
        public static List<MonAn> LayDanhSachMonAn()
        {
            return MonAnDAO.LayDanhSachMonAn();
        }

        public static List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
        {
            return MonAnDAO.LayDanhSachMonAnTheoDanhMuc(maDanhMuc);
        }

        public static MonAn LayMonAn(int maMonAn)
        {
            return MonAnDAO.LayMonAn(maMonAn);
        }
    }
}
