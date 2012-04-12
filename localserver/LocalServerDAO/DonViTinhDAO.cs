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
    }
}
