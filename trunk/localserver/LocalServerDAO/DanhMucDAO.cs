using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class DanhMucDAO
    {
        public static List<DanhMuc> LayDanhSachDanhMuc()
        {
            return ThucDonDienTu.DataContext.DanhMucs.ToList();
        }

        public static DanhMuc LayDanhMuc(int maDanhMuc)
        {
            var temp = ThucDonDienTu.DataContext.DanhMucs.Where(d => d.MaDanhMuc == maDanhMuc);
            if (temp.Count() > 0)
            {
                DanhMuc dm = temp.First();
                return dm;
            }
            return null;
        }

        public static List<DanhMuc> LayDanhSachDanhMucTheoDanhMucCha(int maDanhMucCha)
        {
            var temp = ThucDonDienTu.DataContext.DanhMucs.Where(d => d.MaDanhMuc == maDanhMucCha);
            if (temp.Count() > 0)
            {
                DanhMuc danhMucCha = temp.First();
                return ThucDonDienTu.DataContext.DanhMucs.Where(c => c.DanhMucCha == danhMucCha).ToList();
            }
            return null;
        }

        public static List<DanhMuc> LayDanhSachDanhMucCha()
        {
            return ThucDonDienTu.DataContext.DanhMucs.Where(d => d.DanhMucCha == null).ToList();
        }


        public static List<DanhMuc> LayDanhSachDanhMucLevelThapNhat()
        {
            var temp =
                ThucDonDienTu.DataContext.DanhMucs.Where(
                    d => !ThucDonDienTu.DataContext.DanhMucs.Where(c => c.DanhMucCha == d).Any());
            return temp.ToList();
        }
    }
}
