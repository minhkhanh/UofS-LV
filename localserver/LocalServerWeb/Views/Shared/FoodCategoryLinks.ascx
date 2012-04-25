<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@Import Namespace = LocalServerWeb.ViewModels %>

<div id="food_category_link">
<% if (ViewData["foodCategoryLinksViewModel"] != null)
   {
       FoodCategoryLinksViewModel viewModel = (FoodCategoryLinksViewModel)ViewData["foodCategoryLinksViewModel"];
       for (int i = 0; i < viewModel.Names.Count; ++i)
       {
           string actionName = (viewModel.IsCategories[i] == true) ? "Category" : "Food";
           %>

           <%: Html.ActionLink(viewModel.Names[i], actionName, "FoodCategory", new {id = viewModel.Ids[i]}, "") %>
           <% if(i <viewModel.Names.Count -1)
                %>
                <%:Html.DisplayText(" / ") %>
     <%}
         
   }
     %>

</div>
