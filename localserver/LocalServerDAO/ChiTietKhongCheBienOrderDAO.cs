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
        public static ChiTietKhongCheBienOrder LayChiTietKhongCheBienOrder(int maChiTietKhongCheBienOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietKhongCheBienOrder);
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
                    if (chiTietOrder.Order.TinhTrang == 0)
                        chiTietOrder.Order.TinhTrang = 2;
                    else // nguoc lai thi chi lock 1 chitiet
                        chiTietOrder.TinhTrang = 2;
                    ThucDonDienTu.DataContext.SubmitChanges();
                    // tao chitietkhongchebien
                    ChiTietKhongCheBienOrder chiTietKhongCheBienOrder = new ChiTietKhongCheBienOrder();
                    chiTietKhongCheBienOrder.ChiTietOrder = chiTietOrder;
                    chiTietKhongCheBienOrder.SoLuongKhongCheBien = soLuongHetCheBien;
                    ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.InsertOnSubmit(chiTietKhongCheBienOrder);

                    ThucDonDienTu.DataContext.SubmitChanges();
                    transaction.Complete();
                    return true;
                }
                catch (Exception e)
                {
                    Console.Out.WriteLine(e.StackTrace);
                    return false;
                }
            }
        }
    }
}
