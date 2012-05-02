<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<System.Int32>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:FoodCategoryString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorylinks.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorysidebar.css" type="text/css" />
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Food</h2>

    

    <table width="100%">
        <tr>
            <td colspan="2">
                Links
                <% Html.RenderPartial("FoodCategoryLinks"); %>
            </td>
        </tr>
        <tr>
            <td>
                <% Html.RenderPartial("FoodCategorySidebar"); %>
            </td>
            <td>
                <% Html.RenderPartial("FoodDetail"); %>
            </td>
        </tr>
    </table>
    
</asp:Content>




