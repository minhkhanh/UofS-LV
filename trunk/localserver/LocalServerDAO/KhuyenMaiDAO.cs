using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;


namespace LocalServerDAO
{
    public class KhuyenMaiDAO
    {
        public static List<KhuyenMai> LayDanhSachKhuyenMai()
        {
            return ThucDonDienTu.DataContext.KhuyenMais.ToList();
        }

        public static KhuyenMai LayKhuyenMai(int maKhuyenMai)
        {
            var temp = ThucDonDienTu.DataContext.KhuyenMais.Where(d => d.MaKhuyenMai == maKhuyenMai);
            if (temp.Count() > 0)
            {
                KhuyenMai dm = temp.First();
                return dm;
            }
            return null;
        }


        public static bool Xoa(int maKhuyenMai)
        {
            try
            {
                var objKhuyenMai = LayKhuyenMai(maKhuyenMai);
                ThucDonDienTu.DataContext.KhuyenMais.DeleteOnSubmit(objKhuyenMai);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }


        public static bool Them(KhuyenMai khuyenMai)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuyenMais.InsertOnSubmit(khuyenMai);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(KhuyenMai khuyenMai)
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

        public static List<KhuyenMai> LayDanhSachKhuyenMaiApDung(int maOrder)
        {
            List<KhuyenMai> result = new List<KhuyenMai>();
            float giaHoaDon = 0;

            var orderList = ThucDonDienTu.DataContext.Orders.Where(o => o.MaOrder == maOrder);
            if (orderList.Count() != 1)
                return new List<KhuyenMai>();

            Order order = orderList.First();

            // lay khuyen mai mon
            var ctOrderList = ThucDonDienTu.DataContext.ChiTietOrders.Where(c => c.Order.MaOrder == order.MaOrder);
            foreach (ChiTietOrder c in ctOrderList)
            {
                float giaGiam = 0;
                float donGia = ChiTietMonAnDonViTinhDAO.LayDonGia(c.MonAn.MaMonAn, c.DonViTinh.MaDonViTinh);
                if (donGia == -1)
                    donGia = 0;

                var kmMonList = ThucDonDienTu.DataContext.KhuyenMaiMons.Where(k => k.MonAn.MaMonAn == c.MonAn.MaMonAn);
                foreach (KhuyenMaiMon kmm in kmMonList)
                {
                    KhuyenMai km = kmm.KhuyenMai;
                    result.Add(km);

                    if (km.GiaGiam != 0)
                        giaGiam = km.GiaGiam;
                    else
                        giaGiam = donGia * km.TiLeGiam;
                }

                giaHoaDon += donGia - giaGiam;
            }

            //// lay km khu vuc
            //var kmKhuVucList = ThucDonDienTu.DataContext.KhuyenMaiKhuVucs.Where(k => k.KhuVuc.MaKhuVuc == order.Ban.KhuVuc.MaKhuVuc);
            //foreach (KhuyenMaiKhuVuc kmkv in kmKhuVucList)
            //{
            //    KhuyenMai km = kmkv.KhuyenMai;
            //    result.Add(km);

            //    if (km.GiaGiam != 0)
            //        giaHoaDon -= km.GiaGiam;
            //    else
            //        giaHoaDon -= giaHoaDon * km.TiLeGiam;

            //}

            // lay km hoa don
            KhuyenMai kmHoaDon = KhuyenMaiHoaDonDAO.LayKhuyenMai(giaHoaDon);
            if (kmHoaDon != null)
                result.Add(kmHoaDon);

            return result;
        }

        public static List<KhuyenMai> LayDanhSachKhuyenMaiCoHieuLucJson()
        {
            return ThucDonDienTu.DataContext.KhuyenMais.Where(k => k.BatDau <= DateTime.Now && DateTime.Now <= k.KetThuc).ToList();
        }
    }
}
