using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Configuration;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.Text;
using System.Web;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using System.IO;
using System.ServiceModel.Web;

namespace LocalServerWeb
{
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    public class LocalService : ILocalService
    {
        // Khoi Tao CSDL
        public LocalService()
        {
            SharedCode.KhoiTaoCSDL();
        }

        // Khu Vuc
        public List<KhuVuc> LayDanhKhuVuc(string junk)
        {
            try
            {
                return KhuVucBUS.LayDanhSachKhuVuc();
            }
            catch (Exception ex)
            {
                MyLogger.Log(ex.StackTrace);
                return null;
            }
        }

        
        // Ban
        public List<Ban> LayDanhSachBan(string junk)
        {
            var listBan = new List<Ban>();
            try
            {
                listBan = BanBUS.LayDanhSachBan();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listBan;
        }

        public Ban LayBan(int maBan, string junk)
        {
            var ban = new Ban();
            try
            {
                ban = BanBUS.LayBan(maBan);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ban;
        }

        public List<Ban> LayDanhSachBanChinh(string junk)
        {
            try
            {
                return BanBUS.LayDanhSachBanChinh();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh, string junk)
        {
            try
            {
                return BanBUS.LayDanhSachBanThuocBanChinh(maBanChinh);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc, string junk)
        {
            try
            {
                return BanBUS.LayDanhSachBan(maKhuVuc);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool TachBan(int maBan)
        {
            try
            {
                return BanBUS.TachBan(maBan);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public bool GhepBan(RequestGhepBan request)
        {
            try
            {
                return BanBUS.GhepBan(request);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public bool CapNhatBan(Ban ban)
        {
            try
            {
                return BanBUS.CapNhat(ban);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public bool ChuyenBan(int maBanChuyenDi, int maBanChuyenDen)
        {
            try
            {
                return BanBUS.ChuyenBan(maBanChuyenDi, maBanChuyenDen);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }



        // Mon An
        public List<MonAn> LayDanhSachMonAn(string junk)
        {
            var listMonAn = new List<MonAn>();
            try
            {
                listMonAn = MonAnBUS.LayDanhSachMonAn();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listMonAn;
        }

        public List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc, string junk)
        {
            var listMonAn = new List<MonAn>();
            try
            {
                listMonAn = MonAnBUS.LayDanhSachMonAnTheoDanhMuc(maDanhMuc);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listMonAn;

        }

        public MonAn LayMonAn(int maMonAn, string junk)
        {
            var monAn = new MonAn();
            try
            {
                monAn = MonAnBUS.LayMonAn(maMonAn);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return monAn;
        }

        public bool DanhGiaMonAn(int maMonAn, float diemDanhGia, string junk)
        {
            try
            {
                return MonAnBUS.DanhGiaMonAn(maMonAn, diemDanhGia);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public bool DanhGiaNhieuMonAn(List<DanhGiaMonAn> listDanhGiaMonAn)
        {
            try
            {
                return MonAnBUS.DanhGiaNhieuMonAn(listDanhGiaMonAn);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }


        // Don Vi Tinh
        public List<DonViTinh> LayDanhSachDonViTinh(string junk)
        {
            var listDonViTinh = new List<DonViTinh>();
            try
            {
                listDonViTinh = DonViTinhBUS.LayDanhSachDonViTinh();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listDonViTinh;
        }

        
        // Danh Muc
        public List<DanhMuc> LayDanhSachDanhMuc(string junk)
        {
            var listDanhMuc = new List<DanhMuc>();
            try
            {
                listDanhMuc = DanhMucBUS.LayDanhSachDanhMuc();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listDanhMuc;
        }


        // Chi Tiet Danh Muc Da Ngon Ngu
        public ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu, string junk)
        {
            var ct = new ChiTietDanhMucDaNgonNgu();
            try
            {
                ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ct;
        }

        public List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu(string junk)
        {
            var listChiTietDanhMucDaNgonNgu = new List<ChiTietDanhMucDaNgonNgu>();
            try
            {
                listChiTietDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayDanhSachChiTietDanhMucDaNgonNgu();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietDanhMucDaNgonNgu;
        }

        // Chi Tiet Don Vi Tinh Da Ngon Ngu
        public ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu, string junk)
        {
            var ct = new ChiTietDonViTinhDaNgonNgu();
            try
            {
                ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(maDonViTinh, maNgonNgu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ct;
        }

        public List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu(string junk)
        {
            var listChiTietDonViTinhDaNgonNgu = new List<ChiTietDonViTinhDaNgonNgu>();
            try
            {
                listChiTietDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayDanhSachChiTietDonViTinhDaNgonNgu();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietDonViTinhDaNgonNgu;
        }


        // Chi Tiet Mon An Da Ngon Ngu
        public ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu, string junk)
        {
            var ct = new ChiTietMonAnDaNgonNgu();
            try
            {
                ct = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ct;
        }

        public List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu(string junk)
        {
            var listChiTietMonAnDaNgonNgu = new List<ChiTietMonAnDaNgonNgu>();
            try
            {
                listChiTietMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayDanhSachChiTietMonAnDaNgonNgu();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietMonAnDaNgonNgu;
        }


        // Chi Tiet Mon An Don Vi Tinh
        public float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh, string junk)
        {
            float donGia = -1;
            try
            {
                donGia = ChiTietMonAnDonViTinhBUS.LayDonGia(maMonAn, maDonViTinh);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return donGia;
        }

        public ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh, string junk)
        {
            var ct = new ChiTietMonAnDonViTinh();
            try
            {
                ct = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(maMonAn, maDonViTinh);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ct;
        }

        public List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh(string junk)
        {
            var listChiTietMonAnDonViTinh = new List<ChiTietMonAnDonViTinh>();
            try
            {
                listChiTietMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinh();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietMonAnDonViTinh;
        }


        // Ngon Ngu
        public List<NgonNgu> LayDanhSachNgonNgu(string junk)
        {
            var listNgonNgu = new List<NgonNgu>();
            try
            {
                listNgonNgu = NgonNguBUS.LayDanhSachNgonNgu();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listNgonNgu;
        }


        // Ti Gia
        public List<TiGia> LayDanhSachTiGia(string junk)
        {
            var listTiGia = new List<TiGia>();
            try
            {
                listTiGia = TiGiaBUS.LayDanhSachTiGia();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listTiGia;
        }



        // Nhom Tai Khoan
        public List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan(string junk)
        {
            var listNhomTaiKhoan = new List<NhomTaiKhoan>();
            try
            {
                listNhomTaiKhoan = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listNhomTaiKhoan;
        }


        // Tai Khoan
        public List<TaiKhoan> LayDanhSachTaiKhoan(string junk)
        {
            var listTaiKhoan = new List<TaiKhoan>();
            try
            {
                listTaiKhoan = TaiKhoanBUS.LayDanhSachTaiKhoan();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listTaiKhoan;
        }



        // Phu Thu
        public List<PhuThu> LayDanhSachPhuThu(string junk)
        {
            var listPhuThu = new List<PhuThu>();
            try
            {
                listPhuThu = PhuThuBUS.LayDanhSachPhuThu();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listPhuThu;
        }


        // Phu Thu Khu Vuc
        public List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc(string junk)
        {
            var listPhuThuKhuVuc = new List<PhuThuKhuVuc>();
            try
            {
                listPhuThuKhuVuc = PhuThuKhuVucBUS.LayDanhSachPhuThuKhuVuc();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listPhuThuKhuVuc;
        }

        public List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu, string junk)
        {
            var listPhuThuKhuVuc = new List<PhuThuKhuVuc>();
            try
            {
                listPhuThuKhuVuc = PhuThuKhuVucBUS.LayDanhSachPhuThuKhuVucTheoMa(maPhuThu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listPhuThuKhuVuc;
        }

        // Khuyen Mai
        public List<KhuyenMai> LayDanhSachKhuyenMai(string junk)
        {
            var listKhuyenMai = new List<KhuyenMai>();
            try
            {
                listKhuyenMai = KhuyenMaiBUS.LayDanhSachKhuyenMai();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMai;
        }

        // Khuyen Mai Khu Vuc
        public List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc(string junk)
        {
            var listKhuyenMaiKhuVuc = new List<KhuyenMaiKhuVuc>();
            try
            {
                listKhuyenMaiKhuVuc = KhuyenMaiKhuVucBUS.LayDanhSachKhuyenMaiKhuVuc();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiKhuVuc;
        }

        public List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai, string junk)
        {
            var listKhuyenMaiKhuVuc = new List<KhuyenMaiKhuVuc>();
            try
            {
                listKhuyenMaiKhuVuc = KhuyenMaiKhuVucBUS.LayDanhSachKhuyenMaiKhuVucTheoMa(maKhuyenMai);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiKhuVuc;
        }


        // Khuyen Mai Danh Muc
        public List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc(string junk)
        {
            var listKhuyenMaiDanhMuc = new List<KhuyenMaiDanhMuc>();
            try
            {
                listKhuyenMaiDanhMuc = KhuyenMaiDanhMucBUS.LayDanhSachKhuyenMaiDanhMuc();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiDanhMuc;
        }

        public List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai, string junk)
        {
            var listKhuyenMaiDanhMuc = new List<KhuyenMaiDanhMuc>();
            try
            {
                listKhuyenMaiDanhMuc = KhuyenMaiDanhMucBUS.LayDanhSachKhuyenMaiDanhMucTheoMa(maKhuyenMai);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiDanhMuc;
        }

        // Khuyen Mai Mon
        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon(string junk)
        {
            var listKhuyenMaiMon = new List<KhuyenMaiMon>();
            try
            {
                listKhuyenMaiMon = KhuyenMaiMonBUS.LayDanhSachKhuyenMaiMon();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiMon;
        }

        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai, string junk)
        {
            var listKhuyenMaiMon = new List<KhuyenMaiMon>();
            try
            {
                listKhuyenMaiMon = KhuyenMaiMonBUS.LayDanhSachKhuyenMaiMonTheoMa(maKhuyenMai);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiMon;
        }

        // Khuyen Mai Hoa Don
        public List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon(string junk)
        {
            var listKhuyenMaiHoaDon = new List<KhuyenMaiHoaDon>();
            try
            {
                listKhuyenMaiHoaDon = KhuyenMaiHoaDonBUS.LayDanhSachKhuyenMaiHoaDon();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiHoaDon;
        }

        public List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai, string junk)
        {
            var listKhuyenMaiHoaDon = new List<KhuyenMaiHoaDon>();
            try
            {
                listKhuyenMaiHoaDon = KhuyenMaiHoaDonBUS.LayDanhSachKhuyenMaiHoaDonTheoMa(maKhuyenMai);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listKhuyenMaiHoaDon;
        }


        // Bo Phan Che Bien
        public List<BoPhanCheBien> LayDanhSachBoPhanCheBien(string junk)
        {
            var listBoPhanCheBien = new List<BoPhanCheBien>();
            try
            {
                listBoPhanCheBien = BoPhanCheBienBUS.LayDanhSachBoPhanCheBien();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listBoPhanCheBien;
        }

        // Chi Tiet Mon Lien Quan
        public List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(string junk)
        {
            var listChiTietMonLienQuan = new List<ChiTietMonLienQuan>();
            try
            {
                listChiTietMonLienQuan = ChiTietMonLienQuanBUS.LayDanhSachChiTietMonLienQuan();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietMonLienQuan;
        }

        public List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanTheoMaMon(int maMonAn, string junk)
        {
            var listChiTietMonLienQuan = new List<ChiTietMonLienQuan>();
            try
            {
                listChiTietMonLienQuan = ChiTietMonLienQuanBUS.LayDanhSachChiTietMonLienQuan(maMonAn);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietMonLienQuan;
        }

        

        // Order
        public List<Order> LayDanhSachOrder(string junk)
        {
            var listOrder = new List<Order>();
            try
            {
                listOrder = OrderBUS.LayDanhSachOrder();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listOrder;
        }

        public Order LayOrder(int maOrder, string junk)
        {
            var Order = new Order();
            try
            {
                Order = OrderBUS.LayOrder(maOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return Order;
        }


        public Order ThemOrder(Order _order)
        {
            try
            {
                return OrderBUS.ThemOrder(_order);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool SuaOrder(Order _order)
        {
            try
            {
                return OrderBUS.SuaOrder(_order);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public List<ChiTietOrder> LapOrder(int maTaiKhoan, int maBan, List<ChiTietOrder> _listChiTietOrder)
        {
            try
            {
                return OrderBUS.LapOrder(maTaiKhoan, maBan, _listChiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }



        // Chi Tiet Order
        public ChiTietOrder LayChiTietOrder(int maChiTietOrder, string junk)
        {
            var ChiTietOrder = new ChiTietOrder();
            try
            {
                ChiTietOrder = ChiTietOrderBUS.LayChiTietOrder(maChiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ChiTietOrder;
        }

        public List<ChiTietOrder> LayNhieuChiTietOrder(int maOrder, string junk)
        {
            var listChiTietOrder = new List<ChiTietOrder>();
            try
            {
                listChiTietOrder = ChiTietOrderBUS.LayNhieuChiTietOrder(maOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietOrder;
        }

        public List<ChiTietOrder> LayNhieuChiTietOrderChuaThanhToan(int maBan, string junk)
        {
            try
            {
                return ChiTietOrderBUS.LayNhieuChiTietOrderChuaThanhToan(maBan);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool DuocPhepThanhToan(int maBan)
        {
            try
            {
                return ChiTietOrderBUS.DuocPhepThanhToan(maBan);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            try
            {
                return ChiTietOrderBUS.ThemChiTietOrder(_chiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder)
        {
            try
            {
                return ChiTietOrderBUS.ThemNhieuChiTietOrder(_listChiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool SuaChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            try
            {
                return ChiTietOrderBUS.SuaChiTietOrder(_chiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public ChiTietHuyOrder LaySoLuongChoPhepHuyOrder(int maChiTietOrder, string junk)
        {
            try
            {
                return ChiTietOrderBUS.LaySoLuongChoPhepHuyOrder(maChiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool YeuCauHuyOrder(int maChiTietOrder, int soLuongYeuCauHuy, string ghiChu)
        {
            try
            {
                return ChiTietOrderBUS.YeuCauHuyOrder(maChiTietOrder, soLuongYeuCauHuy, ghiChu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }


        // Chi Tiet Huy Order
        public ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietHuyOrder, string junk)
        {
            var ChiTietHuyOrder = new ChiTietHuyOrder();
            try
            {
                ChiTietHuyOrder = ChiTietHuyOrderBUS.LayChiTietHuyOrder(maChiTietHuyOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ChiTietHuyOrder;
        }

        public bool ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            try
            {
                return ChiTietHuyOrderBUS.ThemChiTietHuyOrder(_chiTietHuyOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        public bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            try
            {
                return ChiTietHuyOrderBUS.SuaChiTietHuyOrder(_chiTietHuyOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        // Hoa Don
        public HoaDon LayHoaDon(int maHoaDon, string junk)
        {
            var hoaDon = new HoaDon();
            try
            {
                hoaDon = HoaDonBUS.LayHoaDon(maHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return hoaDon;
        }

        public List<HoaDon> LayDanhSachHoaDon(string junk)
        {
            var listHoaDon = new List<HoaDon>();
            try
            {
                listHoaDon = HoaDonBUS.LayDanhSachHoaDon();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listHoaDon;
        }

        public HoaDon ThemHoaDon(HoaDon _hoaDon)
        {
            try
            {
                return HoaDonBUS.ThemHoaDon(_hoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public bool SuaHoaDon(HoaDon _hoaDon)
        {
            try
            {
                return HoaDonBUS.SuaHoaDon(_hoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        
        // Chi Tiet Hoa Don
        public ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon, string junk)
        {
            var ChiTietHoaDon = new ChiTietHoaDon();
            try
            {
                ChiTietHoaDon = ChiTietHoaDonBUS.LayChiTietHoaDon(maChiTietHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return ChiTietHoaDon;
        }

        public List<ChiTietHoaDon> LayNhieuChiTietHoaDon(int maHoaDon, string junk)
        {
            var listChiTietHoaDon = new List<ChiTietHoaDon>();
            try
            {
                listChiTietHoaDon = ChiTietHoaDonBUS.LayNhieuChiTietHoaDon(maHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listChiTietHoaDon;
        }

        public ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            try
            {
                return ChiTietHoaDonBUS.ThemChiTietHoaDon(_chiTietHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public int ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            int ketQua = 0;
            try
            {
                ketQua = ChiTietHoaDonBUS.ThemNhieuChiTietHoaDon(_listChiTietHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }

            // Neu them nhieu ct hoa don thanh cong
            if (ketQua == 0)
            {
                if(_listChiTietHoaDon != null && _listChiTietHoaDon.Count > 0 && _listChiTietHoaDon[0] != null && _listChiTietHoaDon[0].HoaDon != null)
                {
                    int maHoaDon = _listChiTietHoaDon[0].HoaDon.MaHoaDon;
                    Reports.ReportManager.PrintBill(maHoaDon, SharedCode.GetCurrentLanguage(new HttpSessionStateWrapper(HttpContext.Current.Session)).MaNgonNgu);
                    //Reports.ReportManager.PrintBill(maHoaDon, 1);
                }
                
            }

            return ketQua;
        }

        public bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon)
        {
            try
            {
                return ChiTietHoaDonBUS.SuaChiTietHoaDon(_chiTietHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        // Voucher
        public List<Voucher> LayDanhSachVoucher(string junk)
        {
            var listVoucher = new List<Voucher>();
            try
            {
                listVoucher = VoucherBUS.LayDanhSachVoucher();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listVoucher;
        }


        public float KiemTraVoucher(string soPhieu, float tongHoaDon)
        {
            try
            {
                return VoucherBUS.KiemTraVoucher(soPhieu, tongHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return 0;
        }

        public bool SuDungVoucher(string soPhieu)
        {
            try
            {
                return ChiTietVoucherBUS.SuDungVoucher(soPhieu);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return false;
        }

        // Bao Cao
        public List<BaoCaoNgay> LayBaoCaoNgay(int ngay, int thang, int nam, string junk)
        {
            var listBaoCaoNgay = new List<BaoCaoNgay>();
            try
            {
                listBaoCaoNgay = BaoCaoBUS.LayBaoCaoNgay(ngay, thang, nam);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listBaoCaoNgay;
        }

        public List<BaoCaoThang> LayBaoCaoThang(int thang, int nam, string junk)
        {
            var listBaoCaoThang = new List<BaoCaoThang>();
            try
            {
                listBaoCaoThang = BaoCaoBUS.LayBaoCaoThang(thang, nam);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listBaoCaoThang;
        }

        // Chung Thuc
        public TaiKhoan ChungThucMobilePhucVu(string tenDangNhap, string matKhau)
        {
            try
            {
                TaiKhoan taiKhoan = TaiKhoanBUS.ChungThucMobilePhucVu(tenDangNhap, matKhau);
                if (taiKhoan != null)
                {
                    HttpContext.Current.Session["taiKhoan"] = taiKhoan;
                    return taiKhoan;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception)
            {
                return null;
            }
        }

        public TaiKhoan ChungThucMobileQuanLy(string tenDangNhap, string matKhau)
        {
            try
            {
                TaiKhoan taiKhoan = TaiKhoanBUS.ChungThucMobileQuanLy(tenDangNhap, matKhau);
                if (taiKhoan != null)
                {
                    HttpContext.Current.Session["taiKhoan"] = taiKhoan;
                    return taiKhoan;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception)
            {
                return null;
            }
        }



        // Picture
        public Stream GetPicture(string path, string junk)
        {
            return PictureBUS.GetPicture(path);
        }

        public bool AddPicture(string path, Stream content)
        {
            return PictureBUS.AddPicture(path, content);
        }


        // Nha Hang
        public NhaHang LayThongTinNhaHang(string junk)
        {
            try
            {
                return NhaHangBUS.LayThongTinNhaHang();
            }
            catch (Exception)
            {
                return null;
            }
        }



        // Testing purpose
        public string AddText()
        {
            return "123";
        }



        // DangNhap
        public bool DangNhap(Stream body)
        {
            try
            {
                //MyLogger.Log(HttpContext.Current.Request.Cookies.Get("c1").Value);
                //MyLogger.Log(HttpContext.Current.Request.Cookies.Get("MatKhau").Name);

                string str = new StreamReader(body).ReadToEnd();
                NameValueCollection nvc = HttpUtility.ParseQueryString(str);
                string ten = nvc["tenDangNhap"];
                string ma = nvc["matKhau"];
                MyLogger.Log(ten + " " + ma);
                TaiKhoan tk = TaiKhoanBUS.KiemTraTaiKhoan(ten, SharedCode.Hash(ma));
                if (tk == null) return false;
                HttpContext.Current.Session["taiKhoan"] = tk;

                //HttpCookie c = new HttpCookie("loggedin", "true");
                //c.Expires = DateTime.Now.AddSeconds(30);
                
            }
            catch (Exception e)
            {
                MyLogger.Log(e.StackTrace);
                return false;                
            }

            return true;
        }

        public bool DangXuat(string junk)
        {
            if (HttpContext.Current.Session["taiKhoan"] != null)
            {
                HttpContext.Current.Session["taiKhoan"] = null;
                return true;
            }
            return false;
        }

        public int PhepCong(int a, int b)
        {
            return a + b;
        }

        // Kiem Tra Server
        public string KiemTraServer(string thamSo, string junk)
        {
            return "FIT HCMUS " + thamSo;
        }


        public string TestGET(string thamSo, string junk)
        {
            if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session)))
                return "Chua chung thuc";
            return "GET OK " + thamSo;
        }

        public string TestPOST(string thamSo)
        {
            return "POST OK " + thamSo;
        }

        public string TestPUT()
        {
            return "PUT OK ";
        }

        public List<Foo> LayDanhSachFoo()
        {
            var listMonAn = new List<Foo>();
            try
            {
                listMonAn = FooBUS.LayDanhSachFoo();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return listMonAn;
        }

        /*JSON SERVICE AREA*/

        public List<Ban> LayDanhSachBanJson(string junk)
        {
            return LayDanhSachBan(junk);
        }

        public List<KhuVuc> LayDanhSachKhuVucJson(string junk)
        {
            MyLogger.Log("synkhuvucjson");
            try
            {
                return LayDanhKhuVuc(junk);
            }
            catch (Exception ex)
            {
                MyLogger.Log(ex.StackTrace);
                return null;
            }

        }

        public List<DanhMuc> LayDanhSachDanhMucJson(string junk)
        {
            return LayDanhSachDanhMuc(junk);
        }

        public List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNguJson(string junk)
        {
            return LayDanhSachChiTietDanhMucDaNgonNgu(junk);
        }

        public List<DonViTinh> LayDanhSachDonViTinhJson(string junk)
        {
            return LayDanhSachDonViTinh(junk);
        }

        public List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNguJson(string junk)
        {
            return LayDanhSachChiTietDonViTinhDaNgonNgu(junk);
        }

        public List<MonAn> LayDanhSachMonAnJson(string junk)
        {
            return LayDanhSachMonAn(junk);
        }

        public bool DanhGiaMonAnJson(int maMonAn, float diemDanhGia, string junk)
        {
            return DanhGiaMonAn(maMonAn, diemDanhGia, junk);
        }

        public List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNguJson(string junk)
        {
            return LayDanhSachChiTietMonAnDaNgonNgu(junk);
        }

        public List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinhJson(string junk)
        {
            return LayDanhSachChiTietMonAnDonViTinh(junk);
        }
                
        public List<KhuVuc> LayDanhKhuVucJson(string junk)
        {
            return LayDanhKhuVuc(junk);
        }

        public List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanJson(string junk)
        {
            return LayDanhSachChiTietMonLienQuan(junk);
        }

        public List<KhuyenMai> LayDanhSachKhuyenMaiCoHieuLucJson(string junk)
        {
            return KhuyenMaiBUS.LayDanhSachKhuyenMaiCoHieuLucJson();
        }

        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonCoHieuLucJson(string junk)
        {
            return KhuyenMaiMonBUS.LayDanhSachKhuyenMaiMonCoHieuLucJson();
        }

        public List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucJson(string junk)
        {
            return LayDanhSachKhuyenMaiDanhMuc(junk);
        }

        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonJson(string junk)
        {
            return LayDanhSachKhuyenMaiMon(junk);
        }

        public List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonJson(string junk)
        {
            return LayDanhSachKhuyenMaiHoaDon(junk);
        }

        public List<NgonNgu> LayDanhSachNgonNguJson(string junk)
        {
            return LayDanhSachNgonNgu(junk);
        }

        public List<NhomTaiKhoan> LayDanhSachNhomTaiKhoanJson(string junk)
        {
            return LayDanhSachNhomTaiKhoan(junk);
        }

        public List<TaiKhoan> LayDanhSachTaiKhoanJson(string junk)
        {
            return LayDanhSachTaiKhoan(junk);
        }

        public List<TiGia> LayDanhSachTiGiaJson(string junk)
        {
            return LayDanhSachTiGia(junk);
        }

        public List<PhuThu> LayDanhSachPhuThuCoHieuLucJson(string junk)
        {
            return PhuThuBUS.LayDanhSachPhuThuCoHieuLucJson();
        }

        public List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucCoHieuLucJson(string junk)
        {
            return PhuThuBUS.LayDanhSachPhuThuKhuVucCoHieuLucJson();
        }

        public Stream LapHoaDonJson(int maOrder, List<String> voucherCodes)
        {
            try
            {
                if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                    return new MemoryStream(Encoding.UTF8.GetBytes("Khong xac thuc duoc yeu cau."));

                return HoaDonBUS.LapHoaDonJson(maOrder, voucherCodes);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
                return new MemoryStream(Encoding.UTF8.GetBytes("Loi server"));
            }
        }

        public List<ChiTietOrder> LapOrderJson(int maTaiKhoan, int maBan,  List<ChiTietOrder> _listChiTietOrder)
        {
            return LapOrder(maTaiKhoan, maBan, _listChiTietOrder);
        }

        public bool GhepBanJson(TableIdSelection tabSel)
        {
            try
            {
                MyLogger.Log(tabSel.MainTabId.ToString());
                MyLogger.Log(tabSel.TabIdList.Count.ToString());
                return BanBUS.GhepBanJson(tabSel);
            }
            catch (Exception ex)
            {
                MyLogger.Log(ex.StackTrace);
                return false;
            }
        }

        public bool TachBanJson(int maBan, string junk)
        {
            return BanBUS.TachBanJson(maBan);
        }

        public List<Order> LayDanhSachOrderChuaThanhToanJson(int maBan, string junk)
        {
            return OrderBUS.LayNhieuOrderChuaThanhToan(maBan);
        }

        public List<ChiTietOrder> LayDanhSachChiTietOrderJson(int maOrder, string junk)
        {
            return ChiTietOrderBUS.LayDanhSachChiTietOrderJson(maOrder);
        }

        public List<Ban> LayDanhSachBanChinhJson(int maKhuVuc, string junk)
        {
            return BanBUS.LayDanhSachBanChinhJson(maKhuVuc);
        }

        public List<Ban> LayDanhSachBanThuocBanChinhJson(int maBanChinh, string junk)
        {
            return BanBUS.LayDanhSachBanThuocBanChinh(maBanChinh);
        }

        public int ThemOrderJson(Order _order)
        {
            Order result = OrderBUS.ThemOrder(_order);
            if (result == null) return -1;

            return result.MaOrder;
        }

        public bool ThemNhieuChiTietOrderJson(List<ChiTietOrder> _listChiTietOrder)
        {
            if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                return false;

            List<ChiTietOrder> result = ChiTietOrderBUS.ThemNhieuChiTietOrderJson(_listChiTietOrder);
            if (result == null) return false;

            return true;
        }

        public int CheckYeuCauHuyChiTietOrderJson(int maChiTiet, int soLuong)
        {
            return -1;
        }

        public List<PhuThu> LayDanhSachPhuThuApDungJson(int maOrder)
        {
            return PhuThuBUS.LayDanhSachPhuThuApDungJson(maOrder);
        }

        public List<KhuyenMai> LayDanhSachKhuyenMaiApDungJson(int maOrder)
        {
            return KhuyenMaiBUS.LayDanhSachKhuyenMaiApDungJson(maOrder);
        }

        public KhuyenMai LayKhuyenMaiMonApDung(int maMon)
        {
            return KhuyenMaiMonBUS.LayKhuyenMaiApDung(maMon);
        }

        public Voucher LayVoucherJson(string code, float tongHoaDon)
        {
            return VoucherBUS.LayVoucher(code, tongHoaDon);
        }

        public bool DungVoucherJson(string code)
        {
            return SuDungVoucher(code);
        }

        public string GetImageJson(string path, string junk)
        {
            return PictureBUS.GetBase64Image(path);
        }

        public bool KiemTraTaiKhoanJson(string username, string password, string junk)
        {
            try
            {
                TaiKhoan tk = TaiKhoanBUS.KiemTraTaiKhoan(username, SharedCode.Hash(password));
                if (tk == null)
                    return false;
            }
            catch (Exception e)
            {
                return false;
            }

            return true;
        }

        public bool ChuyenBanJson(int maOrder, int maBanMoi)
        {
            if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                return false;

            try
            {
                return OrderBUS.ChuyenBanJson(maOrder, maBanMoi);
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public bool TachNhomBanJson(int maBan, string junk)
        {
            try
            {
                if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                    return false;

                return BanBUS.TachNhomBanJson(maBan);
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public bool SuaChiTietOrderJson(ChiTietOrder holder)
        {
            try
            {
                if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                    return false;

                return ChiTietOrderBUS.SuaChiTietOrderJson(holder);
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        //public int LaySoLuongChuaCheBien(int maChiTiet)
        //{
        //    return ChiTietOrderBUS.LaySoLuongChuaCheBien(maChiTiet);
        //}

        public ChiTietOrder LayChiTietOrderJson(int maChiTiet, string junk)
        {
            return ChiTietOrderBUS.LayChiTietOrder(maChiTiet);
        }

        public Order LayOrderJson(int maOrder, string junk)
        {
            return OrderBUS.LayOrder(maOrder);
        }

        public bool TachOrderJson(List<SplittingOrderItem> dsMaChiTiet)
        {
            if (!SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(HttpContext.Current.Session), HttpContext.Current.Request.Cookies))
                return false;
            
            try
            {
                return OrderBUS.TachOrder(dsMaChiTiet);
            }
            catch (Exception ex)
            {
                MyLogger.Log(ex.Message);
                return false;
                
            }
        }

        public bool DangNhapJson(Stream body)
        {
            return DangNhap(body);
        }

        public Stream TestGetJson()
        {
            MyLogger.Log("testgetjson");
            string response = "Hi, time is " + DateTime.Now + " and server's fine.";
            //WebOperationContext.Current.OutgoingResponse.ContentType = "application/json; charset=utf-8";
            return new MemoryStream(Encoding.UTF8.GetBytes(response));
        }

        public TableIdSelection TestTabSel()
        {
            TableIdSelection re = new TableIdSelection();
            re.MainTabId = 1;
            re.TabIdList = new List<int> { 1, 2, 3};

            return re;
        }

        public int LayDoanhThuNgay(int ngay, int thang, int nam)
        {
            int doanhThu = 0;
            try
            {
                DateTime date = new DateTime(nam, thang, ngay);
                List<HoaDon> list = HoaDonBUS.LayDanhSachHoaDonTheoNgay(date);
                foreach (HoaDon h in list)
                    doanhThu += (int) h.TongTien;
            }
            catch (Exception ex)
            {
                return 0;
            }

            return doanhThu;
        }

        public int LayDoanhThuThang(int thang, int nam)
        {
            int doanhThu = 0;
            try
            {
                DateTime date = new DateTime(nam, thang, 1);
                List<HoaDon> list = HoaDonBUS.LayDanhSachHoaDonTheoThang(date);
                foreach (HoaDon h in list)
                    doanhThu += (int)h.TongTien;
            }
            catch (Exception ex)
            {
                return 0;
            }

            return doanhThu;
        }

        /*END OF JSON SERVICE AREA*/
    }
}
