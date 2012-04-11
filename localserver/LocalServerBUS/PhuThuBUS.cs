using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class PhuThuBUS
    {
        public static List<PhuThu> LayDanhSachPhuThu()
        {
            return PhuThuDAO.LayDanhSachPhuThu();
        }
    }
}
