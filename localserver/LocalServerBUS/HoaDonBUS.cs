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

        public static List<HoaDon> LayDanhSachHoaDonTheoNgay(DateTime ngay)
        {
            return HoaDonDAO.LayDanhSachHoaDonTheoNgay(ngay);
        }

        public static List<HoaDon> LayDanhSachHoaDonTheoThang(DateTime ngay)
        {
            return HoaDonDAO.LayDanhSachHoaDonTheoThang(ngay);
        }

        public static List<HoaDon> LayDanhSachHoaDonTheoThoiGian(DateTime ngayBatDau, DateTime ngayKetThuc)
        {
            return HoaDonDAO.LayDanhSachHoaDonTheoThoiGian(ngayBatDau, ngayKetThuc);
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

            hoaDon.TongTien += giaTriPhuThu;

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
                if (ctHoaDon.DonGiaLuuTru == -1)
                    continue;

                KhuyenMai kmMon = KhuyenMaiMonBUS.LayKhuyenMai(ctHoaDon.MonAn.MaMonAn);
                if (kmMon != null && kmMon.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= kmMon.KetThuc)
                {
                    if (kmMon.GiaGiam != 0)
                    {
                        ctHoaDon.GiaTriKhuyenMaiLuuTru = kmMon.GiaGiam;
                        ctHoaDon.ThanhTien = ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong - ctHoaDon.GiaTriKhuyenMaiLuuTru;
                        
                    }
                    else
                    {
                        ctHoaDon.GiaTriKhuyenMaiLuuTru = (ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong) * (kmMon.TiLeGiam) / 100;
                        ctHoaDon.ThanhTien = (ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong) - ctHoaDon.GiaTriKhuyenMaiLuuTru;

                    }
                }
                hoaDon.TongTien += ctHoaDon.ThanhTien;
                
            }

            // B6: Ap dung khuyen mai Hoa Don
            KhuyenMai kmHoaDon = KhuyenMaiHoaDonBUS.LayKhuyenMai(hoaDon.TongTien);
            if (kmHoaDon != null && kmHoaDon.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= kmHoaDon.KetThuc)
            {
                if (kmHoaDon.GiaGiam != 0)
                {
                    hoaDon.TongTien -= kmHoaDon.GiaGiam;
                }
                else
                {
                    hoaDon.TongTien -= (hoaDon.TongTien) * (100 - kmHoaDon.TiLeGiam) / 100;
                }
            }

            // B7: Cap nhat Hoa don
            HoaDonBUS.SuaHoaDon(hoaDon);

            // B8: Them nhieu ct Hoa don
            ChiTietHoaDonBUS.ThemNhieuChiTietHoaDon(listCTHoaDon);

            // B9: Doi Tinh trang cac ct Order tuong ung va tach ban
            ChiTietOrderBUS.ThayDoiTinhTrangDaThanhToan(hoaDon.Ban.MaBan);


            return hoaDon;
        }

        public static float LayTongKhuyenMai(int maHoaDon)
        {

            float tongKhuyenMai = 0;
            List<ChiTietHoaDon> listCTHoaDon = ChiTietHoaDonBUS.LayNhieuChiTietHoaDon(maHoaDon);
            foreach (ChiTietHoaDon ct in listCTHoaDon)
            {
                tongKhuyenMai += ct.GiaTriKhuyenMaiLuuTru;
            }

            return tongKhuyenMai;

        }
    }
}
