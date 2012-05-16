using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonLienQuanDAO
    {
        public static ChiTietMonLienQuan LayChiTietMonLienQuan(int maMonAn, int maMonAnLienQuan)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonLienQuans.Where(c => c.MonAn.MaMonAn == maMonAn && c.MonAnLienQuan.MaMonAn == maMonAnLienQuan);
            if (temp.Count() > 0)
            {
                ChiTietMonLienQuan ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan()
        {
            return ThucDonDienTu.DataContext.ChiTietMonLienQuans.ToList();
        }

        public static List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(int maMonAn)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonLienQuans.Where(c => c.MonAn.MaMonAn == maMonAn);
            if (temp.Count() == 0)
                return null;
            return temp.ToList();
        }

        public static bool Xoa(ChiTietMonLienQuan chiTietMonLienQuan)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietMonLienQuans.DeleteOnSubmit(chiTietMonLienQuan);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietMonLienQuan chiTietMonLienQuan)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietMonLienQuans.InsertOnSubmit(chiTietMonLienQuan);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }
    }
}
