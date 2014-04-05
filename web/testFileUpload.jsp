<%-- 
    Document   : testFileUpload
    Created on : 2014年3月31日, 下午11:41:35
    Author     : AlphaWong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>window.onload = function() {
                function xhr(url, data, callback) {
                    var request = new XMLHttpRequest();
                    request.onreadystatechange = function() {
                        if (request.readyState == 4 && request.status == 200) {
                            document.querySelector("#fileName").value="";
                            document.querySelector("#file").value="";
                            callback(request.responseText);
                        }
                    };
                    request.open('POST', url);
                    request.send(data);
                }
                function PostZip(blob, fileType, fileName) {
                    // FormData
                    var formData = new FormData();
                    formData.append(fileType + '-filename', fileName);
                    formData.append(fileType + '-blob', blob);
                    // POST the Blob
                    xhr('upload.jsp', formData, function(jsonmsg) {
                        window.console.log(jsonmsg);
                        var responseMsg = JSON.parse(jsonmsg);
                        //var element = document.createElement(fileType);
                        var downloadIMG=document.createElement('img');
                        downloadIMG.setAttribute("src","images/zip_file_download.png");
                        var source = document.createElement('a');
                        source.href = responseMsg.url;
                        source.appendChild(downloadIMG);
                        document.querySelector("body").appendChild(document.createElement('br'));
                        document.querySelector("body").appendChild(source);
                        //document.querySelector("#file").value="";
                    }
                    );
                }
                document.querySelector("#checkFile").addEventListener('click', function() {
                    window.console.log(document.querySelector("#file").value);
                });
                document.querySelector("#file").addEventListener('change', function() {
                    
                     var filePath=document.querySelector("#file").files[0];
                     var fileName=document.querySelector("#fileName").value;
                     if(fileName==""){
                        alert("fileName cannot be null!!");
                        document.querySelector("#file").value="";
                        return;
                     }
                    PostZip(filePath,'zip',fileName+".zip");
                });

            };

        </script>
    </head>
    <body>
        File Name: <input type="text" id="fileName"/>
        <br>
        File: <input type="file" id="file" />
        <br>
        <button id="checkFile">Check internal file</button>
    </body>
</html>
