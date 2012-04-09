using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonAnDonViTinhDAO
    {
        public static int LayDonGia(int maMonAn, int maDonViTinh)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.Where(c => c.MonAn.MaMonAn == maMonAn && c.DonViTinh.MaDonViTinh == maDonViTinh);
            if (temp.Count() > 0)
            {
                ChiTietMonAnDonViTinh ct = temp.First();
                return ct.DonGia;
            }
            return -1;
        }
    }
}
