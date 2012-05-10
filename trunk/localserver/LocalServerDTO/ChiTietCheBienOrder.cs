using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietCheBienOrder", Namespace = "")]
    [Table(Name = "ChiTietCheBienOrder")]
    public class ChiTietCheBienOrder
    {
        [DataMember(Name = "MaChiTietOrder")]
        [Column(IsPrimaryKey = true, Name = "MaChiTietOrder")]
        public int? _maChiTietOrder;
        private EntityRef<ChiTietOrder> _chiTietOrder = new EntityRef<ChiTietOrder>();

        [Association(Name = "ChiTietOrder_ChiTietCheBienOrder_FK1", IsForeignKey = true, Storage = "_chiTietOrder", ThisKey = "_maChiTietOrder", OtherKey = "MaChiTietOrder")]
        public ChiTietOrder ChiTietOrder
        {
            get { return _chiTietOrder.Entity; }
            set { _chiTietOrder.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoLuongDaCheBien")]
        public int SoLuongDaCheBien { get; set; }

        [DataMember]
        [Column(Name = "SoLuongDangCheBien")]
        public int SoLuongDangCheBien { get; set; }

    }
}
