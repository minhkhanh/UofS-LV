using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class BoPhanCheBienBUS
    {
        public static List<BoPhanCheBien> LayDanhSachBoPhanCheBien()
        {
            return BoPhanCheBienDAO.LayDanhSachBoPhanCheBien();
        }
    }
}
