using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class PhuThuKhuVucDAO
    {
        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc()
        {
            return ThucDonDienTu.DataContext.PhuThuKhuVucs.ToList();
        }

        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu)
        {
            return ThucDonDienTu.DataContext.PhuThuKhuVucs.Where(p => p.PhuThu.MaPhuThu == maPhuThu).ToList();
        }

        public static PhuThuKhuVuc LayPhuThuKhuVuc(int maPhuThu, int maKhuVuc)
        {
            var temp = ThucDonDienTu.DataContext.PhuThuKhuVucs.Where(c => c.PhuThu.MaPhuThu == maPhuThu && c.KhuVuc.MaKhuVuc == maKhuVuc);
            if (temp.Count() > 0)
            {
                PhuThuKhuVuc ct = temp.First();
                return ct;
            }
            return null;
        }

        public static bool Xoa(PhuThuKhuVuc phuThuKhuVuc)
        {
            try
            {
                ThucDonDienTu.DataContext.PhuThuKhuVucs.DeleteOnSubmit(phuThuKhuVuc);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(PhuThuKhuVuc phuThuKhuVuc)
        {
            try
            {
                ThucDonDienTu.DataContext.PhuThuKhuVucs.InsertOnSubmit(phuThuKhuVuc);
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
