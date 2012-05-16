<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>


<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <button id="open-report-demo">Open report</button>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('#open-report-demo').button().click(function () {
                var newwindow = window.open('ReportForms/BillReportForm?maHoaDon=<%:ViewData["maHoaDon"] %>&maNgonNgu=<%:ViewData["maNgonNgu"] %>', 'name', 'height=800,width=600');
                if (window.focus) {
                    newwindow.focus();
                }
            });
        });
    </script>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">

</asp:Content>
