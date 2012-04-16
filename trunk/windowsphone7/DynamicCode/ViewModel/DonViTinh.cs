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
    [DataContract(Name = "DonViTinh", Namespace = "")]
    [Table(Name = "DonViTinh")]
    public class DonViTinh
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaDonViTinh")]
        public int MaDonViTinh { get; set; }
    }
}
