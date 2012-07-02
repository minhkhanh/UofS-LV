using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietKhongCheBienOrderBUS
    {
        public static ChiTietKhongCheBienOrder LayChiTietKhongCheBienOrder(int maChiTietOrder)
        {
            return ChiTietKhongCheBienOrderDAO.LayChiTietKhongCheBienOrder(maChiTietOrder);
        }

        public static bool Them(ChiTietKhongCheBienOrder chiTietCheBienOrder)
        {
            return ChiTietKhongCheBienOrderDAO.Them(chiTietCheBienOrder);
        }

        public static bool CapNhat(ChiTietKhongCheBienOrder chiTietCheBienOrder)
        {
            return ChiTietKhongCheBienOrderDAO.CapNhat(chiTietCheBienOrder);
        }

        public static bool KhoaCheBienVaTaoThongBaoKhongCheBien(ChiTietOrder chiTietOrder, int soLuongHetCheBien)
        {
            return ChiTietKhongCheBienOrderDAO.KhoaCheBienVaTaoThongBaoKhongCheBien(chiTietOrder, soLuongHetCheBien);
        }
    }
}