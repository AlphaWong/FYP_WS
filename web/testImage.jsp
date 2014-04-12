<html>
    <head>
        <title>HTML5 Photo Booth</title>
        <style>
            #live{
                width: 852px;
                height: 480px;
            };
        </style>
    </head>
    <body>
        <h2>HTML5 Photo Booth</h2>

        <video id="live" autoplay></video>
        <canvas id="snapshot" style="display:none"></canvas>

        <p><a href="#" onclick="snap()">Take a picture!</a></p>
        <div id="filmroll"></div>

        <script type="text/javascript">
            video = document.getElementById("live");
            navigator.getUserMedia = (navigator.getUserMedia ||
                    navigator.webkitGetUserMedia ||
                    navigator.mozGetUserMedia ||
                    navigator.msGetUserMedia);
            navigator.getUserMedia({video: {
                    mandatory: {
                        minWidth: 1280,
                        minHeight: 720
                    }
                }},
            function(stream) {
                video.src = window.webkitURL.createObjectURL(stream);
            },
                    function(err) {
                        console.log("Unable to get video stream!");
                    }
            )

            function snap() {
                live = document.getElementById("live");
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

                // Add the new image to the film roll
                filmroll.appendChild(img);
            }
        </script>
    </body>
</html>