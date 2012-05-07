<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminInvoice" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminInvoiceString.Title %>
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
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminInvoiceString.Title %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (ViewData["listHoaDOn"] != null && ((List<HoaDon>)ViewData["listHoaDon"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminInvoiceString.InvoiceID %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.CreatedAt %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.AccountName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.Total %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.MainTable %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.JoinedTables %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.SurchargeName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.SurchargeValue %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminInvoiceString.Option %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var hoaDon in (List<HoaDon>)ViewData["listHoaDon"])
               { %>
            <tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %>>
                <td>
                    <%: hoaDon.MaHoaDon%>
                </td>
                <td>
                    <%: hoaDon.ThoiDiemLap.ToString() %>
                </td>
                <td>
                    <%: hoaDon.TaiKhoan.TenTaiKhoan %>
                </td>
                <td>
                    <%: hoaDon.TongTien %>
                </td>
                <td>
                    <%: hoaDon.Ban.TenBan %>
                </td>
                <td>
                    <%: hoaDon.MoTaBanGhep %>
                </td>
                <td>
                    <%: hoaDon.PhuThu.TenPhuThu %>
                </td>
                <td>
                    <%: (hoaDon.PhuThu.GiaTang != 0)?hoaDon.PhuThu.GiaTang.ToString():hoaDon.PhuThu.TiLeTang.ToString()+"%" %>
                </td>
                <td class="options-width">
                    <%:Html.ActionLink(" ", "InvoiceDetail", "AdminInvoice", new { id = hoaDon.MaHoaDon }, new { title = AdminInvoiceString.DetailTitle, Class = "icon-6 info-tooltip" })%>
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
                            <%: AdminInvoiceString.NoData %></a>
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
