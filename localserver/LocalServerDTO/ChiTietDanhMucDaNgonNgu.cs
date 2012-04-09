using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietDanhMucDaNgonNgu", Namespace = "")]
    [Table(Name = "ChiTietDanhMucDaNgonNgu")]
    public class ChiTietDanhMucDaNgonNgu
    {
        [DataMember(Name = "MaDanhMuc")]
        [Column(IsPrimaryKey = true,  Name = "MaDanhMuc")]
        private int? _maDanhMuc;

        private EntityRef<DanhMuc> _danhMuc = new EntityRef<DanhMuc>();

        [Association(Name = "DanhMuc_ChiTietDanhMucDaNgonNgu_FK1", IsForeignKey = true, Storage = "_danhMuc", ThisKey = "_maDanhMuc")]
        public DanhMuc DanhMuc
        {
            get { return _danhMuc.Entity; }
            set { _danhMuc.Entity = value; }
        }

        [DataMember(Name = "MaNgonNgu")]
        [Column(IsPrimaryKey = true,  Name = "MaNgonNgu")]
        private int? _maNgonNgu;

        private EntityRef<NgonNgu> _ngonNgu = new EntityRef<NgonNgu>();

        [Association(Name = "NgonNgu_ChiTietDanhMucDaNgonNgu_FK1", IsForeignKey = true, Storage = "_ngonNgu", ThisKey = "_maNgonNgu")]
        public NgonNgu NgonNgu
        {
            get { return _ngonNgu.Entity; }
            set { _ngonNgu.Entity = value; }
        }

        [DataMember]
        [Column(Name = "TenDanhMuc")]
        public string TenDanhMuc { get; set; }

        [DataMember]
        [Column(Name = "MoTaDanhMuc")]
        public string MoTaDanhMuc { get; set; }
    }
}
