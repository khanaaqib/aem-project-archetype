$(document).ready(function(){
        var form = document.getElementById('textToSpeechForm');
        var audioPlayer = document.getElementById('audioPlayer');
        var textValue = $("#text")
        form.addEventListener('submit', function (event) {
            event.preventDefault();
              $.ajax({
                  async: true,
                  url: "/bin/kds/texttospeech",
                  type: "POST",
                  crossDomain: true,
                  data:{
                    textValue:textValue,
                  },
                  success: function(result){
                    if(result==="user is already existt"){
                      alert("user is already exist");
                    } else{
                       var blob = new Blob([result], { type: "application/octetstream" });
                       var url = window.URL || window.webkitURL;
                       var objectURL = url.createObjectURL(blob);
                       audioPlayer.src = objectURL;
                       audioPlayer.style.display = 'block';
                       audioPlayer.play();
                       alert("New user is added");
                    }

                  },
                  error: function(error){
                   console.log("form failure");
                  }
              });

        });

});