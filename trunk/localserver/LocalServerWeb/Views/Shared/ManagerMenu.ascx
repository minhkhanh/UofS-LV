<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Menu" %>

<div id="nav-menu">
    <!-- Start css3menu.com BODY section -->
    <ul id="css3menu1" class="topmenu">
        <!-- Home------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="<%: Url.Action("Index", "Home") %>" style="height: 32px;
            line-height: 32px;">
            <img src="../../Content/css3menu/home.png" alt="Home" /><%: MenuString.Home %></a></li>
        <!-- Area ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/table.png" alt="Area" /><%: MenuString.Area %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminArea") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Area manager" /><%: MenuString.ManageArea %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminArea") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add area" /><%: MenuString.AddArea %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminTable") %>"><span>
                    <img src="../../Content/css3menu/2.png" alt="Table manager" /><%: MenuString.ManageTable %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminTable") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add table" /><%: MenuString.AddTable %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <!-- Food ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/food_icon_32.png" alt="Food" /><%: MenuString.Food %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminFood") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Food manager" /><%: MenuString.ManageFood %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminFood") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add food" /><%: MenuString.AddFood %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminCategory") %>"><span>
                    <img src="../../Content/css3menu/2.png" alt="Category manager" /><%: MenuString.ManageCategory %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminCategory") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add category" /><%: MenuString.AddCategory %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminUnit") %>"><span>
                    <img src="../../Content/css3menu/3.png" alt="Unit manager" /><%: MenuString.ManageUnit %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminUnit") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add unit" /><%: MenuString.AddUnit %></a></li>
                    </ul>
                </li>
                <li class="sublast"><a href="<%: Url.Action("Index", "AdminProcessor") %>"><span>
                    <img src="../../Content/css3menu/4.png" alt="Processor manager" /><%: MenuString.ManageProcessor %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminProcessor") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add processor" /><%: MenuString.AddProcessor %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <!-- Order Bill ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/buy.png" alt="Order" /><%: MenuString.Order %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminOrder") %>">
                    <img src="../../Content/css3menu/1.png" alt="Manage order" /><%: MenuString.ManageOrder %></a></li>
                <li class="sublast"><a href="<%: Url.Action("Index", "AdminBill") %>">
                    <img src="../../Content/css3menu/2.png" alt="Manage bill" /><%: MenuString.ManageBill %></a></li>
            </ul>
        </li>
        <!-- Promotion ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/promotion.png" alt="Promotion" /><%: MenuString.Promotion %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminPromotion") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Manage promotion" /><%: MenuString.ManagePromotion %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminPromotion") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add promotion" /><%: MenuString.AddPromotion %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminSurcharge") %>"><span>
                    <img src="../../Content/css3menu/2.png" alt="Manage surcharge" /><%: MenuString.ManageSurcharge %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminSurcharge") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add surcharge" /><%: MenuString.AddSurcharge %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminVoucher") %>"><span>
                    <img src="../../Content/css3menu/3.png" alt="Manage voucher" /><%: MenuString.ManageVoucher %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminVoucher") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add voucher" /><%: MenuString.AddVoucher %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <!-- Other ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/other.png" alt="Other" /><%: MenuString.Other %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminExchangeRate") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Manage exchange rate" /><%: MenuString.ManageExchangeRate %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminArea") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add Exchange rate" /><%: MenuString.AddExchangeRate %></a></li>
                    </ul>
                </li>
                <li><a href="<%: Url.Action("Index", "AdminTable") %>"><span>
                    <img src="../../Content/css3menu/2.png" alt="Manage language" /><%: MenuString.ManageLanguage %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminTable") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add language" /><%: MenuString.AddLanguage %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <!-- Restaurant ------------------------------------------------------------------------------------------------------------------>
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/restaurant.png" alt="Restaurant" /><%: MenuString.Restaurant %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminRestaurant") %>">
                    <img src="../../Content/css3menu/1.png" alt="Manage restaurant" /><%: MenuString.ManageRestaurant %></a></li>
                <li class="sublast"><a href="<%: Url.Action("Index", "AdminReport") %>">
                    <img src="../../Content/css3menu/2.png" alt="View report" /><%: MenuString.ViewReport %></a></li>
            </ul>
        </li>
        <!-- Account ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/register.png" alt="Account" /><%: MenuString.Account %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Edit", "AdminUser", new { id = ((TaiKhoan)Session["taiKhoan"]).MaTaiKhoan}) %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Change Info" /><%: MenuString.ChangeInfo %></span></a>
                </li>
                <li class="subfirst"><a href="<%: Url.Action("LogOff", "Account") %>"><span>
                    <img src="../../Content/css3menu/2.png" alt="Logout" /><%: MenuString.Logout %></span></a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- End css3menu.com BODY section -->
</div>
