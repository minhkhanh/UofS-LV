using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietVoucher", Namespace = "")]
    [Table(Name = "ChiTietVoucher")]
    public class ChiTietVoucher
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaChiTietVoucher")]
        public int MaChiTietVoucher { get; set; }

        [DataMember(Name = "MaVoucher")]
        [Column(Name = "MaVoucher")]
        public int? _maVoucher;
        private EntityRef<Voucher> _voucher = new EntityRef<Voucher>();

        [Association(Name = "Voucher_ChiTietVoucher_FK1", IsForeignKey = true, Storage = "_voucher", ThisKey = "_maVoucher")]
        public Voucher Voucher
        {
            get { return _voucher.Entity; }
            set { _voucher.Entity = value; }
        }

        [DataMember]
        [Column(Name = "SoPhieu")]
        public string SoPhieu { get; set; }

        [DataMember]
        [Column(Name = "Active")]
        public bool Active { get; set; }
    }
}
