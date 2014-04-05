/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var host = document.location.host;
var wsUri = "ws://" + host + "/WS/message";
var user = "Abc";
var websocket;
//初始话WebSocket
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//var user = document.getElementById("userName").value;

var host = document.location.host;

var state;
var websocket;
function init() {
    state = document.getElementById("state");
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) {
        showStateOutput('<span class="notification">CONNECTED</span>');
    };
    websocket.onclose = function(evt) {
        showStateOutput('<span class="notification">DISCONNECTED</span>');
    };
    websocket.onmessage = function(evt) {
        var message = JSON.parse(evt.data);
        showOutput(message.user, message.msg, '<span style="font-style: italic;color: lightblue;">' + message.datetime + '</span>');
    };
    websocket.onerror = function(evt) {
        showOutput('<span class="notification">ERROR:</span> ' + evt.data);
    };

}

function showStateOutput(message) {
    var pre = document.createElement("div");
    pre.innerHTML = message;
    state.appendChild(pre);
    state.scrollTop = output.scrollHeight;
}

function showOutput(user, message, datatime) {
    var pre = document.createElement("div");
    pre.id = 'text';
    var finalout = "";
    if (user === 'system') {
        for (var i = 0; i < message.length; i++) {
            finalout += message.charAt(i);
        }

        pre.innerHTML = '<span style="color: blue;">' + user + ':</span> ' + "<br/>" + '<span id="system">' + finalout + '</span>' + "<br/>" + datatime;
    }
    else {
        for (var ii = 1; ii <= 5; ii++) {
            var iconSrc = "img/" + ii + ".gif";
            var tmpStrIcon = "<img src=" + iconSrc + " />";
            message = message.replace(new RegExp('&:' + ii + ':&', "gm"), tmpStrIcon);
        }

        for (var i = 0; i < message.length; i++) {
            finalout += message.charAt(i);
            if (i % 90 === 0 && i > 0) {
                //finalout += "<br/>";
            }
            pre.style.textAlign = "left";
            var userout = "client " + getCookie("userID") + " ";
            if (user !== userout) {
                pre.style.textAlign = "right";
                pre.innerHTML = '<br><span id="other-content">' + finalout + '</span>' + "" + '<span id="userName" >' + user + '</span>' + "" + "<br/><br/>" + datatime;
            }
            else {
                pre.innerHTML = '<br><span id="userName" >' + user + '</span>' + "" + '<span id="content">' + finalout + '</span>' + "<br/><br/>" + datatime;
            }
        }
    }
    output.appendChild(pre);
    output.scrollTop = output.scrollHeight;


}
window.addEventListener("load", init, false);

function sendMessage() {
    var message = document.getElementById('msg').value;
    if (message != '') {
        websocket.send(message);
        document.getElementById('msg').value = '';
    }
}