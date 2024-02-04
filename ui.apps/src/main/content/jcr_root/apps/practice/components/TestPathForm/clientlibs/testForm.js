$(document).ready(function(){
  $("button-btn").click(function(){
    $.ajax({
                // Our sample url to make request 
                url:
                    '/bin/Pathservelet/readydemopathservlet',
                // Type of Request
                type: "POST",
                 // Function to call when to
                // request is ok 
                success: function (data) {
                    if(data!=null){
                    let x = JSON.stringify(data);
                    console.log(x);
                    }
                },

                // Error handling 
                error: function (error) {
                    console.log(`Error ${error}`);
                }
            });

  });
});

