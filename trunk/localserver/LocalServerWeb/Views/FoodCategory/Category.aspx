<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<PagedList<FoodGalleryItemViewModel>>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>
<%@ Import Namespace="LocalServerWeb.ViewModels"%>
<%@ Import Namespace="Webdiyer.WebControls.Mvc"%>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	<%:FoodCategoryString.Title%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
<link rel="stylesheet" href="../../Content/foodcategorycss/category.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorylinks.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorysidebar.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodgalleryitem.css" type="text/css" />
<link rel="stylesheet" href="../../Content/foodcategorycss/foodgallery.css" type="text/css" />
<script type="text/javascript" src="../../Scripts/jquery/jquery-1.4.1.min.js"></script>

<style type="text/css">    
    .pages { color:red;font-weight:bold; font-size:11px;}
    .pages  .item{padding: 1px 6px;font-size: 13px;} /*numeric pager items*/
    .pages .cpb {color:red;padding: 1px 6px;font-size: 13px;} /*current pager item*/
    .pages a { text-decoration:none;padding: 0 5px; border: 1px solid #ddd;
               margin:0 2px; color:#000;font-weight:normal;}
    .pages a:hover { background-color: #E61636; color:#fff;
                     border:1px solid #E61636; text-decoration:none;font-weight:normal;}
</style>

</asp:Content>


<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
<div = "category">
    <h2>Category</h2>

    <%Html.RenderPartial("AjaxCategory", Model); %>

</div>
</asp:Content>



