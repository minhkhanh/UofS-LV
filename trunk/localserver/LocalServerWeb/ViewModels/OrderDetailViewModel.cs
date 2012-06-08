using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerDTO;

namespace LocalServerWeb.ViewModels
{
    public class OrderDetailViewModel
    {
        public int MaMonAn { get; set; }
        public string TenMonAn { get; set; }
        public int SoLuong { get; set; }
        public int MaBoPhanCheBien { get; set; }
        public string TenBoPhanCheBien { get; set; }
        public string GhiChu { get; set; }
        public string TenTinhTrang { get; set; }

        public bool CoThongTinHuy { get; set; }

        public int SoLuongYeuCau { get; set; }
        public int SoLuongChoPhep { get; set; }
        public string TinhTrangHuy { get; set; }


    }
}