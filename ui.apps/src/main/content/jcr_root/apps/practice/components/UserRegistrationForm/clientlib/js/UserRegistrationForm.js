$(document).ready(function(){
  $("#user-registration-button").click(function(event){
      event.preventDefault();
      var firstNameValue =  $("#fname").val();
      var lastNameValue =  $("#lname").val();
      var emailAddress =  $("#email").val();
      var contactinformation =  $("#contactInfo").val();
      var Address =  $("#address").val();
      var zipCode =  $("#zipcode").val();
      var comment =  $("#comments").val();

      $.ajax({
          url: "/bin/user/userInfo/userRegistrationForm",
          type: "POST",
          data:{
          	firstName:firstNameValue,
            lastName : lastNameValue,
            email :emailAddress,
            contactNo : contactinformation,
            address : Address,
            zipcode: zipCode,
            Comment: comment

          },
          success: function(result){
            if(result==="user is already registered"){
              alert("user is already registered");
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