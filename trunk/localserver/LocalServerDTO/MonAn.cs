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
    [DataContract(Name = "MonAn", Namespace = "")]
    [Table(Name = "MonAn")]
    public class MonAn
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaMonAn")]
        public int MaMonAn { get; set; }

        [DataMember]
        [Column(Name = "HinhAnh")]
        public string HinhAnh { get; set; }

        [DataMember]
        [Column(Name = "DiemDanhGia")]
        public int DiemDanhGia { get; set; }

        [DataMember]
        [Column(Name = "SoLuotDanhGia")]
        public int SoLuotDanhGia { get; set; }

        [DataMember]
        [Column(Name = "MaDonViTinhMacDinh")]
        public int MaDonViTinhMacDinh { get; set; }

        [DataMember]
        [Column(Name = "MaDanhMuc")]
        public int MaDanhMuc { get; set; }

        [DataMember]
        [Column(Name = "NgungBan")]
        public bool NgungBan { get; set; }

        [DataMember]
        [Column(Name = "MaDonViTinh")]
        public int MaDonViTinh { get; set; }



    }
}
