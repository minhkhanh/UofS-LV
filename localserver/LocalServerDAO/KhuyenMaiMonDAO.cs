using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiMonDAO
    {
        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiMons.ToList();
        }

        public static List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiMons.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }

        public static KhuyenMaiMon LayKhuyenMaiMon(int maKhuyenMai, int maMon)
        {
            var temp = ThucDonDienTu.DataContext.KhuyenMaiMons.Where(c => c.KhuyenMai.MaKhuyenMai == maKhuyenMai && c.MonAn.MaMonAn == maMon);
            if (temp.Count() > 0)
            {
                KhuyenMaiMon ct = temp.First();
                return ct;
            }
            return null;
        }

        public static bool Xoa(KhuyenMaiMon khuyenMaiMon)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuyenMaiMons.DeleteOnSubmit(khuyenMaiMon);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(KhuyenMaiMon khuyenMaiMon)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuyenMaiMons.InsertOnSubmit(khuyenMaiMon);
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
