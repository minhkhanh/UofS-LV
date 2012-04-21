using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class OrderBUS
    {
        public static List<Order> LayDanhSachOrder()
        {
            return OrderDAO.LayDanhSachOrder();
        }

        public static Order LayOrder(int maOrder)
        {
            return OrderDAO.LayOrder(maOrder);
        }

        public static Order ThemOrder(Order _order)
        {
            return OrderDAO.ThemOrder(_order);
        }

        public static bool SuaOrder(Order _order)
        {
            return OrderDAO.SuaOrder(_order);
        }
    }
}
