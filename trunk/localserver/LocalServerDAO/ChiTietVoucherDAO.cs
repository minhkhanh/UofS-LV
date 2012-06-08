using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class ChiTietVoucherDAO
    {
        public static List<ChiTietVoucher> LayNhieuChiTietVoucher(int maVoucher)
        {
            return ThucDonDienTu.DataContext.ChiTietVouchers.Where(c => c._maVoucher == maVoucher).ToList();
        }

        public static ChiTietVoucher LayChiTietVoucher(int maChiTietVoucher)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietVouchers.Where(c => c.MaChiTietVoucher == maChiTietVoucher);
            if (temp.Count() > 0)
            {
                ChiTietVoucher ban = temp.First();
                return ban;
            }
            return null;
        }

        public static List<ChiTietVoucher> ThemNhieuChiTietVoucher(List<ChiTietVoucher> _listChiTietVoucher)
        {
            ThucDonDienTu.DataContext.ChiTietVouchers.InsertAllOnSubmit(_listChiTietVoucher);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
            }
            catch (Exception e)
            {
                _listChiTietVoucher = null;
            }

            return _listChiTietVoucher;
        }

        public static bool Xoa(int maChiTietVoucher)
        {
            try
            {
                var objChiTietVoucher = LayChiTietVoucher(maChiTietVoucher);
                ThucDonDienTu.DataContext.ChiTietVouchers.DeleteOnSubmit(objChiTietVoucher);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(ChiTietVoucher ctVoucher)
        {
            try
            {
                ThucDonDienTu.DataContext.ChiTietVouchers.InsertOnSubmit(ctVoucher);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(ChiTietVoucher ctVoucher)
        {
            bool result = true;
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                result = true;
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }

        public static ChiTietVoucher LayChiTietSanSang(string soPhieu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietVouchers.Where(c => c.SoPhieu == soPhieu && c.Active == true);
            if (temp.Count() > 0)
            {
                return temp.First();
            }

            return null;
        }

        public static bool KiemTraTonTai(string soPhieu)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietVouchers.Where(c => c.SoPhieu == soPhieu);
            if (temp.Count() > 0)
            {
                return true;
            }
            return false;
        }
    }
}
