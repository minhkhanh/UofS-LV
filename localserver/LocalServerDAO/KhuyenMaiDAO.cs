using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class KhuyenMaiDAO
    {
        public static List<KhuyenMai> LayDanhSachKhuyenMai()
        {
            return ThucDonDienTu.DataContext.KhuyenMais.ToList();
        }
    }
}
