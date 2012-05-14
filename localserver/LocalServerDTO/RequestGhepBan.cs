using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace LocalServerDTO
{
    [DataContract(Namespace = "")]
    public class RequestGhepBan
    {
        [DataMember]
        public int MaBanChinh { get; set; }

        [DataMember]
        public List<int> MaBanPhuList { get; set; }
    }
}
