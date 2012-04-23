<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUser" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminUserString.AccountList %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
	<!--  start table-content  -->
    <% if (ViewData["listTaiKhoan"] != null && ((List<TaiKhoan>)ViewData["listTaiKhoan"]).Count>0)
       {%>
	<div id="table-content">				 
		<!--  start product-table ..................................................................................... -->
		<form id="mainform" action="">
		<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">        
		<tr>
			<th class="table-header-repeat line-left"><a>Av</a> </th>
			<th class="table-header-repeat line-left minwidth-1"><a href=""><%:AdminUserString.Username%></a>	</th>
			<th class="table-header-repeat line-left minwidth-1"><a href=""><%:AdminUserString.Name%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%:AdminUserString.BOD%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%:AdminUserString.Gender%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%:AdminUserString.SocialID%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%:AdminUserString.Group%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%:AdminUserString.Status%></a></th>
			<th class="table-header-options line-left"><a href=""><%:AdminUserString.Options%></a></th>
		</tr>
        <% int iCount = 0; %>
        <% foreach (var taiKhoan in (List<TaiKhoan>)ViewData["listTaiKhoan"])
{ %>
		<tr <%: (iCount%2==0)?"":"class=alternate-row" %> >
			<td>Avatar</td>
			<td><%:taiKhoan.TenTaiKhoan %></td>
			<td><%:taiKhoan.HoTen %></td>
			<td><%:taiKhoan.NgaySinh %></td>
			<td><%:taiKhoan.GioiTinh %></td>
			<td><%:taiKhoan.CMND %></td>
            <td><%:taiKhoan.NhomTaiKhoan.TenNhom %></td>
            <td><%:taiKhoan.Active %></td>
			<td class="options-width">
			<a href="" title="Edit" class="icon-1 info-tooltip"></a>
			<a href="" title="Edit" class="icon-2 info-tooltip"></a>
			<a href="" title="Edit" class="icon-3 info-tooltip"></a>
			<a href="" title="Edit" class="icon-4 info-tooltip"></a>
			<a href="" title="Edit" class="icon-5 info-tooltip"></a>
			</td>
		</tr>
        <% } %>

		</table>
		<!--  end product-table................................... --> 
		</form>
	</div>
	<!--  end content-table  -->
		
	<!--  start actions-box ............................................... -->
	<div id="actions-box">
		<a href="" class="action-slider"></a>
		<div id="actions-box-slider">
			<a href="" class="action-edit">Edit</a>
			<a href="" class="action-delete">Delete</a>
		</div>
		<div class="clear"></div>
	</div>
	<!-- end actions-box........... -->
			
	<!--  start paging..................................................... -->
	<table border="0" cellpadding="0" cellspacing="0" id="paging-table">
	<tr>
	<td>
		<a href="" class="page-far-left"></a>
		<a href="" class="page-left"></a>
		<div id="page-info">Page <strong>1</strong> / 15</div>
		<a href="" class="page-right"></a>
		<a href="" class="page-far-right"></a>
	</td>
	<td>
	<select  class="styledselect_pages">
		<option value="">Number of rows</option>
		<option value="">1</option>
		<option value="">2</option>
		<option value="">3</option>
	</select>
	</td>
	</tr>
	</table>
	<!--  end paging................ -->
    <% } else { %>
    <h3 align="center"><%:AdminUserString.NoData %></h3>
    <%} %>
</asp:Content>

