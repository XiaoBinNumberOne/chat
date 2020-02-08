import globalConstant from 'static/js/GlobalConstant.js'
import Message from '../../protobuf/ChatMessage_pb.js'


let websocketClient = null;

// 记录连接信息
let connInfo = {
	wsUrl: "",
	autoReconnectNum: 2,
}

// 重新连接次数统计
let tempReconnectNum = 0;


// 初始化websocket连接
function initializer() {
	if (connInfo.wsUrl == "") {
		console.error("请填写正确的websocket地址");
		return;
	}
	try {
		websocketClient = new WebSocket(connInfo.wsUrl);
	} catch (err) {
		console.log("请检查websocket url 是否正确");
		return;
	}
	websocketClient.onopen = (e) => {
		console.log("连接成功");
		let chatMessage = new Message.ChatMessage();
		chatMessage.setBody("aaaaaa");
		websocketClient.send(chatMessage.serializeBinary());
	}

	websocketClient.onmessage = (e) => {
		deserializeBinary(e.data,messageCallback);
	}


}



// protobuf解码
function deserializeBinary(data,callback) {
	let reader = new FileReader();
	reader.readAsArrayBuffer(data);
	reader.onload = () => {
		const buf = new Uint8Array(reader.result);
		const response = Message.ChatMessage.deserializeBinary(buf);
		// 成功回调
		callback(response);
	};
}
// 消息回调
function messageCallback(message){
	console.log(message);
}


// 连接
function connection({
	wsUrl = "",
	autoReconnectNum = 2
} = {}) {
	connInfo.wsUrl = wsUrl;
	connInfo.autoReconnectNum = autoReconnectNum;
	initializer();
}



export {
	connection
}
