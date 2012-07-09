using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace LocalServerDTO
{
    [DataContract(Namespace = "")]
    public class TableIdSelection
    {
        [DataMember]
        public int MainTabId { get; set; }

        [DataMember]
        public List<int> TabIdList { get; set; }
    }
}
