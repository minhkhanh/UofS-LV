<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<HoaDon>>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminInvoice" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminInvoiceString.Title %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    function previewInvoice(maHoaDon) {
        var newwindow = window.open('../../ReportForms/BillReportForm?maHoaDon='+maHoaDon+'&maNgonNgu=<%:ViewData["maNgonNgu"] %>', 'name', 'height=800,width=600');
        if (window.focus) {
            newwindow.focus();
        }
    }
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
                        <%: AdminInvoiceString.NumberOrdinary %></a>
                </th>
                <th class="table-header-repeat line-left minwidth-1">
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
                    <%: ((ViewData["_page"]!=null)?(int)ViewData["_page"] : 1)*10-10 + iCount%>
                </td>
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
                    <%:Html.ActionLink(" ", "InvoiceDetail", "AdminInvoice", new { id = hoaDon.MaHoaDon }, new { title = AdminInvoiceString.DetailTitle, Class = "icon-3 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "Print", "AdminInvoice", new { maHoaDon = hoaDon.MaHoaDon }, new { title = AdminInvoiceString.Print, Class = "icon-4 info-tooltip" })%>
                    <%:Html.ActionLink(" ", "PrintPreview", "AdminInvoice", new { maHoaDon = hoaDon.MaHoaDon }, new { title = AdminInvoiceString.PrintPreview, Class = "icon-4 info-tooltip" })%>
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
    <div style="float:right;">
        <%= Html.Pager(Model, new PagerOptions { PageIndexParameterName = "page", 
        CurrentPagerItemWrapperFormatString = "<span class=\"cpb\">{0}</span>", 
        NumericPagerItemWrapperFormatString = "<span class=\"item\">{0}</span>", 
        CssClass = "pages", SeparatorHtml = "" })%>
    </div>
</asp:Content>
