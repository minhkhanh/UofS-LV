using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDanhMucBoPhanCheBienDAO
    {
        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBien()
        {
            return ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.ToList();
        }

        public static List<ChiTietDanhMucBoPhanCheBien> LayDanhSachChiTietDanhMucBoPhanCheBienTheoBoPhanCheBien(int maBoPhanCheBien)
        {
            return ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.Where(c => c.BoPhanCheBien.MaBoPhanCheBien == maBoPhanCheBien).ToList();
        }

        public static ChiTietDanhMucBoPhanCheBien LayChiTiet(int maBoPhanCheBien, int maDanhMuc)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.Where(c => c.DanhMuc.MaDanhMuc == maDanhMuc && c.BoPhanCheBien.MaBoPhanCheBien == maBoPhanCheBien);
            if (temp.Count() > 0)
            {
                ChiTietDanhMucBoPhanCheBien ct = temp.First();
                return ct;
            }
            return null;
        }

        public static bool Xoa(ChiTietDanhMucBoPhanCheBien chiTietDanhMucBoPhanCheBien)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.DeleteOnSubmit(chiTietDanhMucBoPhanCheBien);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietDanhMucBoPhanCheBien chiTietDanhMucBoPhanCheBien)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDanhMucBoPhanCheBiens.InsertOnSubmit(chiTietDanhMucBoPhanCheBien);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }
    }
}
