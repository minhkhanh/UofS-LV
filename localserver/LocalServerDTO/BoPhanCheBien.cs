using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "BoPhanCheBien", Namespace = "")]
    [Table(Name = "BoPhanCheBien")]
    public class BoPhanCheBien
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaBoPhanCheBien")]
        public int MaBoPhanCheBien { get; set; }

        [DataMember]
        [Column(Name = "TenBoPhan")]
        public string TenBoPhan { get; set; }

        [DataMember]
        [Column(Name = "MoTa")]
        public string MoTa { get; set; }

        [DataMember(Name = "MaTaiKhoan")]
        [Column(Name = "MaTaiKhoan")]
        private int? _maTaiKhoan;
        private EntityRef<TaiKhoan> _taiKhoan = new EntityRef<TaiKhoan>();

        [Association(Name = "TaiKhoan_BoPhanCheBien_FK1", IsForeignKey = true, Storage = "_taiKhoan", ThisKey = "_maTaiKhoan")]
        public TaiKhoan TaiKhoan
        {
            get { return _taiKhoan.Entity; }
            set { _taiKhoan.Entity = value; }
        }
    }
}
