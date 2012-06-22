<%@ Control Language="C#" ClassName="LatestSlider" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>

<div id="latest_product_slider">
    
        <div id="SlideItMoo_outer">	
            <div id="SlideItMoo_inner">			
                <div id="SlideItMoo_items">
                    <ul id="mycarousel" class="jcarousel-skin-tango">
                    <% if (ViewData["randomFoods"]!=null) for (int i = 0; i < ((List<FoodGalleryItemViewModel>)ViewData["randomFoods"]).Count; i++)
                       {
                           var food = ((List<FoodGalleryItemViewModel>) ViewData["randomFoods"])[i];
                       %>
                           <li class="SlideItMoo_element"><a href="/FoodCategory/Food/<%: food.MaMonAn %>">
                           <img src="<%:SharedCode.GetHostApplicationAddress(Request) + food.HinhAnh %>" alt="<%:food.TenMonAn %>" />
                           </a></li>                           
                    <% } %>
                    </ul>
                </div>			
            </div>
        </div>
    	
    </div> <!-- end of latest_product_slider -->
    
<script type="text/javascript">
    function mycarousel_initCallback(carousel) {
        // Disable autoscrolling if the user clicks the prev or next button.
        carousel.buttonNext.bind('click', function () {
            carousel.startAuto(0);
        });

        carousel.buttonPrev.bind('click', function () {
            carousel.startAuto(0);
        });

        // Pause autoscrolling if the user moves with the cursor over the clip.
        carousel.clip.hover(function () {
            carousel.stopAuto();
        }, function () {
            carousel.startAuto();
        });
    };

    $(document).ready(function () {
        $('#mycarousel').jcarousel({
            auto: 2,
            wrap: 'last',
            initCallback: mycarousel_initCallback
        });
    });    
</script>