<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminExchangeRate" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminExchangeRateString.Title%>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminExchangeRateString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
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
    <!--  Error message: Cannot delete this exchange rate  -->
    <% if (TempData["errorCannotDelete"] != null)
       {%>
    <div id="message-red">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        <%:SharedString.Error %>
                        <a href="">
                            <%: TempData["errorCannotDelete"]%></a>
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
    <!--  Error message: Cannot update the value for this exchange rate  -->
    <% if (TempData["errorCannotUpdate"] != null)
       {%>
    <div id="Div2">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td class="red-left">
                        <%:SharedString.Error %>
                        <a href="">
                            <%: TempData["errorCannotDelete"]%></a>
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
    <% if (ViewData["listTiGia"] != null && ((List<TiGia>)ViewData["listTiGia"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminExchangeRateString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.Code %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.Value %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminExchangeRateString.UpdatedAt %></a>
                </th>
                <th class="table-header-repeat line-left">
                    <a>
                        <%:  AdminExchangeRateString.Option%></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var tiGia in (List<TiGia>)ViewData["listTiGia"])
               { %>
            <tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount %>
                </td>
                <td>
                    <%: tiGia.KiHieu %>
                </td>
                <td>
                    <%: tiGia.GiaTri %>
                </td>
                <td>
                    <%: tiGia.ThoiDiemCapNhat.ToString() %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "Update", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Update, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Edit", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Edit, Class = "icon-6 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Delete", "AdminExchangeRate", new { id = tiGia.MaTiGia }, new { title = AdminExchangeRateString.Delete, Class = "icon-6 info-tooltip" })%>
                </td>
            </tr>
            <% } %>
        </table>
        <!--  table................ -->
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
                        <%:SharedString.Error %>
                        <a href="">
                            <%: AdminExchangeRateString.NoData%></a>
                    </td>
                    <td class="red-right">
                        <a class="close-red">
                            <img src="../../Images/adminimages/table/icon_close_red.gif" alt="" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <%} %>
</asp:Content>
