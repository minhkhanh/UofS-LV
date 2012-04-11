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
    [DataContract(Name = "NhomTaiKhoan", Namespace = "")]
    [Table(Name = "NhomTaiKhoan")]
    public class NhomTaiKhoan
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaNhomTaiKhoan")]
        public int MaNhomTaiKhoan { get; set; }

        [DataMember]
        [Column(Name = "TenNhom")]
        public string TenNhom { get; set; }

        [DataMember]
        [Column(Name = "MoTaNhom")]
        public string MoTaNhom { get; set; }
    }
}
