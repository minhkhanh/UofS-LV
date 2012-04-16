using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietDanhMucDaNgonNguBUS
    {
        public static ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu)
        {
            return ChiTietDanhMucDaNgonNguDAO.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);
        }

        public static List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu()
        {
            return ChiTietDanhMucDaNgonNguDAO.LayDanhSachChiTietDanhMucDaNgonNgu();
        }
    }
}
