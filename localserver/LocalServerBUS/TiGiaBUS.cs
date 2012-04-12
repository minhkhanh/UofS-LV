using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class TiGiaBUS
    {
        public static List<TiGia> LayDanhSachTiGia()
        {
            return TiGiaDAO.LayDanhSachTiGia();
        }
    }
}
