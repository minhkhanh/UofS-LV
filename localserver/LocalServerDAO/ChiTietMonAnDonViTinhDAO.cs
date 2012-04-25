using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonAnDonViTinhDAO
    {
        public static float LayDonGia(int maMonAn, int maDonViTinh)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.Where(c => c.MonAn.MaMonAn == maMonAn && c.DonViTinh.MaDonViTinh == maDonViTinh);
            if (temp.Count() > 0)
            {
                ChiTietMonAnDonViTinh ct = temp.First();
                return ct.DonGia;
            }
            return -1;
        }

        public static ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.Where(c => c.MonAn.MaMonAn == maMonAn && c.DonViTinh.MaDonViTinh == maDonViTinh);
            if (temp.Count() > 0)
            {
                ChiTietMonAnDonViTinh ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh()
        {
            return ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.ToList();
        }

        public static List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinhTheoMonAn(int maMonAn)
        {
            return ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.Where(c => c.MonAn.MaMonAn == maMonAn).ToList();
        }
    }
}
