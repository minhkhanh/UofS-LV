<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>

<div id = "food_gallery">
<% if (ViewData["foodGalleryItemViewModels"] != null)
   {
       List<FoodGalleryItemViewModel> viewModels = ViewData["foodGalleryItemViewModels"] as List<FoodGalleryItemViewModel>;
       if (viewModels.Count > 0)
       {
            %>

            <%: Html.ActionLink(viewModels[0].TenMonAn, "Category", "FoodCategory", new { id = viewModels[0].MaMonAn }, "")%>
            <%
       }
       
       
   } %>


</div>