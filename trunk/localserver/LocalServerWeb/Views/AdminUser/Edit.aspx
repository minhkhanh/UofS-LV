<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUser" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%:AdminUserString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminUserString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"] %>
</asp:Content>


<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot edit user  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>

    <% Html.BeginForm("Edit", "AdminUser", FormMethod.Post, new { enctype = "multipart/form-data" }); %>
    <!-- start id-form -->
    <input type="hidden" name="maTaiKhoan" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top">
                <%:AdminUserString.Username %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenTaiKhoan") && ((Dictionary<string, string>) TempData["checkDic"])["tenTaiKhoan"]!=null) ? "-error" : ""%>"
                    name="tenTaiKhoan" value="<%:TempData["tenTaiKhoan"] ?? ""%>" disabled="disabled"/>
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenTaiKhoan") && ((Dictionary<string, string>)TempData["checkDic"])["tenTaiKhoan"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["tenTaiKhoan"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.Password %>:
            </th>
            <td>
                <input type="password" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("matKhau") && ((Dictionary<string, string>) TempData["checkDic"])["matKhau"]!=null) ? "-error" : ""%>"
                    name="matKhau" value="<%:TempData["matKhau"] ?? ""%>"/>
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("matKhau") && ((Dictionary<string, string>)TempData["checkDic"])["matKhau"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["matKhau"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.ConfirmPassword %>:
            </th>
            <td>
                <input type="password" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("xacNhanMatKhau") && ((Dictionary<string, string>) TempData["checkDic"])["xacNhanMatKhau"]!=null) ? "-error" : ""%>"
                    name="xacNhanMatKhau" value="<%:TempData["matKhau"] ?? ""%>"/>
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("xacNhanMatKhau") && ((Dictionary<string, string>)TempData["checkDic"])["xacNhanMatKhau"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["xacNhanMatKhau"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.Name %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("hoTen") && ((Dictionary<string, string>) TempData["checkDic"])["hoTen"]!=null) ? "-error" : ""%>"
                    name="hoTen" value="<%:TempData["hoTen"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("hoTen") && ((Dictionary<string, string>)TempData["checkDic"])["hoTen"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["hoTen"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.BOD %>:
            </th>
            <td class="noheight">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr valign="top">
                        <td>
                            <select id="d" class="styledselect-day" name="day">
                            </select>
                        </td>
                        <td>
                            <select id="m" class="styledselect-month" name="month">
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
                            <select id="y" class="styledselect-year" name="year">
                                <% for (int i = 2005; i >= 1950; i--)
                                   { %>
                                <option <%:(i==1990)?"selected=true":"" %> value="<%:i %>">
                                    <%:i %></option>
                                <% } %>
                            </select>
                        </td>
                        <td>
                            <a href="" id="date-pick">
                                <img src="../../Images/adminimages/forms/icon_calendar.jpg" alt="" /></a>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.Gender %>:
            </th>
            <td>
                <select class="gioiTinh" name="gioiTinh">
                    <option value="0">
                        <%:AdminUserString.Male %></option>
                    <option value="1">
                        <%:AdminUserString.Female %></option>
                </select>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.Group %>:
            </th>
            <td>
                <%= Html.DropDownList("nhomTaiKhoan", new SelectList(ViewData["listNhomTaiKhoan"] as List<NhomTaiKhoan>, "MaNhomTaiKhoan", "TenNhom", (ViewData["listNhomTaiKhoan"]as List<NhomTaiKhoan>).First().MaNhomTaiKhoan), new { Class = "nhomTaiKhoan" })%>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%:AdminUserString.SocialID %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("cmnd") && ((Dictionary<string, string>) TempData["checkDic"])["cmnd"]!=null) ? "-error" : ""%>"
                    name="cmnd" value="<%:TempData["cmnd"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("cmnd"))
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>) TempData["checkDic"])["cmnd"] %></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th>
                <%:AdminUserString.Picture %>:
            </th>
            <td>
                <input type="file" class="file_1" name="picture" accept="image/*" />
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <th>
                &nbsp;
            </th>
            <td valign="top">
                <input type="submit" value="<%: SharedString.Edit %>"/>
                <input type="reset" value="<%: SharedString.Reset %>" />
                <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminUser") %>';"/>
            </td>
            <td>
            </td>
        </tr>
    </table>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.gioiTinh').selectbox({ inputClass: "gioiTinh" });
            $('.nhomTaiKhoan').selectbox({ inputClass: "nhomTaiKhoan" });
        });
    </script>
    <!--  date picker script -->
    <link rel="stylesheet" href="../../Content/admincss/datePicker.css" type="text/css" />
    <script src="../../Scripts/jquery/date.js" type="text/javascript"></script>
    <script src="../../Scripts/jquery/jquery.datePicker.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
    $(function () {

        // initialise the "Select date" link
        $('#date-pick')
	.datePicker(
        // associate the link with a date picker
		{
		createButton: false,
		startDate: '01/01/1950',
		endDate: '31/12/2005'
}
	).bind(
        // when the link is clicked display the date picker
		'click',
		function () {
		    updateSelects($(this).dpGetSelected()[0]);
		    $(this).dpDisplay();
		    return false;
		}
	).bind(
        // when a date is selected update the SELECTs
		'dateSelected',
		function (e, selectedDate, $td, state) {
		    updateSelects(selectedDate);
		}
	).bind(
		'dpClosed',
		function (e, selected) {
		    updateSelects(selected[0]);
		}
	);

        var updateSelects = function(selectedDate) {
            var selectedDate = new Date(selectedDate);
            $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
            $('#m option[value=' + (selectedDate.getMonth() + 1) + ']').attr('selected', 'selected');
            $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
        };
        // listen for when the selects are changed and update the picker
        $('#d, #y')
            .bind(
            'change',
            function () {
                var d = new Date(
                    $('#y').val(),
                    $('#m').val() - 1,
                    $('#d').val()
					);
                $('#date-pick').dpSetSelected(d.asString());
            });
        $('#m')
            .bind(
            'change',
            function () {
                var oldDay = $('#d').val();
                $('#d').find('option').remove();
                var day = daysInMonth($('#m').val(), $('#y').val());
                if (oldDay > day) oldDay = day;
                console.log("day: " + day);
                for (var i = 1; i <= day; i++) {
                    $('#d').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
                }
                $('#d').val(oldDay);
                var d = new Date(
                    $('#y').val(),
                    $('#m').val() - 1,
                    $('#d').val()
					);
                $('#date-pick').dpSetSelected(d.asString());
            }
	    );

        // default the position of the selects to today
        var today = new Date();
        updateSelects(today.getTime());

        // and update the datePicker to reflect it...
        $('#d').trigger('change');
    });
    function daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }
    $(document).ready(function () {
        <% if (TempData["year"]!=null) { %>
        $('#y').val( <%:(int) TempData["year"]%> ); 
        <% } %>
        
        <% if (TempData["month"]!=null) { %>
        $('#m').val( <%:(int) TempData["month"]%> ); 
        <% } %>        
        
        $('#d').find('option').remove();
        var day = daysInMonth($('#m').val(), $('#y').val());
        for (var i = 1; i <= day; i++) {
            $('#d').find('option').end().append("<option value='" + i + "'" + ">" + i + "</option");
        }
        $('#d').val(1);
        <% if (TempData["day"]!=null) { %>
        $('#d').val( <%:(int) TempData["day"]%> ); 
        <% } %>          
    });    
//</script>
    <!--  styled file upload script -->
    <script src="../../Scripts/jquery/jquery.filestyle.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            $("input.file_1").filestyle({
                image: "../../Images/adminimages/forms/choose-file.gif",
                imageheight: 21,
                imagewidth: 78,
                width: 310
            });
        });
    </script>
</asp:Content>
