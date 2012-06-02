using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietDanhMucBoPhanCheBienBUS
    {
        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBien()
        {
            return ChiTietDanhMucBoPhanCheBienDAO.LayDanhSachChiTietDanhMucBoPhanCheBien();
        }

        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBienTheoBoPhanCheBien(int maBoPhanCheBien)
        {
            return ChiTietDanhMucBoPhanCheBienDAO.LayDanhSachChiTietDanhMucBoPhanCheBienTheoBoPhanCheBien(maBoPhanCheBien);
        }
        

        public static ChiTietDanhMucBoPhanCheBien LayChiTiet(int maBoPhanCheBien, int maDanhMuc)
        {
            return ChiTietDanhMucBoPhanCheBienDAO.LayChiTiet(maBoPhanCheBien, maDanhMuc);
        }

        public static bool Xoa(ChiTietDanhMucBoPhanCheBien chiTietDanhMucBoPhanCheBien)
        {
            return ChiTietDanhMucBoPhanCheBienDAO.Xoa(chiTietDanhMucBoPhanCheBien);
        }

        public static bool Them(ChiTietDanhMucBoPhanCheBien chiTietDanhMucBoPhanCheBien)
        {
            return ChiTietDanhMucBoPhanCheBienDAO.Them(chiTietDanhMucBoPhanCheBien);
        }

        public static BoPhanCheBien LayBoPhanCheBienTheoDanhMuc(int maDanhMuc)
        {
            return ChiTietDanhMucBoPhanCheBienDAO.LayBoPhanCheBienTheoDanhMuc(maDanhMuc);
        }
    }
}
