using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class DanhMucBUS
    {
        public static List<DanhMuc> LayDanhSachDanhMuc()
        {
            return DanhMucDAO.LayDanhSachDanhMuc();
        }

        public static DanhMuc LayDanhMuc(int maDanhMuc)
        {
            return DanhMucDAO.LayDanhMuc(maDanhMuc);
        }

        public static List<DanhMuc> LayDanhSachDanhMucTheoDanhMucCha(int maDanhMucCha)
        {
            return DanhMucDAO.LayDanhSachDanhMucTheoDanhMucCha(maDanhMucCha);
        }

        public static List<DanhMuc> LayDanhSachDanhMucRoot()
        {
            return DanhMucDAO.LayDanhSachDanhMucRoot();
        }

        public static List<DanhMuc> LayDanhSachDanhMucConChauDanhMucCha(int maDanhMucCha)
        {
            List<DanhMuc> dsDanhMuc = DanhMucBUS.LayDanhSachDanhMucTheoDanhMucCha(maDanhMucCha);
            if (dsDanhMuc != null)
            {
                for (int i = 0; i < dsDanhMuc.Count; ++i)
                    dsDanhMuc.AddRange(LayDanhSachDanhMucConChauDanhMucCha(dsDanhMuc[i].MaDanhMuc));
            }
            return dsDanhMuc;
        }


        private static List<DanhMuc> LayDanhSachDanhMucLevelThapNhat()
        {
            return DanhMucDAO.LayDanhSachDanhMucLevelThapNhat();
        }

        public static List<DanhMuc> LayDanhSachDanhMucLevelThapNhatTheoMaNgonNgu(int maNgonNgu, string noInformation)
        {
            List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucLevelThapNhat();

            foreach (DanhMuc danhmuc in listDanhMuc)
            {
                try
                {
                    ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhmuc.MaDanhMuc, maNgonNgu);
                    if (ct != null)
                    {
                        danhmuc.TenDanhMuc = ct.TenDanhMuc;
                        danhmuc.MoTaDanhMuc = ct.MoTaDanhMuc;
                    }
                    else
                    {
                        danhmuc.TenDanhMuc = noInformation;
                        danhmuc.MoTaDanhMuc = noInformation;
                    }

                }
                catch (Exception e)
                {
                    danhmuc.TenDanhMuc = noInformation;
                    danhmuc.MoTaDanhMuc = noInformation;

                    Console.WriteLine(e.Message);
                }
            }

            return listDanhMuc;
        }

        

        // Check if B is descendant of A
        public static bool IsItsDescendant(int maDanhMucA, int maDanhMucB)
        {
            List<DanhMuc> listConChau = LayDanhSachDanhMucConChauDanhMucCha(maDanhMucA);
            if (listConChau != null && listConChau.Count > 0 && listConChau.Contains(DanhMucBUS.LayDanhMuc(maDanhMucB)))
                return true;
            return false;
        }

        public static List<DanhMuc> LayDanhSachDanhMucTheoMaNgonNgu(int maNgonNgu, string noInformation)
        {
            List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMuc();

            foreach (DanhMuc danhmuc in listDanhMuc)
            {
                try
                {
                    ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhmuc.MaDanhMuc, maNgonNgu);
                    if (ct != null)
                    {
                        danhmuc.TenDanhMuc = ct.TenDanhMuc;
                        danhmuc.MoTaDanhMuc = ct.MoTaDanhMuc;
                    }
                    else
                    {
                        danhmuc.TenDanhMuc = noInformation;
                        danhmuc.MoTaDanhMuc = noInformation;
                    }

                }
                catch (Exception e)
                {
                    danhmuc.TenDanhMuc = noInformation;
                    danhmuc.MoTaDanhMuc = noInformation;

                    Console.WriteLine(e.Message);
                }
            }

            return listDanhMuc;
        }

        public static bool Xoa(int maDanhMuc)
        {
            return DanhMucDAO.Xoa(maDanhMuc);
        }

        public static bool Them(DanhMuc danhMuc)
        {
            return DanhMucDAO.Them(danhMuc);
        }

        public static bool CapNhat(DanhMuc danhMuc)
        {
            return DanhMucDAO.CapNhat(danhMuc);
        }
    }
}
