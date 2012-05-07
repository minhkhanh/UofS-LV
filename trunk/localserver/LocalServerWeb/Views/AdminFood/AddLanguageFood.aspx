<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%: AdminFoodString.AddLanguageFood %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (TempData["error"] != null) {%>       
    <div id="message-red">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td class="red-left"><%:AdminFoodString.Error %> <a href=""><%:TempData["error"] %></a></td>
			<td class="red-right"><a class="close-red"><img src="../../Images/adminimages/table/icon_close_red.gif" alt=""/></a></td>
		</tr>
		</tbody></table>
    </div>
    <% } %>
    <% Html.BeginForm("AddLanguageFood", "AdminFood", FormMethod.Post); %>
    <table border="0" cellpadding="0" cellspacing="0"  id="id-form">

    	<tr>
		<th valign="top"><%:AdminFoodString.FoodName %>:</th>
		<td>
            <input type="text" name="foodName" value="<%: TempData["tenMonAn"] ?? "" %>"/>
		</td>
		<td></td>
		</tr>  

    	<tr>
		<th valign="top"><%:AdminFoodString.FoodDescription %>:</th>
		<td>
            <textarea id="foodDescription" name="foodDescription" rows="80"><%: TempData["moTaMonAn"] ?? "" %></textarea>
		</td>
		<td></td>
		</tr>  

    	<tr>
		<th></th>
		<td>
            <input type="button" style="float: right;margin-right: 22px;" id="them-ngon-ngu-mon" value="<%:AdminFoodString.AddLanguageFood%>" />
        </td>
		<td></td>
		</tr>  
    </table>
    <% Html.EndForm(); %>

</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Content/ckeditor/ckeditor.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#them-ngon-ngu-mon').button().click(function () {
            $('').submit();
        });
        CKEDITOR.replace('foodDescription');
    });
</script>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminFoodString.AddLanguageFood + ": " + (ViewData["ngonNgu"] as NgonNgu).TenNgonNgu %>
</asp:Content>
