﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietHoaDonDAO
    {
        public static ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietHoaDons.Where(m => m.MaChiTietHoaDon == maChiTietHoaDon);
            if (temp.Count() > 0)
            {
                ChiTietHoaDon ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietHoaDon> LayNhieuChiTietHoaDon(int maHoaDon)
        {
            return ThucDonDienTu.DataContext.ChiTietHoaDons.Where(m => m.HoaDon.MaHoaDon == maHoaDon).ToList();
        }

        public static ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            ThucDonDienTu.DataContext.ChiTietHoaDons.InsertOnSubmit(_chiTietHoaDon);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
            }
            catch (Exception e)
            {
                _chiTietHoaDon = null;
            }

            return _chiTietHoaDon;
        }

        public static List<ChiTietHoaDon> ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            ThucDonDienTu.DataContext.ChiTietHoaDons.InsertAllOnSubmit(_listChiTietHoaDon);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
            }
            catch (Exception e)
            {
                _listChiTietHoaDon = null;
            }

            return _listChiTietHoaDon;
        }

        public static bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietHoaDons.Where(c => c.MaChiTietHoaDon == _chiTietHoaDon.MaChiTietHoaDon);
            if (temp.Count() > 0)
            {
                ChiTietHoaDon ct = temp.First();
                ct._maHoaDon = _chiTietHoaDon._maHoaDon;
                ct._maKhuyenMai = _chiTietHoaDon._maKhuyenMai;
                ct._maMonAn = _chiTietHoaDon._maMonAn;
                ct._maDonViTinh = _chiTietHoaDon._maDonViTinh;


                ct.DonGiaLuuTru = _chiTietHoaDon.DonGiaLuuTru;
                ct.GiaTriKhuyenMaiLuuTru = _chiTietHoaDon.GiaTriKhuyenMaiLuuTru;
                ct.SoLuong = _chiTietHoaDon.SoLuong;
                ct.ThanhTien = _chiTietHoaDon.ThanhTien;
            }

            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                result = true;
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }
    }
}
