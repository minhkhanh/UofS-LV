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
    }
}
