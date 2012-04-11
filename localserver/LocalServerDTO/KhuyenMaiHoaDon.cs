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
    [DataContract(Name = "KhuyenMaiHoaDon", Namespace = "")]
    [Table(Name = "KhuyenMaiHoaDon")]
    public class KhuyenMaiHoaDon
    {
        [DataMember(Name = "MaKhuyenMai")]
        [Column(IsPrimaryKey = true, Name = "MaKhuyenMai")]
        private int? _maKhuyenMai;

        private EntityRef<KhuyenMai> _khuyenMai = new EntityRef<KhuyenMai>();

        [Association(Name = "KhuyenMai_KhuyenMaiHoaDon_FK1", IsForeignKey = true, Storage = "_khuyenMai", ThisKey = "_maKhuyenMai")]
        public KhuyenMai KhuyenMai
        {
            get { return _khuyenMai.Entity; }
            set { _khuyenMai.Entity = value; }
        }

        [DataMember]
        [Column(Name = "MucGiaApDung")]
        public float MucGiaApDung { get; set; }
    }
}
