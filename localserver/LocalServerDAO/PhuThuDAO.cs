using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class PhuThuDAO
    {
        public static List<PhuThu> LayDanhSachPhuThu()
        {
            return ThucDonDienTu.DataContext.PhuThus.ToList();
        }

        public static PhuThu LayPhuThu(int maPhuThu)
        {
            var temp = ThucDonDienTu.DataContext.PhuThus.Where(d => d.MaPhuThu == maPhuThu);
            if (temp.Count() > 0)
            {
                PhuThu dm = temp.First();
                return dm;
            }
            return null;
        }


        public static bool Xoa(int maPhuThu)
        {
            try
            {
                var objPhuThu = LayPhuThu(maPhuThu);
                ThucDonDienTu.DataContext.PhuThus.DeleteOnSubmit(objPhuThu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }


        public static bool Them(PhuThu phuThu)
        {
            try
            {
                ThucDonDienTu.DataContext.PhuThus.InsertOnSubmit(phuThu);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(PhuThu phuThu)
        {
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static List<PhuThu> LayDanhSachPhuThuApDungJson(int maOrder)
        {
            List<PhuThu> result = new List<PhuThu>();

            var orderList = ThucDonDienTu.DataContext.Orders.Where(o => o.MaOrder == maOrder);
            if (orderList.Count() != 1)
                return new List<PhuThu>();

            Order order = orderList.First();

            var phuthuList = ThucDonDienTu.DataContext.PhuThuKhuVucs.Where(p => p.KhuVuc.MaKhuVuc == order.Ban.KhuVuc.MaKhuVuc);
            if (phuthuList.Count() > 0)
            {
                foreach (PhuThuKhuVuc phuThu in phuthuList)
                {
                    result.Add(phuThu.PhuThu);
                }
            }            

            return result;
        }

        public static List<PhuThu> LayDanhSachPhuThuCoHieuLucJson()
        {
            return ThucDonDienTu.DataContext.PhuThus.Where(p => p.BatDau <= DateTime.Now && DateTime.Now <= p.KetThuc).ToList();
        }

        public static List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucCoHieuLucJson()
        {
            return ThucDonDienTu.DataContext.PhuThuKhuVucs.Where(p => p.PhuThu.BatDau <= DateTime.Now && DateTime.Now <= p.PhuThu.KetThuc).ToList();
        }
    }
}
