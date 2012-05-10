<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Kitchen" %>

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
        <% int iCount = 0; %>
        <% foreach (var chiTietOrder in (List<ChiTietOrder>) ViewData["listChiTietOrder"])
           { %>
               
           
		<tr <%: (iCount++%2==0)?"":"class=alternate-row" %> >
			<td><%: chiTietOrder %></td>
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
        <%} %>
		</table>