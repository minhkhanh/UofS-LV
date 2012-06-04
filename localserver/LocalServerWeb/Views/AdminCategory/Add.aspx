<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminCategory" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminCategoryString.AddTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminCategoryString.AddTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "styledselect_pages", debug: true });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot add new category  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Add", "AdminCategory", FormMethod.Post); %>
    <!-- start id-form -->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: SharedString.Guidance %>
                </th>
                <td>
                    <%: SharedString.Step %> 1: 
                    <%: AdminCategoryString.ChooseParentCategory %>
                    <br />
                    <%: SharedString.Step %> 2: 
                    <%: AdminCategoryString.AddTitle %>
                    <br />
                    <%: SharedString.Step %> 3: 
                    <%: AdminCategoryString.AddLanguageDetail %>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: SharedString.Step %> 1
                </th>
                <td>
                    <%= Html.DropDownList("maDanhMucCha", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", 1), new { Class = "listDanhMuc" })%>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th>
                    <%: SharedString.Step %> 2
                </th>
                <td valign="top">
                    <input type="submit" value="<%: SharedString.Add %>"/>
                    <input type="reset" value="<%: SharedString.Reset %>" />
                    <input type="button" value="<%: SharedString.Back %>"  onclick="window.location.href='<%: Url.Action("Index", "AdminCategory") %>';"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!-- end id-form  -->
    <% Html.EndForm(); %>
</asp:Content>
