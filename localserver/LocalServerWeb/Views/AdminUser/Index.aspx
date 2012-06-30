<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<TaiKhoan>>" %>

<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUser" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%:AdminUserString.Title %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        function changeGroupUser(groupObj, userId) {
            alert(userId);

        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.nhomTaiKhoan').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminUserString.AccountList %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Edit user success  -->
    <% if (TempData["infoEditSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoEditSuccess"]);
       } 
    %>
    <!--  Add user success  -->
    <% if (TempData["infoAddSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoAddSuccess"]);
       } 
    %>
    <!--  Delete user success  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Lock user success  -->
    <% if (TempData["infoLockUnlockSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoLockUnlockSuccess"]);
       } 
    %>
    <!--  Can't lock unlock  -->
    <% if (TempData["errorCannotLockUnlock"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotLockUnlock"]);
       } 
    %>
    <!--  Can't delete  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Can't edit  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  start table-content  -->
    <% if (ViewData["listTaiKhoan"] != null && ((List<TaiKhoan>)ViewData["listTaiKhoan"]).Count > 0)
       {
           if (TempData["error"] != null)
           {%>
    <div id="message-red">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        <%:AdminUserString.Error %>
                        <a href="">
                            <%:TempData["error"] %></a>
                    </td>
                    <td class="red-right">
                        <a class="close-red">
                            <img src="../../Images/adminimages/table/icon_close_red.gif" alt="" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <% } %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminUserString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%:AdminUserString.Avatar%></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%:AdminUserString.Username%></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%:AdminUserString.Name%></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:AdminUserString.BOD%></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:AdminUserString.Gender%></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:AdminUserString.SocialID%></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:AdminUserString.Group%></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:AdminUserString.Status%></a>
                </th>
                <th class="table-header-options line-left">
                    <a>
                        <%:AdminUserString.Options%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var taiKhoan in (List<TaiKhoan>)ViewData["listTaiKhoan"])
               { %>
            <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
                <td>
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
                <td align="center" valign="middle">
                    <div class="image_food">
                        <img src="<%:Url.Content("~/"+taiKhoan.Avatar+"")%>" alt=""
                            width="50px" height="50px" /></div>
                </td>
                <td>
                    <%:taiKhoan.TenTaiKhoan %>
                </td>
                <td>
                    <%:taiKhoan.HoTen %>
                </td>
                <td>
                    <%:taiKhoan.NgaySinh %>
                </td>
                <td>
                    <%:(taiKhoan.GioiTinh==0)?AdminUserString.Male:AdminUserString.Female %>
                </td>
                <td>
                    <%:taiKhoan.CMND %>
                </td>
                <td>
                    <% Html.BeginForm("ChangeGroupUser", "AdminUser", FormMethod.Post); %>
                    <%= Html.DropDownList("nhomTaiKhoan", new SelectList(ViewData["listNhomTaiKhoan"] as List<NhomTaiKhoan>, "MaNhomTaiKhoan", "TenNhom", taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan), new { onchange = "submit();", Class = "nhomTaiKhoan" })%>
                    <input type="hidden" name="maTaiKhoan" value="<%:taiKhoan.MaTaiKhoan %>" id="maTaiKhoan<%:iCount %>" />
                    <% Html.EndForm(); %>
                </td>
                <td>
                    <%:taiKhoan.Active?AdminUserString.Active:AdminUserString.Deactive %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Edit", "AdminUser", new { id = taiKhoan.MaTaiKhoan }, new { title = AdminUserString.Edit, Class = "icon-1 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "LockUnlock", "AdminUser", new { maTaiKhoan = taiKhoan.MaTaiKhoan }, new { title = AdminUserString.Lock, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminUser", new { maTaiKhoan = taiKhoan.MaTaiKhoan }, new { title = AdminUserString.Delete, Class = "icon-2 info-tooltip" })%>
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
                            <%:AdminUserString.NoData %></a>
                    </td>
                    <td class="red-right">
                        <a class="close-red">
                            <img src="<%: Url.Content("~/Images/adminimages/table/icon_close_red.gif") %>" alt="" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <%} %>
    <div style="float: right;">
        <%= Html.Pager(Model, new PagerOptions { PageIndexParameterName = "page", 
        CurrentPagerItemWrapperFormatString = "<span class=\"cpb\">{0}</span>", 
        NumericPagerItemWrapperFormatString = "<span class=\"item\">{0}</span>", 
        CssClass = "pages", SeparatorHtml = "" })%>
    </div>
</asp:Content>
