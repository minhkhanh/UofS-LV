using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class DanhMucDAO
    {
        public static List<DanhMuc> LayDanhSachDanhMuc()
        {
            return ThucDonDienTu.DataContext.DanhMucs.ToList();
        }
    }
}
