<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Home" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%: HomeString.TitleHome %>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="HeadContent" runat="server">
    <script language="javascript" type="text/javascript" src="../../Scripts/mootools-1.2.1-core.js"></script>
    <script language="javascript" type="text/javascript" src="../../Scripts/mootools-1.2-more.js"></script>
    <script language="javascript" type="text/javascript" src="../../Scripts/slideitmoo-1.1.js"></script>
    <script language="javascript" type="text/javascript">
        window.addEvents({
            'domready': function () {
                /* thumbnails example , div containers */
                new SlideItMoo({
                    overallContainer: 'SlideItMoo_outer',
                    elementScrolled: 'SlideItMoo_inner',
                    thumbsContainer: 'SlideItMoo_items',
                    itemsVisible: 6,
                    elemsSlide: 2,
                    duration: 160,
                    itemsSelector: '.SlideItMoo_element',
                    itemWidth: 140,
                    showControls: 1
                });
            } 
        });
        function clearText(field) {
            if (field.defaultValue == field.value) field.value = '';
            else if (field.value == '') field.value = field.defaultValue;
        }
    </script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="LeftSidebar" runat="server">
    <% Html.RenderPartial("LeftSidebar"); %>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div id="templatemo_content">
    
    	<div class="content_section">
        
        	<h2>Welcome to the Shoe Store</h2>
            
            <p>Free CSS Templates are provided by <a href="http://www.templatemo.com" target="_parent">TemplateMo.com</a> for everyone. Feel free to download, edit and use this template for your websites. Credit goes to <a href="http://www.vectorvaco.com" target="_blank">Free Vectors</a> for vector graphics used in this layout.</p>
            
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et quam vitae ipsum vulputate varius vitae semper nunc. Quisque eget elit quis augue pharetra feugiat. <a href="#">Suspendisse</a> turpis arcu, dignissim ac laoreet a, <a href="#">condimentum</a> in massa.</p>
            
        </div>
        
        <div class="content_section">
        
        	<h2>New Products</h2>
                    
        	<div class="product_box margin_r40">
                
                <div class="image_wrapper"><a href="http://www.templatemo.com/page/1" target="_parent"><img src="../../Images/siteimages/templatemo_image_01.jpg" alt="product" /></a></div>
                <h3>Phasellus eget lorem</h3>
                <p>Nullam scelerisque odio ante, in facilisis mauris. Sed justo quam, sollicitudin vitae.</p>
                <p class="price">Price: $250</p>
                <a href="#">Detail</a> | <a href="#">Buy Now</a>
            
         	    </div>
                    
                    <div class="product_box">
                    
                <div class="image_wrapper"><a href="http://www.templatemo.com/page/2" target="_parent"><img src="../../Images/siteimages/templatemo_image_02.jpg" alt="product" /></a></div>
                <h3>Aenean magna felis</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur dictum ultricies purus.</p>
                <p class="price">Price: $350</p>
                <a href="#">Detail</a> | <a href="#">Buy Now</a>
                      
                    </div>
                    
                    <div class="cleaner"></div>
                    
                    <div class="product_box margin_r40">
                    
                <div class="image_wrapper"><a href="http://www.templatemo.com/page/3" target="_parent"><img src="../../Images/siteimages/templatemo_image_03.jpg" alt="product" /></a></div>
                <h3>Vestibulum eu lacus mauris</h3>
                <p>Donec a tortor erat. Ut arcu quam, congue non vulputate non, placerat vel nibh.</p>
                <p class="price">Price: $450</p>
                <a href="#">Detail</a> | <a href="#">Buy Now</a>
    
                    </div>
                    
                    <div class="product_box">
                    
                <div class="image_wrapper"><a href="http://www.templatemo.com/page/4" target="_parent"><img src="../../Images/siteimages/templatemo_image_04.jpg" alt="product" /></a></div>
                <h3>Phasellus vitae alique</h3>
                <p>Aliquam mollis, ligula id condimentum imperdiet, tellus eros elementum orci. </p>
                <p class="price">Price: $550</p>
                <a href="#">Detail</a> | <a href="#">Buy Now</a>
                      
                    </div>
                    
				<div class="cleaner"></div>
        
        </div>
    
    </div> <!-- end of templatemo_content -->
</asp:Content>
