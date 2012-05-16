<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<MonAn>>" %>

<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminFoodString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminFoodString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this food  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Error message: Cannot change category  -->
    <% if (TempData["errorCannotChangeCategory"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeCategory"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Change category successfully  -->
    <% if (TempData["infoChangeCategorySuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoChangeCategorySuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!--  start table-content  -->
    <% if (ViewData["listMonAn"] != null && ((List<MonAn>)ViewData["listMonAn"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminFoodString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.Picture %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.FoodName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.FoodDescription %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.Score %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.RatingTimes %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.Status %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.Category %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminFoodString.Option %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var monAn in (List<MonAn>)ViewData["listMonAn"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
                <td>
                    <div class="image_food">
                        <img src="<%:SharedCode.GetHostApplicationAddress(Request)+monAn.HinhAnh %>"
                            alt="" width="50px" height="50px" /></div>
                </td>
                <td>
                    <%: monAn.TenMonAn %>
                </td>
                <td>
                    <%: monAn.TenMonAn %>
                </td>
                <td>
                    <%: monAn.DiemDanhGia %>
                </td>
                <td>
                    <%: monAn.SoLuotDanhGia %>
                </td>
                <td>
                    <%: (monAn.NgungBan==true)?AdminFoodString.NotAvailable:AdminFoodString.Available %>
                </td>
                <td>
                    <% Html.BeginForm("ChangeCategory", "AdminFood", FormMethod.Post); %>
                    <%= Html.DropDownList("maDanhMuc", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (monAn.DanhMuc!=null)?monAn.DanhMuc.MaDanhMuc:1), new { onchange = "submit();", Class = "listDanhMuc" })%>
                    <input type="hidden" name="maMonAn" value="<%: monAn.MaMonAn %>" id="maMonAn<%:iCount %>" />
                    <input type="hidden" name="previous_action" value="Index" />
                    <% Html.EndForm(); %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminFood", new { id = monAn.MaMonAn }, new { title = AdminFoodString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminFood", new { id = monAn.MaMonAn }, new { title = AdminFoodString.Delete, Class = "icon-2 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "RelatedFood", "AdminFood", new { id = monAn.MaMonAn }, new { title = AdminFoodString.EditRelatedFood, Class = "icon-3 info-tooltip" })%>
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
                            <%: AdminFoodString.NoData %></a>
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
    </div
</asp:Content>
