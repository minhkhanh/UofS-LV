using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerBUS;
using LocalServerDTO;

namespace LocalServerWeb.Reports
{
    public class BillReportDataBinding
    {
        private List<BillReportData> billReportDatas  = new List<BillReportData>();
        public List<BillReportData> BillReportDatas
        {
            get { return billReportDatas; }
        }
        public List<BillReportData> GetBillReportDatas()
        {
            return billReportDatas;
        }
    }
}