<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<System.Int32>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:FoodCategoryString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Food</h2>
    <% Html.RenderPartial("FoodCategoryLinks"); %>
    <% Html.RenderPartial("FoodCategorySidebar"); %>
    <% Html.RenderPartial("FoodDetail"); %>
    
</asp:Content>




