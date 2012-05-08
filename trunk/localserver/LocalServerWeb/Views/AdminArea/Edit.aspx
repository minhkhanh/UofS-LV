<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminArea" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminAreaString.EditTitle %>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminAreaString.EditTitle%>
    <%: Url.RequestContext.RouteData.Values["id"]%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
 <% if (TempData["error"] != null)
       {%>
    <div id="message-red">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        <%:SharedString.Error %>
                        <a href="">
                            <%: TempData["error"] %></a>
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
    <% Html.BeginForm("Edit", "AdminArea", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maKhuVuc" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top">
                <%: AdminAreaString.AreaName %>:
            </th>
            <td>
                <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenKhuVuc") && ((Dictionary<string, string>) TempData["checkDic"])["tenKhuVuc"]!=null) ? "-error" : ""%>"
                    name="tenKhuVuc" value="<%:TempData["tenKhuVuc"] ?? ""%>" />
            </td>
            <td>
                <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenKhuVuc") && ((Dictionary<string, string>)TempData["checkDic"])["tenKhuVuc"] != null)
                   { %>
                <div class="error-left">
                </div>
                <div class="error-inner">
                    <%:((Dictionary<string, string>)TempData["checkDic"])["tenKhuVuc"]%></div>
                <% } %>
            </td>
        </tr>
        <tr>
            <th valign="top">
                <%: AdminAreaString.AreaDescription %>:
            </th>
            <td>
                <input type="text" class="inp-form"
                    name="moTa" value="<%:TempData["moTa"] ?? ""%>"/>
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
                <input type="submit" value="" class="form-submit" />
                <input type="reset" value="" class="form-reset" />
                <input type="button" class="pretty-button" value="<%: SharedString.Cancel %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminArea") %>';"/>
            </td>
            <td>
            </td>
        </tr>
    </table>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
