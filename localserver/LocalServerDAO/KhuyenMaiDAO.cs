using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class KhuyenMaiDAO
    {
        public static List<KhuyenMai> LayDanhSachKhuyenMai()
        {
            return ThucDonDienTu.DataContext.KhuyenMais.ToList();
        }

        public static KhuyenMai LayKhuyenMai(int maKhuyenMai)
        {
            var temp = ThucDonDienTu.DataContext.KhuyenMais.Where(d => d.MaKhuyenMai == maKhuyenMai);
            if (temp.Count() > 0)
            {
                KhuyenMai dm = temp.First();
                return dm;
            }
            return null;
        }


        public static bool Xoa(int maKhuyenMai)
        {
            try
            {
                var objKhuyenMai = LayKhuyenMai(maKhuyenMai);
                ThucDonDienTu.DataContext.KhuyenMais.DeleteOnSubmit(objKhuyenMai);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }


        public static bool Them(KhuyenMai khuyenMai)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuyenMais.InsertOnSubmit(khuyenMai);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(KhuyenMai khuyenMai)
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
