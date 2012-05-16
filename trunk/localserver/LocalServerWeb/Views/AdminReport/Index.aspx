<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Admin.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<%@ Register Assembly="Microsoft.ReportViewer.WebForms, Version=10.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a"
    Namespace="Microsoft.Reporting.WebForms" TagPrefix="rsweb" %>

<script runat="server">                        
    protected void Page_Load(object sender, EventArgs e) 
    {         
        try
        {            
            rvReport.Reset();
            rvReport.ProcessingMode = ProcessingMode.Local;
            rvReport.LocalReport.ReportPath = (string)ViewData["reportPath"];
            rvReport.LocalReport.SetParameters((IEnumerable<ReportParameter>)ViewData["reportParameter"]);
            rvReport.LocalReport.DataSources.Clear();
            rvReport.LocalReport.DataSources.Add((ReportDataSource)ViewData["reportData"]);
            rvReport.DataBind();
            rvReport.LocalReport.Refresh();
        }
        catch (Exception ex)
        {
            System.Diagnostics.Debug.Write("Error: " + ex.StackTrace);
        }   
    }  
</script> 

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
<form id="Form1" runat="server"> 
    <rsweb:ReportViewer ID="rvReport" runat="server" >

    </rsweb:ReportViewer>
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
</form>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="PageHeadingContent" runat="server">

</asp:Content>
