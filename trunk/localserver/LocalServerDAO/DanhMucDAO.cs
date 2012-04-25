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

        public static DanhMuc LayDanhMuc(int maDanhMuc)
        {
            var temp = ThucDonDienTu.DataContext.DanhMucs.Where(d => d.MaDanhMuc == maDanhMuc);
            if (temp.Count() > 0)
            {
                DanhMuc dm = temp.First();
                return dm;
            }
            return null;
        }
    }
}
