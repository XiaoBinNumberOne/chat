<script>
	export default {
		onLaunch: function() {
			console.log('App Launch');
		},
		onShow: function() {
			console.log('App Show');
		},
		onHide: function() {
			console.log('App Hide');
		}
	};
</script>

<style>
	/*每个页面公共css */
	* {
		font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
		padding: 0;
		margin: 0;
	}

	page {
		display: block;
		width: 100%;
		height: 100%;
	}

	.box-warpper {
		display: flex;
		flex-direction: column;
		width: 100%;
		height: 100%;
	}
</style>
