<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminFood" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:AdminFoodString.Title %>
</asp:Content>


<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <%--<% Html.BeginForm("AddFood", "AdminFood", FormMethod.Post, new { enctype = "multipart/form-data" }); %>--%>
		<!-- start id-form -->
		<table border="0" cellpadding="0" cellspacing="0"  id="id-form">
        
		<tr>
		<th valign="top"><%:AdminFoodString.Category %>:</th>
		<td>
            <% Html.BeginForm("UpdateCategory", "AdminFood", FormMethod.Post); %>                                
            <select name="listDanhMuc" class="listDanhMuc" onchange="submit();">
            <% foreach (var danhMuc in ViewData["listDanhMuc"] as List<DanhMuc>)
               { %>
                <option value="<%:danhMuc.MaDanhMuc %>" <%:(danhMuc.MaDanhMuc==(ViewData["monAn"] as MonAn).DanhMuc.MaDanhMuc)?"selected=true":"" %>><%:danhMuc.TenDanhMuc %></option>   
               <%} %>                
            </select>
            <input type="hidden" name="maMonAn" value="<%: Request.QueryString["maMonAn"] %>"/>	
            <% Html.EndForm(); %>
		</td>
		<td></td>
		</tr>         

	    <tr>
	    <th><%:AdminFoodString.Picture %>:</th>
	    <td><div class="image_food"><img src="<%:SharedCode.GetHostApplicationAddress(Request)+(ViewData["monAn"] as MonAn).HinhAnh %>" alt=""/></div></td>
	    <td>
	    </td>
	    </tr>

	    <tr>
	    <th><%:AdminFoodString.PictureUpdate %>:</th>
	    <td>
            <% Html.BeginForm("UpdateImageFood", "AdminFood", FormMethod.Post, new {enctype = "multipart/form-data"}); %>
            <input type="file" class="file_1" name="uploadFile" accept="image/*" onchange="submit();"/>
            <input type="hidden" name="maMonAn" value="<%: Request.QueryString["maMonAn"] %>"/>
            <% Html.EndForm(); %>
        </td>
	    <td>
	    </td>
	    </tr>

	    <tr>
	    <th></th>
	    <td><%:AdminFoodString.PictureSize %></td>
	    <td>
	    </td>
	    </tr>

	    <tr>
		    <th><%:AdminFoodString.Unit %></th>
		    <td>
                <table id="hor-minimalist-b" summary="Employee Pay Sheet">
                    <thead>
    	                <tr>
        	                <th scope="col"><%:AdminFoodString.Unit %></th>
                            <th scope="col"><%:AdminFoodString.Price %></th>
                            <th scope="col"><%:AdminFoodString.UnitPriceAction %></th>
                        </tr>
                    </thead>
                    <tbody>
                    <% int iCount = 0; %>
                    <% foreach (var chiTienMonAnDonViTinh in ViewData["listChiTienMonAnDonViTinh"] as List<ChiTietMonAnDonViTinh>) { %>                                                            
    	                <tr>                                                     
        	                <td><%:chiTienMonAnDonViTinh.TenDonViTinh %></td>
                            <td><input value="<%:chiTienMonAnDonViTinh.DonGia %>" name="gia" id="gia_input_<%:++iCount %>"/></td>
                            <td>
                                <% Html.BeginForm("EditPrice", "AdminFood", FormMethod.Post, new { id = "form_editPrice_" + (iCount)}); %>  
                                <input name="maMonAn" type="hidden" value="<%:Request.QueryString["maMonAn"] %>"/>
                                <input name="maDonViTinh" type="hidden" value="<%:chiTienMonAnDonViTinh.DonViTinh.MaDonViTinh %>"/>
                                <input type="hidden" name="gia" value="-1" id="gia_submit"/>
                                <a title="<%:AdminFoodString.Save %>" class="icon-1 info-tooltip" onclick="editPrice('#form_editPrice_<%:iCount %>', '#gia_input_<%:iCount %>');"></a>		                                
                                <% Html.EndForm(); %>
                                <% Html.BeginForm("DeleteUnit", "AdminFood", FormMethod.Post, new { id = "form_delete_" + (iCount) }); %>  
                                <input name="maMonAn" type="hidden" value="<%:Request.QueryString["maMonAn"] %>"/>
                                <input name="maDonViTinh" type="hidden" value="<%:chiTienMonAnDonViTinh.DonViTinh.MaDonViTinh %>"/>
                                <a title="<%:AdminFoodString.Delete %>" class="icon-2 info-tooltip" onclick="deleteUnit('#form_delete_<%:iCount %>');" />
                                <% Html.EndForm(); %>
                            </td>
                        </tr>                    
                    <%} %>
                    </tbody>
                </table>
                <input type="button" value="<%:AdminFoodString.AddUnit %>" style="float: right;margin-right: 50px;<%: (ViewData["listDonViTinh"] as List<DonViTinh>).Count>0 ? "":"display: none;"%>" id="buttAddUnit"/>
                <div id="dialog-form-add-unit" title="<%:AdminFoodString.AddUnit %>">
	                <% Html.BeginForm("AddUnitPrice", "AdminFood", FormMethod.Post, new { id = "form_add_unit"}); %>
		                <label for="name"><%:AdminFoodString.Unit %></label>
                        <select name="listDonViTinh" class="listDonViTinh">
                        <% var listDonViTinh = ViewData["listDonViTinh"] as List<DonViTinh>;
                            for (int i=0; i<listDonViTinh.Count; ++i)
                            {
                                var donViTinh = listDonViTinh[i];%>
                            <option value="<%:donViTinh.MaDonViTinh %>"><%:donViTinh.TenDonViTinh%></option>   
                           <%} %>                
                        </select>
		                <label for="email"><%:AdminFoodString.Price %></label>
		                <input type="text" name="price_new" id="price_new" value="" class="text ui-widget-content ui-corner-all" />		                
                        <input type="hidden" name="maMonAn" value="<%:Request.QueryString["maMonAn"] %>"/>
	                <% Html.EndForm(); %>
                </div>

			    <%--<div id="unit_content">                
                    <div class="unit">
                        <div class="unit_name"></div>
                        <div class="unit_price"></div>
                        <div class="unit_action"></div>
                    </div>                
                </div>--%>
		    </td>
		    <td></td>
	    </tr>

        
        <% for (int i = 0; i < (ViewData["listChiTietMonAnDaNgonNgu"] as List<ChiTietMonAnDaNgonNgu>).Count; ++i )
           {
               var chiTietMonAnDaNgonNgu =
                   (ViewData["listChiTietMonAnDaNgonNgu"] as List<ChiTietMonAnDaNgonNgu>)[i]; %>
	    <tr>
		    <th><%:AdminFoodString.Detail + chiTietMonAnDaNgonNgu.NgonNgu.TenNgonNgu%></th>
		    <td>        
                <div class="mon-an-header">
                    <label class="ten-mon-an-title"><%:AdminFoodString.FoodName%></label> 
                    <div class="mon-an-action">                        
                        <table>
                            <tr>
                                <td>
                                    <% Html.BeginForm("EditLanguageFood", "AdminFood", FormMethod.Get, new { id = "form_edit_language_" + (i) }); %>  
                                    <input name="maMonAn" type="hidden" value="<%:Request.QueryString["maMonAn"] %>"/>
                                    <input name="maNgonNgu" type="hidden" value="<%:chiTietMonAnDaNgonNgu.NgonNgu.MaNgonNgu %>"/>
                                    <a title="<%:AdminFoodString.Edit %>" class="icon-1 info-tooltip" onclick="$('#form_edit_language_<%:i %>').submit();" />
                                    <% Html.EndForm(); %>                                
                                </td>                                
                                <td>
                                    <% Html.BeginForm("DeleteLanguageFood", "AdminFood", FormMethod.Post, new { id = "form_delete_language_" + (i) }); %>
                                    <input name="maMonAn" type="hidden" value="<%:Request.QueryString["maMonAn"] %>"/>
                                    <input name="maNgonNgu" type="hidden" value="<%:chiTietMonAnDaNgonNgu.NgonNgu.MaNgonNgu %>"/>                                      
                                    <a title="<%:AdminFoodString.Delete %>" class="icon-2 info-tooltip" onclick="$('#form_delete_language_<%:i %>').submit();" />
                                    <% Html.EndForm(); %>
                                </td>
                            </tr>
                        </table>                                                
                    </div>
                </div>
                <label class="ten-mon-an"><%:chiTietMonAnDaNgonNgu.TenMonAn%></label> 
                <label class="mo-ta-mon-an-title"><%:AdminFoodString.FoodDescription%></label> 
                <div class="mo-ta-mon-an"><%=chiTietMonAnDaNgonNgu.MoTaMonAn%></div>
		    </td>
		    <td></td>
	    </tr>               
           <%} %>

        <% if (((List<NgonNgu>)ViewData["listNgonNguChuaCo"]).Count > 0)
           {%>
        <tr>
	    <th></th>
	    <td>
                
            <input type="button" style="float: right;margin-right: 50px;" id="them-ngon-ngu-mon" value="<%:AdminFoodString.AddLanguageFood%>" />

            <div id="dialog-form-add-language" title="<%:AdminFoodString.AddUnit %>">
	            <% Html.BeginForm("AddLanguageFood", "AdminFood", FormMethod.Get, new { id = "form_add_language" }); %>
		            <label for="name"><%:AdminFoodString.Language %></label>
                    <select name="listNgonNguChuaCo" class="listData">
                    <% var listNgonNguChuaCo = ViewData["listNgonNguChuaCo"] as List<NgonNgu>;
                       for (int i = 0; i < listNgonNguChuaCo.Count; ++i)
                        {
                            var ngonNgu = listNgonNguChuaCo[i];%>
                        <option value="<%:ngonNgu.MaNgonNgu %>"><%:ngonNgu.TenNgonNgu%></option>   
                        <%} %>                
                    </select>	                
                    <input type="hidden" name="maMonAn" value="<%:Request.QueryString["maMonAn"] %>"/>
	            <% Html.EndForm(); %>
            </div>
        </td>
	    <td>
	    </td>
	    </tr>
        <% } %>
	</table>
	<!-- end id-form  -->
    <%--<% Html.EndForm(); %>--%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="../../Scripts/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.listDanhMuc').selectbox({ inputClass: "listDanhMuc", debug: true });
            $('input:button').button();
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
<style>
    label, input { display:block; }
    #dialog-form-add-unit label { color:steelblue;}
    #price_new { width: 195px;}
    .ten-mon-an-title 
    {
        color: #039;
        font-size: 14px;
        padding: 10px 8px;
        float: left;
        width: 85%;            
    }
    .mo-ta-mon-an-title
    {
        color: #039;
        font-size: 14px;
        padding: 10px 8px;    
    }
    .ten-mon-an 
    {
        color: black;
        font-size: 13px;
        padding: 10px 8px;        
    }
    .mo-ta-mon-an 
    {
        padding: 10px 8px;
    }
    .mon-an-header 
    {
        display: block;
        border-top: 2px solid #6678B1;
    }
    .mon-an-action
    {
        line-height: 20px;
        padding-top: 10px;
        margin-right: 5px;
    }
</style>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
    <%:AdminFoodString.Title %>
</asp:Content>
