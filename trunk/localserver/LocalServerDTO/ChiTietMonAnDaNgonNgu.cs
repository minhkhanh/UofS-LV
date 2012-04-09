using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietMonAnDaNgonNgu", Namespace = "")]
    [Table(Name = "ChiTietMonAnDaNgonNgu")]
    public class ChiTietMonAnDaNgonNgu
    {
        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true,  Name = "MaMonAn")]
        private int? _maMonAn;

        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietMonAnDaNgonNgu_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember(Name = "MaNgonNgu")]
        [Column(IsPrimaryKey = true,  Name = "MaNgonNgu")]
        private int? _maNgonNgu;

        private EntityRef<NgonNgu> _ngonNgu = new EntityRef<NgonNgu>();

        [Association(Name = "NgonNgu_ChiTietMonAnDaNgonNgu_FK1", IsForeignKey = true, Storage = "_ngonNgu", ThisKey = "_maNgonNgu")]
        public NgonNgu NgonNgu
        {
            get { return _ngonNgu.Entity; }
            set { _ngonNgu.Entity = value; }
        }

        [DataMember]
        [Column(Name = "TenMonAn")]
        public string TenMonAn { get; set; }

        [DataMember]
        [Column(Name = "MoTaMonAn")]
        public string MoTaMonAn { get; set; }
    }
}
