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

namespace DynamicCode.ViewModel
{
    [DataContract(Name = "KhuVuc", Namespace = "")]
    [Table(Name = "KhuVuc")]
    public class KhuVuc
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true)]
        public int MaKhuVuc { get; set; }

        [DataMember]
        [Column]
        public string TenKhuVuc { get; set; }

        [DataMember]
        [Column]
        public string MoTa { get; set; }
    }
}
