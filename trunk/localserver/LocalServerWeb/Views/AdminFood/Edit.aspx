<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%:AdminFoodString.EditTitle %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminFoodString.EditTitle %>
    <%: Url.RequestContext.RouteData.Values["id"] %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!--  Error message: Cannot delete  -->
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
    <!--  Error message: Cannot change area for this table  -->
    <% if (TempData["errorCannotChangeCategory"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeParentCategory"]);
       } 
    %>
    <!--  Change parent category successfully  -->
    <% if (TempData["infoChangeCategorySuccess"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["infoChangeCategorySuccess"]);
       } 
    %>
    <!--  If you add a language detail that already exist, show error -->
    <% if (TempData["errorLanguageDetailExist"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["errorLanguageDetailExist"]);
       } 
    %>
    <!--  If you add a language detail not found, show error -->
    <% if (TempData["errorLanguageDetailNotFound"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["errorLanguageDetailNotFound"]);
       } 
    %>
    <!--  If you add a language detail that already exist, show error -->
    <% if (TempData["errorUnitDetailExist"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["errorUnitDetailExist"]);
       } 
    %>
    <!--  If you add a unit detail not found, show error -->
    <% if (TempData["errorUnitDetailNotFound"] != null)
       {
           Html.RenderPartial("InfoMessageTooltip", model: TempData["errorUnitDetailNotFound"]);
       } 
    %>
    <!--  If user has not added any language detail, show warning -->
    <% if (TempData["warningNoLanguageDetail"] != null)
       {
           Html.RenderPartial("WarningMessageTooltip", model: TempData["warningNoLanguageDetail"]);
       } 
    %>
    <!--  If user has not added any unit detail, show warning -->
    <% if (TempData["warningNoUnitDetail"] != null)
       {
           Html.RenderPartial("WarningMessageTooltip", model: TempData["warningNoUnitDetail"]);
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
    <!--  Cannot Change food Status To Available when it has no unit details-->
    <% if (TempData["errorCannotChangeStatusToAvailable"] != null)
       {
           Html.RenderPartial("ErrorMessageTooltip", model: TempData["errorCannotChangeStatusToAvailable"]);
       } 
    %>
    <!--  Main code  --------------------------------------------------------------------------->
    <div id="table-content">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form" width="100%">
            <!-- Choose category-->
            <tr>
                <th valign="top">
                    <%:AdminFoodString.Category %>:
                </th>
                <td>
                    <% Html.BeginForm("ChangeCategory", "AdminFood", FormMethod.Post); %>
                    <%= Html.DropDownList("maDanhMuc", new SelectList(ViewData["listDanhMuc"] as List<DanhMuc>, "MaDanhMuc", "TenDanhMuc", (TempData["maDanhMuc"]!=null)?TempData["maDanhMuc"]:1), new { onchange = "submit();", Class = "listDanhMuc" })%>
                    <input type="hidden" name="maMonAn" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                    <input type="hidden" name="previous_action" value="Edit" />
                    <% Html.EndForm(); %>
                </td>
                <td>
                </td>
            </tr>
            <!-- Change available status-->
            <tr>
                <th valign="top">
                    <%:AdminFoodString.Status %>:
                </th>
                <td>
                    <% if (ViewData["status"] != null)
                       {
                           Html.BeginForm("ChangeStatus", "AdminFood", FormMethod.Post, new { id = "form_change_status" }); %>
                    <select name="status" class="listDanhMuc" onchange="$('#form_change_status').submit();">
                        <option <%:((bool)ViewData["status"])?"selected":"" %> value="0">
                            <%:AdminFoodString.NotAvailable %></option>
                        <option <%:(!(bool)ViewData["status"])?"selected":"" %> value="1">
                            <%:AdminFoodString.Available %></option>
                    </select>
                    <input type="hidden" name="maMonAn" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                    <% Html.EndForm();
                       }%>
                </td>
                <td>
                    
                </td>
            </tr>
            <!--Show picture -->
            <tr>
                <th>
                    <%:AdminFoodString.Picture %>:
                </th>
                <td>
                    <div class="image_food">
                        <img src="<%:SharedCode.GetHostApplicationAddress(Request)+(ViewData["monAn"] as MonAn).HinhAnh %>"
                            alt="" width="100px" height="100px"/></div>
                </td>
                <td>
                </td>
            </tr>
            <!--Picture update-->
            <tr>
                <th>
                    <%:AdminFoodString.PictureUpdate %>:
                </th>
                <td>
                    <% Html.BeginForm("UpdateImageFood", "AdminFood", FormMethod.Post, new { enctype = "multipart/form-data" }); %>
                    <input type="file" class="file_1" name="uploadFile" accept="image/*" onchange="submit();" />
                    <input type="hidden" name="maMonAn" value="<%: Url.RequestContext.RouteData.Values["id"] %>" />
                    <% Html.EndForm(); %>
                </td>
                <td>
                </td>
            </tr>
            <!--Picture size-->
            <tr>
                <th>
                </th>
                <td>
                    <%:AdminFoodString.PictureSize %>
                </td>
                <td>
                </td>
            </tr>
            <!--Add new unit detail button-->
            <tr>
                <th>
                    <input type="button" value="<%:AdminFoodString.AddUnit %>" style="margin-top:20px;margin-right: 50px;
                        <%: (ViewData["listDonViTinh"] as List<DonViTinh>).Count>0 ? "":"display: none;"%>"
                        id="buttAddUnit" />
                    <div id="dialog-form-add-unit" title="<%:AdminFoodString.AddUnit %>">
                        <% Html.BeginForm("AddUnitPrice", "AdminFood", FormMethod.Post, new { id = "form_add_unit" }); %>
                        <label for="name">
                            <%:AdminFoodString.Unit %></label>
                        <select name="maDonViTinh" class="listData">
                            <% var listDonViTinh = ViewData["listDonViTinh"] as List<DonViTinh>;
                               for (int i = 0; i < listDonViTinh.Count; ++i)
                               {
                                   var donViTinh = listDonViTinh[i];%>
                            <option value="<%:donViTinh.MaDonViTinh %>">
                                <%:donViTinh.TenDonViTinh%></option>
                            <%} %>
                        </select>
                        <label for="email">
                            <%:AdminFoodString.Price %></label>
                        <input type="text" name="price_new" id="price_new" value="" class="text ui-widget-content ui-corner-all" />
                        <input type="hidden" name="maMonAn" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                        <% Html.EndForm(); %>
                    </div>
                </th>
                <td>
                </td>
                <td>
                </td>
            </tr>
            <!--Unit detail banner -->
            <tr>
                <td colspan="2">
                    <table width="100%" id="table-chi-tiet-don-vi-tinh">
                        <tr>
                            <td width="250px">
                                <%: AdminFoodString.UnitDetail %>
                            </td>
                            <td align="right">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--Unit detail table -->
            <tr>
                <th>
                    <%:AdminFoodString.Unit %>
                </th>
                <td>
                    <table id="hor-minimalist-b" summary="Employee Pay Sheet">
                        <thead>
                            <tr>
                                <th scope="col">
                                    <%:AdminFoodString.Unit %>
                                </th>
                                <th scope="col">
                                    <%:AdminFoodString.Price %>
                                </th>
                                <th scope="col">
                                    <%:AdminFoodString.UnitPriceAction %>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int iCount = 0; %>
                            <% foreach (var chiTietMonAnDonViTinh in ViewData["listChiTietMonAnDonViTinh"] as List<ChiTietMonAnDonViTinh>)
                               { %>
                            <tr>
                                <td>
                                    <%:chiTietMonAnDonViTinh.TenDonViTinh %>
                                </td>
                                <td>
                                    <input value="<%:chiTietMonAnDonViTinh.DonGia %>" name="gia" id="gia_input_<%:++iCount %>" />
                                </td>
                                <td>
                                    <% Html.BeginForm("EditPrice", "AdminFood", FormMethod.Post, new { id = "form_editPrice_" + (iCount) }); %>
                                    <input name="maMonAn" type="hidden" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                                    <input name="maDonViTinh" type="hidden" value="<%:chiTietMonAnDonViTinh.DonViTinh.MaDonViTinh %>" />
                                    <input type="hidden" name="gia" value="-1" id="gia_submit" />
                                    <a title="<%:AdminFoodString.Save %>" class="icon-1 info-tooltip" onclick="editPrice('#form_editPrice_<%:iCount %>', '#gia_input_<%:iCount %>');">
                                    </a>
                                    <% Html.EndForm(); %>
                                    <% Html.BeginForm("DeleteUnit", "AdminFood", FormMethod.Post, new { id = "form_delete_" + (iCount) }); %>
                                    <input name="maMonAn" type="hidden" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                                    <input name="maDonViTinh" type="hidden" value="<%:chiTietMonAnDonViTinh.DonViTinh.MaDonViTinh %>" />
                                    <a title="<%:AdminFoodString.Delete %>" class="icon-2 info-tooltip" onclick="deleteUnit('#form_delete_<%:iCount %>');" />
                                    <% Html.EndForm(); %>
                                </td>
                            </tr>
                            <%} %>
                        </tbody>
                    </table>
                    <%--<div id="unit_content">                
                    <div class="unit">
                        <div class="unit_name"></div>
                        <div class="unit_price"></div>
                        <div class="unit_action"></div>
                    </div>                
                </div>--%>
                </td>
                <td>
                </td>
            </tr>
            <!--Show add new language detail button-->
            <% if (((List<NgonNgu>)ViewData["listNgonNguChuaCo"]).Count > 0)
               {%>
            <tr>
                <th>
                    <input type="button" style="float: right; margin-right: 50px;" id="them-ngon-ngu-mon"
                        value="<%:AdminFoodString.AddLanguageFood%>" />
                    <div id="dialog-form-add-language" title="<%:AdminFoodString.AddUnit %>">
                        <% Html.BeginForm("AddLanguageFood", "AdminFood", FormMethod.Get, new { id = "form_add_language" }); %>
                        <label for="name">
                            <%:AdminFoodString.Language %></label>
                        <select name="maNgonNgu" class="listData">
                            <% var listNgonNguChuaCo = ViewData["listNgonNguChuaCo"] as List<NgonNgu>;
                               for (int i = 0; i < listNgonNguChuaCo.Count; ++i)
                               {
                                   var ngonNgu = listNgonNguChuaCo[i];%>
                            <option value="<%:ngonNgu.MaNgonNgu %>">
                                <%:ngonNgu.TenNgonNgu%></option>
                            <%} %>
                        </select>
                        <input type="hidden" name="maMonAn" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                        <% Html.EndForm(); %>
                    </div>
                </th>
                <td>
                </td>
                <td>
                </td>
            </tr>
            <% } %>
            <!--Language detail banner table-->
            <% if (ViewData["listChiTietMonAnDaNgonNgu"] != null)
               {
                   for (int i = 0; i < (ViewData["listChiTietMonAnDaNgonNgu"] as List<ChiTietMonAnDaNgonNgu>).Count; ++i)
                   {
                       var chiTietMonAnDaNgonNgu =
                           (ViewData["listChiTietMonAnDaNgonNgu"] as List<ChiTietMonAnDaNgonNgu>)[i]; %>
            <tr>
                <td colspan="2">
                    <table width="100%" id="table-chi-tiet-ngon-ngu">
                        <tr>
                            <td width="250px">
                                <%: AdminFoodString.LanguageDetail %>
                                <%: chiTietMonAnDaNgonNgu.NgonNgu.TenNgonNgu%>
                            </td>
                            <td align="right">
                                <div class="mon-an-action">
                                    <table>
                                        <tr>
                                            <td>
                                                <% Html.BeginForm("EditLanguageFood", "AdminFood", FormMethod.Get, new { id = "form_edit_language_" + (i) }); %>
                                                <input name="maMonAn" type="hidden" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                                                <input name="maNgonNgu" type="hidden" value="<%:chiTietMonAnDaNgonNgu.NgonNgu.MaNgonNgu %>" />
                                                <a title="<%:AdminFoodString.Edit %>" class="icon-1 info-tooltip" onclick="$('#form_edit_language_<%:i %>').submit();" />
                                                <% Html.EndForm(); %>
                                            </td>
                                            <td>
                                                <% Html.BeginForm("DeleteLanguageFood", "AdminFood", FormMethod.Post, new { id = "form_delete_language_" + (i) }); %>
                                                <input name="maMonAn" type="hidden" value="<%:Url.RequestContext.RouteData.Values["id"] %>" />
                                                <input name="maNgonNgu" type="hidden" value="<%:chiTietMonAnDaNgonNgu.NgonNgu.MaNgonNgu %>" />
                                                <a title="<%:AdminFoodString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_language_<%:i %>').submit();" />
                                                <% Html.EndForm(); %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="chi-tiet-title">
                                <%: AdminFoodString.FoodName%>
                            </td>
                            <td>
                                <%: chiTietMonAnDaNgonNgu.TenMonAn%>
                            </td>
                        </tr>
                        <tr>
                            <td class="chi-tiet-title">
                                <%: AdminFoodString.FoodDescription%>
                            </td>
                            <td>
                                <%= chiTietMonAnDaNgonNgu.MoTaMonAn%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <%}
               } %>
        </table>
    </div>
    <!-- end id-form  -->
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "listDanhMuc", debug: true });
            $('#add-language-food').button();
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
        $(function () {
            $("#dialog-form-add-unit").dialog({
                autoOpen: false,
                width: 350,
                position: 'center',
                buttons: {
                    "Submit": function () {
                        if ($('#price_new').val() > 0) {
                            $(this).dialog("close");
                            $('#form_add_unit').submit();
                            $(this).dialog("close");
                        }
                    }
                }
            });

            $("#buttAddUnit")
			.button()
			.click(function () {
			    $("#dialog-form-add-unit").dialog("open");
			    $('.ui-dialog').center();
			    //$('#dialog-form-add-unit #listDonViTinh :nth-child(4)').attr('selected', 'selected');
			    $('.listDonViTinh').selectbox({ inputClass: "listData", debug: true });
			});
        });

        $(document).ready(function () {
            $('#them-ngon-ngu-mon').button().click(function () {
                $("#dialog-form-add-language").dialog("open");
                $('.ui-dialog').center();
                $('.listNgonNguChuaCo').selectbox({ inputClass: "listData", debug: true });
            });
            $("#dialog-form-add-language").dialog({
                autoOpen: false,
                width: 350,
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
        #dialog-form-add-unit label
        {
            color: steelblue;
        }
        #price_new
        {
            width: 195px;
        }
        
        .mon-an-action
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
        
        #table-chi-tiet-don-vi-tinh tr:first-child
        {
            background: #B0B0B0;
            margin-bottom: 5px;
            font-size: larger;
            height: 50px;
        }
        
        #table-chi-tiet-don-vi-tinh td
        {
           padding-left: 5px;
           padding-top: 5px;
        }
        
    </style>
</asp:Content>
