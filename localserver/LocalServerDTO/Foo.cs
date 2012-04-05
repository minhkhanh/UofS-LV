using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;
namespace LocalServerDTO
{
    [DataContract(Name = "Foo", Namespace = "")]
    public class Foo
    {
        [DataMember]
        public int id
        {
            get;
            set;
        }

        [DataMember]
        public string name
        {
            get;
            set;
        }
    }
}
