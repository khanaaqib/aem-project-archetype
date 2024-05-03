$(document).ready(function(){
        var form = document.getElementById('textToSpeechForm');
        var audioPlayer = document.getElementById('audioPlayer');
        form.addEventListener('submit', async function (event) {
            event.preventDefault();
             var text = $("#textInput").val().trim();
                     if (text) {
                         var xhr = new XMLHttpRequest();
                         xhr.open("POST", "/bin/ks/TextToSpeech", true); // Update the URL
                         xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                         xhr.responseType = "blob";

                         xhr.onload = function() {
                             if (xhr.status === 200) {
                                 var blob = new Blob([xhr.response], { type: "audio/mpeg" });
                                 var audioURL = URL.createObjectURL(blob);
                                 audioPlayer.src = audioURL;
                                 audioPlayer.play();
                             } else {
                                 console.error("Error:", xhr.statusText);
                             }
                         };

                         xhr.onerror = function() {
                             console.error("Request failed");
                         };

                         xhr.send("text=" + encodeURIComponent(text));
                     } else {
                         console.error("Text input is empty");
                     }

        });

});