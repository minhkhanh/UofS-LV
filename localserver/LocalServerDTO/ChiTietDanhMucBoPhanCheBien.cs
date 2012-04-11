using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietDanhMucBoPhanCheBien", Namespace = "")]
    [Table(Name = "ChiTietDanhMucBoPhanCheBien")]
    public class ChiTietDanhMucBoPhanCheBien
    {
        [DataMember(Name = "MaDanhMuc")]
        [Column(IsPrimaryKey = true, Name = "MaDanhMuc")]
        private int? _maDanhMuc;

        private EntityRef<DanhMuc> _danhMuc = new EntityRef<DanhMuc>();

        [Association(Name = "DanhMuc_ChiTietDanhMucBoPhanCheBien_FK1", IsForeignKey = true, Storage = "_danhMuc", ThisKey = "_maDanhMuc")]
        public DanhMuc DanhMuc
        {
            get { return _danhMuc.Entity; }
            set { _danhMuc.Entity = value; }
        }

        [DataMember(Name = "MaBoPhanCheBien")]
        [Column(IsPrimaryKey = true, Name = "MaBoPhanCheBien")]
        private int? _maBoPhanCheBien;

        private EntityRef<BoPhanCheBien> _boPhanCheBien = new EntityRef<BoPhanCheBien>();

        [Association(Name = "BoPhanCheBien_ChiTietDanhMucBoPhanCheBien_FK1", IsForeignKey = true, Storage = "_boPhanCheBien", ThisKey = "_maBoPhanCheBien")]
        public BoPhanCheBien BoPhanCheBien
        {
            get { return _boPhanCheBien.Entity; }
            set { _boPhanCheBien.Entity = value; }
        }
    }
}
