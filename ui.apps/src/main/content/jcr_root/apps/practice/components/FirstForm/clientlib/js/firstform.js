$(document).ready(function(){
  $("#submit-btn").click(function(){
    var firstname = $("#fname").val();
    var lastname = $("#lname").val();
    var email = $("#email").val();
      $.ajax({
          url:'/content/practice/us/en/test/jcr:content',
          type: 'POST',
          data: {
              fname : firstname,
              lname : lastname,
              email : email
          },
          success: function (data){
                   if(data != null || data != ''){
                       console.log(data);
                       alert("Form Submission Successfull");
                   }

          },
          error: function(error){
              console.log("error message");
          }

      });

  });
});