<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminPromotion" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminPromotionString.Title %>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminPromotionString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this promotion  -->
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
    <!--  start table-content  -->
    <% if (ViewData["listKhuyenMai"] != null && ((List<KhuyenMai>)ViewData["listKhuyenMai"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminPromotionString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.PromotionName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.PromotionDescription %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.DiscountValue %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.DiscountPercent %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.StartDate %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.EndDate %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminPromotionString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var khuyenMai in (List<KhuyenMai>)ViewData["listKhuyenMai"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: khuyenMai.TenKhuyenMai %>
                </td>
                <td>
                    <%: khuyenMai.MoTaKhuyenMai %>
                </td>
                <td>
                    <%: khuyenMai.GiaGiam %>
                </td>
                <td>
                    <%: khuyenMai.TiLeGiam %>
                </td>
                <td>
                    <%: khuyenMai.BatDau %>
                </td>
                <td>
                    <%: khuyenMai.KetThuc %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminPromotion", new { id = khuyenMai.MaKhuyenMai }, new { title = AdminPromotionString.Edit, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminPromotion", new { id = khuyenMai.MaKhuyenMai }, new { title = AdminPromotionString.Delete, Class = "icon-2 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "FoodPromotion", "AdminPromotion", new { id = khuyenMai.MaKhuyenMai }, new { title = AdminPromotionString.EditFoodPromotion, Class = "icon-2 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "InvoicePromotion", "AdminPromotion", new { id = khuyenMai.MaKhuyenMai }, new { title = AdminPromotionString.EditInvoicePromotion, Class = "icon-2 info-tooltip" })%>
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
                            <%: AdminPromotionString.NoData %></a>
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
