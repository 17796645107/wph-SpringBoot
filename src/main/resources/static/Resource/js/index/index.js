$(document).ready(function(){
	//加载头部和页脚
	$("#header").load("index_header.html");
	$("#footer").load("index_footer.html");

	//侧栏导航
	$("#mainLeft_nav li").click(function(){
		$(this).css("background","rgb(241,1,128)");
		$(this).children("a").css("color","white");
		$(this).siblings().css("background","white");
		$(this).siblings().children("a").css("color","#666");
	});
});

//导航栏固定顶部效果
document.onscroll = function(){
	if(window.pageYOffset>1860){
		$("#main_nav").addClass("mainNav_fixed");
		$("#mainLeft_nav").addClass("mainLeft_nav_fixed");
	}
    else if(window.pageYOffset<1860 && window.pageYOffset>130){
    	$("#main_nav").addClass("mainNav_fixed");
    	$("#mainLeft_nav").removeClass("mainLeft_nav_fixed");
    }
	else{
		$("#main_nav").removeClass("mainNav_fixed");
	}
}
