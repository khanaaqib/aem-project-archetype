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
               lastName :lastName,
               Email: email,
               Address: address,
               user : 'aem-simple-form-Info'
           },
           success: function(result){
                if(result=="user is already exist"){
                    alert("user is already exist");
                } else{
                    alert("new user added");
                }

  		   },
           error: function(error){
               alert("failure");
           }
  });	
  });
});