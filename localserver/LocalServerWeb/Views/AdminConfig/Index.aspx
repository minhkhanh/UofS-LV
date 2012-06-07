<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminConfig" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%: AdminConfigString.Title %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">
<%: AdminConfigString.Title %>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="title-content"><%:AdminConfigString.PrinterName %>:</div>
    <div class="body-content">             
        <div style="float: left; padding-right: 10px;"><input id="billPrinter" type="text" name="billPrinter" value="<%: ViewData["billPrinter"] %>" /> </div>            
        <div><button id="button-save-bill-printer">Save</button></div>
    </div>
    <div class="clear"> </div>
    <div class="line-distance"></div>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).ready(function () {
                $('#button-save-bill-printer').hide();
                $('input#billPrinter').keydown(function () {
                    $('#button-save-bill-printer').show();
                });
            });
            $('#button-save-bill-printer').button().click(function () {
                $.ajax({
                    type: 'POST',
                    url: '<%= Url.Action("ChangeBillPrinter") %>',
                    data: 'billPrinter=' + $('#billPrinter').val(),
                    success: function (response) {
                        if (response.toLowerCase() == "true") {
                            $('#button-save-bill-printer').hide();
                        }
                    }
                });
            });
        })
    </script>
    <style type="text/css">
        .title-content 
        {
            padding: 5px 5px 15px;
            font-size: 15px;
        }
        .body-content 
        {
            padding-left: 30px;
        }
        .body-content input 
        {
            min-width: 350px;
            min-height: 22px;
        }
        .line-distance 
        {
            padding: 20px 10px 10px 20px;
            border-bottom: 2px double black;
        }
    </style>
</asp:Content>


