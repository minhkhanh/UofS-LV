using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class PhuThuKhuVucBUS
    {
        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc()
        {
            return PhuThuKhuVucDAO.LayDanhSachPhuThuKhuVuc();
        }

        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu)
        {
            return PhuThuKhuVucDAO.LayDanhSachPhuThuKhuVucTheoMa(maPhuThu);
        }


    }
}
