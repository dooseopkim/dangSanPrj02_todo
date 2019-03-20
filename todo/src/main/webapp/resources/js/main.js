const TODOTYPESERVLET_REQUEST_URI = "TodoTypeServlet/";
const TODO_CONTAINER = document.querySelector('.todos-container');
const TODO_COL = TODO_CONTAINER.querySelector('#todo');
const DOING_COL = TODO_CONTAINER.querySelector('#doing');
const DONE_COL = TODO_CONTAINER.querySelector('#done');

const updateBtn = (e) => {
	e.preventDefault();
	const btn = e.target;
	const id = btn.parentElement.id;
	const type = btn.parentElement.parentElement.id;
	
	updateTypeFunction(id, type).then(updateCard, updateFail);
}
const updateTypeFunction = (id, type) => {
	const uri = TODOTYPESERVLET_REQUEST_URI + id;
    let data = {};
    	data.type = type.toUpperCase();
    const json = JSON.stringify(data);
    
    const xhr = new XMLHttpRequest();
    
    return new Promise((resolve, reject) => {
    	xhr.onreadystatechange = () => {
    		if(xhr.readyState !== 4) return;
    		if(xhr.status >= 200 && xhr.status < 300){
    			resolve(xhr.responseText);
    		}else{
    			reject("Connect to server failed.");
    		}    		
    	}
    	xhr.open("PUT", uri, true);
    	xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    	xhr.send(json);
    });
 }
const updateCard = (result) => {
	const loadedJsonData = JSON.parse(result);
	const loadedResult = loadedJsonData.resultMsg;
	const loadedType = loadedJsonData.type;
	const loadedId = loadedJsonData.id;
	
	if(loadedResult !== 'success') return;
	
	const targetItem =  TODO_CONTAINER.querySelector(`[id='${loadedId}']`);
	
	switch(loadedType){
		case 'TODO':
			TODO_COL.removeChild(targetItem);
			DOING_COL.append(targetItem);
			break;
		case 'DOING':
			const btn = targetItem.querySelector('.js__update-btn');
			DOING_COL.removeChild(targetItem);
			targetItem.removeChild(btn);
			DONE_COL.append(targetItem);
			break;
		default:
			break;
	}
 }
const updateFail = (result) => {
	console.error(result);
}
const init = () => {
	const updateBtnList = TODO_CONTAINER.querySelectorAll('.js__update-btn');
	updateBtnList.forEach((btn) => {
		btn.addEventListener('click', updateBtn, false);
	})
}
init();
