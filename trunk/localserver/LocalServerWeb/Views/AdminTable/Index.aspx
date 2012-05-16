<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<Ban>>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminTable" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminTableString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminTableString.Title %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listKhuVuc').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
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
    <% if (TempData["errorCannotChangeArea"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeArea"]);
       } 
    %>
    <!--  Update successfully  -->
    <% if (TempData["infoChangeAreaSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoChangeAreaSuccess"]);
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
    <% if (ViewData["listBan"] != null && ((List<Ban>)ViewData["listBan"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminTableString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminTableString.TableName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminTableString.AreaName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminTableString.Note %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminTableString.ActiveName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminTableString.Option %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var ban in (List<Ban>)ViewData["listBan"])
               { %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
                <td>
                    <%: ban.TenBan %>
                </td>
                <td>
                    <% Html.BeginForm("ChangeArea", "AdminTable", FormMethod.Post); %>
                    <%= Html.DropDownList("maKhuVuc", new SelectList(ViewData["listKhuVuc"] as List<KhuVuc>, "MaKhuVuc", "TenKhuVuc", ban.KhuVuc.MaKhuVuc), new { onchange = "submit();", Class = "listKhuVuc" })%>
                    <input type="hidden" name="maBan" value="<%: ban.MaBan %>" id="maBan<%:iCount %>" />
                    <% Html.EndForm(); %>
                </td>
                <td>
                    <%: ban.GhiChu %>
                </td>
                <td>
                    <%: ban.Active?AdminTableString.Active:AdminTableString.Deactive %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminTable", new { id = ban.MaBan }, new { title = AdminTableString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminTable", new { id = ban.MaBan }, new { title = AdminTableString.Delete, Class = "icon-2 info-tooltip" })%>
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
                            <%:AdminTableString.NoData %></a>
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
</asp:Content>
