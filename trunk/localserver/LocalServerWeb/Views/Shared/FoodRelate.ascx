<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<List<FoodGalleryItemViewModel>>" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<div id="food_relate">
    <% if (Model.Count > 0)
       {
    %>
    <div id="slider">
        <img class="scrollButtons left" src="<%:Url.Content("~/Images/foodcategory/foodrelate/leftarrow.png")%>"
            alt="scroll_button_left" />
        <div style="overflow: hidden;" class="scroll">
            <div class="scrollContainer">
                <%
           int i = 1;
           foreach (FoodGalleryItemViewModel item in Model)
           {
                %>
                <div class="panel" id="panel_<%: i++.ToString() %>">
                    <div class="inside">
                        <% Html.RenderPartial("FoodGalleryItem", item); %>
                    </div>
                </div>
                <%

           }
                %>
            </div>
            <div id="left-shadow">
            </div>
            <div id="right-shadow">
            </div>
        </div>
        <img class="scrollButtons right" src="<%:Url.Content("~/Images/foodcategory/foodrelate/rightarrow.png")%>"
            alt="scroll_button_right" />
    </div>
    <%              
       }
       else
       {
    %>
    <div id="slider">
        <img class="scrollButtons left" src="<%:Url.Content("~/Images/foodcategory/foodrelate/leftarrow.png")%>"
            alt="scroll_button_left" />
        <div style="overflow: hidden;" class="scroll">
            <div class="scrollContainer">
                <div class="panel" id="panel1">
                </div>
            </div>
            <div id="left-shadow">
            </div>
            <div id="right-shadow">
            </div>
        </div>
        <img class="scrollButtons right" src="<%:Url.Content("~/Images/foodcategory/foodrelate/rightarrow.png")%>"
            alt="scroll_button_right" />
    </div>
    <%
       }
    %>
</div>
