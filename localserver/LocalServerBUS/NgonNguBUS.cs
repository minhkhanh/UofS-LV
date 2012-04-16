using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;
namespace LocalServerBUS
{
    public class NgonNguBUS
    {
        public static List<NgonNgu> LayDanhSachNgonNgu()
        {
            return NgonNguDAO.LayDanhSachNgonNgu();
        }
    }
}
