using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class PhuThuDAO
    {
        public static List<PhuThu> LayDanhSachPhuThu()
        {
            return ThucDonDienTu.DataContext.PhuThus.ToList();
        }
    }
}
