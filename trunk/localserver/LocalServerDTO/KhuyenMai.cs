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
    [DataContract(Name = "KhuyenMai", Namespace = "")]
    [Table(Name = "KhuyenMai")]
    public class KhuyenMai
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaKhuyenMai")]
        public int MaKhuyenMai { get; set; }

        [DataMember]
        [Column(Name = "TenKhuyenMai")]
        public string TenKhuyenMai { get; set; }

        [DataMember]
        [Column(Name = "MoTaKhuyenMai")]
        public string MoTaKhuyenMai { get; set; }

        [DataMember]
        [Column(Name = "GiaGiam")]
        public float GiaGiam { get; set; }

        [DataMember]
        [Column(Name = "TiLeGiam")]
        public float TiLeGiam { get; set; }

        [DataMember]
        [Column(Name = "BatDau")]
        public DateTime BatDau { get; set; }

        [DataMember]
        [Column(Name = "KetThuc")]
        public DateTime KetThuc { get; set; }

        [DataMember]
        [Column(Name = "LichKhuyenMai")]
        public string LichKhuyenMai { get; set; }

    }
}
