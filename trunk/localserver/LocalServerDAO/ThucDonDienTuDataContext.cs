using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Data.Linq.Mapping;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    [Database]
    public class ThucDonDienTuDataContext : DataContext
    {
        public ThucDonDienTuDataContext(string strConnection)
            : base(strConnection)
        {
            
        }

        public Table<Ban> Bans;
        public Table<KhuVuc> KhuVucs;
        public Table<MonAn> MonAns;
        public Table<DanhMuc> DanhMucs;
        public Table<DonViTinh> DonViTinhs;

        public Table<ChiTietDanhMucDaNgonNgu> ChiTietDanhMucDaNgonNgus;
        public Table<ChiTietDonViTinhDaNgonNgu> ChiTietDonViTinhDaNgonNgus;
        public Table<ChiTietMonAnDaNgonNgu> ChiTietMonAnDaNgonNgus;
        public Table<ChiTietMonAnDonViTinh> ChiTietMonAnDonViTinhs;

        public Table<ChiTietMonLienQuan> ChiTietMonLienQuans;

        public Table<NhomTaiKhoan> NhomTaiKhoans;
        public Table<TaiKhoan> TaiKhoans;

        public Table<KhuyenMai> KhuyenMais;
        public Table<PhuThu> PhuThus;
        public Table<TiGia> TiGias;

        public Table<HoaDon> HoaDons;
        public Table<Order> Orders;
        public Table<ChiTietOrder> ChiTietOrders;
        public Table<ChiTietHuyOrder> ChiTietHuyOrders;

        public Table<BoPhanCheBien> BoPhanCheBiens;
        public Table<ChiTietDanhMucBoPhanCheBien> ChiTietDanhMucBoPhanCheBiens;

        public Table<NgonNgu> NgonNgus;
    }
}
