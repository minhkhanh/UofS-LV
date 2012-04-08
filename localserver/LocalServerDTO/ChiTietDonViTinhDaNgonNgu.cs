using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietDonViTinhDaNgonNgu", Namespace = "")]
    [Table(Name = "ChiTietDonViTinhDaNgonNgu")]
    public class ChiTietDonViTinhDaNgonNgu
    {
        [DataMember(Name = "MaDonViTinh")]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaDonViTinh")]
        private int? _maDonViTinh;

        private EntityRef<DonViTinh> _donViTinh = new EntityRef<DonViTinh>();

        [Association(Name = "DonViTinh_ChiTietDonViTinhDaNgonNgu_FK1", IsForeignKey = true, Storage = "_donViTinh", ThisKey = "_maDonViTinh")]
        public DonViTinh DonViTinh
        {
            get { return _donViTinh.Entity; }
            set { _donViTinh.Entity = value; }
        }

        [DataMember(Name = "MaNgonNgu")]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaNgonNgu")]
        private int? _maNgonNgu;

        private EntityRef<NgonNgu> _ngonNgu = new EntityRef<NgonNgu>();

        [Association(Name = "NgonNgu_ChiTietDonViTinhDaNgonNgu_FK1", IsForeignKey = true, Storage = "_ngonNgu", ThisKey = "_maNgonNgu")]
        public NgonNgu NgonNgu
        {
            get { return _ngonNgu.Entity; }
            set { _ngonNgu.Entity = value; }
        }

        [DataMember]
        [Column(Name = "TenDonViTinh")]
        public string TenDonViTinh { get; set; }
    }
}
