<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>


<div id = "food_detail">
    <h2>Food Detail</h2>
    <% if (ViewData["foodDetailViewModel"] != null)
       {
           FoodDetailViewModel viewModel = (FoodDetailViewModel)ViewData["foodDetailViewModel"];
           %>
           <table width="100%">
                <tr>
                    <td>
                        <img src =" <%: viewModel.HinhAnh %> "  alt="food_image" />
                    </td>
                    <td>
                       <%: viewModel.TenMonAn %>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <%: viewModel.MoTaMonAn %>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <% Html.RenderPartial("FoodRelate", viewModel.listMonLienQuan); %>
                    </td>
                </tr>
           </table>


           
           <%
       }
        
         %>

</div>