$(document).ready(function(){
	//左侧导航手风琴效果
	$("#nav_deal").click(function(){
		//隐藏状态
		var temp=$(this).attr("title");
		if($.trim(temp)==1){
			$(this).css("background-position","-88px -209px");
			$(this).parent().siblings().css("display","none");
			$(this).parent().parent().css("height","40px")
			$(this).attr("title","0");
		}
		//展开状态
		if($.trim(temp)==0){
			$(this).css("background-position","-71px -209px");
			$(this).parent().siblings().css("display","block");
			$(this).parent().parent().css("height","103px")
			$(this).attr("title","1");
		}
	});
	$("#nav_asset").click(function(){
		//隐藏状态
		var temp1=$(this).attr("title");
		if($.trim(temp1)==1){
			$(this).css("background-position","-88px -209px");
			$(this).parent().siblings().css("display","none");
			$(this).parent().parent().css("height","40px")
			$(this).attr("title","0");
		}
		//展开状态
		if($.trim(temp1)==0){
			$(this).css("background-position","-71px -209px");
			$(this).parent().siblings().css("display","block");
			$(this).parent().parent().css("height","210px")
			$(this).attr("title","1");
		}
	});
	$("#nav_account").click(function(){
		//隐藏状态
		var temp2=$(this).attr("title");
		if($.trim(temp2)==1){
			$(this).css("background-position","-88px -209px");
			$(this).parent().siblings().css("display","none");
			$(this).parent().parent().css("height","40px")
			$(this).attr("title","0");
		}
		//展开状态
		if($.trim(temp2)==0){
			$(this).css("background-position","-71px -209px");
			$(this).parent().siblings().css("display","block");
			$(this).parent().parent().css("height","129px")
			$(this).attr("title","1");
		}
	});
});


