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
    [DataContract(Name = "MonAn", Namespace = "")]
    [Table(Name = "MonAn")]
    public class MonAn
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaMonAn")]
        public int MaMonAn { get; set; }

        [DataMember]
        [Column(Name = "HinhAnh")]
        public string HinhAnh { get; set; }

        [DataMember]
        [Column(Name = "DiemDanhGia")]
        public int DiemDanhGia { get; set; }

        [DataMember]
        [Column(Name = "SoLuotDanhGia")]
        public int SoLuotDanhGia { get; set; }


        [DataMember(Name = "MaDonViTinhMacDinh")]
        [Column(Name = "MaDonViTinhMacDinh")]
        private int? _maDonViTinhMacDinh;

        private EntityRef<DonViTinh> _donViTinhMacDinh = new EntityRef<DonViTinh>();
        [Association(Name = "DonViTinh_MonAn_FK1", IsForeignKey = true, Storage = "_donViTinhMacDinh", ThisKey = "_maDonViTinhMacDinh")]
        public DonViTinh DonViTinhMacDinh
        {
            get { return _donViTinhMacDinh.Entity; }
            set { _donViTinhMacDinh.Entity = value; }
        }

        [DataMember(Name = "MaDanhMuc")]
        [Column(Name = "MaDanhMuc")]
        private int? _maDanhMuc;

        private EntityRef<DanhMuc> _danhMuc = new EntityRef<DanhMuc>();
        [Association(Name = "DanhMuc_MonAn_FK1", IsForeignKey = true, Storage = "_danhMuc", ThisKey = "_maDanhMuc")]
        public DanhMuc DanhMuc
        {
            get { return _danhMuc.Entity; }
            set { _danhMuc.Entity = value; }
        }

        [DataMember]
        [Column(Name = "NgungBan")]
        public bool NgungBan { get; set; }

        [DataMember(Name = "MaDonViTinh")]
        [Column(Name = "MaDonViTinh")]
        private int? _maDonViTinh;

        private EntityRef<DonViTinh> _donViTinh = new EntityRef<DonViTinh>();
        [Association(Name = "DonViTinh_MonAn_FK1", IsForeignKey = true, Storage = "_donViTinh", ThisKey = "_maDonViTinh")]
        public DonViTinh DonViTinh
        {
            get { return _donViTinh.Entity; }
            set { _donViTinh.Entity = value; }
        }



    }
}
