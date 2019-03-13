const TODOTYPESERVLET_REQUEST_URI = "TodoTypeServlet/";

function updateBtn(e){
	e.preventDefault();
	const btn = e.target;
	const id = btn.parentElement.id;
	const type = btn.parentElement.parentElement.id;
	
	updateTypeFunction(id, type).then(updateCard, updateFail);
	
}
var updateTypeFunction = function(id, type) {
    const uri = TODOTYPESERVLET_REQUEST_URI + id;
    let data = {};
    	data.type = type;
    let json = JSON.stringify(data);
    
    let xhr = new XMLHttpRequest();
    
    return new Promise(function (resolve, reject){
    	xhr.onreadystatechange = function(){
    		if(xhr.readyState !== 4) return;
    		if(xhr.status >= 200 && xhr.status < 300){
    			resolve(xhr.responseText);
    		}else{
    			reject("Connection to server failed.");
    		}    		
    	}
    	xhr.open("PUT", uri, true);
    	xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    	xhr.send(json);
    });
 }
var updateCard = function(result) {
	const loadedJsonData = JSON.parse(result);
	const loadedResult = loadedJsonData.resultMsg;
	
	if(loadedResult !== 'success') return;
	let ul = document.getElementById(loadedJsonData.type);
	let li = document.getElementById(loadedJsonData.id);
    if (loadedJsonData.type === 'TODO') {
       ul.removeChild(li);
       document.getElementById('DOING').append(li);
    } else {
       var btn = li.querySelector('.js__update-btn');
       ul.removeChild(li);
       li.removeChild(btn);
       document.getElementById('DONE').append(li);
    }
 }
var updateFail = function(result){
	console.error(result);
}