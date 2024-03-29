﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<KhuVuc>>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminArea" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminAreaString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminAreaString.Title %>
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
    <% if (ViewData["listKhuVuc"] != null && ((List<KhuVuc>)ViewData["listKhuVuc"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminAreaString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminAreaString.AreaName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminAreaString.AreaDescription %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminAreaString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var khuVuc in (List<KhuVuc>)ViewData["listKhuVuc"])
               { %>
            <tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %>>
                <td>
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
                <td>
                    <%: khuVuc.TenKhuVuc %>
                </td>
                <td>
                    <%: khuVuc.MoTa %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminArea", new { id = khuVuc.MaKhuVuc }, new { title = AdminAreaString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminArea", new { id = khuVuc.MaKhuVuc }, new { title = AdminAreaString.Delete, Class = "icon-2 info-tooltip" })%>
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
                            <%: AdminAreaString.NoData %></a>
                    </td>
                    <td class="red-right">
                        <a class="close-red">
                            <img src="<%: Url.Content("~/Images/adminimages/table/icon_close_red.gif") %>" alt="" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <%} %>
    <div style="float:right;">
        <%= Html.Pager(Model, new PagerOptions { PageIndexParameterName = "page", 
        CurrentPagerItemWrapperFormatString = "<span class=\"cpb\">{0}</span>", 
        NumericPagerItemWrapperFormatString = "<span class=\"item\">{0}</span>", 
        CssClass = "pages", SeparatorHtml = "" })%>
    </div>
</asp:Content>
