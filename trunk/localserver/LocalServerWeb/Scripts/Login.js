function checkValidationLoginObj(objName) {
    var x = document.forms["frmDangNhap"][objName].value;
    if (x == null || x.length <= 4) {
        document.forms["frmDangNhap"][objName].className = 'imgCheckError';
    } else {
        document.forms["frmDangNhap"][objName].className = 'imgCheckOk';
    }
}
function checkValidationLogin() {
    var result = true;

    var x = document.forms["frmDangNhap"]["tenDangNhap"].value;
    if (x == null || x.length <= 4) {
        document.forms["frmDangNhap"]["tenDangNhap"].className = 'imgCheckError';
        result = false;
    } else {
        document.forms["frmDangNhap"]["tenDangNhap"].className = 'imgCheckOk';
    }

    var y = document.forms["frmDangNhap"]["matKhau"].value;
    if (y == null || y.length <= 4) {
        document.forms["frmDangNhap"]["matKhau"].className = 'imgCheckError';
        result = false;
    } else {
        document.forms["frmDangNhap"]["matKhau"].className = 'imgCheckOk';
    }
    if (result) {
        document.forms["frmDangNhap"]["matKhau"].value = MD5(document.forms["frmDangNhap"]["matKhau"].value);
    }
    return result;
}