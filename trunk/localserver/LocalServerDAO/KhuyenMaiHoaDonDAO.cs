using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class KhuyenMaiHoaDonDAO
    {
        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon()
        {
            return ThucDonDienTu.DataContext.KhuyenMaiHoaDons.ToList();
        }

        public static List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai)
        {
            return ThucDonDienTu.DataContext.KhuyenMaiHoaDons.Where(k => k.KhuyenMai.MaKhuyenMai == maKhuyenMai).ToList();
        }

        public static KhuyenMai LayKhuyenMai(float tongTien)
        {
            var temp = ThucDonDienTu.DataContext.KhuyenMaiHoaDons.Where(c => c.MucGiaApDung <= tongTien);
            KhuyenMaiHoaDon kmLonNhat;
            if (temp.Count() > 0)
            {
                kmLonNhat = temp.First();
                foreach (KhuyenMaiHoaDon kmHoaDon in temp)
                {
                    if (kmHoaDon.MucGiaApDung > kmLonNhat.MucGiaApDung)
                        kmLonNhat = kmHoaDon;
                }

                return kmLonNhat.KhuyenMai;
            }
            return null;
        }
        

        public static KhuyenMaiHoaDon LayKhuyenMaiHoaDon(int maKhuyenMai)
        {
            var temp = ThucDonDienTu.DataContext.KhuyenMaiHoaDons.Where(c => c.KhuyenMai.MaKhuyenMai == maKhuyenMai);
            if (temp.Count() > 0)
            {
                KhuyenMaiHoaDon ct = temp.First();
                return ct;
            }
            return null;
        }

        public static bool CapNhat(KhuyenMaiHoaDon khuyenMaiHoaDon)
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


        public static bool Them(KhuyenMaiHoaDon khuyenMaiHoaDon)
        {
            try
            {
                ThucDonDienTu.DataContext.KhuyenMaiHoaDons.InsertOnSubmit(khuyenMaiHoaDon);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }
    }
}
