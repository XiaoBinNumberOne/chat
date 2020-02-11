<template>
	<view style="display: flex;flex-direction: column">
		<view style="display: flex;flex-direction: row;align-items: center;">
			<input type="text" />
			<button @click="sendMsg()">发送信息</button>
		</view>
		<view>
			<button @click="gotoNewPage()">打开新页面</button>
			<text>显示</text>
			<view v-for="item in 10" :key="item">1</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			sendMsg: function() {
				this.xbIM.connection({
					wsUrl: "ws://121.40.165.18:8800"
				});
				this.xbIM.websocketClient.onmessage=(msg)=>{
					console.log(msg);
				};
			},
			gotoNewPage: function() {

			}
		}
	}
</script>

<style>

</style>
