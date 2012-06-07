using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;


namespace LocalServerDTO
{
    [DataContract(Name = "Voucher", Namespace = "")]
    [Table(Name = "Voucher")]
    public class Voucher
    {
        [DataMember]
        [Column(IsPrimaryKey = true, IsDbGenerated = true, Name = "MaVoucher")]
        public int MaVoucher { get; set; }

        [DataMember]
        [Column(Name = "TenVoucher")]
        public string TenVoucher { get; set; }

        [DataMember]
        [Column(Name = "MoTaVoucher")]
        public string MoTaVoucher { get; set; }

        [DataMember]
        [Column(Name = "MucGiaApDung")]
        public float MucGiaApDung { get; set; }

        [DataMember]
        [Column(Name = "GiaGiam")]
        public float GiaGiam { get; set; }

        [DataMember]
        [Column(Name = "BatDau")]
        public DateTime BatDau { get; set; }

        [DataMember]
        [Column(Name = "KetThuc")]
        public DateTime KetThuc { get; set; }
    }
}
