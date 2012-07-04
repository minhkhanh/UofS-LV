using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Transactions;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietCheBienOrderDAO
    {
        public static ChiTietCheBienOrder LayChiTietCheBienOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietCheBienOrder or = temp.First();
                return or;
            }
            return null;
        }

        public static bool ThemChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietCheBienOrders.InsertOnSubmit(_chiTietCheBienOrder);
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

        public static bool SuaChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == _chiTietCheBienOrder._maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietCheBienOrder ct = temp.First();
                ct._maChiTietOrder = _chiTietCheBienOrder._maChiTietOrder;
                ct.SoLuongDaCheBien = _chiTietCheBienOrder.SoLuongDaCheBien;
                ct.SoLuongDangCheBien = _chiTietCheBienOrder.SoLuongDangCheBien;
            }

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

        public static bool CheBienXong(ChiTietCheBienOrder chiTietCheBienOrder, int soLuongCheBienXong)
        {
            using (var transaction = new TransactionScope())
            {
                try
                {
                    if (soLuongCheBienXong > chiTietCheBienOrder.SoLuongDangCheBien) return false;
                    chiTietCheBienOrder.SoLuongDaCheBien += soLuongCheBienXong;
                    chiTietCheBienOrder.SoLuongDangCheBien -= soLuongCheBienXong;
                    //ChiTietCheBienOrderDAO.SuaChiTietCheBienOrder(chiTietCheBienOrder);
                    if (chiTietCheBienOrder.ChiTietOrder.SoLuong == chiTietCheBienOrder.SoLuongDaCheBien)
                    {
                        // da che bien xong
                        chiTietCheBienOrder.ChiTietOrder.TinhTrang = 1;
                        //ChiTietOrderBUS.SuaChiTietOrder(chiTietOrder);
                    }
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

        public static bool CheBien(int maChiTietOrder, int soLuongCheBien)
        {
            using (var transaction = new TransactionScope())
            {
                try
                {
                    var chiTietOrder = ChiTietOrderDAO.LayChiTietOrder(maChiTietOrder);
                    // check co bi lock ko dc che bien ko
                    if (chiTietOrder == null || chiTietOrder.Order.TinhTrang == 2 || chiTietOrder.TinhTrang == 2) return false;
                    var chiTietCheBienOrder = ChiTietCheBienOrderDAO.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder);

                    int iSoLuongCheBienToiDa = chiTietOrder.SoLuong;
                    if (chiTietCheBienOrder != null)
                    {
                        iSoLuongCheBienToiDa -= (chiTietCheBienOrder.SoLuongDaCheBien + chiTietCheBienOrder.SoLuongDangCheBien);
                    }
                    if (soLuongCheBien <= 0 || soLuongCheBien > iSoLuongCheBienToiDa) return false;

                    if (chiTietCheBienOrder != null)
                    {
                        chiTietCheBienOrder.SoLuongDangCheBien += soLuongCheBien;                        
                    } else
                    {
                        chiTietCheBienOrder = new ChiTietCheBienOrder();
                        chiTietCheBienOrder.ChiTietOrder = chiTietOrder;
                        chiTietCheBienOrder.SoLuongDaCheBien = 0;
                        chiTietCheBienOrder.SoLuongDangCheBien = soLuongCheBien;
                        ThucDonDienTu.DataContext.ChiTietCheBienOrders.InsertOnSubmit(chiTietCheBienOrder);                        
                    }
                    chiTietOrder.TinhTrang = 1;
                    chiTietOrder.Order.TinhTrang = 1;

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
