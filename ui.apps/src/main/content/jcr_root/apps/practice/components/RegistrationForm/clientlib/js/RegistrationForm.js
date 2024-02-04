$(document).ready(function(){
  $("#submit-btn").click(function(event){
     event.preventDefault();
     var firstName = $("#fname").val();
     var lastName = $("#lname").val();
     var emailAddr = $("#email").val();
     var phoneNumber = $("#phoneNumber").val();
     var Address = $("#address").val();
     var zipcodeValue = $("#zipCode").val();

      $.ajax({
              url: "/bin/form/registrationForm",
              type: "POST",
              data: {
                  fname: firstName,
                  lname : lastName,
                  email : emailAddr,
                  phoneNumber : phoneNumber,
                  address: Address,
                  zipCode: zipcodeValue

               },
               success: function(result){
                   var data = JSON.parse(result);
                   var message = data.message;
                   if(message===false){
                       alert("user is already exist");
                   }else {
                       document.getElementById('id01').style.display='block'
                   }
  	           },
          	   error: function(error){
                  alert("form failure");
               }
      });
  });
});