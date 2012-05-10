<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminCategory" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: AdminCategoryString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%: AdminCategoryString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"] %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!-- start id-form -->
    <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <!-- begin parent category list -->
        <tr>
            <th valign="top">
                <%: AdminCategoryString.ParentCategoryName %>:
            </th>
            <td>
                <% Html.BeginForm("ChangeParentCategory", "AdminCateory", FormMethod.Post); %>
                <%= Html.DropDownList("maDanhMucCha", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (TempData["maDanhMucCha"]!=null)?TempData["maDanhMucCha"]:1), new { onchange = "submit();", Class = "listDanhMucCha" })%>
                <input type="hidden" name="maDanhMuc" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                <input type="hidden" name="previous_action" value="Edit" />
                <% Html.EndForm(); %>
            </td>
        </tr>
        <!-- end parent category list -->
        <!-- begin add new language detail -->
        <% if (((List<NgonNgu>)ViewData["listNgonNguChuaCo"]).Count > 0)
           {%>
        <tr>
            <th>
                <input type="button" style="float: right; margin-right: 50px;" id="them-chi-tiet-ngon-ngu"
                    value="<%: AdminCategoryString.AddLanguageDetail %>" />
                <div id="dialog-form-add-language" title="<%: AdminCategoryString.AddLanguageDetail %>">
                    <% Html.BeginForm("AddCategoryLanguage", "AdminCategory", FormMethod.Post, new { id = "form_add_language" }); %>
                    <label for="name">
                        <%: AdminCategoryString.LanguageName %></label>
                    <%= Html.DropDownList("maNgonNgu", new SelectList(ViewData["listNgonNguChuaCo"] as List<NgonNgu>, "MaNgonNgu", "TenNgonNgu", 1), new { Class = "listNgonNguChuaCo" })%>
                    <label for="name">
                        <%: AdminCategoryString.CategoryName %></label>
                    <input type="text" name="tenDanhMuc" id="category_name" value="" class="text ui-widget-content ui-corner-all" />
                    <label for="name">
                        <%: AdminCategoryString.CategoryDescription %></label>
                    <input type="text" name="moTaDanhMuc" id="category_description" value="" class="text ui-widget-content ui-corner-all" />
                    <input type="hidden" name="maDanhMuc" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
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
        <% for (int i = 0; i < (ViewData["listChiTietDanhMucDaNgonNgu"] as List<ChiTietDanhMucDaNgonNgu>).Count; ++i)
           {
               var chiTietDanhMucDaNgonNgu =
                   (ViewData["listChiTietDanhMucDaNgonNgu"] as List<ChiTietDanhMucDaNgonNgu>)[i]; %>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th>
                            <%: AdminCategoryString.LanguageDetail%>
                            <%: chiTietDanhMucDaNgonNgu.NgonNgu.TenNgonNgu %>
                        </th>
                        <td>
                            <div class="danh-muc-header">
                                <div class="danh-muc-action">
                                    <table>
                                        <tr>
                                            <td>
                                                <!--
                                    <% Html.BeginForm("EditCategoryLanguage", "AdminCategory", FormMethod.Post, new { id = "form_edit_language_" + (i) }); %>  
                                    <input name="maMonAn" type="hidden" value="<%:Url.RequestContext.RouteData.Values["id"] %>"/>
                                    <input name="maNgonNgu" type="hidden" value="<%:chiTietDanhMucDaNgonNgu.NgonNgu.MaNgonNgu %>"/>
                                    <% Html.EndForm(); %> 
                                    -->
                                                <a title="<%: AdminCategoryString.Edit %>" class="icon-1 info-tooltip" onclick="$('#form_edit_language_<%:i %>').submit();" />
                                            </td>
                                            <td>
                                                <% Html.BeginForm("DeleteCategoryLanguage", "AdminCategory", FormMethod.Post, new { id = "form_delete_language_" + (i) }); %>
                                                <input name="maDanhMuc" type="hidden" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                                                <input name="maNgonNgu" type="hidden" value="<%: chiTietDanhMucDaNgonNgu.NgonNgu.MaNgonNgu %>" />
                                                <a title="<%: AdminCategoryString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_language_<%:i %>').submit();" />
                                                <% Html.EndForm(); %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="ten-danh-muc-title">
                                <%: AdminCategoryString.CategoryName %></label>
                        </td>
                        <td>
                            <%: chiTietDanhMucDaNgonNgu.TenDanhMuc %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="mo-ta-danh-muc-title">
                                <%: AdminCategoryString.CategoryDescription %></label>
                        </td>
                        <td>
                            <%: chiTietDanhMucDaNgonNgu.MoTaDanhMuc %>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <%} %>
        <!-- end list table language detail -->
    </table>
    <!-- end id-form  -->
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listNgonNguChuaCo').selectbox({ inputClass: "styledselect_pages", debug: true });
            $('.listDanhMucCha').selectbox({ inputClass: "styledselect_pages", debug: true });
            $('input:button').button();
            $('#add-language-food').button();
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
        function editPrice(formContent, elementInput) {
            var tmp = $(elementInput).val();
            var a = $(formContent).find('#gia_submit').val();
            $(formContent).find('#gia_submit').val($(elementInput).val());
            $(formContent).submit();
        }
        function deleteUnit(formContent) {
            $(formContent).submit();
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
                //$('.listNgonNguChuaCo').selectbox({ inputClass: "listData", debug: true });
            });
            $("#dialog-form-add-language").dialog({
                autoOpen: false,
                width: 350,
                height: 400,
                position: 'center',
                buttons: {
                    "Submit": function () {
                        $('#form_add_language').submit();
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
        #price_new
        {
            width: 195px;
        }
        .ten-danh-muc-title
        {
            color: #039;
            font-size: 14px;
            padding: 10px 8px;
            float: left;
            width: 85%;
        }
        .mo-ta-danh-muc-title
        {
            color: #039;
            font-size: 14px;
            padding: 10px 8px;
        }
        .ten-danh-muc
        {
            color: black;
            font-size: 13px;
            padding: 10px 8px;
        }
        .mo-ta-danh-muc
        {
            padding: 10px 8px;
        }
        .danh-muc-header
        {
            display: block;
            border-top: 2px solid #6678B1;
        }
        .danh-muc-action
        {
            line-height: 20px;
            padding-top: 10px;
            margin-right: 5px;
        }
    </style>
</asp:Content>
