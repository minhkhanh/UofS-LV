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
    <!-- Tooltips -->
    <script src="../../Scripts/jquery/jquery.tooltip.js" type="text/javascript"></script>
    <script src="../../Scripts/jquery/jquery.dimensions.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $('a.info-tooltip ').tooltip({
                track: true,
                delay: 0,
                fixPNG: true,
                showURL: false,
                showBody: " - ",
                top: -35,
                left: 5
            });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       }
    %>
    <% Html.BeginForm("AddBasic", "AdminUnit", FormMethod.Post); %>
    <!-- start id-form -->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    &nbsp;
                </th>
                <td>
                    &nbsp;
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
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
