using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietMonLienQuanBUS
    {
        public static ChiTietMonLienQuan LayChiTietMonLienQuan(int maMonAn, int maMonAnLienQuan)
        {
            return ChiTietMonLienQuanDAO.LayChiTietMonLienQuan(maMonAn, maMonAnLienQuan);
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan()
        {
            return ChiTietMonLienQuanDAO.LayDanhSachChiTietMonLienQuan();
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(int maMonAn)
        {
            return ChiTietMonLienQuanDAO.LayDanhSachChiTietMonLienQuan(maMonAn);
        }

        public static bool Xoa(ChiTietMonLienQuan chiTietMonLienQuan)
        {
            return ChiTietMonLienQuanDAO.Xoa(chiTietMonLienQuan);
        }

        public static bool Them(ChiTietMonLienQuan chiTietMonLienQuan)
        {
            return ChiTietMonLienQuanDAO.Them(chiTietMonLienQuan);
        }
    }
}
