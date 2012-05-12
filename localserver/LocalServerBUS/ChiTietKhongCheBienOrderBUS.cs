using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietKhongCheBienOrderBUS
    {
        public static ChiTietKhongCheBienOrder LayChiTietCheBienOrder(int maChiTietKhongCheBienOrder)
        {
            return ChiTietKhongCheBienOrderDAO.LayChiTietKhongCheBienOrder(maChiTietKhongCheBienOrder);
        }

        public static bool Them(ChiTietKhongCheBienOrder chiTietCheBienOrder)
        {
            return ChiTietKhongCheBienOrderDAO.Them(chiTietCheBienOrder);
        }

        public static bool CapNhat(ChiTietKhongCheBienOrder chiTietCheBienOrder)
        {
            return ChiTietKhongCheBienOrderDAO.CapNhat(chiTietCheBienOrder);
        }
    }
}