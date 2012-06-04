<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<div id="food_category_link">
    <% if (ViewData["foodCategoryLinksViewModel"] != null)
       {
           FoodCategoryLinksViewModel viewModel = (FoodCategoryLinksViewModel)ViewData["foodCategoryLinksViewModel"];
    %>
    <ul class="breadcrumb">
        <%
       for (int i = 0; i < viewModel.Names.Count; ++i)
       {
           string actionName = (viewModel.IsCategories[i] == true) ? "Category" : "Food";
        %>
        <li>
            <%: Html.ActionLink(viewModel.Names[i], actionName, "FoodCategory", new {id = viewModel.Ids[i]}, "") %>
        </li>
        <% if (viewModel.IsCategories[viewModel.Names.Count - 1] == true)
           {
        %>
        <a href="#"></a>
        <%
               } %>
        <%}

   }
        %>
    </ul>
</div>
