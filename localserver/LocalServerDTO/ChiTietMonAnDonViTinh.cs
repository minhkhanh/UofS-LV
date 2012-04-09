using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietMonAnDonViTinh", Namespace = "")]
    [Table(Name = "ChiTietMonAnDonViTinh")]
    public class ChiTietMonAnDonViTinh
    {
        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true, Name = "MaMonAn")]
        private int? _maMonAn;

        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietMonAnDonViTinh_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember(Name = "MaDonViTinh")]
        [Column(IsPrimaryKey = true, Name = "MaDonViTinh")]
        private int? _maDonViTinh;

        private EntityRef<DonViTinh> _donViTinh = new EntityRef<DonViTinh>();

        [Association(Name = "DonViTinh_ChiTietMonAnDonViTinh_FK1", IsForeignKey = true, Storage = "_donViTinh", ThisKey = "_maDonViTinh")]
        public DonViTinh DonViTinh
        {
            get { return _donViTinh.Entity; }
            set { _donViTinh.Entity = value; }
        }

        [DataMember]
        [Column(Name = "DonGia")]
        public int DonGia { get; set; }
    }
}
