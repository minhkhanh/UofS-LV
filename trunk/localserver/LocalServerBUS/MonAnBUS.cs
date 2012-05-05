﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class MonAnBUS
    {

        public static List<MonAn> LayDanhSachMonAn()
        {
            return MonAnDAO.LayDanhSachMonAn();
        }

        public static List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
        {
            return MonAnDAO.LayDanhSachMonAnTheoDanhMuc(maDanhMuc);
        }

        public static MonAn LayMonAn(int maMonAn)
        {
            return MonAnDAO.LayMonAn(maMonAn);
        }

        public static bool ThemMonAn(MonAn monAn)
        {
            return MonAnDAO.ThemMonAn(monAn);
        }

        public static bool CapNhatMonAn(MonAn monAn)
        {
            return MonAnDAO.CapNhapMonAn(monAn);
        }

        public static List<DonViTinh> LayDanhSachDonViTinhChuaCoTheoNgonNgu(MonAn monAn, NgonNgu ngonNgu)
        {
            var tmp = MonAnDAO.LayDanhSachDonViTinhChuaCoTheoNgonNgu(monAn);
            foreach (var donViTinh in tmp)
            {
                donViTinh.TenDonViTinh =
                    ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(donViTinh.MaDonViTinh, ngonNgu.MaNgonNgu).
                        TenDonViTinh;
            }
            return tmp;
        }
    }
}