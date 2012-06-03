using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;


namespace LocalServerBUS
{
    public class ChiTietHoaDonBUS
    {
        public static ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon)
        {
            return ChiTietHoaDonDAO.LayChiTietHoaDon(maChiTietHoaDon);
        }

        public static List<ChiTietHoaDon> LayNhieuChiTietHoaDon(int maHoaDon)
        {
            return ChiTietHoaDonDAO.LayNhieuChiTietHoaDon(maHoaDon);
        }

        public static ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.ThemChiTietHoaDon(_chiTietHoaDon);
        }

        public static int ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            // ketQua
            // 0: OK
            // 1: sai o B1
            // 2: sai o B2
            int ketQua = 0;

            // B1: Them nhieu ct Hoa don
            if (ChiTietHoaDonDAO.ThemNhieuChiTietHoaDon(_listChiTietHoaDon) == null)
            {
                return 1;
            }

            // B2: thay doi Tinh Trang cua cac ct Order tuong ung
            int maBan = 0;
            if (_listChiTietHoaDon.Count > 0)
                maBan = _listChiTietHoaDon[0].HoaDon.Ban.MaBan;

            if (ChiTietOrderBUS.ThayDoiTinhTrangDaThanhToan(maBan) == false)
                ketQua = 2;

            return ketQua;
        }

        public static bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            return ChiTietHoaDonDAO.SuaChiTietHoaDon(_chiTietHoaDon);
        }
    }
}
