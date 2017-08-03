//组件内容
var data = [{
	code:"<div style='width:100%;height:94px;'>"+
		
		"<div style='margin-left: 30px;margin-top:20px;background: red;height: 65px;display: -webkit-inline-box;min-width: 100px;border-radius: 10px;-moz-border-radius: 10px;-webkit-border-radius: 10px;'>"
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
}];




for(var x in data){
	$(".toolBar").html($(".toolBar").html()+"<div class='tools' data-id='"+x+"' style='background: url("+data[x].backImg+") center no-repeat;background-size: 100% 100%;'></div>");
}

$('.toolBar').on('click','.tools',function(){　　　　
	var x = $(this).data('id');
	$(".note-editable").html($(".note-editable").html()+data[x].code);
});

$(".resultBtu").click(function(){
	alert($(".note-editable").html());
});
