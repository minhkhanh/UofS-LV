<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>

<div id="nav-menu">
<!-- Start css3menu.com BODY section -->
<ul id="css3menu1" class="topmenu">
	<li class="topmenu"><a href="<%: Url.Action("Index", "AdminHome") %>" style="height:32px;line-height:32px;"><img src="../../Content/css3menu/home.png" alt="Home"/>Home</a></li>
	<li class="topmenu"><a href="" style="height:32px;line-height:32px;"><span><img src="../../Content/css3menu/news.png" alt="Area"/>Area</span></a>
	<ul>
		<li class="subfirst"><a href="<%: Url.Action("Index", "AdminArea") %>"><span><img src="../../Content/css3menu/1.png" alt="Area manager"/>Area manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminArea") %>"><img src="../../Content/css3menu/adduser.png" alt="Add area"/>Add area</a></li>
		</ul></li>
		<li><a href="<%: Url.Action("Index", "AdminTable") %>"><span><img src="../../Content/css3menu/2.png" alt="Table manager"/>Table manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminTable") %>"><img src="../../Content/css3menu/adduser.png" alt="Add table"/>Add table</a></li>
		</ul></li>
		<li class="sublast"><a href="<%: Url.Action("Index", "AdminLanguage") %>"><span><img src="../../Content/css3menu/3.png" alt="Language manager"/>Language manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminLanguage") %>"><img src="../../Content/css3menu/adduser.png" alt="Add language"/>Add language</a></li>
		</ul></li>
	</ul></li>
	<li class="topmenu"><a href="" style="height:32px;line-height:32px;"><span><img src="../../Content/css3menu/food_icon_32.png" alt="Food"/>Food</span></a>
	<ul>
		<li class="subfirst"><a href="<%: Url.Action("Index", "AdminFood") %>"><span><img src="../../Content/css3menu/1.png" alt="Food manager"/>Food manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminFood") %>"><img src="../../Content/css3menu/adduser.png" alt="Add food"/>Add food</a></li>
		</ul></li>
		<li><a href="<%: Url.Action("Index", "AdminCategory") %>"><span><img src="../../Content/css3menu/2.png" alt="Category manager"/>Category manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminCategory") %>"><img src="../../Content/css3menu/adduser.png" alt="Add category"/>Add category</a></li>
		</ul></li>
		<li><a href="<%: Url.Action("Index", "AdminSurcharge") %>"><span><img src="../../Content/css3menu/3.png" alt="Surcharge manager"/>Surcharge manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminSurcharge") %>"><img src="../../Content/css3menu/adduser.png" alt="Add surcharge"/>Add surcharge</a></li>
		</ul></li>
		<li><a href="<%: Url.Action("Index", "AdminUnit") %>"><span><img src="../../Content/css3menu/4.png" alt="Unit manager"/>Unit manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminUnit") %>"><img src="../../Content/css3menu/adduser.png" alt="Add unit"/>Add unit</a></li>
		</ul></li>
		<li class="sublast"><a href="<%: Url.Action("Index", "AdminProcessor") %>"><span><img src="../../Content/css3menu/5.png" alt="Processor manager"/>Processor manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminProcessor") %>"><img src="../../Content/css3menu/adduser.png" alt="Add processor"/>Add processor</a></li>
		</ul></li>
		<li class="sublast"><a href="<%: Url.Action("Index", "AdminPromotion") %>"><span><img src="../../Content/css3menu/6.png" alt="Promotion manager"/>Promotion manager</span></a>
		<ul>
			<li class="sublast"><a href="<%: Url.Action("Add", "AdminPromotion") %>"><img src="../../Content/css3menu/adduser.png" alt="Add promotion"/>Add promotion</a></li>
		</ul></li>
	</ul></li>
	<li class="topmenu"><a href="" style="height:32px;line-height:32px;"><span><img src="../../Content/css3menu/buy.png" alt="Order"/>Order</span></a>
	<ul>
		<li class="subfirst"><a href="<%: Url.Action("Index", "AdminOrder") %>"><img src="../../Content/css3menu/1.png" alt="Order manager"/>Order manager</a></li>
		<li class="sublast"><a href="<%: Url.Action("Index", "AdminInvoice") %>"><img src="../../Content/css3menu/2.png" alt="Invoice manager"/>Invoice manager</a></li>
	</ul></li>
	<li class="topmenu"><a href="" style="height:32px;line-height:32px;"><span><img src="../../Content/css3menu/report_32.png" alt="Report"/>Report</span></a>
	<ul>
		<li class="sublast"><a href="<%: Url.Action("Index", "AdminReport") %>"><img src="../../Content/css3menu/1.png" alt="View report"/>View report</a></li>
	</ul></li>
</ul>
<!-- End css3menu.com BODY section -->
</div>