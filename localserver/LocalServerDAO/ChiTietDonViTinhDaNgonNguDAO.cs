using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietDonViTinhDaNgonNguDAO
    {
        public static ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.Where(c => c.DonViTinh.MaDonViTinh == maDonViTinh && c.NgonNgu.MaNgonNgu == maNgonNgu);
            if (temp.Count() > 0)
            {
                ChiTietDonViTinhDaNgonNgu ct = temp.First();
                return ct;
            }
            return null;
        }

        public static List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu()
        {
            return ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.ToList();
        }

        public static List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNguTheoDonViTinh(int maDonViTinh)
        {
            return ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.Where(c => c.DonViTinh.MaDonViTinh == maDonViTinh).ToList();
        }

        public static bool Xoa(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.DeleteOnSubmit(chiTietDonViTinhDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietDonViTinhDaNgonNgus.InsertOnSubmit(chiTietDonViTinhDaNgonNgu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(ChiTietDonViTinhDaNgonNgu chiTietDonViTinhDaNgonNgu)
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
