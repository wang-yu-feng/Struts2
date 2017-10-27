$(document).ready(function(){
	var arr;
	var arrCookies = document.cookie.split(";");
	
	for(var i = 0;i<arrCookies.length;i++){
		arr = arrCookies[i].split("=");
		if(arr[1]!=null){
			$("input[name='userName']").val(arr[1]);
		}
		
	}
});

$(document).on('click','#button',function(){
	if($("input[type='checkbox']").is(':checked')==true){
		var userName = $("input[name='userName']").val();
		var password = $("input[name='password']").val();
		
		document.cookie = "userName="+userName+";password="+password;
		/*alert(document.cookie.split(";")[0].split("=")[1]);*/
	}
});