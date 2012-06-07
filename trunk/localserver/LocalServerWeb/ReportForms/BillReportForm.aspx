<%@ Page Title="" Language="C#" MasterPageFile="~/ReportForms/WebFormReport.Master" AutoEventWireup="true" CodeBehind="BillReportForm.aspx.cs" Inherits="LocalServerWeb.ReportsForm.BillReportForm" %>
<%@ Register TagPrefix="rsweb" Namespace="Microsoft.Reporting.WebForms" Assembly="Microsoft.ReportViewer.WebForms, Version=10.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <rsweb:ReportViewer ID="rvReport" runat="server" Height="700" Width="1000">

    </rsweb:ReportViewer>
</asp:Content>
