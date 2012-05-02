<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
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
            <select name="listDanhMuc" class="listDanhMuc" onchange="submit();">
            <% foreach (var danhMuc in ViewData["listDanhMuc"] as List<DanhMuc>)
               { %>
                <option value="<%:danhMuc.MaDanhMuc %>" <%:(danhMuc.MaDanhMuc==(ViewData["monAn"] as MonAn).DanhMuc.MaDanhMuc)?"selected=true":"" %>><%:danhMuc.TenDanhMuc %></option>   
               <%} %>                
            </select>
            <input type="hidden" name="maMonAn" value="<%: Request.QueryString["maMonAn"] %>"/>	
            <% Html.EndForm(); %>
		</td>
		<td></td>
		</tr>         

	    <tr>
	    <th><%:AdminFoodString.Picture %>:</th>
	    <td><div class="image_food"><img src="<%:SharedCode.GetHostApplicationAddress(Request)+(ViewData["monAn"] as MonAn).HinhAnh %>" alt=""/></div></td>
	    <td>
	    </td>
	    </tr>

	    <tr>
	    <th><%:AdminFoodString.PictureUpdate %>:</th>
	    <td>
            <% Html.BeginForm("UpdateImageFood", "AdminFood", FormMethod.Post, new {enctype = "multipart/form-data"}); %>
            <input type="file" class="file_1" name="uploadFile" accept="image/*" onchange="submit();"/>
            <input type="hidden" name="maMonAn" value="<%: Request.QueryString["maMonAn"] %>"/>
            <% Html.EndForm(); %>
        </td>
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
		    <th><%:AdminFoodString.Unit %></th>
		    <td>
                <table id="hor-minimalist-b" summary="Employee Pay Sheet">
                    <thead>
    	                <tr>
        	                <th scope="col"><%:AdminFoodString.Unit %></th>
                            <th scope="col"><%:AdminFoodString.Price %></th>
                            <th scope="col"><%:AdminFoodString.UnitPriceAction %></th>
                        </tr>
                    </thead>
                    <tbody>
                    <% foreach (var chiTienMonAnDonViTinh in ViewData["listChiTienMonAnDonViTinh"] as List<ChiTietMonAnDonViTinh>) { %>                                          
    	                <tr>
        	                <td><%:chiTienMonAnDonViTinh.TenDonViTinh %></td>
                            <td><input value="<%:chiTienMonAnDonViTinh.DonGia %>"/></td>
                            <td><%:Html.ActionLink(" ", "LockUnlock", "AdminUser", new {  }, new { tilte = AdminFoodString.Save, Class = "icon-1 info-tooltip" })%>
                                <%:Html.ActionLink(" ", "LockUnlock", "AdminUser", new {  }, new { tilte = AdminFoodString.Delete, Class = "icon-2 info-tooltip" })%>
                            </td>
                        </tr>
                    <%} %>
                    </tbody>
                </table>

			    <%--<div id="unit_content">                
                    <div class="unit">
                        <div class="unit_name"></div>
                        <div class="unit_price"></div>
                        <div class="unit_action"></div>
                    </div>                
                </div>--%>
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
    $(document).ready(function () {
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
<!-- Tooltips -->
<script src="../../Scripts/jquery/jquery.tooltip.js" type="text/javascript"></script>
<script src="../../Scripts/jquery/jquery.dimensions.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
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
