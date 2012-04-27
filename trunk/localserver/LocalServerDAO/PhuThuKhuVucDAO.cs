using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class PhuThuKhuVucDAO
    {
        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc()
        {
            return ThucDonDienTu.DataContext.PhuThuKhuVucs.ToList();
        }

        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu)
        {
            return ThucDonDienTu.DataContext.PhuThuKhuVucs.Where(p => p.PhuThu.MaPhuThu == maPhuThu).ToList();
        }
    }
}
