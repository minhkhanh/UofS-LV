using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class DonViTinhBUS
    {
        public static List<DonViTinh> LayDanhSachDonViTinh()
        {
            return DonViTinhDAO.LayDanhSachDonViTinh();
        }

        public static DonViTinh LayDonViTinhTheoMa(int maDonViTinh)
        {
            return DonViTinhDAO.LayDonViTinhTheoMa(maDonViTinh);
        }

        public static bool Xoa(int maDonViTinh)
        {
            return DonViTinhDAO.Xoa(maDonViTinh);
        }

        public static bool Them(DonViTinh donViTinh)
        {
            return DonViTinhDAO.Them(donViTinh);
        }

        public static bool CapNhat(DonViTinh donViTinh)
        {
            return DonViTinhDAO.CapNhat(donViTinh);
        }
    }
}
