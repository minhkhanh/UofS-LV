<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:FoodCategoryString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorylinks.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorysidebar.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodgalleryitem.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodgallery.css" type="text/css" />
</asp:Content>


<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Category</h2>
    <% Html.RenderPartial("FoodCategoryLinks"); %>
    <% Html.RenderPartial("FoodCategorySidebar"); %>
    <% Html.RenderPartial("FoodGallery"); %>

</asp:Content>



