<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Kitchen" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: KitchenString.ChooseTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: KitchenString.ChooseTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listBoPhanCheBien').selectbox({ inputClass: "listData", debug: true });
        });

        function chooseKitchen() {
            var maBoPhanCheBien = $('.listBoPhanCheBien').val();
            window.location.href = 'Index?maBoPhanCheBien=' + maBoPhanCheBien;

        }
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <%: KitchenString.ChooseTitle %>
            </tr>
            <tr>
                <%= Html.DropDownList("maBoPhanCheBien", new SelectList(ViewData["listBoPhanCheBien"] as List<BoPhanCheBien>, "MaBoPhanCheBien", "TenBoPhan", 1), new { Class = "listBoPhanCheBien" })%>
            </tr>
            <tr>
                <input type="button" value="<%: KitchenString.Choose %>"  onclick="chooseKitchen();"/>
            </tr>
        </table>
    </div>
</asp:Content>
