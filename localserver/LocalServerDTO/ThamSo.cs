using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [Table(Name = "ThamSo")]
    public class ThamSo
    {
        [Column(IsPrimaryKey = true, CanBeNull = false, Name = "Ten")]
        public string Ten { get; set; }

        [Column(Name = "GiaTri", CanBeNull = false)]
        public string GiaTri { get; set; }
    }
}
