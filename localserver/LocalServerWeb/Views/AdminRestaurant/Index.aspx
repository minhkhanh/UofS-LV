<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminRestaurant" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminRestaurantString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminRestaurantString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete this unit  -->
    <% if (TempData["errorCannotUpdate"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotUpdate"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoUpdateSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoUpdateSuccess"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!--  start table-content  -->
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Name %>
                </th>
                <td>
                    Ten nha hang
                </td>
                <th>
                    <a></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a></a>
                </th>
            </tr>
        </table>
        <!--  end product-table................................... -->
    </div>
    <!--  end content-table  -->
</asp:Content>
