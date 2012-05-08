using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class BanDAO
    {
        public static List<Ban> LayDanhSachBan()
        {
            return ThucDonDienTu.DataContext.Bans.ToList();
        }

        public static List<Ban> LayDanhSachBan(int maKhuVuc)
        {
            var temp = ThucDonDienTu.DataContext.KhuVucs.Where(k => k.MaKhuVuc == maKhuVuc);
            if (temp.Count()>0)
            {
                KhuVuc kv = temp.First();
                return ThucDonDienTu.DataContext.Bans.Where(b => b.KhuVuc == kv).ToList();
            }
            return null;
        }

        public static bool TachBan(int maBan)
        {
            //lấy về đối tượng cần tách
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBan);
            if (temp.Count() == 0) return false;
            Ban banChinh = temp.First();

            //lấy danh sách các bàn trong nhóm
            var dsBan = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == banChinh);
            foreach (var ban in dsBan)
            {
                ban.BanChinh = null;
            }

            //cập nhật csdl
            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static bool GhepBan(RequestGhepBan request)
        {
            //lấy về đối tượng bàn chính
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == request.MaBanChinh);
            if (temp.Count() == 0) return false;
            Ban banChinh = temp.First();
            if (banChinh.BanChinh != null) return false;

            //lay cac doi tuong ban phu
            foreach (var ban in request.MaBanPhuList)
            {
                int ban1 = ban;
                var tmp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == ban1);
                if (temp.Count() == 0) return false;
                Ban banTmp = tmp.First();
                if (banTmp.BanChinh != null && banTmp.BanChinh != banChinh) return false;
                //chec nhom ban da cap
                var dsBan = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == banTmp);
                if (dsBan.Count() != 0) return false;
                //cap nhat gia tri ban chinh
                banTmp.BanChinh = banChinh;
            }

            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static List<Ban> LayDanhSachBanChinh()
        {
            return ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == null).ToList();
        }

        public static List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh)
        {
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBanChinh);
            if (temp.Count() == 0) 
                return null;
            Ban banChinh = temp.First();
            return ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == banChinh).ToList();
        }

        public static Ban LayBan(int maBan)
        {
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBan);
            if (temp.Count() > 0)
            {
                Ban ban = temp.First();
                return ban;
            }
            return null;
        }

        public static bool Xoa(int maBan)
        {
            try
            {
                var objBan = LayBan(maBan);
                ThucDonDienTu.DataContext.Bans.DeleteOnSubmit(objBan);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool Them(Ban ban)
        {
            try
            {
                ThucDonDienTu.DataContext.Bans.InsertOnSubmit(ban);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(Ban ban)
        {
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.StackTrace);
            }
            return false;
        }
    }
}
