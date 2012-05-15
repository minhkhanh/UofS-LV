<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminSurcharge" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminSurchargeString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminSurchargeString.EditTitle%>
    <%: Url.RequestContext.RouteData.Values["id"] %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot add new promotion  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Main code  ----------------------------------------------------------------------->
    <% Html.BeginForm("Edit", "AdminSurcharge", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maPhuThu" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <!-- Ten khuyen mai  ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.SurchargeName %>:
                </th>
                <td>
                    <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenPhuThu") && ((Dictionary<string, string>) TempData["checkDic"])["tenPhuThu"]!=null) ? "-error" : ""%>"
                        name="tenPhuThu" value="<%:TempData["tenPhuThu"] ?? ""%>" />
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenPhuThu") && ((Dictionary<string, string>)TempData["checkDic"])["tenPhuThu"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["tenPhuThu"]%></div>
                    <% } %>
                </td>
            </tr>
            <!-- Mo ta khuyen mai  ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.SurchargeDescription %>:
                </th>
                <td>
                    <input type="text" class="inp-form" name="moTa" value="<%:TempData["moTa"] ?? ""%>" />
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <!-- Hinh thuc giam gia  ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.SurchargeType %>:
                </th>
                <td>
                    <select name="hinhThucTang" class="listHinhThucTang">
                        <option <%:((int)TempData["hinhThucTang"]==0)?"selected":"" %> value="0">
                            <%: AdminSurchargeString.SurchargeValue %></option>
                        <option <%:((int)TempData["hinhThucTang"]==1)?"selected":"" %> value="1">
                            <%: AdminSurchargeString.SurchargePercent %></option>
                    </select>
                </td>
                <td>
                </td>
            </tr>
            <!-- Gia tri giam  ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.Surcharge %>:
                </th>
                <td>
                    <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("giaTri") && ((Dictionary<string, string>) TempData["checkDic"])["giaTri"]!=null) ? "-error" : ""%>"
                        name="giaTri" value="<%:TempData["giaTri"] ?? ""%>" />
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("giaTri") && ((Dictionary<string, string>)TempData["checkDic"])["giaTri"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["giaTri"]%></div>
                    <% } %>
                </td>
            </tr>
            <!-- Ngay bat dau ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.StartDate %>
                </th>
                <td class="noheight">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr valign="top">
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
                            <td>
                                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("thoiDiem") && ((Dictionary<string, string>)TempData["checkDic"])["thoiDiem"] != null)
                                   { %>
                                <div class="error-left">
                                </div>
                                <div class="error-inner">
                                    <%:((Dictionary<string, string>)TempData["checkDic"])["thoiDiem"]%></div>
                                <% } %>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                </td>
            </tr>
            <!-- Thoi diem ket thuc ----------------------------->
            <tr>
                <th valign="top">
                    <%: AdminSurchargeString.EndDate %>
                </th>
                <td class="noheight">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr valign="top">
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
                            <td>
                                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("thoiDiemKetThucSau") && ((Dictionary<string, string>)TempData["checkDic"])["thoiDiemKetThucSau"] != null)
                                   { %>
                                <div class="error-left">
                                </div>
                                <div class="error-inner">
                                    <%:((Dictionary<string, string>)TempData["checkDic"])["thoiDiemKetThucSau"]%></div>
                                <% } %>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                </td>
            </tr>
            <!-- Submit button ----------------------------->
            <tr>
                <th>
                    &nbsp;
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Edit %>"/>
                    <input type="reset" value="<%: SharedString.Reset %>" />
                    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminSurcharge") %>';"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listHinhThucTang').selectbox({ inputClass: "listData", debug: true });
        });
    </script>
    <!-- Date chooser -->
    <link rel="stylesheet" href="../../Content/admincss/datePicker.css" type="text/css" />
    <script type="text/javascript">
    function daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }
    $(document).ready(function () {
        // Thoi diem bat dau =====================================
        <% if (TempData["namBatDau"]!=null) { %>
        $('#sy').val( <%:(int) TempData["namBatDau"]%> ); 
        <% } %>
        
        <% if (TempData["thangBatDau"]!=null) { %>
        $('#sm').val( <%:(int) TempData["thangBatDau"]%> ); 
        <% } %>        
        
        $('#sd').find('option').remove();
        var day = daysInMonth($('#sm').val(), $('#sy').val());
        for (var i = 1; i <= day; i++) {
            $('#sd').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
        }
        $('#sd').val(1);
        <% if (TempData["ngayBatDau"]!=null) { %>
        $('#sd').val( <%:(int) TempData["ngayBatDau"]%> ); 
        <% } %>   
        
        // Thoi diem ket thuc ===================================
        <% if (TempData["namKetThuc"]!=null) { %>
        $('#ey').val( <%:(int) TempData["namKetThuc"]%> ); 
        <% } %>
        
        <% if (TempData["thangKetThuc"]!=null) { %>
        $('#em').val( <%:(int) TempData["thangKetThuc"]%> ); 
        <% } %>        
        
        $('#ed').find('option').remove();
        var day = daysInMonth($('#em').val(), $('#ey').val());
        for (var i = 1; i <= day; i++) {
            $('#ed').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
        }
        $('#ed').val(1);
        <% if (TempData["ngayKetThuc"]!=null) { %>
        $('#ed').val( <%:(int) TempData["ngayKetThuc"]%> ); 
        <% } %>        
    });    
    </script>
</asp:Content>
