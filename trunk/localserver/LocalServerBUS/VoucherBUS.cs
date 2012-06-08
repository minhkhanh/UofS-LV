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

        public static Voucher LayVoucherTheoSoPhieu(string soPhieu)
        {
            ChiTietVoucher ctVoucher = ChiTietVoucherBUS.LayChiTietSanSang(soPhieu);
            if (ctVoucher == null)
                return null;

            Voucher voucher = ctVoucher.Voucher;
            if (voucher != null)
                return voucher;

            return null;
        }

        public static float KiemTraVoucher(string soPhieu, float tongHoaDon)
        {
            float giaGiam = 0;
            Voucher voucher = LayVoucherTheoSoPhieu(soPhieu);
            if (voucher == null)
                return 0;

            DateTime hienTai = DateTime.Now;

            if (tongHoaDon >= voucher.MucGiaApDung && voucher.BatDau <= hienTai && hienTai <= voucher.KetThuc)
                giaGiam = voucher.GiaGiam;

            return giaGiam;
        }

        
    }
}
