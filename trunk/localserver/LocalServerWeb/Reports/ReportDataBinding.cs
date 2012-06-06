using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Reports.RevenueDayReport;
using LocalServerWeb.Reports.RevenueMonthReport;
using LocalServerWeb.Reports.RevenuePeriodReport;

namespace LocalServerWeb.Reports
{
    public class ReportDataBinding
    {
        public List<BillReportData> GetBillReportData()
        {
            return new List<BillReportData>();
        }

        public List<RevenueDayReportData> GetRevenueDayReportData()
        {
            return new List<RevenueDayReportData>();
        }

        public List<RevenueMonthReportData> GetRevenueMonthReportData()
        {
            return new List<RevenueMonthReportData>();
        }

        public List<RevenuePeriodReportData> GetRevenuePeriodReportData()
        {
            return new List<RevenuePeriodReportData>();
        }

    }
}