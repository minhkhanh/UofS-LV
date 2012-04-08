using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class DanhMucBUS
    {
        public static List<DanhMuc> LayDanhSachDanhMuc()
        {
            return DanhMucDAO.LayDanhSachDanhMuc();
        }
    }
}
