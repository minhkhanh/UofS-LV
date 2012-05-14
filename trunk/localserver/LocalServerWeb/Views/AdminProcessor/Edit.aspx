<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminProcessor" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminProcessorString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminProcessorString.EditTitle %>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listTaiKhoan').selectbox({ inputClass: "styledselect_pages", debug: true });
            $('.listDanhMuc').selectbox({ inputClass: "styledselect_pages", debug: true });
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
    <!--  Error message: Cannot edit this processor  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Add successfully  -->
    <% if (TempData["infoAddSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoAddSuccess"]);
       } 
    %>
    <!--  Delete fail  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Add fail  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  Warning if there are no category details  -->
    <% if (TempData["warningNoCategoryDetail"] != null)
       {
           Html.RenderPartial("WarningMessageTooltip", model: TempData["warningNoCategoryDetail"]);
       } 
    %>
    <!--  Can't delete if category detail not found  -->
    <% if (TempData["errorCategoryDetailNotFound"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCategoryDetailNotFound"]);
       } 
    %>
    <!--  Can't add if category detail already exist  -->
    <% if (TempData["errorCategoryDetailExist"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCategoryDetailExist"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <% Html.BeginForm("Edit", "AdminProcessor", FormMethod.Post); %>
    <!-- start id-form Edit processor general information-->
    <input type="hidden" name="maBoPhanCheBien" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
    <div id="table-content" style="margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
            <tr>
                <th valign="top">
                    <%: AdminProcessorString.ProcessorName %>:
                </th>
                <td>
                    <input type="text" class="inp-form<%:(((Dictionary<string, string>) TempData["checkDic"]).ContainsKey("tenBoPhan") && ((Dictionary<string, string>) TempData["checkDic"])["tenBoPhan"]!=null) ? "-error" : ""%>"
                        name="tenBoPhan" value="<%:TempData["tenBoPhan"] ?? ""%>" />
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("tenBoPhan") && ((Dictionary<string, string>)TempData["checkDic"])["tenBoPhan"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["tenBoPhan"]%></div>
                    <% } %>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%:AdminProcessorString.ProcessorDescription %>:
                </th>
                <td>
                    <input type="text" class="inp-form" name="moTa" value="<%:TempData["moTa"] ?? ""%>" />
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    <%: AdminProcessorString.AccountName %>:
                </th>
                <td>
                    <%= Html.DropDownList("maTaiKhoan", new SelectList(ViewData["listTaiKhoan"] as List<TaiKhoan>, "MaTaiKhoan", "TenTaiKhoan", (TempData["maTaiKhoan"]!=null)?TempData["maTaiKhoan"]:1), new {Class = "listTaiKhoan" })%>
                </td>
                <td>
                    <% if (((Dictionary<string, string>)TempData["checkDic"]).ContainsKey("maTaiKhoan") && ((Dictionary<string, string>)TempData["checkDic"])["maTaiKhoan"] != null)
                       { %>
                    <div class="error-left">
                    </div>
                    <div class="error-inner">
                        <%:((Dictionary<string, string>)TempData["checkDic"])["maTaiKhoan"]%></div>
                    <% } %>
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
        </table>
    </div>
    <% Html.EndForm(); %>
    <!-- end id-form Edit Processor  -->
    <!-- Begin add category detail --------------------------------------------------------------------------->
    <table border="0" cellpadding="0" cellspacing="0" id="Table1" style="margin: 10px">
        <tr>
            <th valign="top" width="130px" align="left">
                <%: AdminProcessorString.CategoryName %>:
            </th>
            <td>
                <% Html.BeginForm("AddCategoryProcessor", "AdminProcessor", FormMethod.Post); %>
                <%= Html.DropDownList("maDanhMuc", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", 1), new {Class = "listDanhMuc" })%>
                <input type="hidden" name="maBoPhanCheBien" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
            </td>
            <td>
                <input type="submit" style="float: right; margin-right: 50px;"
                    value="<%: AdminProcessorString.AddCategoryDetail %>" />
                <% Html.EndForm(); %>
            </td>
        </tr>
    </table>
    <!-- Begin category detail table--------------------------------------------------------------------------->
    <% if (ViewData["listChiTietDanhMucBoPhanCheBien"] != null && ((List<ChiTietDanhMucBoPhanCheBien>)ViewData["listChiTietDanhMucBoPhanCheBien"]).Count > 0)
       {
    %>
    <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
        <tr>
            <th class="table-header-repeat line-left">
                <a>
                    <%: AdminProcessorString.NumberOrdinary %></a>
            </th>
            <th class="table-header-repeat line-left minwidth-1">
                <a>
                    <%: AdminProcessorString.CategoryName %></a>
            </th>
            <th class="table-header-repeat line-left">
                <a>
                    <%: AdminProcessorString.Option%></a>
            </th>
        </tr>
        <%int iCount = 0;%>
        <% foreach (ChiTietDanhMucBoPhanCheBien ct in (List<ChiTietDanhMucBoPhanCheBien>)ViewData["listChiTietDanhMucBoPhanCheBien"])
           {
        %>
        <tr <%: (iCount++%2==0)?"":"class=alternate-row" %>>
            <td>
                <%: iCount %>
            </td>
            <td>
                <%: ct.DanhMuc.TenDanhMuc %>
            </td>
            <td>
                <% Html.BeginForm("DeleteCategoryProcessor", "AdminProcessor", FormMethod.Post, new { id = "form_delete_category_" + (iCount) }); %>
                <input name="maBoPhanCheBien" type="hidden" value="<%: ct.BoPhanCheBien.MaBoPhanCheBien %>" />
                <input name="maDanhMuc" type="hidden" value="<%: ct.DanhMuc.MaDanhMuc %>" />
                <a title="<%: AdminProcessorString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_category_<%:iCount %>').submit();" />
                <% Html.EndForm(); %>
            </td>
        </tr>
        <%
        }
        %>
    </table>
    <%   }
    %>
</asp:Content>
