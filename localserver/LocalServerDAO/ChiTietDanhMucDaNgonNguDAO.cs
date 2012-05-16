using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDanhMucDaNgonNguDAO
    {
        public static ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.Where(c => c.DanhMuc.MaDanhMuc==maDanhMuc && c.NgonNgu.MaNgonNgu==maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietDanhMucDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu()
        {
            return ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.ToList();
        }

        public static List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNguTheoDanhMuc(int maDanhMuc)
        {
            return ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.Where(c => c.DanhMuc.MaDanhMuc == maDanhMuc).ToList();
        }

        public static bool Xoa(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.DeleteOnSubmit(chiTietDanhMucDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDanhMucDaNgonNgus.InsertOnSubmit(chiTietDanhMucDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(ChiTietDanhMucDaNgonNgu chiTietDanhMucDaNgonNgu)
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
