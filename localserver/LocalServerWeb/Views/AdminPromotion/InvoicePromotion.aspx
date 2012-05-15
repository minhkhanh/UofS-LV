<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminPromotion" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminPromotionString.EditInvoicePromotion %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminPromotionString.EditInvoicePromotion %>
    (<%: ViewData["tenKhuyenMai"] %>)
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    $(document).ready(function () {
        $('input:submit').button();
        $('input:reset').button();
        $('input:button').button();
    });
    </script>
    <script src="../../Scripts/jquery/jquery.tooltip.js" type="text/javascript"></script>
    <script src="../../Scripts/jquery/jquery.dimensions.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
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
    <!--  Error message: Cannot edit this invoice promotion  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  Error message:  edit this invoice promotion successfully  -->
    <% if (TempData["infoEditSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoEditSuccess"]);
       } 
    %>
    <!--  Main code  ----------------------------------------------------------------------->
    <% Html.BeginForm("ChangeInvoicePromotion", "AdminPromotion", FormMethod.Post); %>
    <!-- start id-form -->
    <input type="hidden" name="maKhuyenMai" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <div id="table-content" style="margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: AdminPromotionString.Threshold %>:
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
                    <input type="submit" value="<%:SharedString.Edit %>" />
                    <input type="reset" value="<%: SharedString.Reset %>"  />
                    <input type="button" class="pretty-button" value="<%: SharedString.Cancel %>" onclick="window.location.href='<%: Url.Action("Index", "AdminPromotion") %>';" />
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
