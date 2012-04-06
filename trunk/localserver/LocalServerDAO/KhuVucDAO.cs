using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuVucDAO
    {
        public static List<KhuVuc> LayDanhSachKhuVuc()
        {
            return ThucDonDienTu.DataContext.KhuVucs.ToList();
        }
    }
}
