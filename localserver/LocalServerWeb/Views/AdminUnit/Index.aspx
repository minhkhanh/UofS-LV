<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUnit" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminUnitString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminUnitString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this unit  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!--  start table-content  -->
    <% if (ViewData["listDonViTinh"] != null && ((List<DonViTinh>)ViewData["listDonViTinh"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminUnitString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminUnitString.UnitName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminUnitString.Option %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var donViTinh in (List<DonViTinh>)ViewData["listDonViTinh"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: donViTinh.TenDonViTinh %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminUnit", new { id = donViTinh.MaDonViTinh }, new { title = AdminUnitString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminUnit", new { id = donViTinh.MaDonViTinh }, new { title = AdminUnitString.Delete, Class = "icon-2 info-tooltip" })%>
                </td>
            </tr>
            <% } %>
        </table>
        <!--  end product-table................................... -->
    </div>
    <!--  end content-table  -->
    <% }
       else
       { %>
    <div id="Div1">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        Error. <a href="">
                            <%: AdminUnitString.NoData %></a>
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
