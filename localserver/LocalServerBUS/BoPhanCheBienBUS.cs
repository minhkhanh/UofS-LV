using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class BoPhanCheBienBUS
    {
        public static List<BoPhanCheBien> LayDanhSachBoPhanCheBien()
        {
            return BoPhanCheBienDAO.LayDanhSachBoPhanCheBien();
        }

        public static List<ChiTietOrder> LayDanhSachChiTietOrderCanCheBien(BoPhanCheBien boPhanCheBien)
        {
            var listCTO = BoPhanCheBienDAO.LayDanhSachChiTietOrderCanCheBien(boPhanCheBien);
            //foreach (var chiTietOrder in listCTO)
            //{
            //    chiTietOrder.SoLuongDaCheBien =
            //        ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).SoLuongDaCheBien;
            //    chiTietOrder.SoLuongDangCheBien =
            //        ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(chiTietOrder.MaChiTietOrder).SoLuongDangCheBien;
            //}
            return listCTO;
        }

        public static BoPhanCheBien LayBoPhanCheBienTheoMa(int maBoPhanCheBien)
        {
            return BoPhanCheBienDAO.LayBoPhanCheBienTheoMa(maBoPhanCheBien);
        }

        public static BoPhanCheBien LayBoPhanCheBienTheoTaiKhoan(TaiKhoan taiKhoan)
        {
            return BoPhanCheBienDAO.LayBoPhanCheBienTheoTaiKhoan(taiKhoan);
        }

        public static bool Xoa(int maBoPhanCheBien)
        {
            return BoPhanCheBienDAO.Xoa(maBoPhanCheBien);
        }

        public static bool Them(BoPhanCheBien boPhanCheBien)
        {
            return BoPhanCheBienDAO.Them(boPhanCheBien);
        }

        public static bool CapNhat(BoPhanCheBien boPhanCheBien)
        {
            return BoPhanCheBienDAO.CapNhat(boPhanCheBien);
        }
    }
}
