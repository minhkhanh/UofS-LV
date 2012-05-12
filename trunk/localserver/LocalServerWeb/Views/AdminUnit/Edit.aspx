<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminUnit" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminUnitString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminUnitString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"] %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete language detail  -->
    <% if (TempData["errorCannotDelete"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotDelete"]);
       } 
    %>
    <!--  Delete successfully  -->
    <% if (TempData["infoDeleteSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoDeleteSuccess"]);
       } 
    %>
    <!--  Error message: Cannot edit language detail  -->
    <% if (TempData["errorCannotEdit"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotEdit"]);
       } 
    %>
    <!--  Edit successfully  -->
    <% if (TempData["infoEditSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoEditSuccess"]);
       } 
    %>
    <!--  Add successfully, this will be show if previous Add redirect to Edit -->
    <!--  This will also be shown when add new languge detail successfully -->
    <% if (TempData["infoAddSuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoAddSuccess"]);
       } 
    %>
    <!--  If add new language detail failed, show this  -->
    <% if (TempData["errorCannotAdd"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotAdd"]);
       } 
    %>
    <!--  If you add a language detail that already exist, show error -->
    <% if (TempData["errorLanguageDetailExist"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["errorLanguageDetailExist"]);
       } 
    %>
    <!--  If user has not added any language detail, show warning -->
    <% if (TempData["warningNoLanguageDetail"] != null)
       {
           Html.RenderPartial("WarningMessageTooltip", model: TempData["warningNoLanguageDetail"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <!-- start id-form -->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form" width="100%">
            <!-- begin add new language detail -->
            <% if ((ViewData["listNgonNguChuaCo"] != null && ((List<NgonNgu>)ViewData["listNgonNguChuaCo"]).Count > 0))
               {%>
            <tr>
                <th>
                    <input type="button" style="float: right; margin-right: 50px;" id="them-chi-tiet-ngon-ngu"
                        value="<%: AdminUnitString.AddLanguageDetail %>" />
                    <div id="dialog-form-add-language" title="<%: AdminUnitString.AddLanguageDetail %>">
                        <% Html.BeginForm("AddUnitLanguage", "AdminUnit", FormMethod.Post, new { id = "form-add-language" }); %>
                        <label for="name">
                            <%: AdminUnitString.LanguageName %></label>
                        <%= Html.DropDownList("maNgonNgu", new SelectList(ViewData["listNgonNguChuaCo"] as List<NgonNgu>, "MaNgonNgu", "TenNgonNgu", 1), new { Class = "listData" })%>
                        <label for="name">
                            <%: AdminUnitString.UnitName %></label>
                        <input type="text" name="tenDonViTinh" value="" class="text ui-widget-content ui-corner-all" />
                        <input type="hidden" name="maDonViTinh" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                        <% Html.EndForm(); %>
                    </div>
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <% } %>
            <!-- end add new language detail -->
            <!-- begin list table language detail -->
            <% if (ViewData["listChiTietDonViTinhDaNgonNgu"] != null)
               {
                   for (int i = 0; i < (ViewData["listChiTietDonViTinhDaNgonNgu"] as List<ChiTietDonViTinhDaNgonNgu>).Count; ++i)
                   {
                       var chiTietDonViTinhDaNgonNgu =
                           (ViewData["listChiTietDonViTinhDaNgonNgu"] as List<ChiTietDonViTinhDaNgonNgu>)[i]; %>
            <tr>
                <td colspan="2">
                    <table width="100%" id="table-chi-tiet-ngon-ngu">
                        <tr>
                            <td width="250px" class="danh-muc-title">
                                <%: AdminUnitString.LanguageDetail%>
                                <%: chiTietDonViTinhDaNgonNgu.NgonNgu.TenNgonNgu%>
                            </td>
                            <td align="right">
                                <div class="don-vi-tinh-action">
                                    <table>
                                        <tr>
                                            <td>
                                                <a title="<%: AdminUnitString.Edit %>" class="icon-1 info-tooltip" onclick="editLanguageDetail(
                                                                                        <%: Url.RequestContext.RouteData.Values["id"] %>,
                                                                                        <%: chiTietDonViTinhDaNgonNgu.NgonNgu.MaNgonNgu%>,
                                                                                        '<%:chiTietDonViTinhDaNgonNgu.NgonNgu.TenNgonNgu %>',
                                                                                        '<%:chiTietDonViTinhDaNgonNgu.TenDonViTinh %>');" />
                                            </td>
                                            <td>
                                                <% Html.BeginForm("DeleteUnitLanguage", "AdminUnit", FormMethod.Post, new { id = "form_delete_language_" + (i) }); %>
                                                <input name="maDonViTinh" type="hidden" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                                                <input name="maNgonNgu" type="hidden" value="<%: chiTietDonViTinhDaNgonNgu.NgonNgu.MaNgonNgu %>" />
                                                <a title="<%: AdminUnitString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_language_<%:i %>').submit();" />
                                                <% Html.EndForm(); %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="chi-tiet-title">
                                <%: AdminUnitString.UnitName%>
                            </td>
                            <td>
                                <%: chiTietDonViTinhDaNgonNgu.TenDonViTinh%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <%}
               } %>
            <!-- end list table language detail -->
        </table>
    </div>
    <!-- end id-form  -->
    <!-- begin edit language detail. This will be call when click Edit-->
    <div id="dialog-form-edit-language" title="<%: AdminUnitString.EditLanguageDetail %>">
        <% Html.BeginForm("EditUnitLanguage", "AdminUnit", FormMethod.Post, new { id = "form-edit-language" }); %>
        <label for="name">
            <%: AdminUnitString.LanguageName %></label>
        <input type="text" name="tenNgonNgu" readonly="readonly" value="" />
        <label for="name">
            <%: AdminUnitString.UnitName %></label>
        <input type="text" name="tenDonViTinh" value="" class="text ui-widget-content ui-corner-all" />
        <input type="hidden" name="maDonViTinh" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
        <input type="hidden" name="maNgonNgu" value="" />
        <% Html.EndForm(); %>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('input:button').button();
        });
    </script>
    <!-- Tooltips -->
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
    <script type="text/javascript">
        function editLanguageDetail(maDonViTinh, maNgonNgu, tenNgonNgu, tenDonViTinh) {
            $("#form-edit-language").children("input[name='maNgonNgu']").val(maNgonNgu);
            $("#form-edit-language").children("input[name='tenNgonNgu']").val(tenNgonNgu);
            $("#form-edit-language").children("input[name='tenDonViTinh']").val(tenDonViTinh);
            $("#form-edit-language").children("input[name='maDonViTinh']").val(maDonViTinh);
            $("#dialog-form-edit-language").dialog("open");
            $(".ui-dialog").center();
        }

        $.fn.center = function () {
            this.css("position", "absolute");
            this.css("top", ($(window).height() - this.height()) / 2 + $(window).scrollTop() + "px");
            this.css("left", ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + "px");
            return this;
        };

        $(document).ready(function () {
            $('#them-chi-tiet-ngon-ngu').button().click(function () {
                $("#dialog-form-add-language").dialog("open");
                $('.ui-dialog').center();
                $('.listNgonNguChuaCo').selectbox({ inputClass: "listData", debug: true });
            });
            $("#dialog-form-add-language").dialog({
                autoOpen: false,
                width: 350,
                height: 400,
                position: 'center',
                buttons: {
                    "Submit": function () {
                        $('#form-add-language').submit();
                        $(this).dialog("close");
                    }
                }
            });
            $("#dialog-form-edit-language").dialog({
                autoOpen: false,
                width: 350,
                height: 400,
                position: 'center',
                buttons: {
                    "Submit": function () {
                        $('#form-edit-language').submit();
                        $(this).dialog("close");
                    }
                }
            });
        });

    </script>
    <style type="text/css">
        label, input
        {
            display: block;
        }
        #dialog-form-add-language label
        {
            color: Black;
        }
        .don-vi-tinh-action
        {
            line-height: 20px;
            padding-top: 10px;
            margin-right: 5px;
        }
        
        #table-chi-tiet-ngon-ngu
        {
            border-bottom: none;
            border-color: gray;
            padding-left: 5px;
        }
        
        #table-chi-tiet-ngon-ngu td
        {
            padding-left: 5px;
            padding-top: 5px;
        }
        
        #table-chi-tiet-ngon-ngu tr:first-child
        {
            background: #B0B0B0;
            margin-bottom: 5px;
            font-size: larger;
        }
        
        #table-chi-tiet-ngon-ngu tr
        {
            background: #E0E0E0;
        }
        
        #table-chi-tiet-ngon-ngu .chi-tiet-title
        {
            font-weight: bold;
        }
    </style>
</asp:Content>
