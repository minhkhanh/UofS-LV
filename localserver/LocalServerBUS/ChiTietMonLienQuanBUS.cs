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
        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan()
        {
            return ChiTietMonLienQuanDAO.LayDanhSachChiTietMonLienQuan();
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(int maMonAn)
        {
            return ChiTietMonLienQuanDAO.LayDanhSachChiTietMonLienQuan(maMonAn);
        }
    }
}
