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
        <!--        <script src="js/RTCPeerConnection-v1.5.js"></script>-->
        <title>JSP Page</title>
        <script>
            var isCaller = true;
            window.onload = function() {

                var call = document.querySelector("#call");
                var answer = document.querySelector("#answer");
                call.addEventListener("click", function() {
                    isCaller = true;
                });
                answer.addEventListener("click", function() {
                    isCaller = false;
                });
            }
        </script>
    </head>
    <body>
        <script>

            var socket;
            function initWebSocket() {
                if (window.WebSocket) {
                    var host = document.location.host;
                    socket = new WebSocket(encodeURI('ws://localhost:8080/WS/message'));
                    //                   socket = new WebSocket(encodeURI('ws://echo.websocket.org'));
                    socket.onopen = function() {
                        //连接成功
                        //win.setTitle(title + ' Send to : <span style="text-decoration:underline;">' + target + '</span> <span style="color: green;">(Connected)</span>');
                        console.log("open socket!");
                    }
                    socket.onerror = function(error) {
                        //连接失败
                        //win.setTitle(title + '&nbsp;&nbsp;(Error)');
                        console.log("Error" + error);
                    }
                    socket.onclose = function() {
                        //连接断开
                        console.log("close socket!");
                    }
                    //消息接收
                    socket.onmessage = function(message) {
                        var message = JSON.parse(message.data);
                        //接收用户发送的消息
                        if (message.type == 'videoOffer') {
                            console.log(peer);
                            console.log(message.content);
                        }
                    }
                }
                ;
            }
            initWebSocket();
            var PeerConnection = (window.PeerConnection ||
                    window.webkitPeerConnection00 ||
                    window.webkitRTCPeerConnection ||
                    window.mozRTCPeerConnection);
            var getUserMedia = (navigator.getUserMedia ||
                    navigator.webkitGetUserMedia ||
                    navigator.mozGetUserMedia ||
                    navigator.msGetUserMedia);
            var iceServer = {
                "iceServers": [{
                        "url": "stun:stun.l.google.com:19302"
                    }]
            };
            var pc = new PeerConnection(iceServer);
            pc.onicecandidate = function(event) {
                socket.send(JSON.stringify({
                    "event": "__ice_candidate",
                    "data": {
                        "candidate": event.candidate
                    }
                }));
            };
            pc.onaddstream = function(event) {
                var remote = document.querySelector("#remote");
                remote.src = URL.createObjectURL(event.stream);
            };
            getUserMedia.call(navigator, {
                "audio": true,
                "video": true
            }, function(stream) {
                //发送offer和answer的函数，发送本地session描述
                var sendOfferFn = function(desc) {
                    pc.setLocalDescription(desc);
                    socket.send(JSON.stringify({
                        "event": "__offer",
                        "data": {
                            "sdp": desc
                        },
                        to: 'all'
                    }));
                };
                var sendAnswerFn = function(desc) {
                    pc.setLocalDescription(desc);
                    socket.send(JSON.stringify({
                        "event": "__answer",
                        "data": {
                            "sdp": desc
                        }, 
                        to: 'all'
                    }));
                };
                var local = document.querySelector("#local");
                //绑定本地媒体流到video标签用于输出
                local.src = URL.createObjectURL(stream);
                //向PeerConnection中加入需要发送的流
                pc.addStream(stream);
                //如果是发送方则发送一个offer信令，否则发送一个answer信令
                if (isCaller) {
                    pc.createOffer(sendOfferFn);
                } else {
                    pc.createAnswer(sendAnswerFn);
                }
            }, function(error) {
                console.error(error);
                //处理媒体流创建失败错误
            });
            socket.onmessage = function(event) {
                var json = JSON.parse(event.data);
                //如果是一个ICE的候选，则将其加入到PeerConnection中，否则设定对方的session描述为传递过来的描述
                if (json.event === "__ice_candidate") {
                    pc.addIceCandidate(new RTCIceCandidate(json.data.candidate));
                } else {
                    pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));
                }
            };
        </script>
        <h1>Hello World!</h1>
        <button id="call">Call</button>
        <button id="answer">Answer</button>
        local<br>
        <video id="local" autoplay="" ></video><br>
        remote<br>
        <video id="remote" autoplay="" ></video>
    </body>
</html>
