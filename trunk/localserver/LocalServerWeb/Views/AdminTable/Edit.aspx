<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminTable" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminTableString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminTableString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"]%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listKhuVuc').selectbox({ inputClass: "styledselect_pages", debug: true });
        });

        function change_active(checkbox) {
            if (checkbox.checked)
                $('#hidden-active').val("true");
            else
                $('#hidden-active').val("false");
        }
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot edit table   -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Edit", "AdminTable", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maBan" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top">
                <%: AdminTableString.TableName %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenBan") && ((Dictionary<string, string>) TempData["checkDic"])["tenBan"]!=null) ? "-error" : ""%>"
                    name="tenBan" value="<%:TempData["tenBan"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenBan") && ((Dictionary<string, string>)TempData["checkDic"])["tenBan"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["tenBan"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%: AdminTableString.AreaName %>:
            </th>
            <td>
                <%= Html.DropDownList("maKhuVuc", new SelectList(ViewData["listKhuVuc"] as List<KhuVuc>, "MaKhuVuc", "TenKhuVuc", (TempData["maKhuVuc"]!=null)?TempData["maKhuVuc"]:1), new {Class = "listKhuVuc" })%>
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("maKhuVuc") && ((Dictionary<string, string>)TempData["checkDic"])["maKhuVuc"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["maKhuVuc"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%: AdminTableString.Note %>:
            </th>
            <td>
                <input type="text" class="inp-form" name="ghiChu" value="<%:TempData["ghiChu"] ?? ""%>" />
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%: AdminTableString.ActiveName %>:
            </th>
            <td>
                <input type="checkbox" class="" name="active-checkbox" <%: (TempData["active"]!=null && (bool)TempData["active"]==true)?"checked":"" %>
                    onclick="change_active(this);" />
                <input type="hidden" name="active" id="hidden-active" value="" />
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <th>
                &nbsp;
            </th>
            <td valign="top">
                <input type="submit" value="<%: SharedString.Edit %>"/>
                <input type="reset" value="<%: SharedString.Reset %>" />
                <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminTable") %>';"/>
            </td>
            <td>
            </td>
        </tr>
    </table>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
