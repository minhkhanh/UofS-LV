<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminReport" %>
<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>

<script type="text/javascript">
    function daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }
    $(document).ready(function () {
        $('#day_d').find('option').remove();
        var day = daysInMonth($('#day_m').val(), $('#day_y').val());
        for (var i = 1; i <= day; i++) {
            $('#day_d').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
        }
    });

    function printDayPreview() {
        var ngay = $('#day_d').val();
        var thang = $('#day_m').val();
        var nam = $('#day_y').val();
        var nguoiLap = '<%: ViewData["nguoiLap"] %>';

        var newwindow = window.open('<%: SharedCode.GetHostApplicationAddress(Request) + "ReportForms/RevenueDayReportForm?p=" %>' +nguoiLap+'&d='+ngay+'&m='+thang+'&y='+nam+'', 'name', 'height=800,width=750');
        if (window.focus) {
            newwindow.focus();
        }
    }   
</script>
<div id="revenue_day_report">
    <h3>
        <%: AdminReportString.RevenueDayReport %></h3>
    <% Html.BeginForm("PrintRevenueDayReport", "AdminReport", FormMethod.Post); %>
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <th valign="middle" style="padding-right:10px">
                <%: AdminReportString.ChooseDate %>
            </th>
            <td>
                <select id="day_d" class="styledselect-day" name="ngay">
                </select>
            </td>
            <td>
                <select id="day_m" class="styledselect-month" name="thang">
                    <% var dd = new DateTime(1, 1, 1);
                       for (int i = 1; i <= 12; i++)
                       {
                           string monthName = CultureInfo.CurrentCulture.DateTimeFormat.GetMonthName(dd.Month);
                    %>
                    <option value="<%:i %>">
                        <%:monthName %></option>
                    <%
                           dd = dd.AddMonths(1);
                       }%>
                </select>
            </td>
            <td>
                <select id="day_y" class="styledselect-year" name="nam">
                    <% for (int i = 2015; i >= 1990; i--)
                       { %>
                    <option <%:(i==2012)?"selected=true":"" %> value="<%:i %>">
                        <%:i %></option>
                    <% } %>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="<%: AdminReportString.Print %>" />
    <% Html.EndForm(); %>
    <input type="button" value="<%: AdminReportString.PrintPreview %>" onclick="printDayPreview();" />
</div>
