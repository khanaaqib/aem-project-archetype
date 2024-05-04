
$(document).ready(function(){
        var form = document.getElementById('textToSpeechForm');
        var audioPlayer = document.getElementById('audioPlayer');
        form.addEventListener('submit', async function (event) {
            event.preventDefault();
             var text = $("#textInput").val().trim()
             var myJson = '{\r\n    "lang": "english_us",\r\n "voice": "female_1"\r\n}';
             var obj  = JSON.parse(myJson);
             var newuser = "text";
             obj[newuser] = text;
            const settings = {
                async: true,
                crossDomain: true,
                url: 'https://tts-tiktok.p.rapidapi.com/tts/v1/tts-tiktok-1?response=url',
                method: 'POST',
                headers: {
                    'content-type': 'application/json',
                    'X-RapidAPI-Key': '4ddf227a28mshf3d728c1b331629p138901jsnca8aa81bcbde',
                    'X-RapidAPI-Host': 'tts-tiktok.p.rapidapi.com'
                },
                data: JSON.stringify(obj)
            }

            $.ajax(settings).done(function (response) {
                audioPlayer.src = response.data.url;
                audioPlayer.play();
                audioPlayer.style.display='block';
                console.log(response);
            });

        });

});