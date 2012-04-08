using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDanhMucDaNgonNguDAO
    {
        public static ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.Where(c => c.DanhMuc.MaDanhMuc==maDanhMuc && c.NgonNgu.MaNgonNgu==maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietDanhMucDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }
    }
}
