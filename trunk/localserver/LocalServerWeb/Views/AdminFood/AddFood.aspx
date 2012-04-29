<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:AdminFoodString.Title %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% Html.BeginForm("AddUser", "AdminUser", FormMethod.Post); %>
		<!-- start id-form -->
		<table border="0" cellpadding="0" cellspacing="0"  id="id-form">
<%--		<tr>
			<th valign="top"><%:AdminFoodString.Username %>:</th>
			<td><input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenTaiKhoan") && ((Dictionary<string, string>) TempData["checkDic"])["tenTaiKhoan"]!=null) ? "-error" : ""%>" name="tenTaiKhoan" 
            value="<%:TempData["tenTaiKhoan"] ?? ""%>"/></td>
			<td>
            <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenTaiKhoan") && ((Dictionary<string, string>)TempData["checkDic"])["tenTaiKhoan"] != null)
               { %>
            <div class="error-left"></div>
			<div class="error-inner"><%:((Dictionary<string, string>)TempData["checkDic"])["tenTaiKhoan"]%></div>
            <% } %>
            </td>
		</tr>--%>

		<tr>
		<th valign="top"><%:AdminFoodString.Category %>:</th>
		<td>	
            <%= Html.DropDownList("listDanhMuc", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (ViewData["listDanhMuc"] as List<DanhMuc>).First().MaDanhMuc), new { Class = "listDanhMuc" })%>
		</td>
		<td></td>
		</tr> 

	<tr>
	<th><%:AdminFoodString.Picture %>:</th>
	<td><input type="file" class="file_1" name="picture" /></td>
	<td>
	</td>
	</tr>

	<tr>
	<th></th>
	<td><%: %></td>
	<td>
	</td>
	</tr>

	<tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" value="" class="form-submit" />
			<input type="reset" value="" class="form-reset"  />
		</td>
		<td></td>
	</tr>
	</table>
	<!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "listDanhMuc" });
        });
    </script>    
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

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminFoodString.AddFood %>
</asp:Content>
