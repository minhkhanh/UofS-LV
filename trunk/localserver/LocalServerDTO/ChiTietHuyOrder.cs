using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietHuyOrder", Namespace = "")]
    [Table(Name = "ChiTietHuyOrder")]
    public class ChiTietHuyOrder
    {
        [DataMember(Name = "MaChiTietOrder")]
        [Column(IsPrimaryKey = true, Name = "MaChiTietOrder")]
        public int? _maChiTietOrder;
        private EntityRef<ChiTietOrder> _chiTietOrder = new EntityRef<ChiTietOrder>();

        [Association(Name = "ChiTietOrder_ChiTietHuyOrder_FK1", IsForeignKey = true, Storage = "_chiTietOrder", ThisKey = "_maChiTietOrder", OtherKey="MaChiTietOrder")]
        public ChiTietOrder ChiTietOrder
        {
            get { return _chiTietOrder.Entity; }
            set { _chiTietOrder.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoLuongYeuCau")]
        public int SoLuongYeuCau { get; set; }

        [DataMember]
        [Column(Name = "SoLuongChoPhep")]
        public int SoLuongChoPhep { get; set; }

        [DataMember]
        [Column(Name = "TinhTrangHuy")]
        public bool TinhTrangHuy { get; set; }

    }
}
