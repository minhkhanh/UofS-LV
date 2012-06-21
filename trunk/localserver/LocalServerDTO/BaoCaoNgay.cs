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
    [DataContract(Namespace = "")]
    public class BaoCaoNgay
    {
        [DataMember]
        public int Stt { get; set; }

        [DataMember]
        public int MaHoaDon { get; set; }

        [DataMember]
        public string ThoiDiemLap { get; set; }

        [DataMember]
        public string NguoiLap { get; set; }

        [DataMember]
        public float TongTien { get; set; }

        [DataMember]
        public string BanChinh { get; set; }

        [DataMember]
        public float PhuThu { get; set; }

        [DataMember]
        public float TongTienNgay { get; set; }

        [DataMember]
        public float TongPhuThuNgay { get; set; }

        [DataMember]
        public float TongKhuyenMaiNgay { get; set; }


    }
}
