using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietCheBienOrderBUS
    {
        public static ChiTietCheBienOrder LayChiTietCheBienOrder(int maChiTietOrder)
        {
            return ChiTietCheBienOrderDAO.LayChiTietCheBienOrder(maChiTietOrder);
        }

        public static bool ThemChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            return ChiTietCheBienOrderDAO.ThemChiTietCheBienOrder(_chiTietCheBienOrder);
        }

        public static bool SuaChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            return ChiTietCheBienOrderDAO.SuaChiTietCheBienOrder(_chiTietCheBienOrder);
        }

        public static bool CheBienXong(ChiTietCheBienOrder chiTietCheBienOrder, int soLuongCheBienXong)
        {
            return ChiTietCheBienOrderDAO.CheBienXong(chiTietCheBienOrder, soLuongCheBienXong);
        }
        public static bool CheBien(int maChiTietOrder, int soLuongCheBien)
        {
            return ChiTietCheBienOrderDAO.CheBien(maChiTietOrder, soLuongCheBien);
        }
    }
}
