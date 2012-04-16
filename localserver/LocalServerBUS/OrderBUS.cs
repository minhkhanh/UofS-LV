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

        public static bool ThemOrder(Order _order)
        {
            return OrderBUS.ThemOrder(_order);
        }
    }
}
