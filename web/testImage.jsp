<!DOCTYPE html>
<html lang="en">
    <!-- Adapted to work with the getUserMedia API using code from http://wesbos.com/html5-video-face-detection-canvas-javascript/ -->
    <head>
        <meta charset="utf-8">
        <title>HTML5 Face Detection - JavaScript getUserMedia API and Groucho Marx glasses!</title>
        <style>
            body {
                font-family: sans-serif;
                font-size: 17px;
                line-height: 24px;
                color: #fff;
                width: 100%;
                height: 100%;
                margin: 0;
                text-align: center;
                background-color: #111;
            }

            #info {
                position: absolute;
                width: 100%;
                height: 30px;
                top: 50%;
                margin-top: -15px;
            }

            #output {
                width: auto;
                height: 100%;
                background: black;
                //-webkit-transform: scale(-1, 1);
            }
        </style>
    </head>
    <body>
        <p id="info">Please allow access to your camera!</p>
        <canvas id="output"></canvas>
        <script src="js/ccv.js"></script>
        <script src="js/face.js"></script>
        <p><a href="#" onclick="useMask()">Use Mask</a></p>
        <p><a href="#" onclick="snap()">Take a picture!</a></p>
        <canvas id="snapshot" style="display:none"></canvas>
        <div id="filmroll"></div>

        <script>

// requestAnimationFrame shim
            /* (function() {
             var i = 0,
             lastTime = 0,
             vendors = ['ms', 'moz', 'webkit', 'o'];
             
             while (i < vendors.length && !window.requestAnimationFrame) {
             window.requestAnimationFrame = window[vendors[i] + 'RequestAnimationFrame'];
             i++;
             }
             
             if (!window.requestAnimationFrame) {
             window.requestAnimationFrame = function(callback, element) {
             var currTime = new Date().getTime(),
             timeToCall = Math.max(0, 1000 / 60 - currTime + lastTime),
             id = setTimeout(function() {
             callback(currTime + timeToCall);
             }, timeToCall);
             
             lastTime = currTime + timeToCall;
             return id;
             };
             }
             }());*/
            var App = {
                start: function(stream) {
                    App.video.addEventListener('canplay', function() {
                        App.video.removeEventListener('canplay');
                        setTimeout(function() {
                            App.video.play();
                            App.canvas.style.display = 'inline';
                            App.info.style.display = 'none';
                            App.canvas.width = App.video.videoWidth;
                            App.canvas.height = App.video.videoHeight;
                            App.backCanvas.width = App.video.videoWidth / 4;
                            App.backCanvas.height = App.video.videoHeight / 4;
                            App.backContext = App.backCanvas.getContext('2d');

                            var w = 300 / 4 * 0.8,
                                    h = 270 / 4 * 0.8;

                            App.comp = [{
                                    x: (App.video.videoWidth / 4 - w) / 2,
                                    y: (App.video.videoHeight / 4 - h) / 2,
                                    width: w,
                                    height: h
                                }];

                            App.drawToCanvas();
                        }, 500);
                    }, true);

                    var domURL = window.URL || window.webkitURL;
                    App.video.src = domURL ? domURL.createObjectURL(stream) : stream;
                },
                denied: function() {
                    App.info.innerHTML = 'Camera access denied!<br>Please reload and try again.';
                },
                error: function(e) {
                    if (e) {
                        console.error(e);
                    }
                    App.info.innerHTML = 'Please go to about:flags in Google Chrome and enable the &quot;MediaStream&quot; flag.';
                },
                drawToCanvas: function() {
                    requestAnimationFrame(App.drawToCanvas);

                    var video = App.video,
                            ctx = App.context,
                            backCtx = App.backContext,
                            m = 4,
                            w = 4,
                            i,
                            comp;

                    ctx.drawImage(video, 0, 0, App.canvas.width, App.canvas.height);

                    backCtx.drawImage(video, 0, 0, App.backCanvas.width, App.backCanvas.height);

                    comp = ccv.detect_objects(App.ccv = App.ccv || {
                        canvas: App.backCanvas,
                        cascade: cascade,
                        interval: 2,
                        min_neighbors: 1
                    });

                    if (comp.length) {
                        App.comp = comp;
                    }

                    for (i = App.comp.length; i--; ) {
                        ctx.drawImage(App.glasses, (App.comp[i].x - w / 2) * m, (App.comp[i].y - w / 2) * m, (App.comp[i].width + w) * m, (App.comp[i].height + w) * m);
                        //ctx.drawImage(App.glasses, (App.comp[i].x - w / 2) * m, (App.comp[i].y - w / 2) * m, (App.comp[i].width + w) * m, (App.comp[i].height + w) * m);
                    }
                }
            };
            App.glasses = new Image();
            //App.glasses.src = 'glasses.png';

            App.init = function() {
                App.video = document.createElement('video');
                App.backCanvas = document.createElement('canvas');
                App.canvas = document.querySelector('#output');
                App.canvas.style.display = 'none';
                App.context = App.canvas.getContext('2d');
                App.info = document.querySelector('#info');

                navigator.getUserMedia_ = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

                try {
                    navigator.getUserMedia_({
                        video: true,
                        audio: false
                    }, App.start, App.denied);
                } catch (e) {
                    try {
                        navigator.getUserMedia_('video', App.start, App.denied);
                    } catch (e) {
                        App.error(e);
                    }
                }

                App.video.loop = App.video.muted = true;
                App.video.load();
            };

            App.init();

            function useMask() {
                if (App.glasses.src == '') {
                    App.glasses = new Image();
                    App.glasses.src = 'images/glasses.png';
                } else {
                    App.glasses = new Image();
                }
            }

            function snap() {
                live = document.getElementById("output");
                snapshot = document.getElementById("snapshot");
                filmroll = document.getElementById("filmroll");

                // Make the canvas the same size as the live video
                snapshot.width = live.clientWidth;
                snapshot.height = live.clientHeight;

                // Draw a frame of the live video onto the canvas
                c = snapshot.getContext("2d");
                c.drawImage(live, 0, 0, snapshot.width, snapshot.height);

                // Create an image element with the canvas image data
                img = document.createElement("img");
                img.src = snapshot.toDataURL("image/webp");
                img.style.padding = 5;
                img.width = snapshot.width /*/ 2*/;
                img.height = snapshot.height /*/ 2*/;
                console.log(img);
                console.log(img.src.split(",")[1]);
                //var file =img.src;
                var file = img.src.split(",")[1];
                //var _b=new Blob([file],{type : 'image/webp'});
                // Add the new image to the film roll
                filmroll.appendChild(img);
                //var fileName = Math.round(Math.random() * 99999999) + 99999999;
                var fileName=random_string(12);
                console.log("fileName?="+fileName);
                PostBlob(file, 'webp', fileName + '.webp');
            }
            function PostBlob(blob, fileType, fileName) {
                // FormData
                var formData = new FormData();
                formData.append(fileType + '-filename', fileName);
                formData.append(fileType + '-blob', blob);

                // POST the Blob
                xhr('upload.jsp', formData, function(jsonmsg) {
                    window.console.log(jsonmsg);
//                    var responseMsg = JSON.parse(jsonmsg);
//                    var mediaElement = document.createElement(fileType);
//                    var source = document.createElement('source');
//                    source.src = responseMsg.url;
//
//                    if (fileType == 'video')
//                        source.type = 'video/webm; codecs="vp8, vorbis"';
//                    if (fileType == 'audio')
//                        source.type = 'audio/ogg';
//                    mediaElement.appendChild(source);
//                    mediaElement.controls = true;
//                    document.querySelector("body").appendChild(mediaElement);
                }
                );
            }
            function xhr(url, data, callback) {
                var request = new XMLHttpRequest();
                request.onreadystatechange = function() {
                    if (request.readyState == 4 && request.status == 200) {
                        callback(request.responseText);
                    }
                };
                request.open('POST', url);
                request.send(data);
            }
            function random_string(size) {
                var str = "";
                for (var i = 0; i < size; i++) {
                    str += random_character();
                }
                return str;
            }
            function random_character() {
                var chars = "0123456789abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
                return chars.substr(Math.floor(Math.random() * 62), 1);
            }
        </script>
    </body>
</html>