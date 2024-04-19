$(document).ready(function(){
  $("#user-registration-button").click(function(event){
      event.preventDefault();
      var firstNameValue =  $("#fname").val();
      var lastNameValue =  $("#lname").val();
      var emailAddress =  $("#email").val();

      $.ajax({
          url: "/bin/kds/TestServlet",
          type: "POST",
          data:{
          	firstName:firstNameValue,
            lastName : lastNameValue,
            Email :emailAddress,
          },
          success: function(result){
            if(result==="user is already existt"){
              alert("user is already exist");
            } else{
               alert("New user is added");
            }

          },
          error: function(error){
           console.log("form failure");
          }
      });
 
  });
});