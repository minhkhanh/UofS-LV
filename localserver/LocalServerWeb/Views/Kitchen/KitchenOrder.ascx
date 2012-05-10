<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Kitchen" %>

		<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
		<tr>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.MaChiTietOrder %></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.KhuVuc %></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.Ban %></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.MaOrder%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.TenMon%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.GhiChu%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.DonViTinh%></a></th>
			<th class="table-header-repeat line-left"><a href=""><%: KitchenString.SoLuong%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.DaXong%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.DangCheBien%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.PhucVu%></a></th>
            <th class="table-header-repeat line-left"><a href=""><%: KitchenString.TuyChon%></a></th>
		</tr>
        <% int iCount = 0; %>
        <% foreach (var chiTietOrder in (List<ChiTietOrderKitchen>)ViewData["listChiTietOrderKitchen"])
           { %>
               
           
		<tr <%: (iCount++%2==0)?"":"class=alternate-row" %> >
            <td><%: chiTietOrder.MaChiTietOrder %></td>
			<td><%: chiTietOrder.TenKhuVuc %></td>
			<td><%: chiTietOrder.TenBan %></td>
            <td><%: chiTietOrder.MaOrder %></td>
			<td><%: chiTietOrder.TenMonAn %></td>
			<td><%: chiTietOrder.GhiChu %></td>
			<td><%: chiTietOrder.TenDonViTinh %></td>
            <td><%: chiTietOrder.SoLuong %></td>
            <td><%: chiTietOrder.SoLuongDaCheBien %></td>
            <% if (chiTietOrder.SoLuongDangCheBien>0) {%>    
                <td><%: chiTietOrder.SoLuongDangCheBien %></td>
            <%} else { %>
                <td><a><%: KitchenString.CheBien %></a></td>
            <%} %>
            <td><%: chiTietOrder.TenPhucVu %></td>
			<td class="options-width">
			<a href="" title="Edit" class="icon-1 info-tooltip"></a>
			<a href="" title="Edit" class="icon-2 info-tooltip"></a>
			</td>
		</tr>
        <%} %>
		</table>