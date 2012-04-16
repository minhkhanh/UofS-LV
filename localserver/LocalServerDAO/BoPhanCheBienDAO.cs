using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class BoPhanCheBienDAO
    {
        public static List<BoPhanCheBien> LayDanhSachBoPhanCheBien()
        {
            return ThucDonDienTu.DataContext.BoPhanCheBiens.ToList();
        }
    }
}
