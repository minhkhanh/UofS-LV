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
    [DataContract(Name = "TaiKhoan", Namespace = "")]
    [Table(Name = "TaiKhoan")]
    public class TaiKhoan
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaTaiKhoan")]
        public int MaTaiKhoan { get; set; }

        [DataMember]
        [Column(Name = "TenTaiKhoan")]
        public string TenTaiKhoan { get; set; }

        [DataMember]
        [Column(Name = "MatKhau")]
        public string MatKhau { get; set; }

        [DataMember]
        [Column(Name = "HoTen")]
        public string HoTen { get; set; }

        [DataMember]
        [Column(Name = "NgaySinh")]
        public DateTime NgaySinh { get; set; }

        [DataMember]
        [Column(Name = "GioiTinh")]
        public int GioiTinh { get; set; }

        [DataMember]
        [Column(Name = "CMND")]
        public string CMND { get; set; }

        [DataMember]
        [Column(Name = "Avatar")]
        public string Avatar { get; set; }

        [DataMember]
        [Column(Name = "Active")]
        public bool Active { get; set; }

        [DataMember(Name = "MaNhomTaiKhoan")]
        [Column(Name = "MaNhomTaiKhoan")]
        private int? _maNhomTaiKhoan;

        private EntityRef<NhomTaiKhoan> _nhomTaiKhoan = new EntityRef<NhomTaiKhoan>();
        [Association(Name = "NhomTaiKhoan_TaiKhoan_FK1", IsForeignKey = true, Storage = "_nhomTaiKhoan", ThisKey = "_maNhomTaiKhoan")]
        public NhomTaiKhoan NhomTaiKhoan
        {
            get { return _nhomTaiKhoan.Entity; }
            set { _nhomTaiKhoan.Entity = value; }
        }
    }
}
