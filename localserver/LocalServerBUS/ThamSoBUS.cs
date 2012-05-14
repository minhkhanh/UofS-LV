using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ThamSoBUS
    {
        public static List<ThamSo> LayDanhSachThamSo()
        {
            return ThamSoDAO.LayDanhSachThamSo();
        }

        public static ThamSo LayThamSo(string ten)
        {
            return ThamSoDAO.LayThamSo(ten);
        }

        public static bool Them(ThamSo thamSo)
        {
            return ThamSoDAO.Them(thamSo);
        }

        public static bool CapNhat(ThamSo thamSo)
        {
            return ThamSoDAO.CapNhat(thamSo);
        }
    }
}
