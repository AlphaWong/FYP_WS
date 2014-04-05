<%-- 
    Document   : testYoutubeVideo
    Created on : 2014年4月4日, 下午11:10:59
    Author     : AlphaWong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script src="js/qrcode.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>window.onload = function() {

                var btn = document.querySelector("#go");
                btn.addEventListener("click", function() {
                    var text = document.querySelector("#text").value;
                    window.console.log('Text?=' + text);
                    var div = document.createElement('div');
                    var qrcode = new QRCode(div, {
                        width: 100,
                        height: 100
                    });
                    function makeCode(_test) {
                        if (_test.length < 0) {
                            alert("Input a text");
                            //elText.focus();
                            return;
                        }
                        qrcode.makeCode(_test);
                    }
                    makeCode(text);
                    //document.querySelector("body").innerHTML += '<br>';
                    document.querySelector("body").appendChild(div);
                });
            };
        </script>
    </head>
    <body>
        <input type="text" id="text" placeholder="Insert Text Here"/>
        <br><button id="go">Get</button>
        <!--        <br><div id="qrcode" style="width:100px; height:100px;"></div>-->
        
    </body>
</html>
