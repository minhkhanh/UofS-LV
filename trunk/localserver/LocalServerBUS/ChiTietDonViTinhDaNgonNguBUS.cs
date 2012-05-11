using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietDonViTinhDaNgonNguBUS
    {
        public static ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
        {
            return ChiTietDonViTinhDaNgonNguDAO.LayChiTietDonViTinhDaNgonNgu(maDonViTinh, maNgonNgu);
        }

        public static List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu()
        {
            return ChiTietDonViTinhDaNgonNguDAO.LayDanhSachChiTietDonViTinhDaNgonNgu();
        }

        public static List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(int maDonViTinh)
        {
            return ChiTietDonViTinhDaNgonNguDAO.LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(maDonViTinh);
        }

        public static bool Xoa(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
        {
            return ChiTietDonViTinhDaNgonNguDAO.Xoa(chiTietDonViTinhDaNgonNgu);
        }

        public static bool Them(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
        {
            return ChiTietDonViTinhDaNgonNguDAO.Them(chiTietDonViTinhDaNgonNgu);
        }

        public static bool CapNhat(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
        {
            return ChiTietDonViTinhDaNgonNguDAO.CapNhat(chiTietDonViTinhDaNgonNgu);
        }

        public static List<NgonNgu> LayDanhSachNgonNguChuaCo(int maDonViTinh)
        {
            List<NgonNgu> listNgonNgu = NgonNguBUS.LayDanhSachNgonNgu();

            List<ChiTietDonViTinhDaNgonNgu> listChiTiet = LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(maDonViTinh);
            foreach (ChiTietDonViTinhDaNgonNgu ct in listChiTiet)
            {
                if (listNgonNgu.Contains(ct.NgonNgu))
                    listNgonNgu.Remove(ct.NgonNgu);
            }

            return listNgonNgu;
        }
    }
}
