using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Data.Linq.Mapping;

namespace LocalServerDTO
{
    [DataContract(Name = "ChiTietMonLienQuan", Namespace = "")]
    [Table(Name = "ChiTietMonLienQuan")]
    public class ChiTietMonLienQuan
    {
        [DataMember(Name = "MaMonAn")]
        [Column(IsPrimaryKey = true, Name = "MaMonAn")]
        private int? _maMonAn;

        private EntityRef<MonAn> _monAn = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietMonLienQuan_FK1", IsForeignKey = true, Storage = "_monAn", ThisKey = "_maMonAn")]
        public MonAn MonAn
        {
            get { return _monAn.Entity; }
            set { _monAn.Entity = value; }
        }

        [DataMember(Name = "MaMonAnLienQuan")]
        [Column(IsPrimaryKey = true, Name = "MaMonAnLienQuan")]
        private int? _maMonAnLienQuan;

        private EntityRef<MonAn> _monAnLienQuan = new EntityRef<MonAn>();

        [Association(Name = "MonAn_ChiTietMonLienQuan_FK1", IsForeignKey = true, Storage = "_monAnLienQuan", ThisKey = "_maMonAnLienQuan")]
        public MonAn MonAnLienQuan
        {
            get { return _monAnLienQuan.Entity; }
            set { _monAnLienQuan.Entity = value; }
        }
    }
}
