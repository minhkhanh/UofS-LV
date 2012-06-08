<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<ChiTietVoucher>>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminVoucher" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminVoucherString.VoucherDetail %>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminVoucherString.VoucherDetail %>
    (<%: ViewData["tenVoucher"] %>)
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this voucher detail -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Error message: Cannot delete this voucher  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
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
    <!--  Error voucher detail exist  -->
    <% if (TempData["errorVoucherDetailExist"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorVoucherDetailExist"]);
       } 
    %>
    <!--  Add new voucher detail  --------------------------------------------------------------------------->
    <table border="0" width="100%" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <% Html.BeginForm("AddVoucherDetail", "AdminVoucher", FormMethod.Post); %> 
            <th valign="top">
                <input type="hidden" name="maVoucher" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                <%: AdminVoucherString.Code %>:
            </th>
            <td width="150px">
                <input type="text" name="soPhieu" />
            </td>
            <td>
                <input type="submit" value="<%: AdminVoucherString.AddVoucherDetail %>" />
            </td>
            <% Html.EndForm(); %>
        </tr>
        <!--  Add many voucher detail  -------------------------------------------------->
        <tr>
            <% Html.BeginForm("AddManyVoucherDetail", "AdminVoucher", FormMethod.Post); %>
            <th valign="top">
                <input type="hidden" name="maVoucher" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                <%: AdminVoucherString.InputAmount %>:
            </th>
            <td>
                <input type="text" name="soLuong" />
            </td>
            <td>
                <input type="submit" value="<%: AdminVoucherString.AddManyVoucherDetail %>" />
            </td>
            <% Html.EndForm(); %>
        </tr>
    </table>
            
    <!--  start voucher detail list  ------------------------------------------------------------------------------------>
    <% if (ViewData["listCTVoucher"] != null && ((List<ChiTietVoucher>)ViewData["listCTVoucher"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminVoucherString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminVoucherString.Code %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminVoucherString.Status %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminVoucherString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var ctVoucher in (List<ChiTietVoucher>)ViewData["listCTVoucher"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
                <td>
                    <%: ctVoucher.SoPhieu %>
                </td>
                <td>
                    <%: (ctVoucher.Active==true)?AdminVoucherString.Active:AdminVoucherString.NotActive %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "DeleteVoucherDetail", "AdminVoucher", new { id = ctVoucher.MaChiTietVoucher, maVoucher = Url.RequestContext.RouteData.Values["id"] }, new { title = AdminVoucherString.Delete, Class = "icon-2 info-tooltip" })%>
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
                            <%: AdminVoucherString.NoVoucherDetail %></a>
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
    <div style="float:right;">
        <%= Html.Pager(Model, new PagerOptions { PageIndexParameterName = "page", 
        CurrentPagerItemWrapperFormatString = "<span class=\"cpb\">{0}</span>", 
        NumericPagerItemWrapperFormatString = "<span class=\"item\">{0}</span>", 
        CssClass = "pages", SeparatorHtml = "" })%>
    </div>
    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminVoucher") %>';"/>
</asp:Content>
