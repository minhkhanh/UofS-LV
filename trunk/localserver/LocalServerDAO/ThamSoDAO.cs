using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ThamSoDAO
    {
        public static List<ThamSo> LayDanhSachThamSo()
        {
            return ThucDonDienTu.DataContext.ThamSos.ToList();
        }

        public static ThamSo LayThamSo(string ten)
        {
            var temp = ThucDonDienTu.DataContext.ThamSos.Where(t => t.Ten == ten);
            if (temp.Count() == 0) 
                return null;
            return temp.First();
        }

        //public static bool Xoa(int maKhuVuc)
        //{
        //    try
        //    {
        //        var objKhuVuc = LayKhuVuc(maKhuVuc);
        //        ThucDonDienTu.DataContext.KhuVucs.DeleteOnSubmit(objKhuVuc);
        //        ThucDonDienTu.DataContext.SubmitChanges();
        //        return true;
        //    }
        //    catch (Exception e)
        //    {
        //        System.Diagnostics.Debug.Write(e.StackTrace);
        //    }
        //    return false;
        //}

        public static bool Them(ThamSo thamSo)
        {
            try
            {
                ThucDonDienTu.DataContext.ThamSos.InsertOnSubmit(thamSo);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(ThamSo thamSo)
        {
            try
            {
                var tmp = LayThamSo(thamSo.Ten);
                tmp.GiaTri = thamSo.GiaTri;
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
