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
            if (temp.Count() == 0) 
                return false;
            Ban banChinh = temp.First();
            banChinh.Active = true;

            //lấy danh sách các bàn trong nhóm
            var dsBan = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == banChinh);
            foreach (var ban in dsBan)
            {
                ban.BanChinh = null;
                ban.Active = true;
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

            banChinh.Active = false;

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
                banTmp.Active = false;
            }

            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static List<Ban> LayDanhSachBanChinh()
        {
            return ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == null).ToList();
        }

        public static List<Ban> LayDanhSachBanChinhJson(int maKhuVuc)
        {
            return ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh == b && b.KhuVuc.MaKhuVuc == maKhuVuc).ToList();
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
                System.Diagnostics.Debug.Write(e.StackTrace);
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
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(Ban ban)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == ban.MaBan);
            if (temp.Count() > 0)
            {
                Ban b = temp.First();
                b.Active = ban.Active;
                b._maBanChinh = ban._maBanChinh;
                b._maKhuVuc = ban._maKhuVuc;
                b.GhiChu = ban.GhiChu;
                b.TinhTrang = ban.TinhTrang;
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

        public static bool TachBanJson(int maBan)
        {
            //lấy về đối tượng cần tách
            var temp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBan);
            if (temp.Count() == 0)
                return false;
            Ban ban = temp.First();
            if (ban.MaBan != ban.BanChinh.MaBan)        // ban nay khong phai ban chinh, tach thoai mai
            {
                ban.Active = true;
                ban.BanChinh = null;

                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }

            // neu no la ban chinh thi tim ban khac de lam ban chinh thay cho no
            var dsBanPhu = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh.MaBan == ban.MaBan && b.MaBan != ban.MaBan);
            if (dsBanPhu.Count() == 0)
                return false;
            Ban banPhu = dsBanPhu.First();

            // chuyen order tu ban chinh sang ban phu thay the
            var dsOrderChuaThanhToan = ThucDonDienTu.DataContext.Orders.Where(o => o.TinhTrang != 4 && o.Ban.MaBan == ban.MaBan);
            foreach (Order o in dsOrderChuaThanhToan)
            {
                o.Ban = banPhu;
            }

            // cap nhat ban chinh moi cho ds ban phu
            foreach (var b in dsBanPhu)
            {
                if (b.MaBan != banPhu.MaBan)
                    b.BanChinh = banPhu;
            }

            ban.Active = true;
            ban.BanChinh = null;

            //cập nhật csdl
            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static bool GhepBanJson(TableIdSelection tabSel)
        {
            if (tabSel.TabIdList.Count < 1) return false;

            var varBanChinh = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == tabSel.MainTabId);
            if (varBanChinh.Count() == 0)
                return false;

            Ban banChinh = varBanChinh.First();
            if (banChinh.BanChinh != null && banChinh.BanChinh.MaBan != banChinh.MaBan)      // ban chinh nam trong nhom khac
                return false;

            //banChinh.BanChinh = banChinh;
    
            List<Ban> listBan = new List<Ban>();            
            foreach (int maBan in tabSel.TabIdList)
            {
                var tmp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBan);
                if (tmp.Count() == 0) return false;
                Ban ban = tmp.First();
                if (ban.BanChinh != null)
                    if (banChinh.MaBan != ban.BanChinh.MaBan) // detect 2 different groups in 1 list
                    {
                        return false;
                    }

                listBan.Add(ban);
            }

            //if (banChinh == null) // tao nhom ban moi bang cach chon ban chinh la ban dau tien
            //    banChinh = listBan[0];

            foreach (Ban ban in listBan)
            {
                //cap nhat gia tri ban chinh
                ban.BanChinh = banChinh;
                ban.Active = false;
            }

            ThucDonDienTu.DataContext.SubmitChanges();
            return true;
        }

        public static bool TachNhomBanJson(int maBan)
        {
            var banTmp = ThucDonDienTu.DataContext.Bans.Where(b => b.MaBan == maBan && b.TinhTrang);
            if (banTmp.Count() == 0)
                return false;

            Ban ban = banTmp.First();

            var dsOrderChuaThanhToan = ThucDonDienTu.DataContext.Orders.Where(o => o.TinhTrang != 4 && o.Ban.MaBan == ban.BanChinh.MaBan);
            if (dsOrderChuaThanhToan.Count() != 0)
                return false;       // nhom ban nay dang phuc vu order, khong cho phep tach nhom

            // lay ra ds ban trong nhom
            var listBanTmp = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh.MaBan == ban.BanChinh.MaBan);
            foreach (Ban b in listBanTmp)
            {
                b.Active = true;
                b.BanChinh = null;
            }

            ThucDonDienTu.DataContext.SubmitChanges();

            return true;
        }
    }
}
