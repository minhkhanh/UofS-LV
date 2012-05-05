<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<PagedList<FoodGalleryItemViewModel>>" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc"%>


<div id = "food_gallery">
<% if (Model != null)
   {
       int numCol = 3;

       if (Model.Count > 0)
       {
           %>
           <table>
           <%
           for (int i = 0; i < Model.Count; ++i)
           {
                if(i % numCol == 0)
                {
                    %>
                    <tr>   
                    <%
                }
                %>
                <td> <% Html.RenderPartial("FoodGalleryItem", Model[i]); %> </td>

                <% if (i % numCol == numCol-1 || i == Model.Count - 1)
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