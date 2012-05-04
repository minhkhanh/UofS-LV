using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class DonViTinhDAO
    {
        public static List<DonViTinh> LayDanhSachDonViTinh()
        {
            return ThucDonDienTu.DataContext.DonViTinhs.ToList();
        }

        public static DonViTinh LayDonViTinhTheoMa(int maDonViTinh)
        {
            var tmp = ThucDonDienTu.DataContext.DonViTinhs.Where(d => d.MaDonViTinh == maDonViTinh);
            if (tmp.Count() == 0) return null;
            return tmp.First();
        }
    }
}
