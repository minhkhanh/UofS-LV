using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

using System.Runtime.Serialization;
using System.Data.Linq.Mapping;
using System.Data.Linq;

namespace DynamicCode.ViewModel
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
        public EntityRef<KhuVuc> _khuVuc = new EntityRef<KhuVuc>();

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

        public EntityRef<Ban> _banChinh = new EntityRef<Ban>();
        [Association(Name = "Ban_Ban_FK1", IsForeignKey = true, Storage = "_banChinh", ThisKey = "_maBanChinh")]
        public Ban BanChinh
        {
            get { return _banChinh.Entity; }
            set { _banChinh.Entity = value; }
        }
    }
}
