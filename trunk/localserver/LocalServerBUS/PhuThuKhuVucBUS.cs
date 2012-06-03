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

        public static PhuThuKhuVuc LayPhuThuKhuVuc(int maPhuThu, int maKhuVuc)
        {
            return PhuThuKhuVucDAO.LayPhuThuKhuVuc(maPhuThu, maKhuVuc);
        }

        public static PhuThu LayPhuThu(int maKhuVuc)
        {
            return PhuThuKhuVucDAO.LayPhuThu(maKhuVuc);
        }

        public static bool Xoa(PhuThuKhuVuc phuThuKhuVuc)
        {
            return PhuThuKhuVucDAO.Xoa(phuThuKhuVuc);
        }

        public static bool Them(PhuThuKhuVuc phuThuKhuVuc)
        {
            return PhuThuKhuVucDAO.Them(phuThuKhuVuc);
        }
    }
}
