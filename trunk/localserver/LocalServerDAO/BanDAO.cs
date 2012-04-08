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
    }
}
