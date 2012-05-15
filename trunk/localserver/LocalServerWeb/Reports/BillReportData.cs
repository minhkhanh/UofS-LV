using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerDTO;

namespace LocalServerWeb.Reports
{
    public class BillReportData
    {
        //public int MaChiTietHoaDon { get; set; }
        public int Stt { get; set; }

        public string TenMonAn { get; set; }

        public string TenDonViTinh { get; set; }

        public int SoLuong { get; set; }

        public float DonGiaLuuTru { get; set; }        

        public float ThanhTien { get; set; }

        public float GiaTriKhuyenMaiLuuTru { get; set; }

        //public string TenKhuyenMai { get; set; }
        
    }
}