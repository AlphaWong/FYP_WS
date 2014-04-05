<%@ page language="java" pageEncoding="UTF-8" import="com.ibcio.WebSocketMessageServlet"%>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        //为用户生成昵称
        user = "Guest" + WebSocketMessageServlet.ONLINE_USER_COUNT;
        WebSocketMessageServlet.ONLINE_USER_COUNT++;
        session.setAttribute("user", user);
    }
    pageContext.setAttribute("user", user);
%>
<html>
    <head>
        <title>WebSocket </title>
        <!-- 引入CSS文件 -->
        <link rel="stylesheet" type="text/css" href="ext4/resources/css/ext-all-gray.css">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <script src="js/qrcode.js"></script>
        <link rel="stylesheet" type="text/css" href="ext4/shared/example.css" />
        <link rel="stylesheet" type="text/css" href="css/websocket.css" />
        <script src="js/RecordRTC.js"></script>
        <!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
        <script type="text/javascript" src="ext4/ext-all-debug.js"></script>

        <script>
            window.onload = function() {
                navigator.getMedia = (navigator.getUserMedia ||
                        navigator.webkitGetUserMedia ||
                        navigator.mozGetUserMedia ||
                        navigator.msGetUserMedia);
                navigator.getMedia({audio: true}, function(mediaStream) {
                    recordRTC = new RecordRTC(mediaStream);
                }, function(e) {
                    console.log('Reeeejected!', e);
                });
                //looks like the property is already set, so lets just add 1 to that number and alert the user
                xhr = function(url, data, callback) {
                    var request = new XMLHttpRequest();
                    request.onreadystatechange = function() {
                        if (request.readyState == 4 && request.status == 200) {
                            callback(request.responseText);
                        }
                    };
                    request.open('POST', url);
                    request.send(data);
                };
                xhr2 = function(url, callback) {
                    var request = new XMLHttpRequest();
                    request.onreadystatechange = function() {
                        if (request.readyState == 4 && request.status == 200) {
                            //document.querySelector("#videoUrl").value = "";
                            //document.querySelector("#file").value = "";
                            callback(request.responseText);
                        } else {
                            console.log("Error?=", request.statusText);
                        }
                    };
                    request.open('GET', url);
                    request.send(/*data*/);
                };
                youtube_parser = function(url) {
                    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
                    var match = url.match(regExp);
                    if (match && match[7].length == 11) {
                        return match[7];
                    } else {
                        alert("Url incorrecta");
                    }
                };
            };
        </script>
        <script type="text/javascript" src="ext4/plusin/myEditor.js"></script>
        <script type="text/javascript" src="websocket.js"></script>
        <script type="text/javascript">
            var user = "${user}";
            var target = 'all';
        </script>

    </head>

    <body>
        <!--	<h1>WebSocket聊天室</h1>
                <p>通过HTML5标准提供的API与Ext富客户端框架相结合起来，实现聊天室，有以下特点：</p>
                <ul class="feature-list" style="padding-left: 10px;">
                        <li>实时获取数据，由服务器推送，实现即时通讯</li>
                        <li>利用WebSocket完成数据通讯，区别于轮询，长连接等技术，节省服务器资源</li>
                        <li>结合Ext进行页面展示</li>
                        <li>用户上线下线通知</li>
                </ul>-->
        <div id="websocket_button"></div>
        <div id="scope" style="position: absolute; bottom: 23px" ></div>
    </body>
</html>
