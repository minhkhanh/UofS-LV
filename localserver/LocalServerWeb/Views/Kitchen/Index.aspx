<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Kitchen" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    var iTimerTick = <%:(int)ViewData["iTimerTick"] %> ;
    var timer;
    var bTimerOn = false;
    $(document).ready(function () {
        $('#test-button').button().click(function () {
            bTimerOn = true;
            timer = setTimeout("getKitchenOrderTable()", 1);
        });
        $("#dialog-form-che-bien").dialog({
            autoOpen: false,
            width: 350,
            position: 'center',
            buttons: {
                "<%=KitchenString.CheBien %>": function () {                    
                    $.ajax({
                        type: 'POST',
                        url: '<%= Url.Action("PostCheBien") %>',
                        data: $('#form-so-luong-che-bien').serialize(),
                        success: function (response) {
                            if (response.toLowerCase()=="true") {
                                clearTimeout(timer);
                                timer = setTimeout("getKitchenOrderTable()", 1000);
                            }
                        },
                        complete: function () {
                            bTimerOn = true;
                        }
                    });
                    $(this).dialog("close");                    
                }
            }
        });
    });
    function getKitchenOrderTable() {
        if (!bTimerOn) {
            timer = setTimeout("getKitchenOrderTable()", 1000);
            return;   
        }
        $('#kitchen-table').load('<%= Url.Action("GetKitchenOrder", new { maBoPhanCheBien = 1}) %>', function () {    
            $('#kitchen-table .button-che-bien').button().click(function () {
                bTimerOn = false;
                var tmp = $(this).parent().parent().find('.ma-chi-tiet-order').html();
                $.ajax({
                    type: 'GET',
                    url: '<%= Url.Action("GetDialogCheBien") %>',
                    data: 'maChiTietOrder='+tmp,       
                    success: function (response) {
                        $("#dialog-form-che-bien").html(response);
                        $("#dialog-form-che-bien").dialog("open");
                        $('.ui-dialog').center();                        
                    }
                });
            });
            timer = setTimeout("getKitchenOrderTable()", iTimerTick);
        });
    }
</script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
	<!--  start table-content  -->
	<div id="table-content">
			
		<!--  start message-yellow -->
		<div id="message-yellow">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="yellow-left">You have a new message. <a href="">Go to Inbox.</a></td>
			<td class="yellow-right"><a class="close-yellow"><img src="../../Images/adminimages/table/icon_close_yellow.gif"   alt="" /></a></td>
		</tr>
		</table>
		</div>
		<!--  end message-yellow -->						
		 
		<!--  start product-table ..................................................................................... -->
        <div id="kitchen-table">

        </div>
		<!--  end product-table................................... --> 
        <a id="test-button">test-button</a>
	</div>
	<!--  end content-table  -->

    <div id="dialog-form-che-bien" title="<%:KitchenString.NhapSoLuongCheBien %>">

    </div>
</asp:Content>
