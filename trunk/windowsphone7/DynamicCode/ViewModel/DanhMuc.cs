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
