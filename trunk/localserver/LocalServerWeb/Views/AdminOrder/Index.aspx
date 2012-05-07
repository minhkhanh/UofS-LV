﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminOrder" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%: AdminOrderString.Title %>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.nhomTaiKhoan').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
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
    <%: AdminOrderString.Title %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (ViewData["listOrder"] != null && ((List<Order>)ViewData["listOrder"]).Count>0)
       {
           %>
	<div id="table-content">				 
		<!--  start product-table ..................................................................................... -->
		<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">        
		<tr>
			<th class="table-header-repeat line-left"><a><%: AdminOrderString.OrderID %></a> </th>
			<th class="table-header-repeat line-left minwidth-1"><a><%:AdminOrderString.AccountName%></a>	</th>
			<th class="table-header-repeat line-left minwidth-1"><a><%:AdminOrderString.TableName%></a></th>
			<th class="table-header-repeat line-left"><a><%:AdminOrderString.Option%></a></th>
		</tr>
        <% int iCount = 0; %>       
        <% foreach (var order in (List<Order>)ViewData["listOrder"])
{ %>
		<tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %> >
			<td><%:order.MaOrder %></td>
			<td><%:order.TaiKhoan.TenTaiKhoan %></td>
			<td><%:order.Ban.TenBan %></td>
            <td class="options-width">
                <%:Html.ActionLink(" ", "OrderDetail", "AdminOrder", new { id = order.MaOrder }, new { title = AdminOrderString.DetailTitle, Class = "icon-6 info-tooltip" })%>
            </td>
		</tr>
        <% } %>

		</table>
		<!--  table................ --> 
	</div>
	<!--  end content-table  -->
    <% } else { %>
    <div id="Div1">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        <%:SharedString.Error %> <a href="">
                            <%: AdminOrderString.NoData %></a>
                    </td>
                    <td class="red-right">
                        <a class="close-red">
                            <img src="../../Images/adminimages/table/icon_close_red.gif" alt="" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <%} %>

</asp:Content>

