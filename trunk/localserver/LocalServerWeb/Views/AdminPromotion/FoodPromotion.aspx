<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminPromotion" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminPromotionString.EditFoodPromotion %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminPromotionString.EditFoodPromotion %>
    (<%: ViewData["tenKhuyenMai"] %>)
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
    <% if (TempData["errorFoodPromotionNotFound"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorFoodPromotionNotFound"]);
       } 
    %>
    <!--  Can't add if category detail already exist  -->
    <% if (TempData["errorFoodPromotionExist"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorFoodPromotionExist"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!-- Begin add category detail --------------------------------------------------------------------------->
    <div id="table-content" style="margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" id="Table1" style="margin: 10px">
            <tr>
                <th valign="center" width="130px" align="left">
                    <%: AdminPromotionString.FoodName %>:
                </th>
                <td valign="center" width="200px">
                    <% Html.BeginForm("AddFoodPromotion", "AdminPromotion", FormMethod.Post); %>
                    <%= Html.DropDownList("maMonAn", new SelectList(ViewData["listMonAn"] as List<MonAn>, "MaMonAn", "TenMonAn", 1), new {Class = "listMonAn" })%>
                    <input type="hidden" name="maKhuyenMai" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                </td>
                <td valign="center">
                    <input type="submit" style="float: right; margin-right: 50px;" value="<%: AdminPromotionString.AddFoodPromotion %>" />
                    <% Html.EndForm(); %>
                </td>
            </tr>
        </table>
        <!-- Begin category detail table--------------------------------------------------------------------------->
        <% if (ViewData["listKhuyenMaiMon"] != null && ((List<KhuyenMaiMon>)ViewData["listKhuyenMaiMon"]).Count > 0)
           {
        %>
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminPromotionString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminPromotionString.FoodName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminPromotionString.Option%></a>
                </th>
            </tr>
            <%int iCount = 0;%>
            <% foreach (KhuyenMaiMon km in (List<KhuyenMaiMon>)ViewData["listKhuyenMaiMon"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: km.MonAn.TenMonAn %>
                </td>
                <td>
                    <% Html.BeginForm("DeleteFoodPromotion", "AdminPromotion", FormMethod.Post, new { id = "form_delete_foodpromotion_" + (iCount) }); %>
                    <input name="maKhuyenMai" type="hidden" value="<%: km.KhuyenMai.MaKhuyenMai %>" />
                    <input name="maMonAn" type="hidden" value="<%: km.MonAn.MaMonAn %>" />
                    <a title="<%: AdminPromotionString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_foodpromotion_<%:iCount %>').submit();" />
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
    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminPromotion") %>';"/>
</asp:Content>
