using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class HoaDonDAO
    {
        public static List<HoaDon> LayDanhSachHoaDon()
        {
            return ThucDonDienTu.DataContext.HoaDons.ToList();
        }

        public static HoaDon LayHoaDon(int maHoaDon)
        {
            var temp = ThucDonDienTu.DataContext.HoaDons.Where(h => h.MaHoaDon == maHoaDon);
            if (temp.Count() > 0)
            {
                HoaDon hd = temp.First();
                return hd;
            }
            return null;
        }

        public static HoaDon ThemHoaDon(HoaDon _hoaDon)
        {
            ThucDonDienTu.DataContext.HoaDons.InsertOnSubmit(_hoaDon);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();

            }
            catch (Exception e)
            {
                _hoaDon = null;
            }

            return _hoaDon;
        }

        public static bool SuaHoaDon(HoaDon _hoaDon)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.HoaDons.Where(h => h.MaHoaDon == _hoaDon.MaHoaDon);
            if (temp.Count() > 0)
            {
                HoaDon hd = temp.First();
                hd._maBanChinh = _hoaDon._maBanChinh;
                hd._maPhuThu = _hoaDon._maPhuThu;
                hd._maTaiKhoan = _hoaDon._maTaiKhoan;
                hd.MoTaBanGhep = _hoaDon.MoTaBanGhep;
                hd.ThoiDiemLap = _hoaDon.ThoiDiemLap;
                hd.TongTien = _hoaDon.TongTien;
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
