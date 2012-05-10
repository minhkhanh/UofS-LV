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
    }
}
