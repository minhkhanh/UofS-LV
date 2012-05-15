<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminSurcharge" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminSurchargeString.EditAreaSurcharge %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminSurchargeString.EditAreaSurcharge %>
    (<%: ViewData["tenPhuThu"] %>)
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listKhuVuc').selectbox({ inputClass: "listData", debug: true });
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
    <% if (TempData["errorAreaSurchargeNotFound"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorAreaSurchargeNotFound"]);
       } 
    %>
    <!--  Can't add if category detail already exist  -->
    <% if (TempData["errorAreaSurchargeExist"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorAreaSurchargeExist"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!-- Begin add category detail --------------------------------------------------------------------------->
    <div id="table-content" style="margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" id="Table1" style="margin: 10px">
            <tr>
                <th valign="center" width="130px" align="left">
                    <%: AdminSurchargeString.AreaName %>:
                </th>
                <td valign="center" width="200px">
                    <% Html.BeginForm("AddAreaSurcharge", "AdminSurcharge", FormMethod.Post); %>
                    <%= Html.DropDownList("maKhuVuc", new SelectList(ViewData["listKhuVuc"] as List<KhuVuc>, "MaKhuVuc", "TenKhuVuc", 1), new {Class = "listKhuVuc" })%>
                    <input type="hidden" name="maPhuThu" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                </td>
                <td valign="center">
                    <input type="submit" style="float: right; margin-right: 50px;" value="<%: AdminSurchargeString.AddAreaSurcharge %>" />
                    <% Html.EndForm(); %>
                </td>
            </tr>
        </table>
        <!-- Begin category detail table--------------------------------------------------------------------------->
        <% if (ViewData["listPhuThuKhuVuc"] != null && ((List<PhuThuKhuVuc>)ViewData["listPhuThuKhuVuc"]).Count > 0)
           {
        %>
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminSurchargeString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminSurchargeString.AreaName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminSurchargeString.Option%></a>
                </th>
            </tr>
            <%int iCount = 0;%>
            <% foreach (PhuThuKhuVuc pt in (List<PhuThuKhuVuc>)ViewData["listPhuThuKhuVuc"])
               {
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: pt.KhuVuc.TenKhuVuc %>
                </td>
                <td>
                    <% Html.BeginForm("DeleteAreaSurcharge", "AdminSurcharge", FormMethod.Post, new { id = "form_delete_areasurcharge_" + (iCount) }); %>
                    <input name="maPhuThu" type="hidden" value="<%: pt.PhuThu.MaPhuThu %>" />
                    <input name="maKhuVuc" type="hidden" value="<%: pt.KhuVuc.MaKhuVuc %>" />
                    <a title="<%: AdminSurchargeString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_areasurcharge_<%:iCount %>').submit();" />
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
    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminSurcharge") %>';"/>
</asp:Content>
