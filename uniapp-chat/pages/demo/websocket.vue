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
		created() {
			console.log("created方法");
		},
		data() {
			return {

			}
		},
		methods: {
			sendMsg:function(){
				this.wsClient.connection({
					wsUrl:"xxx"
				});
			},
			gotoNewPage:function(){
				
			}
		}
	}
</script>

<style>

</style>
