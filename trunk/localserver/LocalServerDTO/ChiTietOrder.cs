using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietOrder", Namespace = "")]
    [Table(Name = "ChiTietOrder")]
    public class ChiTietOrder
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaChiTietOrder")]
        public int MaChiTietOrder { get; set; }

        [DataMember(Name = "MaOrder")]
        [Column(Name = "MaOrder")]
        public int? _maOrder;
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
        public int? _maMonAn;
        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietOrder_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember]
        [Column(Name = "GhiChu")]
        public string GhiChu { get; set; }

        [DataMember(Name = "MaBoPhanCheBien")]
        [Column(Name = "MaBoPhanCheBien")]
        public int? _maBoPhanCheBien;
        private EntityRef<BoPhanCheBien> _boPhanCheBien = new EntityRef<BoPhanCheBien>();

        [Association(Name = "BoPhanCheBien_ChiTietOrder_FK1", IsForeignKey = true, Storage = "_boPhanCheBien", ThisKey = "_maBoPhanCheBien")]
        public BoPhanCheBien BoPhanCheBien
        {
            get { return _boPhanCheBien.Entity; }
            set { _boPhanCheBien.Entity = value; }
        }

        [DataMember(Name = "MaDonViTinh")]
        [Column(Name = "MaDonViTinh")]
        public int? _maDonViTinh;

        private EntityRef<DonViTinh> _donViTinh = new EntityRef<DonViTinh>();
        [Association(Name = "DonViTinh_ChiTietOrder_FK1", IsForeignKey = true, Storage = "_donViTinh", ThisKey = "_maDonViTinh")]
        public DonViTinh DonViTinh
        {
            get { return _donViTinh.Entity; }
            set { _donViTinh.Entity = value; }
        }

        /// <summary>
        /// 0 - binh thuong vua moi order
        /// 1 - dang che bien
        /// 2 - khoa, ko duoc che bien tiep
        /// 3 - da xong
        /// </summary>
        [Column(Name = "TinhTrang")]
        public int TinhTrang { get; set; }

        public int SoLuongDaCheBien { get; set; }
        public int SoLuongDangCheBien { get; set; }

    }
}
