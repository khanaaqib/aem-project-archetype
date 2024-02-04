$(document).ready(function(){
   		console.log("inside ready method");
		document.getElementById('submit-btn').addEventListener("click", function(){
        var firstName = $("#fname").val();
        var lastname = $("#lname").val();
        $.ajax({ 
                url:'/bin/Pathservelet/readydemopathservlet',
                type: "POST",
                data:{
                    fname : firstName,
                    lname: lastname
                },
                success: function (data) {
                    let x = JSON.stringify(data);
                    console.log(x);
                },

                // Error handling 
                error: function (error) {
                    console.log(`Error ${error}`);
                }
            });
	});
  });


