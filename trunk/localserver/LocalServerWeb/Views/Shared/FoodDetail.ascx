<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace = "LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>


<div id = "food_detail">
    <% if (ViewData["foodDetailViewModel"] != null)
       {
           FoodDetailViewModel viewModel = (FoodDetailViewModel)ViewData["foodDetailViewModel"];
           %>
           <table width="100%">
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td rowspan="2">
                                        <img src="<%:Url.Content("~/"+viewModel.HinhAnh+"")%>"
                                        alt="food_image" />
                                </td>
                                <td style="padding-left:5px">
                                    <h3><%: viewModel.TenMonAn %></h3>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <% Html.RenderPartial("FoodRating", viewModel); %>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2>
                            <%: FoodCategoryString.Price %></h2>
                        <% for (int i = 0; i < viewModel.listDonGia.Count; ++i)
                           {
                        %>
                        <%: viewModel.listDonGia[i].ToString() %> VND
                        /
                        <%: viewModel.listTenDonViTinh[i] %>
                        <br />
                        <%
                           }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2><%: FoodCategoryString.Description %></h2>
                        <%: viewModel.MoTaMonAn %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2><%: FoodCategoryString.RelatedFood %></h2>
                        
                        <% Html.RenderPartial("FoodRelate", viewModel.listMonLienQuan); %>
                    </td>
                </tr>
           </table>
           
           <%
       }
        
         %>

</div>