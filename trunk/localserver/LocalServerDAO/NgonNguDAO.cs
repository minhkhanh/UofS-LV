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
            //return ThucDonDienTu.DataContext.GetTable<NgonNgu>().ToList();
            return ThucDonDienTu.DataContext.NgonNgus.ToList();
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

        public static bool XoaNgonNgu(int maNgonNgu)
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
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }
    }
}
