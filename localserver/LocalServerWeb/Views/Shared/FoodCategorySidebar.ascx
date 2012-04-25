﻿<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>

<div id = "food_category_sidebar" >
<% if (ViewData["foodCategorySidebarViewModel"] != null)
   {
       FoodCategorySidebarViewModel viewModel = (FoodCategorySidebarViewModel)ViewData["foodCategorySidebarViewModel"];
       %>
       <%: Html.ActionLink(viewModel.ParentName, "Category", "FoodCategory", viewModel.ParentId, "") %>
   
   <%
       for (int i = 0; i < viewModel.Names.Count; ++i)
       {
            %>
            <%: Html.ActionLink(viewModel.Names[i], "Category", "FoodCategory", viewModel.Ids[i], "") %>
            
            <%
       }
       
   }
     
     %>


</div>

