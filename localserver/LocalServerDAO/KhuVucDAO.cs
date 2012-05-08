using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuVucDAO
    {
        public static List<KhuVuc> LayDanhSachKhuVuc()
        {
            return ThucDonDienTu.DataContext.KhuVucs.ToList();
        }

        public static KhuVuc LayKhuVuc(int maKhuVuc)
        {
            var temp = ThucDonDienTu.DataContext.KhuVucs.Where(k => k.MaKhuVuc == maKhuVuc);
            if (temp.Count() == 0) 
                return null;
            return temp.First();
        }

        public static bool Xoa(int maKhuVuc)
        {
            try
            {
                var objKhuVuc = LayKhuVuc(maKhuVuc);
                ThucDonDienTu.DataContext.KhuVucs.DeleteOnSubmit(objKhuVuc);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool Them(KhuVuc khuVuc)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuVucs.InsertOnSubmit(khuVuc);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(KhuVuc khuVuc)
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
