using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class NhomTaiKhoanBUS
    {
        public static List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan()
        {
            return NhomTaiKhoanDAO.LayDanhSachNhomTaiKhoan();

        }
    }
}
