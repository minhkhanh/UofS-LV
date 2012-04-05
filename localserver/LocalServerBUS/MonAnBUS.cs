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
    }
}
