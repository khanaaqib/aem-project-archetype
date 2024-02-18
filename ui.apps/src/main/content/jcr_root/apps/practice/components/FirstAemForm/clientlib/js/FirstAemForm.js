$(document).ready(function(){
  $("#aem-form-button").click(function(event){
     event.preventDefault();
     var firstNameValue = $("#fname").val(); // var firstName = document.getElementById("fname").value;
     var lastNameValue = $("#lname").val();


      $.ajax({
          url: "/bin/custom/customPostServlet",
          type: "POST",
          data:{
              firstname :firstNameValue,
              lastname: lastNameValue
          },
          success: function(result){
             console.log("success");
         }});

  });
});