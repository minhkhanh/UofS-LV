using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class ThucDonDienTuBUS
    {
        public static void KhoiTao(string strConn)
        {
            ThucDonDienTu.KhoiTao(strConn);
        }
    }
}
