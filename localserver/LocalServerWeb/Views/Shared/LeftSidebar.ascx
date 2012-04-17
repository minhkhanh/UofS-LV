<%@ Control Language="C#" ClassName="LeftSidebar" Inherits="System.Web.Mvc.ViewUserControl" %>

<div id="templatmeo_sidebar">
    	
        
    <div class="sidebar_section">
        <h2>Search Any Item</h2>
       	<div class="sidebar_section_content">
                
            <form action="#" method="get">
                <input type="text" value="Enter a keyword..." name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="Search" alt="Search" id="searchbutton" title="Search" />
            </form>    
                
            <div class="cleaner"></div>
        </div>
             
    </div>
        
    <div class="sidebar_section">
       		
        <h2>Categories</h2>
       	
       	<div class="sidebar_section_content">
                    
            <ul class="categories_list">
                <li><a href="#">Lorem ipsum dolor</a></li>
                <li><a href="#">Phasellus eget lorem</a></li>
                <li><a href="#">Sed sit amet sem</a></li>
                <li><a href="#">Cras eget est vel</a></li>
                <li><a href="#">Quisque in ligula</a></li>
                <li><a href="#">Donec a massa dui</a></li>
                <li><a href="#">Aenean facilisis</a></li>
                <li><a href="#">Etiam vitae consequat</a></li>
                <li><a href="#">Aliquam sollicitudin</a></li>
                <li><a href="#">Nunc fermentum</a></li>
                <li><a href="#">Quisque in ligula</a></li>
                <li><a href="#">Donec a massa dui</a></li>
                <li><a href="#">Aenean facilisis</a></li>
                <li><a href="#">Etiam vitae consequat</a></li>
                <li><a href="#">Aliquam sollicitudin</a></li>
                <li><a href="#">Nunc fermentum</a></li>
            </ul>
        </div>
             
    </div>

</div> <!-- end of templatemo_slider -->      
