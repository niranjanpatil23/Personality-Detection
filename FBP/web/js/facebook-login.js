/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





var width = screen.width / 2;
var height = screen.height / 2;
var left = (screen.width / 2) - (width / 2);
function loginFacebook() {
    window.open("http://www.facebook.com/dialog/oauth?client_id=100123217048446&redirect_uri=http://localhost:8080/FBP/facebookredirect.jsp&scope=email",
            "mywindow", "menubar=0,resizable=0,scrollbars=no,status=0,toolbar=0,\n\
        width=" + width + ",\n\
        height=" + height + ",\n\
        left=" + left + ",\n\
        top=120");
}
