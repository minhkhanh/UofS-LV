using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class TiGiaDAO
    {
        public static List<TiGia> LayDanhSachTiGia()
        {
            return ThucDonDienTu.DataContext.TiGias.ToList();
        }
    }
}
