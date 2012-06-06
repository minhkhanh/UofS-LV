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
        function daysInMonth(month, year) {
            return new Date(year, month, 0).getDate();
        }
        $(document).ready(function () {
            // Thoi diem bat dau =====================================   
            $('#sd').find('option').remove();
            var day = daysInMonth($('#sm').val(), $('#sy').val());
            for (var i = 1; i <= day; i++) {
                $('#sd').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
            }
            // Thoi diem ket thuc ===================================
            $('#ed').find('option').remove();
            var day = daysInMonth($('#em').val(), $('#ey').val());
            for (var i = 1; i <= day; i++) {
                $('#ed').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
            }

            // Thoi diem bat dau cho Period Report
            $('#period_sd').find('option').remove();
            var day = daysInMonth($('#sm').val(), $('#sy').val());
            for (var i = 1; i <= day; i++) {
                $('#period_sd').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
            }
        });

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

        $(document).ready(function () {
            $('#open-report-demo').button().click(function () {
                var newwindow = window.open('ReportForms/BillReportForm?maHoaDon=<%:ViewData["maHoaDon"] %>&maNgonNgu=<%:ViewData["maNgonNgu"] %>', 'name', 'height=800,width=600');
                if (window.focus) {
                    newwindow.focus();
                }
            });

        });

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
