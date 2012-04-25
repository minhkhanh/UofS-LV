<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUser" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:AdminUserString.Title %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

		<!-- start id-form -->
		<table border="0" cellpadding="0" cellspacing="0"  id="id-form">
		<tr>
			<th valign="top"><%:AdminUserString.Username %>:</th>
			<td><input type="text" class="inp-form" /></td>
			<td></td>
		</tr>
		<tr>
			<th valign="top"><%:AdminUserString.Password %>:</th>
			<td><input type="text" class="inp-form-error" /></td>
			<td>
			<div class="error-left"></div>
			<div class="error-inner">This field is required.</div>
			</td>
		</tr>
		<tr>
			<th valign="top"><%:AdminUserString.ConfirmPassword %>:</th>
			<td><input type="text" class="inp-form-error" /></td>
			<td>
			<div class="error-left"></div>
			<div class="error-inner">This field is required.</div>
			</td>
		</tr>
		<tr>
		<th valign="top"><%:AdminUserString.BOD %>:</th>
		<td class="noheight">
		
			<table border="0" cellpadding="0" cellspacing="0">
			<tr  valign="top">
				<td>
				<form id="chooseDateForm" action="#">
				
				<select id="d" class="styledselect-day">
					<option value="">dd</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
				</select>
                </form>
				</td>
				<td>
					<select id="m" class="styledselect-month">
						<% var dd = new DateTime(1, 1, 1);
                           for (int i = 1; i <= 12; i++)
                           {
                               string monthName = CultureInfo.CurrentCulture.DateTimeFormat.GetMonthName(dd.Month);
                               %>
                               <option value="<%:i %>"><%:monthName %></option>
                               <%
                               dd = dd.AddMonths(1);
                           }%>
					</select>
				</td>
				<td>
					<select  id="y"  class="styledselect-year">
                        <% for (int i = 2005; i >= 1950; i--) { %>
                           <option <%:(i==1990)?"selected=true":"" %> value="<%:i %>"><%:i %></option>
                        <% } %>						
					</select>					
				</td>
				<td><a href=""  id="date-pick"><img src="../../Images/adminimages/forms/icon_calendar.jpg"   alt="" /></a></td>
			</tr>
			</table>
		
		</td>
		<td></td>
	    </tr>

		<tr>
		<th valign="top"><%:AdminUserString.Gender %>:</th>
		<td>	
		<select  class="styledselect_form_1">
			<option value="">All</option>
			<option value="">Products</option>
			<option value="">Categories</option>
			<option value="">Clients</option>
			<option value="">News</option>
		</select>
		</td>
		<td></td>
		</tr>

		<tr>
		<th valign="top"><%:AdminUserString.Group %>:</th>
		<td>	
		<select  class="styledselect_form_1">
			<option value="">All</option>
			<option value="">Products</option>
			<option value="">Categories</option>
			<option value="">Clients</option>
			<option value="">News</option>
		</select>
		</td>
		<td></td>
		</tr> 

		<tr>
			<th valign="top"><%:AdminUserString.SocialID %>:</th>
			<td><input type="text" class="inp-form-error" /></td>
			<td>
			<div class="error-left"></div>
			<div class="error-inner">This field is required.</div>
			</td>
		</tr>

	<tr>
		<th valign="top">Description:</th>
		<td><textarea rows="" cols="" class="form-textarea"></textarea></td>
		<td></td>
	</tr>
	<tr>
	<th>Image 1:</th>
	<td><input type="file" class="file_1" /></td>
	<td>
	<div class="bubble-left"></div>
	<div class="bubble-inner">JPEG, GIF 5MB max per image</div>
	<div class="bubble-right"></div>
	</td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="button" value="" class="form-submit" />
			<input type="reset" value="" class="form-reset"  />
		</td>
		<td></td>
	</tr>
	</table>
	<!-- end id-form  -->

</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.styledselect_form_1').selectbox({ inputClass: "styledselect_form_1" });
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

        var updateSelects = function (selectedDate) {
            var selectedDate = new Date(selectedDate);
            $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
            $('#m option[value=' + (selectedDate.getMonth() + 1) + ']').attr('selected', 'selected');
            $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
        }
        // listen for when the selects are changed and update the picker
        $('#d, #m, #y')
	.bind(
		'change',
		function () {
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
    function initYear(objYear) {
        
    }
</script>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminUserString.AddUser %>
</asp:Content>
