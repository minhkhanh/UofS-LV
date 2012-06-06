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
<div id="revenue_period_report">
    <h3>
        <%: AdminReportString.RevenuePeriodReport %></h3>
    <% Html.BeginForm("PrintRevenuePeriodReport", "AdminReport", FormMethod.Post); %>
    <table border="0" cellpadding="0" cellspacing="0">
        <tr valign="top">
            <td>
                <table>
                    <!-- Ngay bat dau -->
                    <tr>
                        <th valign="middle" style="padding-right: 10px">
                            <%: AdminReportString.ChooseStartDate %>
                        </th>
                        <td>
                            <select id="period_sd" class="styledselect-day" name="ngayBatDau">
                            </select>
                        </td>
                        <td>
                            <select id="sm" class="styledselect-month" name="thangBatDau">
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
                            <select id="sy" class="styledselect-year" name="namBatDau">
                                <% for (int i = 2015; i >= 1990; i--)
                                   { %>
                                <option <%:(i==2012)?"selected=true":"" %> value="<%:i %>">
                                    <%:i %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <!-- Ngay ket thuc -->
                    <tr>
                        <th valign="middle" style="padding-right: 10px">
                            <%: AdminReportString.ChooseEndDate %>
                        </th>
                        <td>
                            <select id="ed" class="styledselect-day" name="ngayKetThuc">
                            </select>
                        </td>
                        <td>
                            <select id="em" class="styledselect-month" name="thangKetThuc">
                                <% var edd = new DateTime(1, 1, 1);
                                   for (int i = 1; i <= 12; i++)
                                   {
                                       string monthName = CultureInfo.CurrentCulture.DateTimeFormat.GetMonthName(edd.Month);
                                %>
                                <option value="<%:i %>">
                                    <%:monthName %></option>
                                <%
                                       edd = edd.AddMonths(1);
                                   }%>
                            </select>
                        </td>
                        <td>
                            <select id="ey" class="styledselect-year" name="namKetThuc">
                                <% for (int i = 2015; i >= 1990; i--)
                                   { %>
                                <option <%:(i==2012)?"selected=true":"" %> value="<%:i %>">
                                    <%:i %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <input type="submit" value="<%: AdminReportString.Print %>" />
    <% Html.EndForm(); %>
    <input type="button" value="<%: AdminReportString.PrintPreview %>" onclick="printPreview();" />
</div>
