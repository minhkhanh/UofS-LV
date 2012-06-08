using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "Order", Namespace = "")]
    [Table(Name = "Order")]
    public class Order
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaOrder")]
        public int MaOrder { get; set; }

        [DataMember(Name = "MaTaiKhoan")]
        [Column(Name = "MaTaiKhoan")]
        public int? _maTaiKhoan;
        private EntityRef<TaiKhoan> _taiKhoan = new EntityRef<TaiKhoan>();

        [Association(Name = "TaiKhoan_Order_FK1", IsForeignKey = true, Storage = "_taiKhoan", ThisKey = "_maTaiKhoan")]
        public TaiKhoan TaiKhoan
        {
            get { return _taiKhoan.Entity; }
            set { _taiKhoan.Entity = value; }
        }


        [DataMember(Name = "MaBan")]
        [Column(Name = "MaBan")]
        public int? _maBan;
        private EntityRef<Ban> _ban = new EntityRef<Ban>();

        [Association(Name = "Ban_Order_FK1", IsForeignKey = true, Storage = "_ban", ThisKey = "_maBan")]
        public Ban Ban
        {
            get { return _ban.Entity; }
            set { _ban.Entity = value; }
        }

        /// <summary>
        /// 0 - binh thuong vua moi order
        /// 1 - dang che bien
        /// 2 - khoa ko duoc che bien tiep
        /// 4 - da thanh toan
        /// </summary>
        [DataMember(Name = "TinhTrang")]
        [Column(Name = "TinhTrang")]
        public int TinhTrang { get; set; }

        public string TenTinhTrang { get; set; }

    }
}
