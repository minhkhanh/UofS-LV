using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietVoucherBUS
    {
        public static List<ChiTietVoucher> LayNhieuChiTietVoucher(int maVoucher)
        {
            return ChiTietVoucherDAO.LayNhieuChiTietVoucher(maVoucher);
        }

        public static ChiTietVoucher LayChiTietVoucher(int maChiTietVoucher)
        {
            return ChiTietVoucherDAO.LayChiTietVoucher(maChiTietVoucher);
        }

        public static List<ChiTietVoucher> ThemNhieuChiTietVoucher(List<ChiTietVoucher> _listChiTietVoucher)
        {
            return ChiTietVoucherDAO.ThemNhieuChiTietVoucher(_listChiTietVoucher);
        }

        public static bool Xoa(int maChiTietVoucher)
        {
            return ChiTietVoucherDAO.Xoa(maChiTietVoucher);
        }

        public static bool Them(ChiTietVoucher ctVoucher)
        {
            return ChiTietVoucherDAO.Them(ctVoucher);
        }

        public static bool CapNhat(ChiTietVoucher ctVoucher)
        {
            return ChiTietVoucherDAO.CapNhat(ctVoucher);
        }

        public static bool KiemTraTonTai(string soPhieu)
        {
            return ChiTietVoucherDAO.KiemTraTonTai(soPhieu);
        }

        public static ChiTietVoucher LayChiTietSanSang(string soPhieu)
        {
            return ChiTietVoucherDAO.LayChiTietSanSang(soPhieu);
        }

        public static bool SuDungVoucher(string soPhieu)
        {
            ChiTietVoucher ctVoucher = ChiTietVoucherBUS.LayChiTietSanSang(soPhieu);
            if (ctVoucher == null || ctVoucher.Active == false)
                return false;

            ctVoucher.Active = false;
            if (ChiTietVoucherBUS.CapNhat(ctVoucher))
                return true;

            return false;
        }
    }
}
