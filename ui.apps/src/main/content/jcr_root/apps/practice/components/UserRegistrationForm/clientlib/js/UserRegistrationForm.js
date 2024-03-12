$(document).ready(function(){
  $("#user-registration-button").click(function(event){
      event.preventDefault();
      var firstNameValue =  $("#fname").val();
      var lastNameValue =  $("#lname").val();
      var emailAddress =  $("#email").val();

      $.ajax({
          url: "/bin/user/userForm",
          type: "POST",
          data:{
          	firstName:firstNameValue,
            lastName : lastNameValue,
            email :emailAddress
          },
          success: function(result){
            if(result==="user is already present"){
              alert("user is already present");
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