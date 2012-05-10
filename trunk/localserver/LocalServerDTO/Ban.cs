using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "Ban", Namespace = "")]
    [Table(Name = "Ban")]
    public class Ban
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaBan")]
        public int MaBan { get; set; }

        [DataMember(Name = "MaKhuVuc")] 
        [Column(Name = "MaKhuVuc")] 
        public int? _maKhuVuc;
        private EntityRef<KhuVuc> _khuVuc = new EntityRef<KhuVuc>();

        [Association(Name = "KhuVuc_Ban_FK1", IsForeignKey = true, Storage = "_khuVuc", ThisKey = "_maKhuVuc")]
        public KhuVuc KhuVuc
        {
            get { return _khuVuc.Entity; }
            set { _khuVuc.Entity = value; }
        }

        [DataMember]
        [Column(Name = "TenBan")]
        public string TenBan { get; set; }

        [DataMember]
        [Column(Name = "GhiChu")]
        public string GhiChu { get; set; }

        [DataMember]
        [Column(Name = "Active")]
        public bool Active { get; set; }

        [DataMember]
        [Column(Name = "TinhTrang")]
        public bool TinhTrang { get; set; }

        [DataMember(Name = "MaBanChinh")]
        [Column(Name = "MaBanChinh")] 
        public int? _maBanChinh;

        private EntityRef<Ban> _banChinh = new EntityRef<Ban>();
        [Association(Name = "Ban_Ban_FK1", IsForeignKey = true, Storage = "_banChinh", ThisKey = "_maBanChinh")]
        public Ban BanChinh
        {
            get { return _banChinh.Entity; }
            set { _banChinh.Entity = value; }
        }
    }
}
