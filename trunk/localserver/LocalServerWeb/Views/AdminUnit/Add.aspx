<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUnit" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminUnitString.AddTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminUnitString.AddTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       }
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("AddBasic", "AdminUnit", FormMethod.Post); %>
    <!-- start id-form -->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: SharedString.Guidance %>
                </th>
                <td>
                    <%: SharedString.Step %> 1: 
                    <%: AdminUnitString.AddTitle %>
                    <br />
                    <%: SharedString.Step %> 2: 
                    <%: AdminUnitString.AddLanguageDetail %>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <th>
                    <%: SharedString.Step %> 1
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Add %>"/>
                    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminUnit") %>';"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
