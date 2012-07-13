using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace LocalServerDTO
{
    [DataContract(Namespace = "")]
    public class SplittingOrderItem
    {
        [DataMember]
        public int ItemId { get; set; }

        [DataMember]
        public int QuantityToSplit { get; set; }
    }
}
