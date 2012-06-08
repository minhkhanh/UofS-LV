<%@ Page Language="C#" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Error" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title><%:ErrorString.Title %></title>
</head>
<body background="../../Images/error_page.jpg">
    <div align="center">
        <h2><%: TempData["error"] ?? "" %></h2>
        <h2><%: TempData["errorNotFound"] ?? "" %></h2>
    </div>
    
</body>
</html>
