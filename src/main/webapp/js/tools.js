//组件内容
var data = [{
	code:"<div style='width:90%;margin-left:5%;height:94px;'>"+
		
		"<div style='margin-left: 30px;margin-top:30px;background: red;height: 65px;display: -webkit-inline-box;min-width: 100px;border-radius: 10px;-moz-border-radius: 10px;-webkit-border-radius: 10px;'>"
		+"<div style='width: 50px;height: 50px;background: red;border-radius: 50%;border: 3px solid white;margin-left: 22px;margin-top: -27px;font-size:35px;color:white'>"
		+"	<div style='margin-left: 15px;'>1</div>"
		+"</div>"
		+"	<div style='min-width: 30px;margin-left: 10px; margin-top: 12px;background: white;height: 40px;border-radius: 4px;font-size: 26px;padding-left: 25px;color: red;padding-right: 15px;'>"
		+"		这里添加文字"
		+"	</div>"
		+"</div>"
	+"</div>",
	name:'firstTool',
	backImg:'toolImg\/title.jpg'
},{
	code:"<div style='margin-top:20px;background: red;height: 65px;display: -webkit-inline-box;min-width: 100px;border-radius: 10px;-moz-border-radius: 10px;-webkit-border-radius: 10px;width:50%;'>"+
		
		"	<div style='min-width: 30px; margin-top: 12px;background: white;height: 40px;border-radius: 4px;font-size: 26px;padding-left: 25px;color: red;padding-right: 15px;'>"+
		"		这里添加文字"+
		"</div>"+
		"</div>"+
		"	<div style='border: 1px solid red;width: 49.9%;padding-top: 10px;display:table;margin-top:-10px;'>"+
		"		这里添加文字"+
		"	</div>",
	name:'area',
	backImg:'toolImg\/area.jpg'
},{
	code:"<img src='toolImg/header.jpg' style='width:100%'>",
	name:'header',
	backImg:'toolImg\/header.jpg'
},{
	code:"<img src='toolImg/footer.jpg' style='width:100%'>",
	name:'footer',
	backImg:'toolImg\/footer.jpg'
},{
	code:"<div style='width:90%;margin-left:5%;'>"+
	
	"<div style='margin-top:20px;background: red;height: 65px;display: -webkit-inline-box;min-width: 100px;border-radius: 10px;-moz-border-radius: 10px;-webkit-border-radius: 10px;width:100%;'>"+
	
	"	<div style='min-width: 30px; margin-top: 12px;background: white;height: 40px;border-radius: 4px;font-size: 26px;padding-left: 25px;color: red;padding-right: 15px;'>"+
	"		这里添加文字"+
	"</div>"+
	"</div>"+
	"	<div style='border: 1px solid red;width: 99.9%;padding-top: 10px;display:table;margin-top:-10px;'>"+
	"		这里添加文字"+
	"	</div>"+
"</div>",
name:'area',
backImg:'toolImg\/area.jpg'
},{
	code:"<div id='container' style='width:80%;margin-left:10%;background: #f3f0f0;min-height: 100px;'></div>",
	name:'80%div',
	backImg:'toolImg\/80%.jpg'
},{
	code:"<div id='container' style='width:100%;background: #f3f0f0;min-height: 100px;'></div>",
	name:'100%div',
	backImg:'toolImg\/100%.jpg'
},{
	code:"<div style='width:43%;float:left;min-height:100px;margin-left:5%;'>"+
			"这里是左边容器"+
		"</div>"+
		"<div style='width:43%;float:right;min-height:100px;margin-right:5%;'>"+
			"这里是右边容器"+
		"</div>"+
		"<div style='clear:both'></div>",
		name:'split',
		backImg:'toolImg\/split.jpg'
},{
	code:"<div style='width:90%;margin-left:5%;'>这里输入文本</div>",
	name:'textarea',
	backImg:'toolImg\/textarea.jpg'
}];




for(var x in data){
	$(".toolBar").html($(".toolBar").html()+"<div class='tools' data-id='"+x+"' style='background: url("+data[x].backImg+") center no-repeat;background-size: 100% 100%;'></div>");
}

$('.toolBar').on('click','.tools',function(){
	var x = $(this).data('id');
	if(data[x].code.indexOf("container") > 0){
		$(".note-editable").html($(".note-editable").html()+data[x].code);
	}else{
		$("#container").html($("#container").html()+data[x].code);
	}
});

$(".resultBtu").click(function(){
	alert($(".note-editable").html());
});
