﻿using System;
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
        public static NgonNgu LayNgonNguTheoMa(int maNgonNgu)
        {
            return NgonNguDAO.LayNgonNguTheoMa(maNgonNgu);
        }

        public static NgonNgu LayNgonNguTheoKiHieu(string kiHieu)
        {
            return NgonNguDAO.LayNgonNguTheoKiHieu(kiHieu);
        }

        public static bool Xoa(int maNgonNgu)
        {
            return NgonNguDAO.Xoa(maNgonNgu);
        }

        public static bool Them(NgonNgu ngonNgu)
        {
            return NgonNguDAO.Them(ngonNgu);
        }

        public static bool CapNhat(NgonNgu ngonNgu)
        {
            return NgonNguDAO.CapNhat(ngonNgu);
        }



    }
}
