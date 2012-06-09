using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class NgonNguDAO
    {
        public static List<NgonNgu> LayDanhSachNgonNgu()
        {
            if(ThucDonDienTu.DataContext != null)
                return ThucDonDienTu.DataContext.NgonNgus.ToList();

            NgonNgu ngonNgu = new NgonNgu();
            ngonNgu.KiHieu = "vi";
            ngonNgu.TenNgonNgu = "Tieng Viet";
            List<NgonNgu> listNgonNgu = new List<NgonNgu>();
            listNgonNgu.Add(ngonNgu);
            return listNgonNgu;
        }

        public static NgonNgu LayNgonNguTheoMa(int maNgonNgu)
        {
            //lấy về đối tượng 
            var temp = ThucDonDienTu.DataContext.NgonNgus.Where(b => b.MaNgonNgu == maNgonNgu);
            if (temp.Count() == 0) return null;
            return temp.First();
        }

        public static NgonNgu LayNgonNguTheoKiHieu(string kiHieu)
        {
            //lấy về đối tượng 
            var temp = ThucDonDienTu.DataContext.NgonNgus.Where(b => b.KiHieu == kiHieu);
            if (temp.Count() == 0) return null;
            return temp.First();
        }

        public static bool Xoa(int maNgonNgu)
        {
            try
            {
                var objNgonNgu = LayNgonNguTheoMa(maNgonNgu);
                ThucDonDienTu.DataContext.NgonNgus.DeleteOnSubmit(objNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(NgonNgu ngonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.NgonNgus.InsertOnSubmit(ngonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(NgonNgu ngonNgu)
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
