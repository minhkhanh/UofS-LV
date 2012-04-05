using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class MonAnDAO
    {
        public static List<MonAn> LayDanhSachMonAn()
        {
            return ThucDonDienTu.DataContext.MonAns.ToList();
            
        }
    }
}
