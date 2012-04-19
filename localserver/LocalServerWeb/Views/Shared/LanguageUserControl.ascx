<%@ Control Language="C#" ClassName="LanguageUserControl" Inherits="System.Web.Mvc.ViewUserControl" %>
<form name="selectLanguage" action="Account/LogOff" method="POST">
Ngôn ngữ: <%= Html.DropDownList("MyList", ViewData["MyListItems"] as SelectList, new { onchange = "submit();" })%>
</form>