using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.Text;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using System.IO;

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
        public List<KhuVuc> LayDanhKhuVuc()
        {
            return KhuVucBUS.LayDanhSachKhuVuc();
        }

        
        // Ban
        public List<Ban> LayDanhSachBan()
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

        public Ban LayBan(int maBan)
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

        public List<Ban> LayDanhSachBanChinh()
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

        public List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh)
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

        public List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc)
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



        // Mon An
        public List<MonAn> LayDanhSachMonAn()
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

        public List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
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

        public MonAn LayMonAn(int maMonAn)
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
        
        // Danh Muc
        public List<DanhMuc> LayDanhSachDanhMuc()
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
        public ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu)
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

        public List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu()
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
        public ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
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

        public List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu()
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
        public ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu)
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

        public List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu()
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
        public float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh)
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

        public ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh)
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

        public List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh()
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
        public List<NgonNgu> LayDanhSachNgonNgu()
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
        public List<TiGia> LayDanhSachTiGia()
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
        public List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan()
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
        public List<TaiKhoan> LayDanhSachTaiKhoan()
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
        public List<PhuThu> LayDanhSachPhuThu()
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
        public List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc()
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

        public List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu)
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
        public List<KhuyenMai> LayDanhSachKhuyenMai()
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
        public List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc()
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

        public List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai)
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
        public List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc()
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

        public List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai)
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
        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon()
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

        public List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai)
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
        public List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon()
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

        public List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai)
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
        public List<BoPhanCheBien> LayDanhSachBoPhanCheBien()
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
        public List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan()
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

        public List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanTheoMaMon(int maMonAn)
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
        public List<Order> LayDanhSachOrder()
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

        public Order LayOrder(int maOrder)
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

        // Chi Tiet Order
        public ChiTietOrder LayChiTietOrder(int maChiTietOrder)
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


        // Chi Tiet Huy Order
        public ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietHuyOrder)
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
        public HoaDon LayHoaDon(int maHoaDon)
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
        public ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon)
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

        public List<ChiTietHoaDon> ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon)
        {
            try
            {
                return ChiTietHoaDonBUS.ThemNhieuChiTietHoaDon(_listChiTietHoaDon);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
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



        // Picture
        public Stream GetPicture(string path)
        {
            return PictureBUS.GetPicture(path);
        }

        public bool AddPicture(string path, Stream content)
        {
            return PictureBUS.AddPicture(path, content);
        }


        // Testing purpose
        public string AddText()
        {
            return "123";
        }

        public int PhepCong(int a, int b)
        {
            return a + b;
        }


        public string Test()
        {
            return "Hello eMenu";
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

    }
}
