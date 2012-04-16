using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietDanhMucBoPhanCheBienBUS
    {
        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBien()
        {
            return ChiTietDanhMucBoPhanCheBienDAO.LayDanhSachChiTietDanhMucBoPhanCheBien();
        }
    }
}
