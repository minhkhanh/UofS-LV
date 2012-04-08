using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "NgonNgu", Namespace = "")]
    [Table(Name = "NgonNgu")]
    public class NgonNgu
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaNgonNgu")]
        public int MaNgonNgu { get; set; }

        [DataMember]
        [Column(Name = "TenNgonNgu")]
        public string TenNgonNgu { get; set; }
    }
}
