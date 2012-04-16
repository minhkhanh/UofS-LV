using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDanhMucBoPhanCheBienDAO
    {
        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBien()
        {
            return ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.ToList();
        }
    }
}
