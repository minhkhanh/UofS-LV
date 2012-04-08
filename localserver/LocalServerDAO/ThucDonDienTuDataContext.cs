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
    }
}
