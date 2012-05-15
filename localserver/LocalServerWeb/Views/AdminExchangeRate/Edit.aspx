<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminExchangeRate" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminExchangeRateString.EditTitle %>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminExchangeRateString.EditTitle %>
        <%: Url.RequestContext.RouteData.Values["id"]%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot edit this area  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  Cannot update online  -->
    <% if (TempData["errorCannotUpdate"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotUpdate"]);
       } 
    %>
    <!--  Update successfully  -->
    <% if (TempData["infoUpdateSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoUpdateSuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Edit", "AdminExchangeRate", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maTiGia" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <div id="table-content">
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top">
                <%: AdminExchangeRateString.Code %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("kiHieu") && ((Dictionary<string, string>) TempData["checkDic"])["kiHieu"]!=null) ? "-error" : ""%>"
                    name="kiHieu" value="<%:TempData["kiHieu"] ?? ""%>" />
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
            <th valign="top">
                <%: AdminExchangeRateString.Value %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("giaTri") && ((Dictionary<string, string>) TempData["checkDic"])["giaTri"]!=null) ? "-error" : ""%>"
                    name="giaTri" value="<%:TempData["giaTri"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("giaTri") && ((Dictionary<string, string>)TempData["checkDic"])["giaTri"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["giaTri"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th>
                &nbsp;
            </th>
            <td valign="top">
                <input type="submit" value="<%: SharedString.Edit %>"/>
                <input type="reset" value="<%: SharedString.Reset %>" />
                <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminExchangeRate") %>';"/>
            </td>
            <td>
            </td>
        </tr>
    </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
