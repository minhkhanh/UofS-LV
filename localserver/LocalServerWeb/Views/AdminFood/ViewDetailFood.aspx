<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	ViewDetailFood
</asp:Content>


<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <%--<% Html.BeginForm("AddFood", "AdminFood", FormMethod.Post, new { enctype = "multipart/form-data" }); %>--%>
		<!-- start id-form -->
		<table border="0" cellpadding="0" cellspacing="0"  id="id-form">
        
		<tr>
		<th valign="top"><%:AdminFoodString.Category %>:</th>
		<td>
            <% Html.BeginForm("UpdateCategory", "AdminFood", FormMethod.Post); %>                    
            <%= Html.DropDownList("listDanhMuc", ViewData["listDanhMuc"] as SelectList, new { Class = "listDanhMuc", onchange = "submit();"})%>
            <input type="hidden" name="maMonAn" value="<%: Request.QueryString["maMonAn"] %>"/>	
            <% Html.EndForm(); %>
		</td>
		<td></td>
		</tr>         

	    <tr>
	    <th><%:AdminFoodString.Picture %>:</th>
	    <td><input type="file" class="file_1" name="uploadFile" accept="image/*" /></td>
	    <td>
	    </td>
	    </tr>

	    <tr>
	    <th></th>
	    <td><%:AdminFoodString.PictureSize %></td>
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
    <%--<% Html.EndForm(); %>--%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "listDanhMuc", debug: true });
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
<!-- Tooltips -->
<script src="../../Scripts/jquery/jquery.tooltip.js" type="text/javascript"></script>
<script src="../../Scripts/jquery/jquery.dimensions.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $('a.info-tooltip ').tooltip({
            track: true,
            delay: 0,
            fixPNG: true,
            showURL: false,
            showBody: " - ",
            top: -35,
            left: 5
        });
    });
</script>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
</asp:Content>
