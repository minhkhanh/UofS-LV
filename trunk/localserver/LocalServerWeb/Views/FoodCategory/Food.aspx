<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<System.Int32>" %>

<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    <%:FoodCategoryString.FoodInformation%>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="HeadContent" runat="server">
    <link rel="stylesheet" href="../../Content/foodcategorycss/food.css" type="text/css" />
    <link rel="stylesheet" href="../../Content/foodcategorycss/fooddetail.css" type="text/css" />
    <link rel="stylesheet" href="../../Content/foodcategorycss/foodcategorylinks.css" type="text/css" />
    <link rel="stylesheet" href="../../Content/foodcategorycss/foodgalleryitem.css" type="text/css" />
    <link rel="stylesheet" href="../../Content/foodcategorycss/foodrelate.css" type="text/css" />
    <link rel="stylesheet" href="../../Content/foodcategorycss/jquery.rating.css" type="text/css" />

    <script language="javascript" type="text/javascript" src="<%:Url.Content("~/Scripts/foodcategory/slider.js") %>"></script>
    <script language="javascript" type="text/javascript" src="<%:Url.Content("~/Scripts/foodcategory/jquery.MetaData.js") %>"></script>
    <script language="javascript" type="text/javascript" src="<%:Url.Content("~/Scripts/foodcategory/jquery.rating.pack.js") %>"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div id="food">
        <table width="100%">
            <tr>
                <td>
                    <h1><%: FoodCategoryString.FoodInformation %></h1>
                </td>
            </tr>
            <tr>
                <td>
                    <% Html.RenderPartial("FoodCategoryLinks"); %>
                </td>
            </tr>
            <tr>
                <td>
                    <% Html.RenderPartial("FoodDetail"); %>
                </td>
            </tr>
        </table>
    </div>
</asp:Content>
