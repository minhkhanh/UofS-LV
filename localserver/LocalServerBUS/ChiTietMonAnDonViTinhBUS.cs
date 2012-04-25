using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietMonAnDonViTinhBUS
    {
        public static float LayDonGia(int maMonAn, int maDonViTinh)
        {
            return ChiTietMonAnDonViTinhDAO.LayDonGia(maMonAn, maDonViTinh);
        }

        public static ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh)
        {
            return ChiTietMonAnDonViTinhDAO.LayChiTietMonAnDonViTinh(maMonAn, maDonViTinh);
        }

        public static List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh()
        {
            return ChiTietMonAnDonViTinhDAO.LayDanhSachChiTietMonAnDonViTinh();
        }

        public static List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinhTheoMonAn(int maMonAn)
        {
            return ChiTietMonAnDonViTinhDAO.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(maMonAn);
        }
    }
}
