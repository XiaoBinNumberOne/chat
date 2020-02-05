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
			let chatMsg = new ChatMessage.ChatMessage();
			let head = new ChatMessage.Head();
			let msgType = ChatMessage.MsgType.GROUP_CHAT;
			head.setMsgtype(msgType);
			head.setMsgid("123");
			chatMsg.setHead(head)
			var bytes = chatMsg.serializeBinary();
			console.log(bytes);
			let msg = ChatMessage.ChatMessage.deserializeBinary(bytes);
			console.log(msg.getHead().getMsgtype());
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
