using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;


namespace LocalServerBUS
{
    public class HoaDonBUS
    {
        public static List<HoaDon> LayDanhSachHoaDon()
        {
            return HoaDonDAO.LayDanhSachHoaDon();
        }

        public static HoaDon LayHoaDon(int maHoaDon)
        {
            return HoaDonDAO.LayHoaDon(maHoaDon);
        }

        public static HoaDon ThemHoaDon(HoaDon _hoaDon)
        {
            return HoaDonDAO.ThemHoaDon(_hoaDon);
        }

        public static bool SuaHoaDon(HoaDon _hoaDon)
        {
            return HoaDonDAO.SuaHoaDon(_hoaDon);
        }

        public static HoaDon LapHoaDon(int maOrder)
        {

            Order order = OrderBUS.LayOrder(maOrder);
            if (order == null)
                return null;
            
            // B1: Tao Hoa don moi
            HoaDon hoaDon = new HoaDon();
            hoaDon.Ban = order.Ban;
            hoaDon.TaiKhoan = order.TaiKhoan;
            hoaDon.ThoiDiemLap = DateTime.Now;

            HoaDonBUS.ThemHoaDon(hoaDon);
            
            // B2: Mo ta ban ghep
            string moTaBanGhep = "";
            List<Ban> dsBanPhu = BanBUS.LayDanhSachBanThuocBanChinh(hoaDon.Ban.MaBan);
            foreach (Ban banPhu in dsBanPhu)
            {
                moTaBanGhep += banPhu.TenBan + ",";
            }
            hoaDon.MoTaBanGhep = moTaBanGhep;

            // B3: Phu Thu khu vuc
            float giaTriPhuThu = 0;
            PhuThu phuThu = PhuThuKhuVucBUS.LayPhuThu(hoaDon.Ban.KhuVuc.MaKhuVuc);
            if (phuThu != null && phuThu.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= phuThu.KetThuc)
                giaTriPhuThu = phuThu.GiaTang;

            // B4: Them nhieu ct Hoa Don
            List<ChiTietOrder> listCTOrder = ChiTietOrderBUS.LayNhieuChiTietOrder(maOrder);
            List<ChiTietHoaDon> listCTHoaDon = new List<ChiTietHoaDon>();
            foreach (ChiTietOrder ctOrder in listCTOrder)
            {
                ChiTietHoaDon ctHoaDon = new ChiTietHoaDon();
                ctHoaDon._maHoaDon = hoaDon.MaHoaDon;
                ctHoaDon.MonAn = ctOrder.MonAn;

                // Can xem xet lai so luong, vi co the Huy Order, doi mon, doi don vi tinh ... o Bep va Khach
                ctHoaDon.SoLuong = ctOrder.SoLuong;
                ctHoaDon.DonViTinh = ctOrder.DonViTinh;
                // Truong hop donGia = -1, tuc la Mon do ko co Don vi tinh !!!
                ctHoaDon.DonGiaLuuTru = ChiTietMonAnDonViTinhBUS.LayDonGia(ctHoaDon.MonAn.MaMonAn, ctHoaDon.DonViTinh.MaDonViTinh);

            }

            // B5: Ap dung khuyen mai cho tung ct Hoa Don
            // Tinh tong tien cho hoa don
            foreach (ChiTietHoaDon ctHoaDon in listCTHoaDon)
            {
                KhuyenMai kmMon = KhuyenMaiMonBUS.LayKhuyenMai(ctHoaDon.MonAn.MaMonAn);
                if (kmMon != null && kmMon.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= kmMon.KetThuc)
                {
                    if (kmMon.GiaGiam != 0)
                    {
                        ctHoaDon.ThanhTien = ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong - kmMon.GiaGiam;
                    }
                    else
                    {
                        ctHoaDon.ThanhTien = (ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong) * (100-kmMon.TiLeGiam) / 100;
                    }
                }
                hoaDon.TongTien += ctHoaDon.ThanhTien;
            }

            // B6: Ap dung khuyen mai Hoa Don


            // B7: Cap nhat Hoa don
            HoaDonBUS.SuaHoaDon(hoaDon);

            // B8: Them nhieu ct Hoa don
            ChiTietHoaDonBUS.ThemNhieuChiTietHoaDon(listCTHoaDon);

            // B9: Doi Tinh trang cac ct Order tuong ung va tach ban
            ChiTietOrderBUS.ThayDoiTinhTrangDaThanhToan(hoaDon.Ban.MaBan);


            return hoaDon;
        }
    }
}
