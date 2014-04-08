Ext.define('Ext.ux.form.MyEditor', {
    alias: 'widget.myeditor',
    extend: 'Ext.form.field.HtmlEditor',
    requires: ['Ext.form.field.HtmlEditor'],
    createToolbar: function() {
        var me = this;
        me.callParent(arguments);
        me.toolbar.insert(17, {
            xtype: 'button',
            icon: 'ext4/resources/images/default/editor/edit-image.png',
            handler: this.showImgUploader,
            scope: this
        });
        me.toolbar.insert(17, {
            xtype: 'button',
            icon: 'ext4/resources/images/default/editor/smile.png',
            handler: this.showSmilePanel,
            scope: this
        });
        me.toolbar.insert(17, {
            xtype: 'button',
            icon: 'ext4/resources/images/default/editor/mic.png',
            handler: this.inputMicResult,
            scope: this
        });
        me.toolbar.insert(21, {
            xtype: 'button',
            icon: 'images/assistive-listening.gif',
            handler: function() {
                var selObj = Ext.getCmp('_view').getSelectedNodes()[0].children[1].firstElementChild;//window.getSelection();
//                window.console.log(selObj);
                var text = selObj.innerHTML.toString();
                window.console.log(selObj);
                var u = new SpeechSynthesisUtterance(text);
                //u.lang = 'zh-HK';
                //u.voice = voices[44];
                speechSynthesis.speak(u);
            },
            scope: this
        });
        me.toolbar.insert(17, {
            xtype: 'button',
            enableToggle: true,
            id: 'rrr',
            icon: 'ext4/resources/images/default/editor/rec.png',
            handler: function() {
                var editor = me;
                var _rec = Ext.getCmp('rrr');
                //alert('pressed?='+_rec.pressed);
                if (typeof recordRTC == 'undefined') {
                    Ext.Msg.alert('Premission Dedny', "You must allow the Microphone sharing");
                    _rec.toggle();
                    return;
                }
                if (this.clickCount) {
                    //looks like the property is already set, so lets just add 1 to that number and alert the user
                    this.clickCount = false;
                    alert("stop");
                    recordRTC.stopRecording(function(audioURL) {
                        var fileName = Math.round(Math.random() * 99999999) + 99999999;
                        PostBlob(recordRTC.getBlob(), 'audio', fileName + '.ogg');
                    });
                    function PostBlob(blob, fileType, fileName) {
                        // FormData
                        var formData = new FormData();
                        formData.append(fileType + '-filename', fileName);
                        formData.append(fileType + '-blob', blob);

                        // POST the Blob
                        xhr('upload.jsp', formData, function(jsonmsg) {
                            window.console.log(jsonmsg);
                            var responseMsg = JSON.parse(jsonmsg);
                            var mediaElement = document.createElement(fileType);
                            var source = document.createElement('source');
                            source.src = responseMsg.url;

                            if (fileType == 'video')
                                source.type = 'video/webm; codecs="vp8, vorbis"';
                            if (fileType == 'audio')
                                source.type = 'audio/ogg';
                            mediaElement.appendChild(source);
                            mediaElement.controls = true;
                            //document.querySelector("body").appendChild(mediaElement);
                            if (Ext.isIE) {
                                editor.insertAtCursor(mediaElement.outerHTML);
                            } else {
                                var selection = editor.win.getSelection();
                                if (!selection.isCollapsed) {
                                    selection.deleteFromDocument();
                                }
                                /*if (selection.rangeCount > 0) {
                                 selection.getRangeAt(0).insertNode(mediaElement);
                                 }*/
                                else {
                                    Ext.getCmp('df').setValue(/*Ext.getCmp('df').getValue() + */mediaElement.outerHTML);
                                    //window.console.log(Ext.getCmp('df'));
                                    //Ext.getCmp('df').addChildEls(mediaElement);
                                    //while(mediaElement=null)
                                    //Ext.getCmp('sd').fireEvent('click');
                                }
                                var sendbtn = Ext.getCmp('sd');
                                sendbtn.getEl().dom.click();//send();
                            }
                        }
                        );
                    }
                } else {
                    //if the clickCount property is not set, we will set it and alert the user
                    this.clickCount = true;
                    //alert("start");
                    //window.console.log(recordRTC);//Missing 
                    Ext.getCmp('df').setValue('<h1>Recording</h1>');
                    recordRTC.startRecording();
                }
            },
            scope: this
        });
        me.toolbar.insert(15, {
            xtype: 'button',
            icon: 'images/zip.png',
            handler: this.showZipUploader,
            scope: this
        });
        me.toolbar.insert(16, {
            xtype: 'button',
            icon: 'images/youtube.png',
            handler: this.showYoutube,
            scope: this
        });
        me.toolbar.insert(20, {
            xtype: 'button',
            icon: 'images/icon-dcQRcode-small.png',
            handler: this.showQRcode,
            scope: this
        });

        return me.toolbar;

    },
    inputMicResult: function() {
        var currStr = Ext.getCmp('df').getValue();
        //var editor = this;
        var recognition = new webkitSpeechRecognition();
        recognition.lang = "zh-hk";
        recognition.onstart = function() {
            Ext.getCmp('df').setValue("<img src='images/mic-animate.gif' alt='Processing' >");
        };
        recognition.onresult = function(event) {
            Ext.getCmp('df').setValue("");
            var speechStr = "";
            for (var i = event.resultIndex; i < event.results.length; ++i) {
                if (event.results[i].isFinal) {
                    speechStr += event.results[i][0].transcript;
                } else {
                    // Outputting the interim result to the text field and adding
                    // an interim result marker - 0-length space
                    speechStr += event.results[i][0].transcript;
                }
            }
            var element = document.createElement('div');
            element.text = speechStr;
            /*if (Ext.isIE) {
             editor.insertAtCursor(element.outerHTML);
             } else {
             var selection = editor.win.getSelection();
             if (!selection.isCollapsed) {
             selection.deleteFromDocument();
             }
             selection.getRangeAt(0).insertNode(element);
             }*/
            Ext.getCmp('df').setValue(currStr + " " + speechStr);
            //console.log(speechStr);
        };
        recognition.start();

        //document.getElementById("mic").value = ""
    },
    showQRcode: function() {
        var editor = this;
        var imgform = Ext.create('Ext.tab.Panel', {
            region: 'left',
            border: false,
            activeTab: 0,
            items: [{
                    title: 'QRcodeGenerator',
                    icon: 'images/icon-dcQRcode-small.png',
                    layout: 'fit',
                    items: [{
                            xtype: 'form',
                            border: false,
                            bodyPadding: 10,
                            items: [{
                                    xtype: 'textfield',
                                    labelWidth: 70,
                                    fieldLabel: 'Text',
                                    name: 'qrText',
                                    allowBlank: false,
                                    blankText: 'input cannot be null',
                                    anchor: '100%'
                                }],
                            dockedItems: [{
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {pack: 'end'},
                                    items: [{
                                            text: 'Processing',
                                            formBind: true,
                                            handler: function(obj) {
                                                var f = obj.up('form').getForm();
                                                var qrText = f.findField('qrText').getValue();
                                                window.console.log('Text?=' + qrText);
                                                var div = document.createElement('div');

                                                //div.style.borderStyle='solid';
                                                if (!f.isValid()) {
                                                    return;
                                                }
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
                                                makeCode(qrText);
                                                var _img = div.getElementsByTagName('img')[0];
                                                _img.style.backgroundColor = 'white';
                                                _img.style.padding = '10px';
                                                window.console.log('div?=' + div);
                                                window.console.log(_img);
                                                if (Ext.isIE) {
                                                    editor.insertAtCursor(div.outerHTML);
                                                } else {
                                                    var selection = editor.win.getSelection();
                                                    if (!selection.isCollapsed) {
                                                        selection.deleteFromDocument();
                                                    }
                                                    if (selection.rangeCount > 0) {
                                                        selection.getRangeAt(0).insertNode(div);
                                                    } else {
                                                        Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + div.outerHTML);
                                                    }
                                                }
                                                win.hide();

                                            }
                                        }, {
                                            text: 'cancel',
                                            handler: function() {
                                                win.hide();
                                            }
                                        }]
                                }]
                        }]
                }]
        });
        var win = Ext.create('Ext.Window', {
            title: 'QRcodeGenerator',
            //icon: 'ext4/resources/images/default/editor/edit-image.png',
            width: 400,
            height: 175,
            plain: true,
            modal: true,
            closeAction: 'hide',
            resizable: false,
            border: false,
            layout: 'fit',
            items: imgform
        });
        win.show(this);
    }
    ,
    showYoutube: function() {
        var editor = this;
        var imgform = Ext.create('Ext.tab.Panel', {
            region: 'left',
            border: false,
            activeTab: 0,
            items: [{
                    title: 'YoutubeVideo',
                    icon: 'images/youtube.png',
                    layout: 'fit',
                    items: [{
                            xtype: 'form',
                            border: false,
                            bodyPadding: 10,
                            items: [{
                                    xtype: 'textfield',
                                    labelWidth: 70,
                                    fieldLabel: 'URL',
                                    name: 'youtubeURL',
                                    allowBlank: false,
                                    blankText: 'input cannot be null',
                                    anchor: '100%'
                                }],
                            dockedItems: [{
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {pack: 'end'},
                                    items: [{
                                            text: 'Processing',
                                            formBind: true,
                                            handler: function(obj) {
                                                var f = obj.up('form').getForm();
                                                var youtubeURL = f.findField('youtubeURL').getValue();
                                                var vid = youtube_parser(youtubeURL);
                                                if (!f.isValid()) {
                                                    return;
                                                }
                                                var stringRequest = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cplayer&id=" + vid + "&key=AIzaSyCOMiECTO_u-0gmQcxUgDmRcrRZWtw4E7w";
                                                xhr2(stringRequest, function(jsonmsg) {
                                                    //window.console.log(jsonmsg);
                                                    var responseMsg = JSON.parse(jsonmsg);
                                                    //window.console.log('titile=' + responseMsg.items[0].snippet.title);
                                                    //window.console.log('Image=' + responseMsg.items[0].snippet.thumbnails.default.url);
                                                    window.console.log('Duration=' + responseMsg.items[0].contentDetails.duration.replace('PT', "").replace('H', " Hours, ").replace('M', " Minutes, ").replace('S', " Second"));
                                                    //window.console.log('html=' + responseMsg.items[0].player.embedHtml);
                                                    //responseMsg.items[0].player.embedHtml.replace("width='640'", "width='346'").replace("height='360'", "height='346'");
                                                    var eHTML = responseMsg.items[0].player.embedHtml;

                                                    if (Ext.isIE) {
                                                        editor.insertAtCursor(eHTML);
                                                    } else {
                                                        var selection = window.getSelection();///editor.win.getSelection();
                                                        if (!selection.isCollapsed) {
                                                            selection.deleteFromDocument();
                                                        }
                                                        /*if (selection.rangeCount > 0) {
                                                         selection.getRangeAt(0).insertNode(eHTML);
                                                         } */ else {
                                                            Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + eHTML);
                                                        }
                                                    }
                                                    win.hide();
                                                });
                                            }
                                        }, {
                                            text: 'cancel',
                                            handler: function() {
                                                win.hide();
                                            }
                                        }]
                                }]
                        }]
                }]
        });
        var win = Ext.create('Ext.Window', {
            title: 'Youtube embed',
            //icon: 'images/youtube.png',
            width: 400,
            height: 175,
            plain: true,
            modal: true,
            closeAction: 'hide',
            resizable: false,
            border: false,
            layout: 'fit',
            items: imgform
        });
        win.show(this);
    },
    showSmilePanel: function(_e) {
        var filterPanel = Ext.getCmp('emoji-main');
        filterPanel.show();
        filterPanel.getEl().setStyle('z-index', '80000');
    },
    showZipUploader: function() {
        var editor = this;
        var imgform = Ext.create('Ext.tab.Panel', {
            region: 'left',
            border: false,
            activeTab: 0,
            items: [{
                    title: 'Upload',
                    icon: 'images/zip.png',
                    layout: 'fit',
                    items: [{
                            xtype: 'form',
                            border: false,
                            bodyPadding: 10,
                            items: [{
                                    xtype: 'filefield',
                                    labelWidth: 70,
                                    fieldLabel: 'File Path',
                                    buttonText: 'browse',
                                    name: 'zip',
                                    allowBlank: false,
                                    blankText: 'input cannot be null',
                                    anchor: '100%'
                                }, {
                                    xtype: 'textfield',
                                    labelWidth: 70,
                                    fieldLabel: 'title',
                                    name: 'zip-filename',
                                    allowBlank: false,
                                    blankText: 'title can not be null',
                                    anchor: '100%'
                                }],
                            dockedItems: [{
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {pack: 'end'},
                                    items: [{
                                            text: 'upload',
                                            formBind: true,
                                            handler: function(obj) {
                                                var f = obj.up('form').getForm();

                                                var fileName = f.findField('zip-filename').getValue();
                                                var fileType = f.findField('zip').getValue();
                                                if (fileType.indexOf(".zip") == -1) {
                                                    Ext.Msg.alert('Upload Error', 'Only accept Zip file');
                                                    return;
                                                }
                                                window.console.log("fileType=" + fileType);
                                                f.findField('zip-filename').setValue(fileName + ".zip");
                                                //var vals = f.getValues();

                                                if (!f.isValid()) {
                                                    return;
                                                }
                                                //var pic = vals.pic;
                                                //window.console.log(f);
                                                f.submit({
                                                    waitMsg: 'uploading..',
                                                    waitTitle: 'tips',
                                                    url: 'upload.jsp', //点击插入执行的方法,将图片保存到服务器上
                                                    success: function(form, action) {

                                                        var downloadIMG = document.createElement('img');
                                                        downloadIMG.style.width = "50px";
                                                        downloadIMG.setAttribute("src", "images/zip_file_download.png");

                                                        var responseMsg = action.result.url;
                                                        console.log('jsonMsg=' + responseMsg);
                                                        var element = document.createElement('a');
                                                        element.href = responseMsg;
                                                        element.appendChild(downloadIMG);
                                                        //alert(action);
                                                        console.log(action);

                                                        if (Ext.isIE) {
                                                            editor.insertAtCursor(source.outerHTML);
                                                        } else {
                                                            var selection = editor.win.getSelection();
                                                            if (!selection.isCollapsed) {
                                                                selection.deleteFromDocument();
                                                            }
                                                            if (selection.rangeCount > 0) {
                                                                selection.getRangeAt(0).insertNode(element);
                                                            } else {
                                                                Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + element.outerHTML);
                                                                //window.console.log(Ext.getCmp('df'));
                                                                //Ext.getCmp('df').addChildEls(mediaElement);
                                                                //while(mediaElement=null)
                                                                //Ext.getCmp('sd').fireEvent('click');
                                                            }
                                                        }
                                                        win.hide();
                                                    },
                                                    failure: function(form, action) {
                                                        form.reset();
                                                        if (action.failureType == Ext.form.action.Action.SERVER_INVALID) {
                                                            Ext.MessageBox.show({
                                                                title: 'error',
                                                                msg: action.result.msg,
                                                                icon: Ext.MessageBox.ERROR,
                                                                buttons: Ext.Msg.OK
                                                            });
                                                        }
                                                    }
                                                });
                                            }
                                        }, {
                                            text: 'cancel',
                                            handler: function() {
                                                win.hide();
                                            }
                                        }]
                                }]
                        }]
                }]
        });
        var win = Ext.create('Ext.Window', {
            title: 'Zip File Upload',
            //icon: 'images/zip.png',
            width: 400,
            height: 175,
            plain: true,
            modal: true,
            closeAction: 'hide',
            resizable: false,
            border: false,
            layout: 'fit',
            items: imgform
        });
        win.show(this);
    },
    showImgUploader: function() {
        var editor = this;
        var imgform = Ext.create('Ext.tab.Panel', {
            region: 'left',
            border: false,
            activeTab: 0,
            items: [{
                    title: 'local',
                    //icon: 'extjs/resources/images/computer.png',
                    layout: 'fit',
                    items: [{
                            xtype: 'form',
                            border: false,
                            bodyPadding: 10,
                            items: [{
                                    xtype: 'filefield',
                                    labelWidth: 70,
                                    fieldLabel: 'picture',
                                    buttonText: 'browse',
                                    name: 'pic',
                                    allowBlank: false,
                                    blankText: 'input cannot be null',
                                    anchor: '100%'
                                }/*, {
                                 xtype: 'textfield',
                                 labelWidth: 70,
                                 fieldLabel: 'title',
                                 name: 'title',
                                 allowBlank: false,
                                 blankText: 'title can not be null',
                                 anchor: '100%'
                                 }*/, {
                                    layout: 'column',
                                    border: false,
                                    items: [{
                                            layout: 'form',
                                            columnWidth: .36,
                                            xtype: 'numberfield',
                                            labelWidth: 70,
                                            fieldLabel: 'size(w*h)',
                                            name: 'width'
                                        }, {
                                            columnWidth: .05,
                                            xtype: 'label',
                                            html: ' px'
                                        }, {
                                            layout: 'form',
                                            xtype: 'numberfield',
                                            columnWidth: .15,
                                            name: 'height'
                                        }, {
                                            columnWidth: .05,
                                            xtype: 'label',
                                            html: ' px'
                                        }]
                                }],
                            dockedItems: [{
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {pack: 'end'},
                                    items: [{
                                            text: 'upload',
                                            formBind: true,
                                            handler: function(obj) {
                                                var f = obj.up('form').getForm();
                                                var vals = f.getValues();
                                                if (!f.isValid()) {
                                                    return;
                                                }
                                                //var pic = vals.pic;
                                                //window.console.log(f);
                                                f.submit({
                                                    waitMsg: 'uploading..',
                                                    waitTitle: 'tips',
                                                    url: 'upload.jsp', //点击插入执行的方法,将图片保存到服务器上
                                                    success: function(form, action) {
                                                        var element = document.createElement('img');
                                                        //alert(action);
                                                        console.log(action);
                                                        element.src = action.result.url;
                                                        element.style.width = '150px';
                                                        element.className = "msg-img";
                                                        element.setAttribute('onmouseover', "this.style.width='500px'");
                                                        element.setAttribute('onmouseout', "this.style.width='150px'");
                                                        element.setAttribute('onclick', 'window.open(this.src)');
                                                        if (vals.width > 0 && vals.height > 0) {
                                                            element.width = vals.width;
                                                            element.height = vals.height;
                                                        }
                                                        if (Ext.isIE) {
                                                            editor.insertAtCursor(element.outerHTML);
                                                        } else {
                                                            var selection = editor.win.getSelection();
                                                            if (!selection.isCollapsed) {
                                                                selection.deleteFromDocument();
                                                            }
                                                            if (selection.rangeCount > 0) {
                                                                selection.getRangeAt(0).insertNode(element);
                                                            } else {
                                                                Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + element.outerHTML);
                                                                //window.console.log(Ext.getCmp('df'));
                                                                //Ext.getCmp('df').addChildEls(mediaElement);
                                                                //while(mediaElement=null)
                                                                //Ext.getCmp('sd').fireEvent('click');

                                                            }
                                                        }
                                                        win.hide();
                                                    },
                                                    failure: function(form, action) {
                                                        form.reset();
                                                        if (action.failureType == Ext.form.action.Action.SERVER_INVALID) {
                                                            Ext.MessageBox.show({
                                                                title: 'error',
                                                                msg: action.result.msg,
                                                                icon: Ext.MessageBox.ERROR,
                                                                buttons: Ext.Msg.OK
                                                            });
                                                        }
                                                    }
                                                });
                                            }
                                        }, {
                                            text: 'cancel',
                                            handler: function() {
                                                win.hide();
                                            }
                                        }]
                                }]
                        }]
                }, {
                    title: 'imgURL',
                    //icon: 'extjs/resources/images/link.png',
                    layout: 'fit',
                    items: [{
                            xtype: 'form',
                            border: false,
                            bodyPadding: 10,
                            items: [{
                                    xtype: 'textfield',
                                    vtype: 'url',
                                    labelWidth: 70,
                                    fieldLabel: 'imageURL',
                                    anchor: '100%',
                                    name: 'pic',
                                    //id:'rm',
                                    allowBlank: false,
                                    blankText: 'url cannot be null'
                                }, {
                                    layout: 'column',
                                    border: false,
                                    items: [{
                                            layout: 'form',
                                            columnWidth: .36,
                                            xtype: 'numberfield',
                                            labelWidth: 70,
                                            fieldLabel: 'size(w*h)',
                                            name: 'width',
                                            //id:'w'
                                        }, {
                                            columnWidth: .05,
                                            xtype: 'label',
                                            html: ' px'
                                        }, {
                                            layout: 'form',
                                            xtype: 'numberfield',
                                            columnWidth: .15,
                                            name: 'height',
                                            //id:'h'
                                        }, {
                                            columnWidth: .05,
                                            xtype: 'label',
                                            html: ' px'
                                        }]
                                }],
                            dockedItems: [{
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {pack: 'end'},
                                    border: false,
                                    items: [{
                                            text: 'Add',
                                            formBind: true,
                                            handler: function(obj) {
                                                var f = obj.up('form').getForm();
                                                if (!f.isValid()) {
                                                    return;
                                                }
                                                var vals = f.getValues();
                                                var pic = vals.pic;
                                                var fileext = pic.substring(pic.lastIndexOf('.'), pic.length).toLowerCase();
                                                if (fileext != '.jpg' && fileext != '.gif' && fileext != '.jpeg' && fileext != '.png' && fileext != '.bmp') {
                                                    f.items.items[0].setValue('');
                                                    Ext.Msg.show({
                                                        title: 'tips',
                                                        icon: 'ext-mb-error',
                                                        width: 300,
                                                        msg: 'only allow image standard format',
                                                        buttons: Ext.MessageBox.OK
                                                    });
                                                    return;
                                                }
                                                var _element = document.createElement('img');
                                                _element.src = pic;
                                                _element.style.width = '150px';
                                                _element.className = "msg-img";
                                                _element.setAttribute('onmouseover', "this.style.width='500px'");
                                                _element.setAttribute('onmouseout', "this.style.width='150px'");
                                                _element.setAttribute('onclick', 'window.open(this.src)');
                                                if (vals.width > 0 && vals.height > 0) {
                                                    _element.width = vals.width;
                                                    _element.height = vals.height;
                                                }
                                                if (Ext.isIE) {
                                                    editor.insertAtCursor(_element.outerHTML);
                                                } else {
                                                    var selection = editor.win.getSelection();
                                                    if (!selection.isCollapsed) {
                                                        selection.deleteFromDocument();
                                                    }
                                                    if (selection.rangeCount > 0) {
                                                        selection.getRangeAt(0).insertNode(_element);
                                                    } else {
                                                        Ext.getCmp('df').setValue(Ext.getCmp('df').getValue() + _element.outerHTML);
                                                        //window.console.log(Ext.getCmp('df'));
                                                        //Ext.getCmp('df').addChildEls(mediaElement);
                                                        //while(mediaElement=null)
                                                        //Ext.getCmp('sd').fireEvent('click');

                                                    }
                                                }
                                                win.hide();
                                            }
                                        }, {
                                            text: 'cancel',
                                            handler: function() {
                                                win.hide();
                                            }
                                        }]
                                }]
                        }],
                }]
        });
        var win = Ext.create('Ext.Window', {
            title: 'Add image',
            icon: 'ext4/resources/images/default/editor/edit-image.png',
            width: 400,
            height: 175,
            plain: true,
            modal: true,
            closeAction: 'hide',
            resizable: false,
            border: false,
            layout: 'fit',
            items: imgform
        });
        win.show(this);
    }

});