<template>
	<view class="content">
		页面 - 12
		<button @click="clickBtn()">点击</button>
	</view>
</template>

<script>

import ChatMessage from "../../../protobuf/ChatMessage_pb.js"
	
export default {
	data() {
		return {
			title: 'Hello'
		};
	},
	onLoad() {},
	methods: {
		clickBtn(){
			let chatMsg = new ChatMessage.chatMsg();
			chatMsg.setMsgid("10001");
			chatMsg.setFromid("001");
			chatMsg.setToid("002");
			chatMsg.setMsg("小斌你要加油，争取25岁之前有自己的事业");
			console.log(chatMsg);
			var bytes = chatMsg.serializeBinary();
			console.log(ChatMessage.chatMsg.deserializeBinary(bytes));
		}
	}
};
</script>

<style>
.content {
	text-align: center;
	height: 400upx;
	margin-top: 200upx;
}
</style>
