<%-- 
    Document   : testYoutubeVideo
    Created on : 2014年4月4日, 下午11:10:59
    Author     : AlphaWong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>window.onload = function() {
                function youtube_parser(url) {
                    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
                    var match = url.match(regExp);
                    if (match && match[7].length == 11) {
                        return match[7];
                    } else {
                        alert("Url incorrecta");
                    }
                }
                var videoId = "";
                var sendUrl = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2Cplayer&id=" + videoId + "&key=AIzaSyCOMiECTO_u-0gmQcxUgDmRcrRZWtw4E7w";
                var btn = document.querySelector("#go");
                btn.addEventListener("click", function() {
                    var vvid = document.querySelector("#videoUrl").value;
                    var _vid=youtube_parser(vvid);
                    getYoutubeVidoDetail(_vid);
                });
                function xhr(url, callback) {
                    var request = new XMLHttpRequest();
                    request.onreadystatechange = function() {
                        if (request.readyState == 4 && request.status == 200) {
                            document.querySelector("#videoUrl").value = "";
                            //document.querySelector("#file").value = "";
                            callback(request.responseText);
                        }
                    };
                    request.open('GET', url);
                    request.send(/*data*/);
                }
                function getYoutubeVidoDetail(vid) {
                    // FormData
                    //var formData = new FormData();
                    var stringRequest = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cplayer&id=" + vid + "&key=AIzaSyCOMiECTO_u-0gmQcxUgDmRcrRZWtw4E7w";
                    //window.console.log(stringRequest);
                    // POST the Blob
                    xhr(stringRequest, function(jsonmsg) {
                        //window.console.log(jsonmsg);
                        var responseMsg = JSON.parse(jsonmsg);
                        window.console.log('titile=' + responseMsg.items[0].snippet.title);
                        window.console.log('Image=' + responseMsg.items[0].snippet.thumbnails.default.url);
                        window.console.log('Duration=' + responseMsg.items[0].contentDetails.duration.replace('PT', "").replace('H', " Hours, ").replace('M', " Minutes, ").replace('S', " Second"));
                        window.console.log('html=' + responseMsg.items[0].player.embedHtml);
                        responseMsg.items[0].player.embedHtml.replace("width='640'","width='640'");
                        document.querySelector("body").innerHTML += responseMsg.items[0].player.embedHtml;
                    });
                }
            };
        </script>
    </head>
    <body>
        <input type="text" id="videoUrl" placeholder="Enter the video url"/>
        <button id="go">Get</button>
    </body>
</html>
