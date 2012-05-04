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
    }
}
