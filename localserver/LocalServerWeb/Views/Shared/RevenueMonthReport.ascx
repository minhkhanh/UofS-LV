<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminReport" %>
<%@ Import Namespace="System.Globalization" %>
<script type="text/javascript">
    function printPreview() {
        var day = $('#sd').val();
        var month = $('#sm').val();
        var year = $('#sy').val();
    }   
</script>
<div id="revenue_month_report">
    <h3>
        <%: AdminReportString.RevenueMonthReport %></h3>
    <% Html.BeginForm("PrintRevenueMonthReport", "AdminReport", FormMethod.Post); %>
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <th valign="middle" style="padding-right:10px">
                <%: AdminReportString.ChooseMonth %>
            </th>
            <td>
                <select id="sm" class="styledselect-month" name="thang">
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
                <select id="sy" class="styledselect-year" name="nam">
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
    <input type="button" value="<%: AdminReportString.PrintPreview %>" onclick="printPreview();" />
</div>
