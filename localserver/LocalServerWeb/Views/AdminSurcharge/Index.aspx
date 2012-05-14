<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminSurcharge" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminSurchargeString.Title %>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminSurchargeString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
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
    <% if (ViewData["listPhuThu"] != null && ((List<PhuThu>)ViewData["listPhuThu"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminSurchargeString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.SurchargeName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.SurchargeDescription %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.SurchargeValue %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.SurchargePercent %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.StartDate %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.EndDate %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminSurchargeString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var phuThu in (List<PhuThu>)ViewData["listPhuThu"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: phuThu.TenPhuThu %>
                </td>
                <td>
                    <%: phuThu.MoTaPhuThu %>
                </td>
                <td>
                    <%: phuThu.GiaTang %>
                </td>
                <td>
                    <%: phuThu.TiLeTang %>
                </td>
                <td>
                    <%: phuThu.BatDau %>
                </td>
                <td>
                    <%: phuThu.KetThuc %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminSurcharge", new { id = phuThu.MaPhuThu }, new { title = AdminSurchargeString.Edit, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminSurcharge", new { id = phuThu.MaPhuThu }, new { title = AdminSurchargeString.Delete, Class = "icon-2 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "AreaSurcharge", "AdminSurcharge", new { id = phuThu.MaPhuThu }, new { title = AdminSurchargeString.EditAreaSurcharge, Class = "icon-2 info-tooltip" })%>
                    
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
                            <%: AdminSurchargeString.NoData %></a>
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
