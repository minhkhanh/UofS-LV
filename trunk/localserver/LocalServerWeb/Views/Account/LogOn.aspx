<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LocalServerWeb.Models.LogOnModel>" %>

<asp:Content ID="loginTitle" ContentPlaceHolderID="TitleContent" runat="server">
    Đăng nhập
</asp:Content>

<asp:Content ID="head" ContentPlaceHolderID="HeadContent" runat="server">
    <script language="javascript" type="text/javascript" src="../../Scripts/MD5.js"></script>
    <script language="javascript" type="text/javascript" src="../../Scripts/Login.js"></script>
    <link href="../../Content/Login.css" type="text/css" rel="stylesheet"/>
</asp:Content>

<asp:Content ID="loginContent" ContentPlaceHolderID="MainContent" runat="server">
<%--    <h2>Đăng nhập</h2>
    <p>
        Xin hãy nhập vào tên đăng nhập và mật khẩu. <%: Html.ActionLink("Đăng ký", "Register")%> nếu không có tài khoản.
    </p>

    <% using (Html.BeginForm()) { %>
        <%: Html.ValidationSummary(true, "Đăng nhập thất bại. Xin hãy kiểm tra và thử lại.")%>
        <div>
            <fieldset>
                <legend>Thông tin tài khoản</legend>
                
                <div class="editor-label">
                    <%: Html.LabelFor(m => m.UserName) %>
                </div>
                <div class="editor-field">
                    <%: Html.TextBoxFor(m => m.UserName) %>
                    <%: Html.ValidationMessageFor(m => m.UserName) %>
                </div>
                
                <div class="editor-label">
                    <%: Html.LabelFor(m => m.Password) %>
                </div>
                <div class="editor-field">
                    <%: Html.PasswordFor(m => m.Password) %>
                    <%: Html.ValidationMessageFor(m => m.Password) %>
                </div>
                
                <div class="editor-label">
                    <%: Html.CheckBoxFor(m => m.RememberMe) %>
                    <%: Html.LabelFor(m => m.RememberMe) %>
                </div>
                
                <p>
                    <input type="submit" value="Đăng nhập" />
                </p>
            </fieldset>
        </div>
    <% } %>
--%>
    
    <fieldset>
        <legend>Thông tin đăng nhập</legend>
        <table width="500px" border="1" cellspacing="0" cellpadding="5" align="center">
	        <tr bgcolor="#666666">
		        <td class="bgColorMain"><strong><font color="#FFFFFF">ĐĂNG NHẬP</font></strong>
		        </td>
	        </tr>
	        <tr>
		        <td valign="top">
			        <form id="frmDangNhap" name="frmDangNhap" method="post" action="/Account/Logon"
				        onsubmit="return checkValidationLogin();">
                        <input type="hidden" name="returnUrl" value="<%: ViewData["returnUrl"] %>"/>
				        <table width="100%">
					        <tr>
						        <td colspan="2"><strong>Thông tin đăng nhập</strong>
						        </td>
					        </tr>
					        <tr>
						        <td><label for="tenDangNhap">Tên đăng nhập</label></td>
						        <td><input type="text" name="tenDangNhap" style="width: 100%" value="<%: ViewData["tenTaiKhoan"] %>"
							        onkeyup="checkValidationLoginObj('tenDangNhap')" /></td>
					        </tr>
					        <tr>
						        <td><label for="matKhau">Mật khẩu</label></td>
						        <td><input type="password" name="matKhau" style="width: 100%"
							        onkeyup="checkValidationLoginObj('matKhau')" /></td>
					        </tr>
					        <tr>
						        <td></td>
						        <td align="center"><input type="submit" name="dangNhap"
							        value="Đăng nhập" /></td>
					        </tr>
					        <% if (ViewData["loginFalse"] != null && (bool)ViewData["loginFalse"]==true)
                { %>
						        <tr>
							        <td colspan="2"><font color="#FF0000">Tên đăng nhập
									        không tồn tại hoặc mật khẩu không đúng</font></td>
						        </tr>
					        <% } %>
				        </table>
			        </form></td>
	        </tr>
        </table>
    </fieldset>
</asp:Content>
