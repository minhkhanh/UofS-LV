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
    [DataContract(Name = "KhuyenMaiMon", Namespace = "")]
    [Table(Name = "KhuyenMaiMon")]
    public class KhuyenMaiMon
    {
        [DataMember(Name = "MaKhuyenMai")]
        [Column(IsPrimaryKey = true, Name = "MaKhuyenMai")]
        private int? _maKhuyenMai;

        private EntityRef<KhuyenMai> _khuyenMai = new EntityRef<KhuyenMai>();

        [Association(Name = "KhuyenMai_KhuyenMaiMon_FK1", IsForeignKey = true, Storage = "_khuyenMai", ThisKey = "_maKhuyenMai")]
        public KhuyenMai KhuyenMai
        {
            get { return _khuyenMai.Entity; }
            set { _khuyenMai.Entity = value; }
        }


        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true, Name = "MaMonAn")]
        private int? _maMonAn;

        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_KhuyenMaiMon_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }
    }
}
