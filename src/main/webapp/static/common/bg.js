/*function disableCopy(){
	$("body ")
	.attr("onselectstart","return false;")
	.attr("ondragstart","return false;")
	.attr("onbeforecopy","return false;")
	.attr("oncopy","document.selection.empty();")
	.attr("onselect","document.selection.empty();")
;
	
}*/
/**
 * 禁用复制
 */
$.fn.extend({
	prohibitRight:function(){
		$(this).on("contextmenu",function () {
            return false;
        });
		return this;
	}
	,prohibitCopy:function(){
		$(this).on("copy",function () {
            return false;
        });
		$(this).on("keydown",function (e) {
			if(e.which == "22"){ 
			// press Ctrl+V keyboard 
				 return false;
			}else{
				return true;
			}
           
        });
		return this;
	}
});

