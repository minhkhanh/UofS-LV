<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>

<div id = "food_gallery">
<% if (ViewData["foodGalleryItemViewModels"] != null)
   {
       int numCol = 4;
       int numRow = 10;
       List<FoodGalleryItemViewModel> viewModels = ViewData["foodGalleryItemViewModels"] as List<FoodGalleryItemViewModel>;
       if (viewModels.Count > 0)
       {
           %>
           <table>
           <%
           for (int i = 0; i < viewModels.Count; ++i)
           {
                if(i % numCol == 0)
                {
                    %>
                    <tr>   
                    <%
                }
                %>
                <td> <% Html.RenderPartial("FoodGalleryItem", viewModels[i]); %> </td>

                <% if (i % numCol == numCol-1 || i == viewModels.Count - 1)
                    {
                        %>
                        </tr>
                        <%
                    } %>
                
                <%
           }
           %>
               </table>
               <%      
       }       
       
   } %>


</div>