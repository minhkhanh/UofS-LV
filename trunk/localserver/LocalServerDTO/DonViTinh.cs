using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "DonViTinh", Namespace = "")]
    [Table(Name = "DonViTinh")]
    public class DonViTinh
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaDonViTinh")]
        public int MaDonViTinh { get; set; }
    }

}
