var dataSource = [];
function loadData(cnt){
	for(var i=1;i<=cnt;i++){
		var data = {};
		if(i<10){
			data.tel="0000000000"+i;
		}else if(i<100){
			data.tel="000000000"+i;
		}else {
			data.tel="00000000"+i;
		}
		data.nick="";
		data.url="";
		dataSource.push(data);
	}	
	
}

loadData(personCnt);
