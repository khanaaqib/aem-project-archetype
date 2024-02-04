$(document).ready(function(){
   		console.log("inside ready method");
		document.getElementById('submit-btn').addEventListener("click", function(){
        var firstName = $("#fname").val();
        var lastname = $("#lname").val();
        var regex = /[0-9]/
         if(firstName==null || firstName=="" || regex.test(firstName)){
             $("#firstNameerror").show();
         }
        $.ajax({ 
                url:'/bin/Pathservelet/readydemopathservlet',
                type: "POST",
                data:{
                    fname : firstName,
                    lname: lastname
                },
                success: function (data) {
                    if(data!=null){
                        console.log(data);
                    }
                },

                // Error handling 
                error: function (error) {
                    console.log(`Error ${error}`);
                }
            });
	});

  });