using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "KhuVuc", Namespace = "")]
    [Table(Name = "KhuVuc")]
    public class KhuVuc
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true)]
        public int MaKhuVuc { get; set; }

        [DataMember]
        [Column]
        public string TenKhuVuc { get; set; }

        [DataMember]
        [Column]
        public string MoTa { get; set; }
    }
}
