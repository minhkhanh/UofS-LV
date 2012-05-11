﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

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
    <table border="0" cellpadding="0" cellspacing="0" id="id-form" width="100%">
        <!-- begin parent category list -->
        <tr>
            <th valign="top">
                <%: AdminCategoryString.ParentCategoryName %>:
            </th>
            <td>
                <% Html.BeginForm("ChangeParentCategory", "AdminCategory", FormMethod.Post); %>
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
                    <% Html.BeginForm("AddCategoryLanguage", "AdminCategory", FormMethod.Post, new { id = "form-add-language" }); %>
                    <label for="name">
                        <%: AdminCategoryString.LanguageName %></label>
                    <select name="maNgonNgu" class="listData">
                        <% var listNgonNguChuaCo = ViewData["listNgonNguChuaCo"] as List<NgonNgu>;
                           for (int i = 0; i < listNgonNguChuaCo.Count; ++i)
                           {
                               var ngonNgu = listNgonNguChuaCo[i];%>
                        <option value="<%:ngonNgu.MaNgonNgu %>">
                            <%:ngonNgu.TenNgonNgu
                            %></option>
                        <%} %>
                    </select>
                    <label for="name">
                        <%: AdminCategoryString.CategoryName %></label>
                    <input type="text" name="tenDanhMuc" value="" class="text ui-widget-content ui-corner-all" />
                    <label for="name">
                        <%: AdminCategoryString.CategoryDescription %></label>
                    <input type="text" name="moTaDanhMuc" value="" class="text ui-widget-content ui-corner-all" />
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
                <table width="100%" id="table-chi-tiet-ngon-ngu">
                    <tr>
                        <td width="250px" class="danh-muc-title">
                            <%: AdminCategoryString.LanguageDetail %>
                            <%: chiTietDanhMucDaNgonNgu.NgonNgu.TenNgonNgu %>
                        </td>
                        <td align="right">
                            <div class="danh-muc-action">
                                <table>
                                    <tr>
                                        <td>
                                            <a title="<%: AdminCategoryString.Edit %>" class="icon-1 info-tooltip" onclick="editLanguageDetail(
                                                                                        <%: Url.RequestContext.RouteData.Values["id"] %>,
                                                                                        <%: chiTietDanhMucDaNgonNgu.NgonNgu.MaNgonNgu%>,
                                                                                        '<%:chiTietDanhMucDaNgonNgu.NgonNgu.TenNgonNgu %>',
                                                                                        '<%:chiTietDanhMucDaNgonNgu.TenDanhMuc %>',
                                                                                        '<%:chiTietDanhMucDaNgonNgu.MoTaDanhMuc %>');" />
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
                        </td>
                    </tr>
                    <tr>
                        <td class="chi-tiet-title">
                            <%: AdminCategoryString.CategoryName %>
                        </td>
                        <td>
                            <%: chiTietDanhMucDaNgonNgu.TenDanhMuc %>
                        </td>
                    </tr>
                    <tr>
                        <td class="chi-tiet-title">
                            <%: AdminCategoryString.CategoryDescription %>
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
    <!-- begin edit language detail. This will be call when click Edit-->
    <div id="dialog-form-edit-language" title="<%: AdminCategoryString.EditLanguageDetail %>">
        <% Html.BeginForm("EditCategoryLanguage", "AdminCategory", FormMethod.Post, new { id = "form-edit-language" }); %>
        <label for="name">
            <%: AdminCategoryString.LanguageName %></label>
        <input type="text" name="tenNgonNgu" readonly="readonly" value="" />
        <label for="name">
            <%: AdminCategoryString.CategoryName %></label>
        <input type="text" name="tenDanhMuc" value="" class="text ui-widget-content ui-corner-all" />
        <label for="name">
            <%: AdminCategoryString.CategoryDescription %></label>
        <input type="text" name="moTaDanhMuc" value="" class="text ui-widget-content ui-corner-all" />
        <input type="hidden" name="maDanhMuc" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
        <input type="hidden" name="maNgonNgu" value="" />
        <% Html.EndForm(); %>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
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
        function editLanguageDetail(maDanhMuc, maNgonNgu, tenNgonNgu, tenDanhMuc, moTaDanhMuc) {
            $("#form-edit-language").children("input[name='maNgonNgu']").val(maNgonNgu);
            $("#form-edit-language").children("input[name='tenNgonNgu']").val(tenNgonNgu);
            $("#form-edit-language").children("input[name='tenDanhMuc']").val(tenDanhMuc);
            $("#form-edit-language").children("input[name='moTaDanhMuc']").val(moTaDanhMuc);
            $("#form-edit-language").children("input[name='maDanhMuc']").val(maDanhMuc);
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
        .danh-muc-action
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
