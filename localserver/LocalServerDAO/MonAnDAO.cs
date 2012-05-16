using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class MonAnDAO
    {

        public static List<MonAn> LayDanhSachMonAn()
        {
            return ThucDonDienTu.DataContext.MonAns.ToList();       
        }

        public static List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
        {
            var temp = ThucDonDienTu.DataContext.DanhMucs.Where(d => d.MaDanhMuc == maDanhMuc);
            if (temp.Count() > 0)
            {
                DanhMuc dm = temp.First();
                return ThucDonDienTu.DataContext.MonAns.Where(m => m.DanhMuc == dm).ToList();
            }
            return null;
        }

        public static MonAn LayMonAn(int maMonAn)
        {
            var temp = ThucDonDienTu.DataContext.MonAns.Where(m => m.MaMonAn == maMonAn);
            if (temp.Count() > 0)
            {
                MonAn ma = temp.First();
                return ma;
            }
            return null;
        }

        public static bool Them(MonAn monAn)
        {
            try
            {
                ThucDonDienTu.DataContext.MonAns.InsertOnSubmit(monAn);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(MonAn monAn)
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

        public static bool Xoa(int maMonAn)
        {
            try
            {
                var objMonAn = LayMonAn(maMonAn);
                ThucDonDienTu.DataContext.MonAns.DeleteOnSubmit(objMonAn);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static List<DonViTinh> LayDanhSachDonViTinhChuaCoTheoNgonNgu(MonAn monAn)
        {
            return
                ThucDonDienTu.DataContext.DonViTinhs.Where(
                    d =>
                    !ThucDonDienTu.DataContext.ChiTietMonAnDonViTinhs.Where(
                        c => c.DonViTinh.MaDonViTinh == d.MaDonViTinh && c.MonAn == monAn).Any()).ToList();
        }

        public static List<NgonNgu> LayDanhSachNgonNguCuaMonAn(MonAn monAn)
        {
            return
                ThucDonDienTu.DataContext.NgonNgus.Where(
                    n =>
                    ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.Where(c => c.MonAn == monAn && c.NgonNgu == n).
                        Count() >
                    0).ToList();
        }

        public static List<NgonNgu> LayDanhSachNgonNguMonAnChuaCo(MonAn monAn)
        {
            return ThucDonDienTu.DataContext.NgonNgus.Except(ThucDonDienTu.DataContext.NgonNgus.Where(
                n =>
                ThucDonDienTu.DataContext.ChiTietMonAnDaNgonNgus.Where(c => c.MonAn == monAn && c.NgonNgu == n).
                    Count() >
                0)).ToList();
        }
    }
}
