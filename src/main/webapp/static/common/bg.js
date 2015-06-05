function disableCopy(){
	$("body ")
	.attr("onselectstart","return false;")
	.attr("ondragstart","return false;")
	.attr("onbeforecopy","return false;")
	.attr("oncopy","document.selection.empty();")
	.attr("onselect","document.selection.empty();")
;
}