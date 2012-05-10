using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;
using System.IO;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietHoaDon", Namespace = "")]
    [Table(Name = "ChiTietHoaDon")]
    public class ChiTietHoaDon
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaChiTietHoaDon")]
        public int MaChiTietHoaDon { get; set; }

        [DataMember(Name = "MaHoaDon")]
        [Column(Name = "MaHoaDon")]
        public int? _maHoaDon;
        private EntityRef<HoaDon> _hoaDon = new EntityRef<HoaDon>();

        [Association(Name = "HoaDon_ChiTietHoaDon_FK1", IsForeignKey = true, Storage = "_hoaDon", ThisKey = "_maHoaDon")]
        public HoaDon HoaDon
        {
            get { return _hoaDon.Entity; }
            set { _hoaDon.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoLuong")]
        public int SoLuong { get; set; }

        [DataMember]
        [Column(Name = "DonGiaLuuTru")]
        public float DonGiaLuuTru { get; set; }

        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true, Name = "MaMonAn")]
        public int? _maMonAn;

        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietHoaDon_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember]
        [Column(Name = "ThanhTien")]
        public float ThanhTien { get; set; }

        [DataMember]
        [Column(Name = "GiaTriKhuyenMaiLuuTru")]
        public float GiaTriKhuyenMaiLuuTru { get; set; }

        [DataMember(Name = "MaKhuyenMai")]
        [Column(IsPrimaryKey = true, Name = "MaKhuyenMai")]
        public int? _maKhuyenMai;

        private EntityRef<KhuyenMai> _khuyenMai = new EntityRef<KhuyenMai>();

        [Association(Name = "KhuyenMai_ChiTietHoaDon_FK1", IsForeignKey = true, Storage = "_khuyenMai", ThisKey = "_maKhuyenMai")]
        public KhuyenMai KhuyenMai
        {
            get { return _khuyenMai.Entity; }
            set { _khuyenMai.Entity = value; }
        }

        [DataMember(Name = "MaDonViTinh")]
        [Column(Name = "MaDonViTinh")]
        private int? _maDonViTinh;

        private EntityRef<DonViTinh> _donViTinh = new EntityRef<DonViTinh>();
        [Association(Name = "DonViTinh_ChiTietHoaDon_FK1", IsForeignKey = true, Storage = "_donViTinh", ThisKey = "_maDonViTinh")]
        public DonViTinh DonViTinh
        {
            get { return _donViTinh.Entity; }
            set { _donViTinh.Entity = value; }
        }
    }
}
