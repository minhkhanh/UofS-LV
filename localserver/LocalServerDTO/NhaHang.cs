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
    [DataContract(Namespace = "")]
    public class NhaHang
    {
        [DataMember]
        public string Ten { get; set; }

        [DataMember]
        public string DiaChi { get; set; }

        [DataMember]
        public string MoTa { get; set; }

        [DataMember]
        public string Tel { get; set; }

        [DataMember]
        public string Fax{ get; set; }

        [DataMember]
        public string Logo { get; set; }



    }
}
