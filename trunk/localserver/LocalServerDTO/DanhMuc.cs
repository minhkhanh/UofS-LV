using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "DanhMuc", Namespace = "")]
    [Table(Name = "DanhMuc")]
    public class DanhMuc
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaDanhMuc")]
        public int MaDanhMuc { get; set; }

        [DataMember(Name = "MaDanhMucCha")]
        [Column(Name = "MaDanhMucCha")]
        private int? _maDanhMucCha;

        private EntityRef<DanhMuc> _danhMucCha = new EntityRef<DanhMuc>();
        [Association(Name = "DanhMuc_DanhMuc_FK1", IsForeignKey = true, Storage = "_danhMucCha", ThisKey = "_maDanhMucCha")]
        public DanhMuc DanhMucCha
        {
            get { return _danhMucCha.Entity; }
            set { _danhMucCha.Entity = value; }
        }
    }
}
