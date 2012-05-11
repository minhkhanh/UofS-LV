<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminLanguage" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminLanguageString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminLanguageString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"]%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot edit this language  -->
    <% if (TempData["errorEditDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Edit", "AdminLanguage", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maNgonNgu" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top">
                <%: AdminLanguageString.LanguageName %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenNgonNgu") && ((Dictionary<string, string>) TempData["checkDic"])["tenNgonNgu"]!=null) ? "-error" : ""%>"
                    name="tenNgonNgu" value="<%:TempData["tenNgonNgu"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenNgonNgu") && ((Dictionary<string, string>)TempData["checkDic"])["tenNgonNgu"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["tenNgonNgu"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%: AdminLanguageString.LanguageCuture %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("kiHieu") && ((Dictionary<string, string>) TempData["checkDic"])["kiHieu"]!=null) ? "-error" : ""%>"
                    name="kiHieu" value="<%: TempData["kiHieu"]?? "" %>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("kiHieu") && ((Dictionary<string, string>)TempData["checkDic"])["kiHieu"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["kiHieu"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th>
                &nbsp;
            </th>
            <td valign="top">
                <input type="submit" value="" class="form-submit" />
                <input type="reset" value="" class="form-reset" />
                <input type="button" class="pretty-button" value="<%: SharedString.Cancel %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminLanguage") %>';"/>
            </td>
            <td>
            </td>
        </tr>
    </table>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
