$(document).ready(function(){
        var form = document.getElementById('textToSpeechForm');
        var audioPlayer = document.getElementById('audioPlayer');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            
            var text = form.querySelector('#textInput').value;

            fetch('/bin/texttospeech?text=' + encodeURIComponent(text))
                .then(function (response) {
                    return response.blob();
                })
                .then(function (blob) {
                    var objectURL = URL.createObjectURL(blob);
                    audioPlayer.src = objectURL;
                    audioPlayer.style.display = 'block';
                    audioPlayer.play();
                })
                .catch(function (error) {
                    console.error('Error fetching audio:', error);
                });
        });

});