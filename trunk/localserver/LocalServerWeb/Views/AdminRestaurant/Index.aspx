<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminRestaurant" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminRestaurantString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminRestaurantString.Title%>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.filestyle.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            $("input.file_1").filestyle({
                image: "../../Images/adminimages/forms/choose-file.gif",
                imageheight: 21,
                imagewidth: 78,
                width: 310
            });
        });
    </script>
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
        <!--  start restaurant info table ..................................................................................... -->
        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="id-form">
            <% Html.BeginForm("UpdateInfo", "AdminRestaurant", FormMethod.Post); %>
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Name %>:
                </th>
                <td>
                    <input type="text" name="tenNhaHang" value="<%:ViewData["tenNhaHang"] ?? ""%>" />
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Description %>:
                </th>
                <td>
                    <input type="text" name="moTaNhaHang" value="<%:ViewData["moTaNhaHang"] ?? ""%>" />
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Address %>:
                </th>
                <td>
                    <input type="text" name="diaChiNhaHang" value="<%:ViewData["diaChiNhaHang"] ?? ""%>" />
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Tel %>:
                </th>
                <td>
                    <input type="text" name="telNhaHang" value="<%:ViewData["telNhaHang"] ?? ""%>" />
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Fax %>:
                </th>
                <td>
                    <input type="text" name="faxNhaHang" value="<%:ViewData["faxNhaHang"] ?? ""%>" />
                </td>
            </tr>
            <tr>
                <th>
                    &nbsp;
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Edit %>" />
                    <input type="reset" value="<%: SharedString.Reset %>" />
                </td>
                <td>
                </td>
            </tr>
            <% Html.EndForm(); %>
            <!-- Display restaurant logo ------------------------------------------->
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.Logo %>:
                </th>
                <td>
                    <div class="image_restaurant">
                        <img src="<%:SharedCode.GetHostApplicationAddress(Request)+ViewData["logoNhaHang"] %>"
                            alt="" width="100px" height="100px" /></div>
                </td>
            </tr>
            <!-- Upload restaurant logo ------------------------------------------->
            <tr>
                <th valign="top">
                    <%: AdminRestaurantString.ChooseLogo %>:
                </th>
                <td>
                    <% Html.BeginForm("UpdateLogo", "AdminRestaurant", FormMethod.Post, new { enctype = "multipart/form-data" }); %>
                    <input type="file" class="file_1" name="uploadFile" accept="image/*" onchange="submit();" />
                    <% Html.EndForm(); %>
                </td>
            </tr>
        </table>
        <!--  end restaurant info table................................... -->
    </div>
    <!--  end content-table  -->
</asp:Content>
