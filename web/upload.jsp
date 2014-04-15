<%-- 
    Document   : upload
    Created on : Feb 4, 2014, 10:03:04 PM
    Author     : Alpha
--%>



<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.io.File"%>

<%
    File file;
    DiskFileUpload upload = new DiskFileUpload();
    upload.setHeaderEncoding("utf8");
    java.util.List items = upload.parseRequest(request);
    java.util.ListIterator listIterator = items.listIterator();
    String fileName = "";
    String userName = "";
    userName = (String) request.getSession().getAttribute("user");
    ServletContext context = getServletContext();
    String filePath = context.getInitParameter("file-upload");
    String name = "";
    String value = "";
    while (listIterator.hasNext()) {
        FileItem item = (FileItem) listIterator.next();
        if (item.isFormField()) {
            name = item.getFieldName();
            value = item.getString();
            System.out.println("***Name*** = " + name + "   ***Value=" + value);
        }
        if (!item.isFormField()) {
            fileName = item.getName();

            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);//从全路径中提取文件名
        }
        String dir = filePath + "\\" + userName;
        new File(dir).mkdir();
        //File newFile = new File("c:/" + fileName);
        if (name.equalsIgnoreCase("webp-blob")) {
            Base64 base64 = new Base64();
            //file = new File(dir, value);
            byte[] tmp = base64.decode(value);
            System.out.println("***tmp*** = " + tmp.length);
            //file = new File(dir);
            FileUtils.writeByteArrayToFile(new File(dir+"\\"+fileName), tmp);
            file = new File(dir);
            //FileUtils.writeByteArrayToFile(file, tmp);
            //fileName = value;
        } 
        else if (name.equalsIgnoreCase("webp-filename")) {
            Base64 base64 = new Base64();
            //file = new File(dir, value);
            byte[] tmp = base64.decode(fileName);
            System.out.println("***tmp*** = " + tmp.length);
            //FileUtils.writeByteArrayToFile(new File("C:\\1.webp"), tmp);
            file = new File(dir);
            //FileUtils.writeByteArrayToFile(file, tmp);
            fileName = value;
        } else if (name.equalsIgnoreCase("zip-filename")) {
            file = new File(dir, value);
            fileName = value;
        } else if (name.equalsIgnoreCase("audio-filename")) {
            file = new File(dir, value);
            fileName = value;
        } else if (fileName.lastIndexOf("\\") >= 0) {
            String filedir = fileName.substring(fileName.lastIndexOf("\\"));
            System.out.println("****" + dir);
            file = new File(dir, filedir);
        } else {
            String filedir = fileName.substring(fileName.lastIndexOf("\\") + 1);
            file = new File(dir, filedir);
        }
        try {
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    String domain = request.getServletContext().getRealPath("/");
    String text = "/data/" + userName + "/" + fileName;
    HashMap<String, String> map = new HashMap();
    map.put("success", "true");
    map.put("url", text);
    String json = (new Gson()).toJson(map);
    //String msg = "{success:true,url:\"" + text + "\"}";
    System.out.println("Json = " + json);
    response.getWriter().write(json);
%>

