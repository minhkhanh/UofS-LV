<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Kitchen" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
	<!--  start table-content  -->
	<div id="table-content">
			
		<!--  start message-yellow -->
		<div id="message-yellow">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="yellow-left">You have a new message. <a href="">Go to Inbox.</a></td>
			<td class="yellow-right"><a class="close-yellow"><img src="../../Images/adminimages/table/icon_close_yellow.gif"   alt="" /></a></td>
		</tr>
		</table>
		</div>
		<!--  end message-yellow -->						
		 
		<!--  start product-table ..................................................................................... -->
		<form id="mainform" action="">
		<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
		<tr>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.KhuVuc %></a>	</th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.Ban %></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.MaOrder%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.TenMon%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.DonViTinh%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.SoLuong%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.DaXong%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.DangCheBien%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.PhucVu%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.TuyChon%></a></th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>5</td>
            <td>6</td>
            <td>7</td>
            <td>8</td>
            <td>9</td>
			<td class="options-width">
			<a href="" title="Edit" class="icon-1 info-tooltip"></a>
			<a href="" title="Edit" class="icon-2 info-tooltip"></a>
			</td>
		</tr>
		<tr class="alternate-row">
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>5</td>
            <td>6</td>
            <td>7</td>
            <td>8</td>
            <td>9</td>
			<td class="options-width">
			<a href="" title="Edit" class="icon-1 info-tooltip"></a>
			<a href="" title="Edit" class="icon-2 info-tooltip"></a>
			</td>
		</tr>
		</table>
		<!--  end product-table................................... --> 
		</form>
	</div>
	<!--  end content-table  -->

</asp:Content>
