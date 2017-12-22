<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="info">
		<button onClick="connect()">打开连接</button>
		<button onClick="closeWebSocket()">关闭连接</button>
	</div>
	
<script src="js/jquery.min.js"></script>
<script>
/* 	$.ajax({
		url:'getAll.do',
		type:'get',
		dataType:'json',
		success:function(data){
			console.log(data.list);
			$("#info").html(data.list);
		}
	}); */
	
	var websocket = null;
	
	var result;
	
	
     //判断当前浏览器是否支持WebSocket
     function connect(){
    	 //websocket当前状态，0未连接，1已连接，2正在关闭连接，3连接已关闭或不可用
    	 var websocketState = websocket?(websocket.readyState?websocket.readyState:0):0;
    	 //如果当前已连接，则不再建立新的连接
    	 if(websocketState !== 1){
    		 if ('WebSocket' in window) {
 	     	  	var url = '';
 	 	    	if (window.location.protocol == 'http:') {
 	 	   	        url = 'ws://localhost:8080/mssm/websocket';
 	 	   	    } else {
 	 	   	        url = 'wss://localhost:8080/mssm/websocket';
 	 	   	    }
 	           	websocket = new WebSocket(url);
	 	       }
	 	       else {
	 	           console.log('当前浏览器 Not support websocket')
	 	       }
	     	 
	 	    	//连接发生错误的回调方法
	 	         websocket.onerror = function () {
	 	             console.log("WebSocket连接发生错误");
	 	         };
	 	     
	 	         //连接成功建立的回调方法
	 	         websocket.onopen = function () {
	 	             console.log("WebSocket连接成功");
	 	         }
	 	     
	 	         //接收到消息的回调方法
	 	         websocket.onmessage = function (event) {
	 	       	  result = event.data;
	 	             console.log("接受回的消息"+event.data);
	 	         }
	 	     
	 	         //连接关闭的回调方法
	 	         websocket.onclose = function () {
	 	             console.log("WebSocket连接关闭");
	 	         }
    	 }else{
    		 console.log('当前已经连接websocket,不会重复连接');
    	 }
    	 
     }
     connect();
  

     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function () {
          closeWebSocket();
      }
    
     //关闭WebSocket连接
      function closeWebSocket() {
          websocket.close();
      }
  
      //发送消息
      function send() {
          var message = "this is a message from client";
          websocket.send(message);
      }
      
      
      
</script>
</body>
</html>