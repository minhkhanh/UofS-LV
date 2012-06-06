using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LocalServerWeb.Reports.RevenuePeriodReport
{
    public class RevenuePeriodReportData
    {
        public int Stt { get; set; }
        public string Ngay { get; set; }
        public int Thang { get; set; }
        public int TongSoHoaDon { get; set; }
        public float TongTien { get; set; }
        public float KhuyenMai { get; set; }
        public float PhuThu { get; set; }
    }
}