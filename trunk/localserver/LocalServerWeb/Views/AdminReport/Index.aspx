<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminReport" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminReportString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminReportString.Title %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listReport').selectbox({ inputClass: "listData", debug: true });
            $('#revenue_month_report').hide();
            $('#revenue_period_report').hide();
        });

        function changeReport(listReport) {
            $('#revenue_day_report').hide();
            $('#revenue_month_report').hide();
            $('#revenue_period_report').hide();

            var value = listReport.options[listReport.selectedIndex].value;
            if (value == 0) {
                $('#revenue_day_report').show();
            }
            else if (value == 1) {
                $('#revenue_month_report').show();
            }
            else if (value == 2) {
                $('#revenue_period_report').show();
            }
        }

    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div id="table-content">
        
        <%: AdminReportString.ChooseReport %>
        <select name="status" class="listReport" onchange="changeReport(this);">
                        <option value="0"><%: AdminReportString.RevenueDayReport %></option>
                        <option value="1"><%: AdminReportString.RevenueMonthReport %></option>
                        <option value="2"><%: AdminReportString.RevenuePeriodReport %></option>
       </select>

       <br />
       <br />

       <% Html.RenderPartial("RevenueDayReport"); %>
       <% Html.RenderPartial("RevenueMonthReport"); %>
       <% Html.RenderPartial("RevenuePeriodReport"); %>

    </div>
</asp:Content>
