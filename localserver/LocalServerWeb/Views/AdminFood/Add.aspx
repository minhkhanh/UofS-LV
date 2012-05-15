<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="System.Globalization" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%:AdminFoodString.AddTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminFoodString.AddTitle %>
</asp:Content>
<asp:Content ID="Content5" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot add new category  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <div id="table-content">
        <% Html.BeginForm("Add", "AdminFood", FormMethod.Post, new { enctype = "multipart/form-data" }); %>
        <!-- start id-form -->
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: SharedString.Guidance %>
                </th>
                <td>
                    <%: SharedString.Step %>
                    1:
                    <%: AdminFoodString.ChooseCategory %>
                    <br />
                    <%: SharedString.Step %>
                    2:
                    <%: AdminFoodString.ChoosePicture %>
                    <br />
                    <%: SharedString.Step %>
                    3:
                    <%: AdminFoodString.AddTitle %>
                    <br />
                    <%: SharedString.Step %>
                    4:
                    <%: AdminFoodString.AddLanguageDetail %>
                    &
                    <%: AdminFoodString.AddUnitDetail %>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: SharedString.Step %>
                    1:
                </th>
                <td>
                    <%= Html.DropDownList("maDanhMuc", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (ViewData["listDanhMuc"] as List<DanhMuc>).First().MaDanhMuc), new { Class = "listDanhMuc" })%>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <th>
                    <%: SharedString.Step %>
                    2:
                </th>
                <td>
                    <input type="file" class="file_1" name="uploadFile" accept="image/*" />
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <%:AdminFoodString.PictureSize %>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <th>
                    <%: SharedString.Step %>
                    3:
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Add %>"/>
                    <input type="reset" value="<%: SharedString.Reset %>" />
                    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminFood") %>';"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
        <!-- end id-form  -->
        <% Html.EndForm(); %>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "listDanhMuc" });
        });
    </script>
    <!--  styled file upload script -->
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
