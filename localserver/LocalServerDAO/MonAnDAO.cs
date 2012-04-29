using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class MonAnDAO
    {
        public static List<MonAn> LayDanhSachMonAn()
        {
            return ThucDonDienTu.DataContext.MonAns.ToList();        
        }

        public static List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
        {
            var temp = ThucDonDienTu.DataContext.DanhMucs.Where(d => d.MaDanhMuc == maDanhMuc);
            if (temp.Count() > 0)
            {
                DanhMuc dm = temp.First();
                return ThucDonDienTu.DataContext.MonAns.Where(m => m.DanhMuc == dm).ToList();
            }
            return null;
        }

        public static MonAn LayMonAn(int maMonAn)
        {
            var temp = ThucDonDienTu.DataContext.MonAns.Where(m => m.MaMonAn == maMonAn);
            if (temp.Count() > 0)
            {
                MonAn ma = temp.First();
                return ma;
            }
            return null;
        }

        public static bool ThemMonAn(MonAn monAn)
        {
            try
            {
                ThucDonDienTu.DataContext.MonAns.InsertOnSubmit(monAn);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhapMonAn(MonAn monAn)
        {
            try
            {                
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
