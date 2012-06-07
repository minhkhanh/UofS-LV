using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class ChiTietVoucherDAO
    {
        public static List<Voucher> LayDanhSachVoucher()
        {
            return ThucDonDienTu.DataContext.Vouchers.ToList();
        }

        public static Voucher LayVoucher(int maVoucher)
        {
            var temp = ThucDonDienTu.DataContext.Vouchers.Where(v => v.MaVoucher == maVoucher);
            if (temp.Count() > 0)
            {
                Voucher ban = temp.First();
                return ban;
            }
            return null;
        }

        public static bool Xoa(int maVoucher)
        {
            try
            {
                var objVoucher = LayVoucher(maVoucher);
                ThucDonDienTu.DataContext.Vouchers.DeleteOnSubmit(objVoucher);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(Voucher voucher)
        {
            try
            {
                ThucDonDienTu.DataContext.Vouchers.InsertOnSubmit(voucher);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(Voucher voucher)
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
    }
}
