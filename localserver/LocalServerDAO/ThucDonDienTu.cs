using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;

namespace LocalServerDAO
{
    public class ThucDonDienTu
    {
        private static ThucDonDienTuDataContext _dataContext;
        public static void KhoiTao(string strConn)
        {
            _dataContext = new ThucDonDienTuDataContext(strConn);
        }
        public static bool CanKhoiTao()
        {
            return _dataContext == null;
        }
        public static ThucDonDienTuDataContext DataContext
        {
            get { return _dataContext; }
        }
    }
}
