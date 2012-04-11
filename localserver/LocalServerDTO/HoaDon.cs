using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "HoaDon", Namespace = "")]
    [Table(Name = "HoaDon")]
    public class HoaDon
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaHoaDon")]
        public int MaHoaDon { get; set; }

        [DataMember]
        [Column(Name = "ThoiDiemLap")]
        public DateTime ThoiDiemLap { get; set; }

        [DataMember(Name = "MaTaiKhoan")]
        [Column(Name = "MaTaiKhoan")]
        private int? _maTaiKhoan;
        private EntityRef<TaiKhoan> _taiKhoan = new EntityRef<TaiKhoan>();

        [Association(Name = "TaiKhoan_HoaDon_FK1", IsForeignKey = true, Storage = "_taiKhoan", ThisKey = "_maTaiKhoan")]
        public TaiKhoan TaiKhoan
        {
            get { return _taiKhoan.Entity; }
            set { _taiKhoan.Entity = value; }
        }

        [DataMember]
        [Column(Name = "TongTien")]
        public float TongTien { get; set; }

        [DataMember(Name = "MaBanChinh")]
        [Column(Name = "MaBanChinh")]
        private int? _maBanChinh;
        private EntityRef<Ban> _banChinh = new EntityRef<Ban>();

        [Association(Name = "Ban_HoaDon_FK1", IsForeignKey = true, Storage = "_banChinh", ThisKey = "_maBanChinh")]
        public Ban Ban
        {
            get { return _banChinh.Entity; }
            set { _banChinh.Entity = value; }
        }

        [DataMember]
        [Column(Name = "MoTaBanGhep")]
        public string MoTaBanGhep { get; set; }

        [DataMember(Name = "MaPhuThu")]
        [Column(Name = "MaPhuThu")]
        private int? _maPhuThu;
        private EntityRef<PhuThu> _phuThu = new EntityRef<PhuThu>();

        [Association(Name = "PhuThu_HoaDon_FK1", IsForeignKey = true, Storage = "_phuThu", ThisKey = "_maPhuThu")]
        public PhuThu PhuThu
        {
            get { return _phuThu.Entity; }
            set { _phuThu.Entity = value; }
        }


    }
}
