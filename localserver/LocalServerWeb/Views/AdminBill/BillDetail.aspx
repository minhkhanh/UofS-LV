<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminBill" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminBillString.DetailTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    function previewBill() {
        var newwindow = window.open('../../ReportForms/BillReportForm?maHoaDon=<%:ViewData["maHoaDon"] %>&maNgonNgu=<%:ViewData["maNgonNgu"] %>', 'name', 'height=800,width=600');
        if (window.focus) {
            newwindow.focus();
        }
    }
</script>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminBillString.DetailTitle %>
    <%= Url.RequestContext.RouteData.Values["id"]%>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <% if (ViewData["listChiTietHoaDon"] != null && ((List<ChiTietHoaDon>)ViewData["listChiTietHoaDon"]).Count > 0)
       {
    %>
    <div id="table-content">
        <!--  start product-table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
            <tr>
                <th class="table-header-repeat line-left">
                    <a>
                        <%: AdminBillString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.Quantity %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.UnitPrice %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.FoodName %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.Sum %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.PromotionValue %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
                    <a>
                        <%: AdminBillString.Promotion %></a>
                </th>
            </tr>
            <% int iCount = 0; %>
            <% foreach (var ct in (List<ChiTietHoaDon>)ViewData["listChiTietHoaDon"])
               { %>
            <tr <%: (iCount++ %2 == 0)?"":"class=alternate-row" %>>
                <td>
                    <%: iCount++ %>
                </td>
                <td>
                    <%: ct.SoLuong %>
                </td>
                <td>
                    <%: ct.DonGiaLuuTru %>
                </td>
                <td>
                    <%: ct.MonAn.TenMonAn %>
                </td>
                <td>
                    <%: ct.ThanhTien %>
                </td>
                <td>
                    <%: ct.GiaTriKhuyenMaiLuuTru %>
                </td>
                <td>
                    <%: ct.KhuyenMai.TenKhuyenMai %>
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
                        <%: SharedString.Error %> <a href="">
                            <%: AdminBillString.DetailNoData %></a>
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
    <input type="button" value="<%: AdminBillString.Print %>"  onclick="window.location.href='<%: Url.Action("Print", "AdminBill", new { maHoaDon = Url.RequestContext.RouteData.Values["id"] }) %>';"/>
    <input type="button" value="<%: AdminBillString.PrintPreview %>"  onclick="previewBill();"/>
    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminBill") %>';"/>
</asp:Content>
