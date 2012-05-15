<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminProcessor" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminProcessorString.AddTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminProcessorString.AddTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listTaiKhoan').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot add this table  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Add", "AdminProcessor", FormMethod.Post); %>
    <!-- start id-form -->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: AdminProcessorString.ProcessorName %>:
                </th>
                <td>
                    <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenBoPhan") && ((Dictionary<string, string>) TempData["checkDic"])["tenBoPhan"]!=null) ? "-error" : ""%>"
                        name="tenBoPhan" value="<%:TempData["tenBoPhan"] ?? ""%>" />
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenBoPhan") && ((Dictionary<string, string>)TempData["checkDic"])["tenBoPhan"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["tenBoPhan"]%></div>
                    <% } %>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%:AdminProcessorString.ProcessorDescription %>:
                </th>
                <td>
                    <input type="text" class="inp-form" name="moTa" value="<%:TempData["moTa"] ?? ""%>" />
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminProcessorString.AccountName %>:
                </th>
                <td>
                    <%= Html.DropDownList("maTaiKhoan", new SelectList(ViewData["listTaiKhoan"] as List<TaiKhoan>, "MaTaiKhoan", "TenTaiKhoan", (TempData["maTaiKhoan"]!=null)?TempData["maTaiKhoan"]:1), new {Class = "listTaiKhoan" })%>
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("maTaiKhoan") && ((Dictionary<string, string>)TempData["checkDic"])["maTaiKhoan"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["maTaiKhoan"]%></div>
                    <% } %>
                </td>
            </tr>
            <tr>
                <th>
                    &nbsp;
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Add %>"/>
                    <input type="reset" value="<%: SharedString.Reset %>" />
                    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminProcessor") %>';"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
