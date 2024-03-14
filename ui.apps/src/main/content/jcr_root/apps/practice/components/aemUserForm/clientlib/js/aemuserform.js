$(document).ready(function(){
  $("#user-custom-form").click(function(event){
      event.preventDefault();
      var firstName = $("#fname").val();
      var lastName = $("#lname").val();
      var email = $("#email").val();
      var address = $("#address").val();
       $.ajax({
           url: "/bin/post/customServlet", 
           type: 'POST',
           data: {
               firstName : firstName,
               lastName : lastName,
               Email: email,
               Address: address,
               user : "aem-user-form"
           },
           success: function(result){
                alert("success");
  		   },
           error: function(error){
               alert("failure");
           }
  });	
  });
});