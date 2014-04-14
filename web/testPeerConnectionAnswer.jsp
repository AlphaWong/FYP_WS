<%-- 
    Document   : testPeerConnection
    Created on : 2014年4月13日, 上午11:39:22
    Author     : AlphaWong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/RTCPeerConnection-v1.5.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            window.onload = function() {

                navigator.getMedia = (navigator.getUserMedia ||
                        navigator.webkitGetUserMedia ||
                        navigator.mozGetUserMedia ||
                        navigator.msGetUserMedia);
                var clientStream = "";
                navigator.getMedia({video: true, audio: true}, function(stream) {
                    clientStream = stream;
                }
                , function(err) {
                    console.log("The following error occured: " + err);
                });
//            var STUN = {
//                iceServers: [{
//                        url: !moz ? 'stun:stun.l.google.com:19302' : 'stun:stun.services.mozilla.com'
//                    }]
//            };
                var peer = RTCPeerConnection(
                        {iceServers: [{
                                    url: !moz ? 'stun:stun2.l.google.com:19302' : 'stun:23.21.150.121'
                                }],
                            //attachStream: clientStream,
                            onICE: function(candidate) {
                                //console.log(candidate);
                            },
                            onRemoteStream: function(remoteMediaStream) {
                                //console.log('here');
                                console.log(remoteMediaStream);
                                var remoteVideo = document.createElement('Video');
                                if (moz)
                                    remoteVideo.mozSrcObject = remoteMediaStream;
                                if (!moz)
                                    remoteVideo.src = window.URL.createObjectURL(remoteMediaStream);
                                document.querySelector('body').appendChild(remoteVideo);
                            },
                            onOfferSDP: function(sdp) {
//                            console.log('**********sdp*********************');
//                            console.log("sdp?=" + sdp);
//                            console.log('*******************************');
                            },
                            onAnswerSDP: function(answerSDP) {
//                            console.log('************answerSDP*******************');
//                            console.log(answerSDP);
//                            console.log('*******************************');
                            }
                        });
                var btn = document.querySelector("#go");
                btn.addEventListener("click", function() {
                    alert();
                });
            };
        </script>
        <h1>Hello World!</h1>
        <input id='sdp' type="text" placeholder="sdp"/>
        <br><button id="go">Get</button>
    </body>
</html>
