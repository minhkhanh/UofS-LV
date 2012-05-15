<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminExchangeRate" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminExchangeRateString.Title%>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminExchangeRateString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this area  -->
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
    <!--  Cannot update online  -->
    <% if (TempData["errorCannotUpdate"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotUpdate"]);
       } 
    %>
    <!--  Update successfully  -->
    <% if (TempData["infoUpdateSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoUpdateSuccess"]);
       } 
    %>
    <!--  Add successfully  -->
    <% if (TempData["infoAddSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoAddSuccess"]);
       } 
    %>
    <!--  Edit successfully  -->
    <% if (TempData["infoEditSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoEditSuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% if (ViewData["listTiGia"] != null && ((List<TiGia>)ViewData["listTiGia"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminExchangeRateString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.Code %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.Value %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.UpdatedAt %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:  AdminExchangeRateString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var tiGia in (List<TiGia>)ViewData["listTiGia"])
               { %>
            <tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: tiGia.KiHieu %>
                </td>
                <td>
                    <%: tiGia.GiaTri %>
                </td>
                <td>
                    <%: tiGia.ThoiDiemCapNhat.ToString() %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Update", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Update, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Edit", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Edit, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Delete, Class = "icon-6 info-tooltip" })%>
                </td>
            </tr>
            <% } %>
        </table>
        <!--  table................ -->
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
                        <%:SharedString.Error %>
                        <a href="">
                            <%: AdminExchangeRateString.NoData%></a>
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
