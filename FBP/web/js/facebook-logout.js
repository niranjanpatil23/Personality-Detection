/* global FB, PROJECT_PATH */

function fbLogout(){
    if(typeof FB.logout === 'function'){
        if (FB.getAuthResponse()) {
         FB.logout(function(response) { window.location.href = 'http://localhost:8080/FBP/index.jsp'; }); 
         return;
        }  
    };
  }