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

        public static List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNguTheoDanhMuc(int maDanhMuc)
        {
            return ChiTietDanhMucDaNgonNguDAO.LayDanhSachChiTietDanhMucDaNgonNguTheoDanhMuc(maDanhMuc);
        }

        public static List<NgonNgu> LayDanhSachNgonNguChuaCo(int maDanhMuc)
        {
            List<NgonNgu> listNgonNgu = new List<NgonNgu>();

            List<ChiTietDanhMucDaNgonNgu> listChiTiet = LayDanhSachChiTietDanhMucDaNgonNgu();
            foreach (ChiTietDanhMucDaNgonNgu ct in listChiTiet)
            {
                if (ct.DanhMuc.MaDanhMuc != maDanhMuc)
                    listNgonNgu.Add(ct.NgonNgu);
            }

            return listNgonNgu;
        }

        public static bool Xoa(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
        {
            return ChiTietDanhMucDaNgonNguDAO.Xoa(chiTietDanhMucDaNgonNgu);
        }

        public static bool Them(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
        {
            return ChiTietDanhMucDaNgonNguDAO.Them(chiTietDanhMucDaNgonNgu);
        }

        public static bool CapNhat(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
        {
            return ChiTietDanhMucDaNgonNguDAO.CapNhat(chiTietDanhMucDaNgonNgu);
        }
    }
}
