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
    [DataContract(Name = "PhuThuKhuVuc", Namespace = "")]
    [Table(Name = "PhuThuKhuVuc")]
    public class PhuThuKhuVuc
    {
        [DataMember(Name = "MaKhuVuc")]
        [Column(Name = "MaKhuVuc")]
        private int? _maKhuVuc;
        private EntityRef<KhuVuc> _khuVuc = new EntityRef<KhuVuc>();

        [Association(Name = "KhuVuc_PhuThuKhuVuc_FK1", IsForeignKey = true, Storage = "_khuVuc", ThisKey = "_maKhuVuc")]
        public KhuVuc KhuVuc
        {
            get { return _khuVuc.Entity; }
            set { _khuVuc.Entity = value; }
        }

        [DataMember(Name = "MaPhuThu")]
        [Column(Name = "MaPhuThu")]
        private int? _maPhuThu;
        private EntityRef<PhuThu> _phuThu = new EntityRef<PhuThu>();

        [Association(Name = "PhuThu_PhuThuKhuVuc_FK1", IsForeignKey = true, Storage = "_phuThu", ThisKey = "_maPhuThu")]
        public PhuThu PhuThu
        {
            get { return _phuThu.Entity; }
            set { _phuThu.Entity = value; }
        }
    }
}
