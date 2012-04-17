using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietHuyOrderBUS
    {
        public static ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietOrder)
        {
            return ChiTietHuyOrderDAO.LayChiTietHuyOrder(maChiTietOrder);
        }

        public static ChiTietHuyOrder ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            return ChiTietHuyOrderDAO.ThemChiTietHuyOrder(_chiTietHuyOrder);
        }

        public static bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            return ChiTietHuyOrderDAO.SuaChiTietHuyOrder(_chiTietHuyOrder);
        }
    }
}
