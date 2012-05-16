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

        public static List<DanhMuc> LayDanhSachDanhMucRoot()
        {
            return ThucDonDienTu.DataContext.DanhMucs.Where(d => d.DanhMucCha.MaDanhMuc == 1).ToList();
        }


        public static List<DanhMuc> LayDanhSachDanhMucLevelThapNhat()
        {
            var temp =
                ThucDonDienTu.DataContext.DanhMucs.Where(
                    d => !ThucDonDienTu.DataContext.DanhMucs.Where(c => c.DanhMucCha == d).Any());
            return temp.ToList();
        }

        // Can xoa cac Chi Tiet Danh Muc Da Ngon Ngu tuong ung !?
        public static bool Xoa(int maDanhMuc)
        {
            try
            {
                var objDanhMuc = LayDanhMuc(maDanhMuc);
                ThucDonDienTu.DataContext.DanhMucs.DeleteOnSubmit(objDanhMuc);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        // Can them cac Chi Tiet Danh Muc Da Ngon Ngu tuong ung
        public static bool Them(DanhMuc danhMuc)
        {
            try
            {
                ThucDonDienTu.DataContext.DanhMucs.InsertOnSubmit(danhMuc);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(DanhMuc danhMuc)
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
    }
}
