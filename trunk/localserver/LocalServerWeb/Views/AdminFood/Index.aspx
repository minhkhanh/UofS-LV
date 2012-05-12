<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminFoodString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminFoodString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
    <!-- Tooltips -->
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
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeParentCategory"]);
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
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoChangeParentCategorySuccess"]);
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
                    <%: iCount %>
                </td>
                <td>
                    <%: monAn.HinhAnh %>
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
                    <%:Html.ActionLink(" ", "Edit", "AdminFood", new { id = monAn.MaMonAn }, new { title = AdminFoodString.Edit, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminFood", new { id = monAn.MaMonAn }, new { title = AdminFoodString.Delete, Class = "icon-2 info-tooltip" })%>
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
</asp:Content>
