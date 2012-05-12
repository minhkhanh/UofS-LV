using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietKhongCheBienOrder", Namespace = "")]
    [Table(Name = "ChiTietKhongCheBienOrder")]
    public class ChiTietKhongCheBienOrder
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaChiTietKhongCheBienOrder")]
        public int MaChiTietKhongCheBienOrder { get; set; }

        [DataMember(Name = "MaChiTietOrder")]
        [Column(Name = "MaChiTietOrder")]
        public int? _maChiTietOrder;
        private EntityRef<ChiTietOrder> _chiTietOrder = new EntityRef<ChiTietOrder>();

        [Association(Name = "ChiTietOrder_ChiTietKhongCheBienOrder_FK1", IsForeignKey = true, Storage = "_chiTietOrder", ThisKey = "_maChiTietOrder", OtherKey = "MaChiTietOrder")]
        public ChiTietOrder ChiTietOrder
        {
            get { return _chiTietOrder.Entity; }
            set { _chiTietOrder.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoLuongKhongCheBien")]
        public int SoLuongKhongCheBien { get; set; }

        /// <summary>
        /// 0 - binh thuong
        /// </summary>
        [DataMember]
        [Column(Name = "TinhTrang", IsDbGenerated = true)]
        public int TinhTrang { get; set; }

    }
}
