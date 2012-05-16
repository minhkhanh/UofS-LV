<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminCategory" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminCategoryString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminCategoryString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMucCha').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this table  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Error message: Cannot change area for this category  -->
    <% if (TempData["errorCannotChangeParentCategory"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeParentCategory"]);
       } 
    %>
    <!--  Error message: Cannot choose itself as its parent  -->
    <% if (TempData["errorCannotChooseItself"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChooseItself"]);
       } 
    %>
    <!--  Error message: Cannot choose its descendant as its parent  -->
    <% if (TempData["errorCannotChooseItsDescendant"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChooseItsDescendant"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Change parent category successfully  -->
    <% if (TempData["infoChangeParentCategorySuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoChangeParentCategorySuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!--  start table-content  -->
    <% if (ViewData["listDanhMuc"] != null && ((List<DanhMuc>)ViewData["listDanhMuc"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminCategoryString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminCategoryString.CategoryName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminCategoryString.CategoryDescription %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminCategoryString.ParentCategoryName %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminCategoryString.Option %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var danhMuc in (List<DanhMuc>)ViewData["listDanhMuc"])
               {
                   // Don't show if this is category None
                   if (danhMuc.MaDanhMuc == 1)
                       continue;
            %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: danhMuc.TenDanhMuc %>
                </td>
                <td>
                    <%: danhMuc.MoTaDanhMuc %>
                </td>
                <td>
                    <% Html.BeginForm("ChangeParentCategory", "AdminCategory", FormMethod.Post); %>
                    <%= Html.DropDownList("maDanhMucCha", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (danhMuc.DanhMucCha!=null)?danhMuc.DanhMucCha.MaDanhMuc:0), new { onchange = "submit();", Class = "listDanhMucCha" })%>
                    <input type="hidden" name="maDanhMuc" value="<%: danhMuc.MaDanhMuc %>" id="maDanhMuc<%:iCount %>" />
                    <input type="hidden" name="previous_action" value="Index" />
                    <% Html.EndForm(); %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminCategory", new { id = danhMuc.MaDanhMuc }, new { title = AdminCategoryString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminCategory", new { id = danhMuc.MaDanhMuc }, new { title = AdminCategoryString.Delete, Class = "icon-2 info-tooltip" })%>
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
                            <%: AdminCategoryString.NoData %></a>
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
