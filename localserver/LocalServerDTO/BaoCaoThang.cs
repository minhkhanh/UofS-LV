using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;
using System.IO;

namespace LocalServerDTO
{
    public class BaoCaoThang
    {
        [DataMember]
        public int Stt { get; set; }

        [DataMember]
        public string Ngay { get; set; }

        [DataMember]
        public int TongSoHoaDon { get; set; }

        [DataMember]
        public float TongTien { get; set; }

        [DataMember]
        public float KhuyenMai { get; set; }

        [DataMember]
        public float PhuThu { get; set; }

        [DataMember]
        public float TongTienThang { get; set; }

        [DataMember]
        public float TongPhuThuThang { get; set; }

        [DataMember]
        public float TongKhuyenMaiThang { get; set; }
    }
}
