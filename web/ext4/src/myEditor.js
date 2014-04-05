Ext.define('Ext.ux.form.MyEditor', {
 
    alias: 'widget.myeditor',
 
    extend: 'Ext.form.field.HtmlEditor',
 
    requires: ['Ext.form.field.HtmlEditor'],
 
    createToolbar: function(){
        var me = this;
        me.callParent(arguments);
        me.toolbar.insert(17, {
            xtype: 'button',
            icon: 'ext4/resources/images/default/editor/edit-image.png',
            handler: this.showImgUploader,
            scope: this
        });
        return me.toolbar;
    },
 
    showImgUploader: function(){
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
                    bodyPadding:10,
                    items: [{
                        xtype: 'filefield',
                        labelWidth: 70,
                        fieldLabel: 'picture',
                        buttonText: 'browse',
                        name: 'pic',
                        allowBlank: false,
                        blankText: 'input cannot be null',
                        anchor: '100%'
                    }, {
                        xtype: 'textfield',
                        labelWidth: 70,
                        fieldLabel: 'title',
                        name: 'title',
                        allowBlank: false,
                        blankText: 'title can not be null',
                        anchor: '100%'
                    }, {
                        layout: 'column',
                        border: false,
                        items: [{
                            layout: 'form',
                            columnWidth:.36,
                            xtype: 'numberfield',
                            labelWidth: 70,
                            fieldLabel: 'size(w*h)',
                            name: 'width'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        },{
                            layout: 'form',
                            xtype: 'numberfield',
                            columnWidth:.15,
                            name: 'height'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        }]
                    }],
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        layout: { pack: 'end' },
                        items: [{
                            text: 'upload',
                            formBind: true,
                            handler: function(obj) {
                                var f = obj.up('form');
                                if (!f.isValid()) {
                                    return;
                                }
                                var vals = f.getForm().getValues();
                                f.submit({
                                    waitMsg: 'uploading..',
                                    waitTitle: 'tips',
                                    url: editor.url + '?sign=HtmlEditor', //??插入?行的方法,??片保存到服?器上
                                    success: function(form, action) {
                                        var element = document.createElement('img');
                                        element.src = action.result.file_url;
                                        if(vals.width>0 && vals.height>0){
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
                                            selection.getRangeAt(0).insertNode(element);
                                        }
                                        win.hide();
                                    },
                                    failure: function(form, action) {
                                        form.reset();
                                        if (action.failureType == Ext.form.action.Action.SERVER_INVALID){
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
                    bodyPadding:10,
                    items: [{
                        xtype: 'textfield',
                        vtype: 'url',
                        labelWidth: 70,
                        fieldLabel: 'imageURL',
                        anchor: '100%',
                        name: 'pic',
                        allowBlank: false,
                        blankText: 'url cannot be null'
                    }, {
                        layout: 'column',
                        border: false,
                        items: [{
                            layout: 'form',
                            columnWidth:.36,
                            xtype: 'numberfield',
                            labelWidth: 70,
                            fieldLabel: 'size(w*h)',
                            name: 'width'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        },{
                            layout: 'form',
                            xtype: 'numberfield',
                            columnWidth:.15,
                            name: 'height'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        }]
                    }],
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        layout: { pack: 'end' },
                        border: false,
                        items: [{
                            text: 'Add',
                            formBind: true,
                            handler: function(obj) {
                                var f = obj.up('form');
                                if (!f.isValid()) {
                                    return;
                                }
                                var vals = f.getForm().getValues();
                                var pic = vals.pic;
                                var fileext = pic.substring(pic.lastIndexOf('.'), pic.length).toLowerCase();
                                if (fileext != '.jpg' && fileext != '.gif' && fileext != '.jpeg' && fileext != '.png' && fileext != '.bmp') {
                                    f.items.items[0].setValue('');
                                    Ext.Msg.show({
                                        title: 'tips',
                                        icon: 'ext-mb-error',
                                        width: 300,
                                        msg: 'only allow image standard formal',
                                        buttons: Ext.MessageBox.OK
                                    });
                                    return;
                                }
                                var element = document.createElement('img');
                                element.src = pic;
                                if(vals.width>0 && vals.height>0){
                                    element.width = vals.width;
                                    element.height = vals.height;
                                }
                                if(Ext.isIE) {
                                    editor.insertAtCursor(element.outerHTML);
                                }else{
                                    var selection = editor.win.getSelection();
                                    if(!selection.isCollapsed) {
                                        selection.deleteFromDocument();
                                    }
                                    selection.getRangeAt(0).insertNode(element);
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