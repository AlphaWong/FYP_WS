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
         <script src="js/WebSpeech.js"></script>
        <title>JSP Page</title>
        <script>window.onload = function() {

                var btn = document.querySelector("#go");
                btn.addEventListener("click", function() {
                    var selObj = window.getSelection();
                    var text = selObj.toString();
                    window.console.log('Text?=' + text);
                    var u = new SpeechSynthesisUtterance(text);
                    //u.lang = 'zh-HK';
                    //u.voice = voices[44];
                    speechSynthesis.speak(u);
                });
            };
        </script>
    </head>
    <body>

        <div>疑被僱主虐待受傷的印傭Erwiana下午已由印尼飛抵本港, 協助警方調查 .
            Erwiana會住在印尼領事館 , 外傭工會指違反Erwiana的意願 . Erwiana在香港會接受身體檢查及為警方補錄口供.
            警方說已基於證人的安全, 妥善安排, 並提供有效的保護 .
            涉嫌虐打Erwiana的女僱主羅允彤, 被控傷人及恐嚇等七項罪名 , 會在本月二十九號再提堂.
            Erwiana去年在將軍澳一個家庭工作 , 今年一月返回印尼, 被發現身體多處受傷, 之後揭發女僱主涉嫌襲擊她 . 印尼的主診醫生說Erwiana頭部傷勢最嚴重, 腦組織腫脹, 估計是由硬物撞擊造成.</div>
        <div>
            In JavaScript, when an object is passed to a function expecting a string (like window.alert or document.write), the object's toString() method is called and the returned value is passed to the function. This can make the object appear to be a string when used with other functions when it is really an object with properties and methods.

            In the above example, selObj.toString() is automatically called when it is passed to window.alert. However, attempting to use a JavaScript String property or method such as length or substr directly on a Selection object will result in an error if it does not have that property or method and may return unexpected results if it does. To use a Selection object as a string, call its toString method directly:
        </div>
        <br><button id="go">Get</button>
        <!--        <br><div id="qrcode" style="width:100px; height:100px;"></div>-->

    </body>
</html>
