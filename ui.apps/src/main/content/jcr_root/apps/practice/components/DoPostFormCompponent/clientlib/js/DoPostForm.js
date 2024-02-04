$(document).ready(function(){
  $("#post-submit-btn").click(function(){
       var firstNameVlaue = $("#fname").val();
       var lastNameValue = $("#lname").val();
       var emailAddress = $("#email").val();
       var contactInformation = $("#contactNo").val();
       $.ajax({
           url: "/bin/post/aemservlet", 
           type : 'POST',
           data :{
               firstName : firstNameVlaue,
               lastName : lastNameValue,
               email : emailAddress,
               contact : contactInformation
           },
           success: function(result){
                console.log("form submitted successfully");
           }, 
           error: function(error){
                 console.log("error in servlet");
           }
       });
  });
});