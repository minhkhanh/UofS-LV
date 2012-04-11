using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "TiGia", Namespace = "")]
    [Table(Name = "TiGia")]
    public class TiGia
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaTiGia")]
        public int MaTiGia { get; set; }

        [DataMember]
        [Column(Name = "KiHieu")]
        public string KiHieu { get; set; }

        [DataMember]
        [Column(Name = "GiaTri")]
        public float GiaTri { get; set; }

        [DataMember]
        [Column(Name = "ThoiDiemCapNhat")]
        public DateTime ThoiDiemCapNhat { get; set; }
    }
}
