using System;
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
