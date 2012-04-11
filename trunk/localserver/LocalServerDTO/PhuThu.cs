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
    [DataContract(Name = "PhuThu", Namespace = "")]
    [Table(Name = "PhuThu")]
    public class PhuThu
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaPhuThu")]
        public int MaPhuThu { get; set; }

        [DataMember]
        [Column(Name = "TenPhuThu")]
        public string TenPhuThu { get; set; }

        [DataMember]
        [Column(Name = "MoTaPhuThu")]
        public string MoTaPhuThu { get; set; }

        [DataMember]
        [Column(Name = "GiaTang")]
        public float GiaTang { get; set; }

        [DataMember]
        [Column(Name = "TiLeTang")]
        public float TiLeTang { get; set; }

        [DataMember]
        [Column(Name = "BatDau")]
        public DateTime BatDau { get; set; }

        [DataMember]
        [Column(Name = "KetThuc")]
        public DateTime KetThuc { get; set; }

        [DataMember]
        [Column(Name = "LichPhuThu")]
        public string LichPhuThu { get; set; }

    }
}
