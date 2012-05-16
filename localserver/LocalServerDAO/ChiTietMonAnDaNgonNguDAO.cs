using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietMonAnDaNgonNguDAO
    {
        public static ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.Where(c => c.MonAn.MaMonAn == maMonAn && c.NgonNgu.MaNgonNgu == maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietMonAnDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu()
        {
            return ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.ToList();
        }

        public static List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNguTheMonAn(MonAn monAn)
        {
            return ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.Where(c => c.MonAn == monAn).ToList();
        }

        public static bool Xoa(ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.DeleteOnSubmit(chiTietMonAnDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.InsertOnSubmit(chiTietMonAnDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(ChiTietMonAnDaNgonNgu chiTietMonAnDaNgonNgu)
        {
            try
            {
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
