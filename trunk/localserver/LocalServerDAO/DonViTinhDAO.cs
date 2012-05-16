using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class DonViTinhDAO
    {
        public static List<DonViTinh> LayDanhSachDonViTinh()
        {
            return ThucDonDienTu.DataContext.DonViTinhs.ToList();
        }

        public static DonViTinh LayDonViTinhTheoMa(int maDonViTinh)
        {
            var tmp = ThucDonDienTu.DataContext.DonViTinhs.Where(d => d.MaDonViTinh == maDonViTinh);
            if (tmp.Count() == 0) return null;
            return tmp.First();
        }

        public static bool Xoa(int maDonViTinh)
        {
            try
            {
                var objDonViTinh = LayDonViTinhTheoMa(maDonViTinh);
                ThucDonDienTu.DataContext.DonViTinhs.DeleteOnSubmit(objDonViTinh);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        // Can them cac Chi Tiet Danh Muc Da Ngon Ngu tuong ung
        public static bool Them(DonViTinh donViTinh)
        {
            try
            {
                ThucDonDienTu.DataContext.DonViTinhs.InsertOnSubmit(donViTinh);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(DonViTinh donViTinh)
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
