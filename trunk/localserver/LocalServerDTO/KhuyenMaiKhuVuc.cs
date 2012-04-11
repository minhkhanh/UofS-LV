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
    [DataContract(Name = "KhuyenMaiKhuVuc", Namespace = "")]
    [Table(Name = "KhuyenMaiKhuVuc")]
    public class KhuyenMaiKhuVuc
    {
        [DataMember(Name = "MaKhuyenMai")]
        [Column(IsPrimaryKey = true, Name = "MaKhuyenMai")]
        private int? _maKhuyenMai;

        private EntityRef<KhuyenMai> _khuyenMai = new EntityRef<KhuyenMai>();

        [Association(Name = "KhuyenMai_KhuyenMaiKhuVuc_FK1", IsForeignKey = true, Storage = "_khuyenMai", ThisKey = "_maKhuyenMai")]
        public KhuyenMai KhuyenMai
        {
            get { return _khuyenMai.Entity; }
            set { _khuyenMai.Entity = value; }
        }


        [DataMember(Name = "MaKhuVuc")]
        [Column(Name = "MaKhuVuc")]
        private int? _maKhuVuc;
        private EntityRef<KhuVuc> _khuVuc = new EntityRef<KhuVuc>();

        [Association(Name = "KhuVuc_KhuyenMaiKhuVuc_FK1", IsForeignKey = true, Storage = "_khuVuc", ThisKey = "_maKhuVuc")]
        public KhuVuc KhuVuc
        {
            get { return _khuVuc.Entity; }
            set { _khuVuc.Entity = value; }
        }
    }
}
