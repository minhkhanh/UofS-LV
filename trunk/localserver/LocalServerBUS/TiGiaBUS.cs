using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class TiGiaBUS
    {
        public static List<TiGia> LayDanhSachTiGia()
        {
            return TiGiaDAO.LayDanhSachTiGia();
        }

        public static List<TiGia> LayDanhSachTiGiaThatSu()
        {
            List<TiGia> listTiGia = TiGiaDAO.LayDanhSachTiGia();
            
            // TiGia VND is record 1
            TiGia vnd = TiGiaDAO.LayTiGia(1);
            listTiGia.Remove(vnd);
            return listTiGia;
        }

        public static TiGia LayTiGia(int maTiGia)
        {
            return TiGiaDAO.LayTiGia(maTiGia);
        }

        public static bool Xoa(int maTiGia)
        {
            return TiGiaDAO.Xoa(maTiGia);
        }

        public static bool Them(TiGia tiGia)
        {
            return TiGiaDAO.Them(tiGia);
        }

        public static bool CapNhat(TiGia tiGia)
        {
            return TiGiaDAO.CapNhat(tiGia);
        }
    }
}
