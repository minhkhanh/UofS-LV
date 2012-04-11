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
    [DataContract(Name = "KhuyenMaiDanhMuc", Namespace = "")]
    [Table(Name = "KhuyenMaiDanhMuc")]
    public class KhuyenMaiDanhMuc
    {
        [DataMember(Name = "MaKhuyenMai")]
        [Column(IsPrimaryKey = true, Name = "MaKhuyenMai")]
        private int? _maKhuyenMai;

        private EntityRef<KhuyenMai> _khuyenMai = new EntityRef<KhuyenMai>();

        [Association(Name = "KhuyenMai_KhuyenMaiDanhMuc_FK1", IsForeignKey = true, Storage = "_khuyenMai", ThisKey = "_maKhuyenMai")]
        public KhuyenMai KhuyenMai
        {
            get { return _khuyenMai.Entity; }
            set { _khuyenMai.Entity = value; }
        }

        [DataMember(Name = "MaDanhMuc")]
        [Column(IsPrimaryKey = true, Name = "MaDanhMuc")]
        private int? _maDanhMuc;

        private EntityRef<DanhMuc> _danhMuc = new EntityRef<DanhMuc>();

        [Association(Name = "DanhMuc_KhuyenMaiDanhMuc_FK1", IsForeignKey = true, Storage = "_danhMuc", ThisKey = "_maDanhMuc")]
        public DanhMuc DanhMuc
        {
            get { return _danhMuc.Entity; }
            set { _danhMuc.Entity = value; }
        }
    }
}
