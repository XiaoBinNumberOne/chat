import globalConstant from 'static/js/GlobalConstant.js'
import Message from '../../protobuf/ChatMessage_pb.js'


let websocketClient = null;

// 记录连接信息
let connInfo = {
	wsUrl:"",
	autoReconnectNum:2,
}

// 重新连接次数统计
let tempReconnectNum = 0;


// 初始化websocket连接
function initializer(){
	if(connInfo.wsUrl == ""){
		console.error("请填写正确的websocket地址");
		return;
	}
	try{
		websocketClient = new WebSocket(connInfo.wsUrl);
	}catch(err){
		console.log("请检查websocket url 是否正确");
		return;
	}
	websocketClient.onopen = (e)=>{
		console.log("连接成功");
		let chatMessage = new Message.ChatMessage();
		chatMessage.setBody("aaaaaa");
		console.log(chatMessage);
		websocketClient.send(chatMessage.serializeBinary());
	}
	
	// 判断是否成功连接
	// if(websocketClient.readyState == WebSocket.OPEN){
	// 	console.log("连接成功");
	// }else{
	// 	if(tempReconnectNum == connInfo.autoReconnectNum){
	// 		tempReconnectNum = 0;
	// 		console.log("连接失败,请查看websocket服务端是否启动");
	// 	}else{
	// 		tempReconnectNum++;
	// 		initializer(connInfo.wsUrl);
	// 	}
		
	// }
}

// 连接
function connection({
	wsUrl="",
	autoReconnectNum=2
}={}){
	connInfo.wsUrl = wsUrl;
	connInfo.autoReconnectNum = autoReconnectNum;
	initializer();
}



export{
	connection
}