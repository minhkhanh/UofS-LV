using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LocalServerWeb
{
    public class ChiTietOrderKitchen
    {
        public int MaChiTietOrder { get; set; }

        public int MaOrder { get; set; }

        public string TenKhuVuc { get; set; }

        public string TenBan { get; set; }

        public string TenMonAn { get; set; }

        public string GhiChu { get; set; }

        public string TenDonViTinh { get; set; }

        public int SoLuong { get; set; }

        public int SoLuongDaCheBien { get; set; }

        public int SoLuongDangCheBien { get; set; }

        //public bool DuocPhepCheBien { get; set; }

    }
}