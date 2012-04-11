using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietsOrder", Namespace = "")]
    [Table(Name = "ChiTietOrder")]
    public class ChiTietOrder
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaChiTietOrder")]
        public int MaChiTietOrder { get; set; }

        [DataMember(Name = "MaOrder")]
        [Column(Name = "MaOrder")]
        private int? _maOrder;
        private EntityRef<Order> _order = new EntityRef<Order>();

        [Association(Name = "Order_ChiTietOrder_FK1", IsForeignKey = true, Storage = "_order", ThisKey = "_maOrder")]
        public Order Order
        {
            get { return _order.Entity; }
            set { _order.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoLuong")]
        public int SoLuong { get; set; }

        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true, Name = "MaMonAn")]
        private int? _maMonAn;
        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietOrder_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember]
        [Column(Name = "GhiChu")]
        public int GhiChu { get; set; }


    }
}
