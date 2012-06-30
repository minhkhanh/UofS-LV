using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace LocalServerDTO
{
    [DataContract(Namespace = "")]
    public class DanhGiaMonAn
    {
        [DataMember]
        public int MaMonAn { get; set; }

        [DataMember]
        public int DiemDanhGia { get; set; }
    }
}
