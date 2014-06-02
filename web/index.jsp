<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>

<%@ page language="java" pageEncoding="UTF-8" import="com.ibcio.WebSocketMessageServlet"%>
<%
    //String _request = request.getParameter("AAA");
    //String user = new String(Base64.decodeBase64(_request));
    String user= (String) request.getSession().getAttribute("user");
    //String user = (String) request.getParameter("user");
    //System.out.println(request.getSession().getAttribute("user")));
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
        <script src="js/RTCPeerConnection-v1.5.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <script src="js/qrcode.js"></script>
        <link rel="stylesheet" type="text/css" href="ext4/shared/example.css" />
        <link rel="stylesheet" type="text/css" href="css/websocket.css" />
        <link rel="stylesheet" type="text/css" href="css/image.css" />
        <script src="js/RecordRTC.js"></script>
        <script>
            function speech(obj) {
                //var audio = new Audio();
                //console.log('http://translate.google.com/translate_tts?ie=utf-8&tl=en&q='+obj.value);
                //audio.src = 'http://translate.google.com/translate_tts?ie=utf-8&tl=en&q='+obj.value;
                //audio.play();
                var u = new SpeechSynthesisUtterance(obj.value);
                //u.lang = 'zh-HK';
                //u.voice = voices[44];
                speechSynthesis.speak(u);
            }
        </script>
        <!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
        <script type="text/javascript" src="ext4/ext-all-debug.js"></script>
        <script type="text/javascript" src="js/Notification.js"></script>
        <script>
            window.onload = function() {

                //document.querySelector('#body').onmousemove = findScreenCoords;
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
                        alert("Url incorrect");
                    }
                };

                var filterPanel = Ext.create('Ext.panel.Panel', {
                    bodyPadding: 5, // Don't want content to crunch against the borders
                    width: 300,
                    title: 'Emoji',
                    //items: icon,
                    autoScroll: true,
                    closable: true,
                    closeAction: 'hide',
                    height: 300,
                    draggable: 'true',
                    hidden: 'true',
                    id: 'emoji-main',
                    renderTo: Ext.getBody()
                });
                var icon = [];
                for (var i = 1; i <= 471; i++) {
                    var tmpBtn = Ext.create('Ext.Button', {
                        icon: 'emoji/' + i + '.png',
                        applyTo: 'filterPanel',
                        //hidden:'true',
                        id: i,
                        handler: function(e) {
                            var editor = Ext.getCmp('df');
                            var _id = e.getId();
                            //alert("id?=" + e.getId());
                            var _div = document.createElement('img');
                            _div.src = 'emoji/' + _id + '.png';
                            if (Ext.isIE) {
                                editor.insertAtCursor(_div.outerHTML);
                            } else {
                                var selection = editor.win.getSelection();
                                if (!selection.isCollapsed) {
                                    selection.deleteFromDocument();
                                }
                                if (selection.rangeCount > 0) {
                                    selection.getRangeAt(0).insertNode(_div);
                                } else {
                                    Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + _div.outerHTML);
                                }
                            }
                            //var _c = Ext.getCmp('emoji-main').hide();
                            ;
                        }
                    });
                    icon.push(tmpBtn);
                }
                filterPanel.add(icon);
            };
        </script>
        <script type="text/javascript">
                    var user = "${pageScope.user}";
                    var target = 'all';
                    var _at = false;
                    var websocket;
        </script>
        <script type="text/javascript" src="ext4/plusin/myEditor.js"></script>
        <script type="text/javascript" src="websocket.js"></script>

    </head>

    <body>
        <div id="websocket_button"></div>
        <div id="scope" style="position: absolute; bottom: 23px;right:920px" ></div>
    </body>
</html>
