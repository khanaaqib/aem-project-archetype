$(document).ready(function(){

  $("#aem-simple-form").click(function(event){

     event.preventDefault();

	 var firstname = $("#fname").val();

     var lastname =  $("#lname").val();

     var email = 	 $("#email").val();

     var contact =   $("#contact").val();

     var address =   $("#address").val();

        $.ajax({

            url: "/bin/servlet/SimpleAemServlet",

            type: 'POST',

            data:{

                firstname : firstname,

                lastname :  lastname,

                email:		email,

                contact:	contact,

                address:	address

            },

            success: function(result){

				alert("success");

				console.log("success");

  			},

            error: function(error) {

                alert("failure");

                console.log("failure");

            }

        });

  });

});