//用于展示用户的聊天信息
Ext.define('MessageContainer', {
    extend: 'Ext.view.View',
    trackOver: true,
    multiSelect: false,
    itemCls: 'l-im-message',
    itemSelector: 'div.l-im-message',
    overItemCls: 'l-im-message-over',
    selectedItemCls: 'l-im-message-selected',
    style: {
        overflow: 'auto',
        backgroundColor: '#fff'
    },
    tpl: [
        '<div class="l-im-message-warn">​Never give out personal details</div>',
        '<tpl for=".">',
        '<div class="l-im-message">',
        '<div class="l-im-message-header l-im-message-header-{source}">{from}  {timestamp}</div>',
        '<div class="l-im-message-body">{content}</div>', '</div>',
        '</tpl>'],
    messages: [],
    initComponent: function() {
        var me = this;
        me.messageModel = Ext.define('Leetop.im.MessageModel', {
            extend: 'Ext.data.Model',
            fields: ['from', 'timestamp', 'content', 'source']
        });
        me.store = Ext.create('Ext.data.Store', {
            model: 'Leetop.im.MessageModel',
            data: me.messages
        });
        me.callParent();
    },
    //将服务器推送的信息展示到页面中
    receive: function(message) {
        var me = this;
        message['timestamp'] = Ext.Date.format(new Date(message['timestamp']),
                'H:i:s');
        if (message.from == user) {
            message.source = 'self';
        } else {
            message.source = 'remote';
        }
        me.store.add(message);
        if (me.el.dom) {
            me.el.dom.scrollTop = me.el.dom.scrollHeight;
        }
    }
});

Ext.onReady(function() {
    //创建用户输入框
    var tmp = {
        id: 'df',
        xtype: 'myeditor',
        region: 'south',
        height: 120,
        enableFont: false,
        enableSourceEdit: false,
        enableAlignments: false,
        url: '/upload', //图片上传路径在这里设置
        listeners: {
            initialize: function() {
                Ext.EventManager.on(me.input.getDoc(), {
                    click: function(e) {
                        if (e.ctrlKey === true
                                && e.keyCode == 13) {
                            e.preventDefault();
                            e.stopPropagation();
                            send();
                        }
                    }
                });
            }
        }
    };

    var input = Ext.create('Ext.form.field.HtmlEditor', {
        region: 'south',
        height: 120,
        enableFont: false,
        enableSourceEdit: false,
        enableAlignments: false,
        listeners: {
            initialize: function() {
                Ext.EventManager.on(me.input.getDoc(), {
                    keyup: function(e) {
                        if (e.ctrlKey === true
                                && e.keyCode == 13) {
                            e.preventDefault();
                            e.stopPropagation();
                            send();
                        }
                    }
                });
            }
        }
    });
    //创建消息展示容器
    var output = Ext.create('MessageContainer', {
        region: 'center'
    });

    var dialog = Ext.create('Ext.panel.Panel', {
        region: 'center',
        layout: 'border',
        items: [tmp, output],
        buttons: [{
                id: 'sd',
                text: 'Send',
                handler: send
            }]
    });
    var websocket;

    //初始话WebSocket
    function initWebSocket() {
        if (window.WebSocket) {
            var host = document.location.host;
//            websocket = new WebSocket(encodeURI('ws://localhost:8080/WS/message'));
            websocket = new WebSocket(encodeURI('ws://' + host + '/WS/message'));
            websocket.onopen = function() {
                //连接成功
                win.setTitle(title + ' Send to : <span style="text-decoration:underline;">' + target + '</span> <span style="color: green;">(Connected)</span>');
            }
            websocket.onerror = function() {
                //连接失败
                win.setTitle(title + '&nbsp;&nbsp;(Error)');
            }
            websocket.onclose = function() {
                //连接断开
                win.setTitle(title + '&nbsp;&nbsp;(Disconnected)');
            }
            //消息接收
            websocket.onmessage = function(message) {
                var message = JSON.parse(message.data);
                //接收用户发送的消息
                if (message.type == 'message') {
                    output.receive(message);
                } else if (message.type == 'get_online_user') {
                    //获取在线用户列表
                    var root = onlineUser.getRootNode();
                    Ext.each(message.list, function(user) {
                        var node = root.createNode({
                            id: user,
                            text: user,
                            iconCls: 'user',
                            leaf: true
                        });
                        root.appendChild(node);
                    });
                } else if (message.type == 'user_join') {
                    var user = message.user;
                    //window.console.log("User?="+user);
                    //Ext.example.msg("User Join",'User '+user+' is Online');
                    //用户上线
                    var _no = Ext.create('Ext.ux.window.Notification',{
                        xtype: 'Notification',
                        title:'Online Notification',
                        html :"<h1>"+user+" Online </h1>",
                        position: 'br'
                    });
                    window.console.log(_no);
                    _no.show();
                    var root = onlineUser.getRootNode();
                    
                    var node = root.createNode({
                        id: user,
                        text: user,
                        iconCls: 'user',
                        leaf: true
                    });
                    root.appendChild(node);
                } else if (message.type == 'user_leave') {
                    //用户下线
                    var root = onlineUser.getRootNode();
                    var user = message.user;
                    var node = root.findChild('id', user);
                    root.removeChild(node);
                }
            }
        }
    }
    ;


    //在线用户树
    var onlineUser = Ext.create('Ext.tree.Panel', {
        title: 'User List',
        rootVisible: false,
        region: 'east',
        width: 150,
        lines: false,
        useArrows: true,
        autoScroll: true,
        split: true,
        iconCls: 'user-online',
        store: Ext.create('Ext.data.TreeStore', {
            root: {
                text: 'User List',
                expanded: true,
                children: [{
                        id: 'All',
                        text: 'all',
                        iconCls: 'user',
                        leaf: true
                    }]
            }
        }),
        listeners: {
            itemclick: function(s, r) {
                target = r.data.text;
                //alert(target);
                win.setTitle(title + ' Send to : <span style="text-decoration:underline;">' + target + '</span><span style="color: green;">(Connected)</span>');
            }
        }
    });
    var title = 'Welcome!：' + user;
    //展示窗口
    var win = Ext.create('Ext.window.Window', {
        title: title + '&nbsp;&nbsp;(Connecting)',
        layout: 'border',
        iconCls: 'user-win',
        minWidth: 900/*650*/,
        minHeight: 700/*460*/,
        width: 900 /*650*/,
        animateTarget: 'websocket_button',
        height: 700 /*460*/,
        minimizable: true,
        maximizable: true,
        closable: false,
        items: [dialog, onlineUser],
        border: false,
        listeners: {
            render: function() {
                initWebSocket();
            },
            minimize: function(win, obj) {
                win.collapse(false);
                win.alignTo(document.querySelector("#scope"), 'br');
                //win.minimize();
            }
        }
    });
    win.show();

    //发送消息
    function send() {
        //window.console.log(Ext.getCmp('df').getValue());
        var input = Ext.getCmp('df');
        var message = {};
        if (websocket != null) {
            if (input.getValue()) {
                if (target == "all") {
                    Ext.apply(message, {
                        from: user,
                        content: input.getValue(),
                        timestamp: new Date().getTime(),
                        type: 'message',
                        to: 'all'
                    });
                }
                else {
                    Ext.apply(message, {
                        from: user,
                        content: input.getValue(),
                        timestamp: new Date().getTime(),
                        type: 'message',
                        to: target
                    });
                }
                websocket.send(JSON.stringify(message));
                //output.receive(message);
                input.setValue('');
            }
        } else {
            Ext.Msg.alert('提示', '您已经掉线，无法发送消息!');
        }
    }
});
