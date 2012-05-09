using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class TiGiaDAO
    {
        public static List<TiGia> LayDanhSachTiGia()
        {
            return ThucDonDienTu.DataContext.TiGias.ToList();
        }

        public static TiGia LayTiGia(int maTiGia)
        {
            var temp = ThucDonDienTu.DataContext.TiGias.Where(t => t.MaTiGia == maTiGia);
            if (temp.Count() > 0)
            {
                TiGia TiGia = temp.First();
                return TiGia;
            }
            return null;
        }

        public static bool Xoa(int maTiGia)
        {
            try
            {
                var objTiGia = LayTiGia(maTiGia);
                ThucDonDienTu.DataContext.TiGias.DeleteOnSubmit(objTiGia);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool Them(TiGia tiGia)
        {
            try
            {
                ThucDonDienTu.DataContext.TiGias.InsertOnSubmit(tiGia);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(TiGia tiGia)
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
