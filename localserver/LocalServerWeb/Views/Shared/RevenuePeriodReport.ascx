<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminReport" %>
<%@ Import Namespace="System.Globalization" %>
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
    });

    function printPeriodPreview() {
        var ngayBatDau = $('#sd').val();
        var thangBatDau = $('#sm').val();
        var namBatDau = $('#sy').val();
        var ngayKetThuc = $('#ed').val();
        var thangKetThuc = $('#em').val();
        var namKetThuc = $('#ey').val();
        var nguoiLap = '<%: ViewData["nguoiLap"] %>';

        var period_url = 'ReportForms/RevenuePeriodReportForm?p=' + nguoiLap
                                         + '&sd=' + ngayBatDau + '&sm=' + thangBatDau + '&sy=' + namBatDau
                                         + '&ed=' + ngayKetThuc + '&em=' + thangKetThuc + '&ey=' + namKetThuc + '';
                                                                    
        var newwindow = window.open(period_url, 'name', 'height=800,width=750');
        if (window.focus) {
            newwindow.focus();
        }
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
                            <select id="sd" class="styledselect-day" name="ngayBatDau">
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
    <input type="button" value="<%: AdminReportString.PrintPreview %>" onclick="printPeriodPreview();" />
</div>
