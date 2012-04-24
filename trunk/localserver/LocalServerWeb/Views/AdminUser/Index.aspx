<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUser" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:AdminUserString.Title %>
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
			<th class="table-header-repeat line-left minwidth-1"><a><%:AdminUserString.Username%></a>	</th>
			<th class="table-header-repeat line-left minwidth-1"><a><%:AdminUserString.Name%></a></th>
			<th class="table-header-repeat line-left"><a><%:AdminUserString.BOD%></a></th>
			<th class="table-header-repeat line-left"><a><%:AdminUserString.Gender%></a></th>
			<th class="table-header-repeat line-left"><a><%:AdminUserString.SocialID%></a></th>
            <th class="table-header-repeat line-left"><a><%:AdminUserString.Group%></a></th>
            <th class="table-header-repeat line-left"><a><%:AdminUserString.Status%></a></th>
			<th class="table-header-options line-left"><a><%:AdminUserString.Options%></a></th>
		</tr>
        <% int iCount = 0; %>       
        <% foreach (var taiKhoan in (List<TaiKhoan>)ViewData["listTaiKhoan"])
{ %>
		<tr <%: (iCount++%2==0)?"":"class=alternate-row" %> >
			<td>Avatar</td>
			<td><%:taiKhoan.TenTaiKhoan %></td>
			<td><%:taiKhoan.HoTen %></td>
			<td><%:taiKhoan.NgaySinh %></td>
			<td><%:(taiKhoan.GioiTinh==0)?AdminUserString.Male:AdminUserString.Female %></td>
			<td><%:taiKhoan.CMND %></td>            
            <td>                
                <% Html.BeginForm("ChangeGroupUser", "AdminUser", FormMethod.Post); %>
                <%= Html.DropDownList("nhomTaiKhoan" + iCount, new SelectList(ViewData["listNhomTaiKhoan"] as List<NhomTaiKhoan>, "MaNhomTaiKhoan", "TenNhom", taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan), new { onchange = "submit();", Class = "nhomTaiKhoan"+iCount })%>
                <input type="hidden" name="maTaiKhoan" value="<%:taiKhoan.MaTaiKhoan %>"/>
                <% Html.EndForm(); %>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('.nhomTaiKhoan<%:iCount %>').selectbox({ inputClass: "styledselect_pages" });
                    });
                </script>
            </td>
            <td><%:taiKhoan.Active?AdminUserString.Active:AdminUserString.Deactive %></td>
			<td class="options-width">
			<a href="" title="<%:AdminUserString.Edit %>" class="icon-1 info-tooltip"></a>			
            <a href="" title="<%:AdminUserString.Lock %>" class="icon-6 info-tooltip"></a>
            <a href="" title="<%:AdminUserString.Delete %>" class="icon-2 info-tooltip"></a>
<%--			<a href="" title="Edit" class="icon-3 info-tooltip"></a>
			<a href="" title="Edit" class="icon-4 info-tooltip"></a>
			<a href="" title="Active" class="icon-5 info-tooltip"></a>--%>
			</td>
		</tr>
        <% } %>

		</table>
		<!--  end product-table................................... --> 
		</form>
	</div>
	<!--  end content-table  -->
    <% } else { %>
    <h3 align="center"><%:AdminUserString.NoData %></h3>
    <%} %>
</asp:Content>

