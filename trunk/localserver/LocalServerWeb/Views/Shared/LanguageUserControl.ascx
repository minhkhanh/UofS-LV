<%@ Control Language="C#" ClassName="LanguageUserControl" Inherits="System.Web.Mvc.ViewUserControl" %>
<form name="selectLanguage" action="/Language/ChangeLanguage" method="post">
Ngôn ngữ: <%= Html.DropDownList("maNgonNgu", ViewData["listNgonNgu"] as SelectList, new { onchange = "submit();" })%>
</form>