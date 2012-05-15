<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminFoodString.EditRelatedFood %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminFoodString.EditRelatedFood %>
    (<%: ViewData["tenMonAn"] %>)
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listMonAn').selectbox({ inputClass: "listData", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
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
    <!--  Delete fail  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Add fail  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Can't delete if category detail not found  -->
    <% if (TempData["errorRelatedFoodNotFound"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorRelatedFoodNotFound"]);
       } 
    %>
    <!--  Can't add if category detail already exist  -->
    <% if (TempData["errorRelatedFoodExist"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorRelatedFoodExist"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!-- Begin add related food --------------------------------------------------------------------------->
    <div id="table-content" style="margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" id="Table1" style="margin: 10px">
            <tr>
                <th valign="center" width="130px" align="left">
                    <%: AdminFoodString.FoodName %>:
                </th>
                <td valign="center" width="200px">
                    <% Html.BeginForm("AddRelatedFood", "AdminFood", FormMethod.Post); %>
                    <%= Html.DropDownList("maMonAnLienQuan", new SelectList(ViewData["listMonAn"] as List<MonAn>, "MaMonAn", "TenMonAn", 1), new {Class = "listMonAn" })%>
                    <input type="hidden" name="maMonAn" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                </td>
                <td valign="center">
                    <input type="submit" style="float: right; margin-right: 50px;" value="<%: AdminFoodString.AddRelatedFood %>" />
                    <% Html.EndForm(); %>
                </td>
            </tr>
        </table>
        <!-- Begin related food table--------------------------------------------------------------------------->
        <% if (ViewData["listMonLienQuan"] != null && ((List<ChiTietMonLienQuan>)ViewData["listMonLienQuan"]).Count > 0)
           {
        %>
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminFoodString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminFoodString.FoodName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminFoodString.Option %></a>
                </th>
            </tr>
            <%int iCount = 0;%>
            <% foreach (ChiTietMonLienQuan ct in (List<ChiTietMonLienQuan>)ViewData["listMonLienQuan"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: ct.MonAnLienQuan.TenMonAn %>
                </td>
                <td>
                    <% Html.BeginForm("DeleteRelatedFood", "AdminFood", FormMethod.Post, new { id = "form_delete_relatedfood_" + (iCount) }); %>
                    <input name="maMonAnLienQuan" type="hidden" value="<%: ct.MonAnLienQuan.MaMonAn %>" />
                    <input name="maMonAn" type="hidden" value="<%: ct.MonAn.MaMonAn %>" />
                    <a title="<%: AdminFoodString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_relatedfood_<%:iCount %>').submit();" />
                    <% Html.EndForm(); %>
                </td>
            </tr>
            <%
           }
            %>
        </table>
        <%   }
        %>
    </div>
</asp:Content>
