using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class BanBUS
    {
        public static List<Ban> LayDanhSachBan()
        {
            return BanDAO.LayDanhSachBan();
        }

        public static List<Ban> LayDanhSachBan(int maKhuVuc)
        {
            return BanDAO.LayDanhSachBan(maKhuVuc);
        }

        public static bool TachBan(int maBan)
        {
            return BanDAO.TachBan(maBan);
        }

        public static bool GhepBan(RequestGhepBan request)
        {
            if (request.MaBanPhuList.Count() == 0) return false;
            return BanDAO.GhepBan(request);
        }

        public static List<Ban> LayDanhSachBanChinh()
        {
            return BanDAO.LayDanhSachBanChinh();
        }

        public static List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh)
        {
            return BanDAO.LayDanhSachBanThuocBanChinh(maBanChinh);
        }

        public static Ban LayBan(int maBan)
        {
            return BanDAO.LayBan(maBan);
        }

        public static bool Xoa(int maBan)
        {
            return BanDAO.Xoa(maBan);
        }

        public static bool Them(Ban ban)
        {
            return BanDAO.Them(ban);
        }

        public static bool CapNhat(Ban ban)
        {
            return BanDAO.CapNhat(ban);
        }

        public static bool ChuyenBan(int maBanChuyenDi, int maBanChuyenDen)
        {
            bool ketQua = true;

            Ban banChuyenDi = BanBUS.LayBan(maBanChuyenDi);
            Ban banChuyenDen = BanBUS.LayBan(maBanChuyenDen);

            // Neu Ban chuyen di chua co khach 
            // Neu Ban chuyen den dang co khach 
            if (banChuyenDi == null || banChuyenDen == null || banChuyenDi.Active == true || banChuyenDen.Active == false)
                return false;

            // B1: Lay Order cua banChuyenDi
            // Doi MaBan thanh maBanChuyenDen
            if (!OrderBUS.ChuyenBan(maBanChuyenDi, maBanChuyenDen))
                ketQua = false;


            // B2: Tach banChuyenDi
            if (!BanBUS.TachBan(maBanChuyenDi))
                ketQua = false;

            // B3: Doi tinh trang banChuyenDen
            banChuyenDen.Active = false;
            if (!BanBUS.CapNhat(banChuyenDen))
                ketQua = false;

            return ketQua;
        }
    }
}
