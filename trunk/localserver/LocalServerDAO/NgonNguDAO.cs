using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class NgonNguDAO
    {
        public static List<NgonNgu> LayDanhSachNgonNgu()
        {
            return ThucDonDienTu.DataContext.NgonNgus.ToList();
        }
    }
}
