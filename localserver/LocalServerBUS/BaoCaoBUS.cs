using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class BaoCaoBUS
    {
        public static List<BaoCaoNgay> LayBaoCaoNgay(int ngay, int thang, int nam)
        {
            // Kiem tra thoi gian hop le
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, ngay);
            }
            catch (Exception)
            {
                return null;
            }

            List<BaoCaoNgay> listBaoCaoNgay = new List<BaoCaoNgay>();
            List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoNgay(ngayLap);

            float khuyenMai = 0;
            float phuThu = 0;
            float tongTien = 0;


            int iCount = 1;
            foreach (HoaDon hoaDon in listHoaDon)
            {
                BaoCaoNgay bc = new BaoCaoNgay();

                bc.Stt = iCount++;
                bc.MaHoaDon = hoaDon.MaHoaDon;
                bc.ThoiDiemLap = hoaDon.ThoiDiemLap.ToShortTimeString();
                bc.NguoiLap = hoaDon.TaiKhoan.TenTaiKhoan;
                bc.TongTien = hoaDon.TongTien;
                bc.BanChinh = hoaDon.Ban.TenBan;
                bc.PhuThu = hoaDon.PhuThu.GiaTang;

                tongTien += hoaDon.TongTien;
                phuThu += hoaDon.PhuThu.GiaTang;
                khuyenMai += HoaDonBUS.LayTongKhuyenMai(hoaDon.MaHoaDon);

                listBaoCaoNgay.Add(bc);
            }

            // TongTienNgay se gan vao moi doi tuong Bao Cao trong listBaoCao
            foreach (BaoCaoNgay bc in listBaoCaoNgay)
            {
                bc.TongTienNgay = tongTien;
                bc.TongPhuThuNgay = phuThu;
                bc.TongKhuyenMaiNgay = khuyenMai;
            }

            return listBaoCaoNgay;

        }

        public static List<BaoCaoThang> LayBaoCaoThang(int thang, int nam)
        {
            // Kiem tra thoi gian hop le
            DateTime ngayLap;
            try
            {
                ngayLap = new DateTime(nam, thang, 1);
            }
            catch (Exception)
            {
                return null;
            }

            List<BaoCaoThang> listBaoCaoThang = new List<BaoCaoThang>();
            List<HoaDon> listHoaDon = HoaDonBUS.LayDanhSachHoaDonTheoThang(ngayLap);

            // Report parameters
            float khuyenMai = 0;
            float phuThu = 0;
            float tongTien = 0;

            int iCount = 1;

            // Danh sach Hoa Don da duoc sap xep tu Nho den Lon
            // Chia theo tung ngay
            DateTime ngayDangXet = new DateTime(2000, 1, 1);
            foreach (HoaDon hoaDon in listHoaDon)
            {
                if (hoaDon.ThoiDiemLap.Date != ngayDangXet.Date)
                {
                    ngayDangXet = hoaDon.ThoiDiemLap;
                    BaoCaoThang bcMoi = new BaoCaoThang();
                    bcMoi.Stt = iCount++;
                    bcMoi.Ngay = hoaDon.ThoiDiemLap.ToShortDateString();

                    listBaoCaoThang.Add(bcMoi);
                }

                // Ap dung cho 1 ngay
                BaoCaoThang bc = listBaoCaoThang[listBaoCaoThang.Count - 1];
                bc.TongSoHoaDon++;

                float tempTongTien = hoaDon.TongTien;
                float tempPhuThu = hoaDon.PhuThu.GiaTang;
                float tempKhuyenMai = HoaDonBUS.LayTongKhuyenMai(hoaDon.MaHoaDon);

                bc.TongTien += tempTongTien;
                bc.PhuThu += tempPhuThu;
                bc.KhuyenMai += tempKhuyenMai;

                tongTien += tempTongTien;
                phuThu += tempPhuThu;
                khuyenMai += tempKhuyenMai;
            }



            // TongTienThang se gan vao moi doi tuong Bao Cao trong listBaoCao
            foreach (BaoCaoThang bc in listBaoCaoThang)
            {
                bc.TongTienThang = tongTien;
                bc.TongPhuThuThang = phuThu;
                bc.TongKhuyenMaiThang = khuyenMai;
            }

            return listBaoCaoThang;

        }
    }
}
