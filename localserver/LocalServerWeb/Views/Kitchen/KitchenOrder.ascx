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
               
          
		<tr <%: (iCount++%2==0)?"":"class=alternate-row" %> <%= (chiTietOrder.TinhTrang==2)?"style='border-style: solid; border-color: red;'":(chiTietOrder.TinhTrang==3)?"style='border-style: solid; border-color: green;'":"" %> >
            <td class="ma-chi-tiet-order"><%: chiTietOrder.MaChiTietOrder %></td>
			<td><%: chiTietOrder.TenKhuVuc %></td>
			<td><%: chiTietOrder.TenBan %></td>
            <td><%: chiTietOrder.MaOrder %></td>
			<td><%: chiTietOrder.TenMonAn %></td>
			<td><%: chiTietOrder.GhiChu %></td>
			<td><%: chiTietOrder.TenDonViTinh %></td>
            <td><%: chiTietOrder.SoLuong %></td>
            <td><%: chiTietOrder.SoLuongDaCheBien %></td>
            <td><%: chiTietOrder.SoLuongDangCheBien + " " %>            
            <% if (chiTietOrder.TinhTrang!=2 && chiTietOrder.SoLuongDangCheBien + chiTietOrder.SoLuongDaCheBien < chiTietOrder.SoLuong) {%>    
                <button class="button-che-bien"><%: KitchenString.CheBien %></button>
            <%} %>
            <% if (chiTietOrder.SoLuongDangCheBien > 0) {%>    
                <button class="button-che-bien-xong"><%: KitchenString.Xong %></button>
            <%} %>
            <%--<% if (chiTietOrder.TinhTrang==2) {%>    
                <button class="button-bi-khoa-che-bien"><%: KitchenString.BiKhoa %></button>
            <%} %>--%>
            </td>
            <td><%: chiTietOrder.TenPhucVu %></td>
			<td class="options-width">
            <% if (chiTietOrder.TinhTrang==3) {%>    
                <%:KitchenString.DaXong %>
            <%} else if (chiTietOrder.TinhTrang != 2) { %>
                <a id="button-het-che-bien" title="<%:KitchenString.HetCheBien %>" class="icon-2 info-tooltip"></a>
            <%} else { %>
                <%:KitchenString.BiKhoa %>
            <%} %>
			</td>
		</tr>
        <%} %>
		</table>

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