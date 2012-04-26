<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>

<div id = "food_category_sidebar" >
<% if (ViewData["foodCategorySidebarViewModel"] != null)
   {
       FoodCategorySidebarViewModel viewModel = (FoodCategorySidebarViewModel)ViewData["foodCategorySidebarViewModel"];
       %>
       <ul class="nice-menu">
       <li class="blue">
       <%: Html.ActionLink(viewModel.ParentName, "Category", "FoodCategory", new { id = viewModel.ParentId }, "")%>
       </li>
   
   <%
       for (int i = 0; i < viewModel.Names.Count; ++i)
       {
            %>
            <li class="orange" id="child">
            <%: Html.ActionLink(viewModel.Names[i], "Category", "FoodCategory", new { id = viewModel.Ids[i] }, "")%>
            </li>
            <%
       }
       
   }
     
     %>
     </ul>

</div>

