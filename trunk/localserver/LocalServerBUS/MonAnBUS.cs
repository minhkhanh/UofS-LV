using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class MonAnBUS
    {

        public static List<MonAn> LayDanhSachMonAn()
        {
            return MonAnDAO.LayDanhSachMonAn();
        }

        public static List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
        {
            return MonAnDAO.LayDanhSachMonAnTheoDanhMuc(maDanhMuc);
        }

        public static MonAn LayMonAn(int maMonAn)
        {
            return MonAnDAO.LayMonAn(maMonAn);
        }

        public static bool Them(MonAn monAn)
        {
            return MonAnDAO.Them(monAn);
        }

        public static bool CapNhat(MonAn monAn)
        {
            return MonAnDAO.CapNhat(monAn);
        }

        public static bool Xoa(int maMonAn)
        {
            return MonAnDAO.Xoa(maMonAn);
        }

        public static List<DonViTinh> LayDanhSachDonViTinhChuaCoTheoNgonNgu(MonAn monAn, NgonNgu ngonNgu)
        {
            var tmp = MonAnDAO.LayDanhSachDonViTinhChuaCoTheoNgonNgu(monAn);
            foreach (var donViTinh in tmp)
            {
                donViTinh.TenDonViTinh =
                    ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(donViTinh.MaDonViTinh, ngonNgu.MaNgonNgu).
                        TenDonViTinh;
            }
            return tmp;
        }

        public static List<NgonNgu> LayDanhSachNgonNguCuaMonAn(MonAn monAn)
        {
            return MonAnDAO.LayDanhSachNgonNguCuaMonAn(monAn);
        }

        public static List<NgonNgu> LayDanhSachNgonNguMonAnChuaCo(MonAn monAn)
        {
            return MonAnDAO.LayDanhSachNgonNguMonAnChuaCo(monAn);
        }

        public static List<MonAn> LayDanhSachMonAnTheoMaNgonNgu(int maNgonNgu, string noInformation)
        {
            List<MonAn> listMonAn = MonAnBUS.LayDanhSachMonAn();
            foreach (MonAn monAn in listMonAn)
            {
                try
                {
                    ChiTietMonAnDaNgonNgu ctNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
                    if (ctNgonNgu != null)
                    {
                        monAn.TenMonAn = ctNgonNgu.TenMonAn;
                        monAn.MoTaMonAn = ctNgonNgu.MoTaMonAn;
                    }
                    else
                    {
                        monAn.TenMonAn = noInformation;
                        monAn.MoTaMonAn = noInformation;
                    }

                }
                catch (Exception e)
                {
                    monAn.TenMonAn = noInformation;
                    monAn.MoTaMonAn = noInformation;
                    Console.WriteLine(e.Message);
                }
            }

            return listMonAn;
        }

        public static bool DanhGiaMonAn(int maMonAn, float diemDanhGia)
        {
            return MonAnDAO.DanhGiaMonAn(maMonAn, diemDanhGia);
        }
    }
}
