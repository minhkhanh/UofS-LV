using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Transactions;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietKhongCheBienOrderDAO
    {
        public static ChiTietKhongCheBienOrder LayChiTietKhongCheBienOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietKhongCheBienOrder or = temp.First();
                return or;
            }
            return null;
        }

     

        public static bool Them(ChiTietKhongCheBienOrder chiTietKhongCheBienOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.InsertOnSubmit(chiTietKhongCheBienOrder);
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

        public static bool CapNhat(ChiTietKhongCheBienOrder chiTietKhongCheBienOrder)
        {
            bool result = false;
            //var temp = LayChiTietKhongCheBienOrder(chiTietKhongCheBienOrder.MaChiTietKhongCheBienOrder);
            //if (temp != null)
            //{
            //    temp.SoLuongKhongCheBien = chiTietKhongCheBienOrder.SoLuongKhongCheBien;
            //}

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

        public static bool KhoaCheBienVaTaoThongBaoKhongCheBien(ChiTietOrder chiTietOrder, int soLuongHetCheBien)
        {
            using (var transaction = new TransactionScope())
            {
                try
                {
                    // neu van chua bat dau che bien thi lock ca order
                    var order = OrderDAO.LayOrder(chiTietOrder.Order.MaOrder);
                    if (order.TinhTrang == 0)
                        order.TinhTrang = 2;
                    // nguoc lai thi chi lock 1 chitiet
                    chiTietOrder.TinhTrang = 2;
                    // tao chitietkhongchebien
                    var chiTietKhongCheBienOrder = new ChiTietKhongCheBienOrder();
                    chiTietKhongCheBienOrder._maChiTietOrder = chiTietOrder.MaChiTietOrder;
                    chiTietKhongCheBienOrder.SoLuongKhongCheBien = soLuongHetCheBien;
                    ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.InsertOnSubmit(chiTietKhongCheBienOrder);

                    ThucDonDienTu.DataContext.SubmitChanges();
                    transaction.Complete();
                    return true;
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                    return false;
                }
            }
        }
    }
}
