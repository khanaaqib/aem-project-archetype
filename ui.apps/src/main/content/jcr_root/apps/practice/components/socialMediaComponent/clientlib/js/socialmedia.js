$(document).ready(function() {
      // Twitter API credentials
      const apiKey = "GFkqWqI9U5NTwlkTedn5SnPUP";
      const apiSecretKey = "xdb9mrPlcDs8rAP0oIBt6x0o3dBz0P33wQEiriNaWGZWDSyol4";
      const accessToken = "1786808133999828992-ldzrQ79fQGbs6FcZxT8DZiLWCpp4YY";
      const accessTokenSecret = "9lPRTTPhoFpCS0BbINb2GfSR6tVBxboby3wBoRhgyUxlC";
      fetchRecentTweets(accessToken);
 });

 function fetchRecentTweets(accessToken) {
  $.ajax({
    url: 'https://api.twitter.com/1.1/statuses/user_timeline.json',
    type: 'GET',
    dataType: 'json',
    data: {
      screen_name: '1786809479880708096Aaqin278053', // Change to the desired Twitter username
      count: 5 // Number of recent tweets to fetch
    },
    beforeSend: function(xhr) {
      xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
    },
    success: function(data) {
      // Display fetched tweets
      displayTweets(data);
    },
    error: function(xhr, status, error) {
      console.error('Error fetching tweets:', error);
    }
  });

    function displayTweets(tweets) {
      var $tweetsContainer = $('#tweets');
      $tweetsContainer.empty(); // Clear previous tweets

      // Loop through fetched tweets and append them to the container
      $.each(tweets, function(index, tweet) {
        $tweetsContainer.append('<p>' + tweet.text + '</p>');
      });
    }
  }