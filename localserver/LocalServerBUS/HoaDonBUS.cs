﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;
using System.IO;


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

        public static Stream LapHoaDonJson(int maOrder, List<String> voucherCodes)
        {
            string response;
            Order order = OrderBUS.LayOrder(maOrder);
            if (order == null || order.TinhTrang == 2)
            {
                response = "Khong tim thay order";
                return new MemoryStream(Encoding.UTF8.GetBytes(response));
            }
            
            // B1: Tao Hoa don moi
            HoaDon hoaDon = new HoaDon();
            hoaDon.Ban = order.Ban;
            hoaDon.TaiKhoan = order.TaiKhoan;
            hoaDon.ThoiDiemLap = DateTime.Now;

            //if (HoaDonBUS.ThemHoaDon(hoaDon) == null)
            //    return null;
            
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
                if (ctOrder.SoLuong == 0)       // ct order nay huy roi
                    continue;

                ChiTietHoaDon ctHoaDon = new ChiTietHoaDon();
                //ctHoaDon._maHoaDon = hoaDon.MaHoaDon;
                ctHoaDon.MonAn = ctOrder.MonAn;

                ctHoaDon.SoLuong = ctOrder.SoLuong;
                ctHoaDon.DonViTinh = ctOrder.DonViTinh;
                // Truong hop donGia = -1, tuc la Mon do ko co Don vi tinh !!!
                ctHoaDon.DonGiaLuuTru = ChiTietMonAnDonViTinhBUS.LayDonGia(ctHoaDon.MonAn.MaMonAn, ctHoaDon.DonViTinh.MaDonViTinh);

                listCTHoaDon.Add(ctHoaDon);
            }

            // B5: Ap dung khuyen mai cho tung ct Hoa Don
            // Tinh tong tien cho hoa don
            foreach (ChiTietHoaDon ctHoaDon in listCTHoaDon)
            {
                if (ctHoaDon.DonGiaLuuTru == -1)
                    continue;

                ctHoaDon.ThanhTien = ctHoaDon.DonGiaLuuTru * ctHoaDon.SoLuong;
                KhuyenMai kmMon = KhuyenMaiMonBUS.LayKhuyenMai(ctHoaDon.MonAn.MaMonAn);
                if (kmMon != null && kmMon.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= kmMon.KetThuc)
                {
                    ctHoaDon.GiaTriKhuyenMaiLuuTru = kmMon.GiaGiam + (kmMon.TiLeGiam / 100f)* ctHoaDon.ThanhTien;
                    ctHoaDon.ThanhTien -= ctHoaDon.GiaTriKhuyenMaiLuuTru;
                }

                hoaDon.TongTien += ctHoaDon.ThanhTien;                
            }

            // B6: Ap dung khuyen mai Hoa Don
            //KhuyenMai kmHoaDon = KhuyenMaiHoaDonBUS.LayKhuyenMai(hoaDon.TongTien);
            //if (kmHoaDon != null && kmHoaDon.BatDau <= hoaDon.ThoiDiemLap && hoaDon.ThoiDiemLap <= kmHoaDon.KetThuc)
            //{
            //    hoaDon.TongTien -= kmHoaDon.GiaGiam + hoaDon.TongTien * (kmHoaDon.TiLeGiam / 100f);
            //}

            // check and use voucher
            foreach (String code in voucherCodes)
            {
                ChiTietVoucher c = ChiTietVoucherBUS.LayChiTietSanSang(code);
                if (c == null)
                {
                    response = "Khong dung voucher duoc. Ma voucher: " + code;
                    return new MemoryStream(Encoding.UTF8.GetBytes(response));
                }

                //if (ChiTietVoucherBUS.SuDungVoucher(code) == false)
                //    return null;

                hoaDon.TongTien -= c.Voucher.GiaGiam;
                //c.Active = false;
            }

            if (HoaDonBUS.ThemHoaDon(hoaDon) == null)
            {
                response = "Khong lap hoa don duoc";
                return new MemoryStream(Encoding.UTF8.GetBytes(response));
            }

            foreach (ChiTietHoaDon c in listCTHoaDon)
                c.HoaDon = hoaDon;

            // B7: Cap nhat Hoa don
            //if (!HoaDonBUS.SuaHoaDon(hoaDon)) return null;

            // B8: Them nhieu ct Hoa don
            if (ChiTietHoaDonDAO.ThemNhieuChiTietHoaDon(listCTHoaDon) == null)
            {
                response = "Khong lap hoa don duoc";
                return new MemoryStream(Encoding.UTF8.GetBytes(response));
            }
            //ChiTietHoaDonBUS.ThemNhieuChiTietHoaDon(listCTHoaDon);

            // B9: Doi Tinh trang cac ct Order tuong ung va tach ban
            //ChiTietOrderBUS.ThayDoiTinhTrangDaThanhToan(hoaDon.Ban.MaBan);

            // cap nhat tinh trang Order: da thanh toan
            order.TinhTrang = 4;
            //OrderBUS.SuaOrder(order);

            //ThucDonDienTu.DataContext.SubmitChanges();  // submit here to change for later query

            var varOrderBanCu = ThucDonDienTu.DataContext.Orders.Where(o => o._maBan == order._maBan && o.TinhTrang != 4);
            MyLogger.Log(varOrderBanCu.Count().ToString());
            if (varOrderBanCu.Count() == 1)     // order chuyen di la order cuoi cung
            {
                var varBanCu = ThucDonDienTu.DataContext.Bans.Where(b => b.BanChinh.MaBan == order.Ban.MaBan);
                if (varBanCu.Count() == 0)
                    return new MemoryStream(Encoding.UTF8.GetBytes("Lỗi server."));

                foreach (Ban b in varBanCu)
                {
                    b.Active = true;
                    b.BanChinh = null;
                }
            }

            MyLogger.Log("table after payment: " + order.Ban.Active);

            ThucDonDienTu.DataContext.SubmitChanges();

            response = hoaDon.MaHoaDon.ToString();
            return new MemoryStream(Encoding.UTF8.GetBytes(response));
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
