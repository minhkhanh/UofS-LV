using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class VoucherBUS
    {
        public static List<Voucher> LayDanhSachVoucher()
        {
            return VoucherDAO.LayDanhSachVoucher();
        }

        public static Voucher LayVoucher(int maVoucher)
        {
            return VoucherDAO.LayVoucher(maVoucher);
        }

        public static bool Xoa(int maVoucher)
        {
            return VoucherDAO.Xoa(maVoucher);
        }

        public static bool Them(Voucher voucher)
        {
            return VoucherDAO.Them(voucher);
        }

        public static bool CapNhat(Voucher voucher)
        {
            return VoucherDAO.CapNhat(voucher);
        }
    }
}
