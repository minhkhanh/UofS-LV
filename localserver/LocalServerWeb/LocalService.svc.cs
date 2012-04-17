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

namespace LocalServerWeb
{
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    public class LocalService : ILocalService
    {
        public LocalService()
        {
            string strConn = ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString;
            ThucDonDienTuBUS.KhoiTao(strConn);
        }

        public int PhepCong(int a, int b)
        {
            return a + b;
        }


        public string Test()
        {
            return "Hello eMenu";
        }

        public List<KhuVuc> LayDanhKhuVuc()
        {
            return KhuVucBUS.LayDanhSachKhuVuc();
        }


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

        // for testing purpose
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

        public ChiTietOrder ThemChiTietOrder(ChiTietOrder _ChiTietOrder)
        {
            try
            {
                return ChiTietOrderBUS.ThemChiTietOrder(_ChiTietOrder);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            return null;
        }

        public ChiTietHuyOrder ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            try
            {
                return ChiTietHuyOrderBUS.ThemChiTietHuyOrder(_chiTietHuyOrder);
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

    }
}
