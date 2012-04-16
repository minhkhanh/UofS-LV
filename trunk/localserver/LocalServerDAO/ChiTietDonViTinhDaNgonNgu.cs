using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDonViTinhDaNgonNguDAO
    {
        public static ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.Where(c => c.DonViTinh.MaDonViTinh == maDonViTinh && c.NgonNgu.MaNgonNgu == maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietDonViTinhDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu()
        {
            return ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.ToList();
        }
    }
}
