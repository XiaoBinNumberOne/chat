<template>
	<view class="box-warpper">
		<view class="warpper">
			<view class="warpper-item"  v-show="showItem==0">
				<view style="padding-bottom: 180upx;font-size:60upx;color: white;font-weight: 800;text-shadow: 1px 1px 0px #000;">Welcome
					back</view>
					
				<view class="user-input-button" @click="loginBtnClick()">
					<text>Login as Chat</text>
				</view>
			</view>

			<view class="warpper-item02" v-show="showItem==1">
				<view class="avatar">
					<image src="../static/img/login/default_avatar.jpeg"></image>
				</view>
				<view class="user-input">
					<view class="username-input">
						<view class="username-icon"><image src="../static/img/login/username.png"/></view>
						<view style="display: flex;flex-direction: column;justify-content: center;">
							<view style="text-transform: uppercase;font-size: 32upx;color: #5c695b;">username/phone number</view>
							<input type="text" style="color: white;font-size: 28upx;"  placeholder="enter name"/>
						</view>
					</view>
					<view class="username-input">
						<view class="username-icon"><image src="../static/img/login/password.png"/></view>
						<view style="display: flex;flex-direction: column;justify-content: center;">
							<view style="text-transform: uppercase;font-size: 32upx;color: #5c695b;">password</view>
							<input type="password" style="color: white;font-size: 28upx;"  placeholder="enter password"/>
						</view>
					</view>
				</view>
				<view class="user-input-button">
					<text>Sign In</text>
				</view>
				<view class="info">
					<view class="info-link">forget password?</view>
					<view style="margin-left: 10upx;">don't have an account?<span class="info-link">sign up</span></view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		components: {

		},
		data() {
			return {
				showItem: 0
			}
		},
		methods: {
			loginBtnClick: function() {
				this.showItem = 1;

			}
		}
	}
</script>

<style scoped>
	.warpper {
		display: flex;
		flex-direction: column;
		position: relative;
		width: 100%;
		height: 100%;
	}

	.warpper::before {
		content: '';
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-image: url(../static/img/login/login_bg.jpg);
		background-size: 100% 100%;
		filter: blur(12upx);
		z-index: -1;
	}

	.warpper-item {
		position: absolute;
		display: flex;
		bottom: 400upx;
		width: 100%;
		height: 400upx;
		flex-direction: column;
		align-items: center;
		justify-content: center;

	}

	
	.user-input-button{
		display: flex;
		width: 80%;
		height: 98upx;
		background-color: #2a2331a8;
		align-items: center;
		justify-content: center;
		border-radius: 40upx;
	}
	.user-input-button text{
		font-size:48upx;
		color: white;
	}



	.warpper-item02 {
		position: absolute;
		display: flex;
		bottom: 400upx;
		width: 100%;
		height: 100%;
		left: 0upx;
		opacity: 1;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		z-index: 999;
	}



	.avatar {
		width: 260upx;
		height: 260upx;
		overflow: hidden;
		border-radius: 260upx;
		box-shadow: 0upx 0upx 0upx 2upx #fff;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 120upx;
		margin-top: 600upx;
	}

	.avatar image {
		width: 260upx;
		height: 260upx;
	}

	.user-input {
		box-sizing: border-box;
		display: flex;
		flex-direction: column;
		width: 100%;
		height: 360upx;
		padding: 10upx 20upx;
		align-items: center;
		justify-content: center;
	}

	.username-input {
		width: 100%;
		height: 120upx;
		display: flex;
		flex-direction: row;
		border-bottom: 1upx solid #5c695b;
	}
	
	.username-icon{
		width: 60upx;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 0upx 20upx;
	}
	.username-icon image{
		width: 60upx;
		height: 60upx;
	}



	.info {
		margin-top: 30upx;
		color: #564f5d;
		text-transform: uppercase;
		font-size:18upx;
		display: flex;
		flex-direction: row;
		
	}

	.info .info-link {
		margin-left: 16upx;
		font-weight: bold;
		color: #2a2331;

	}
</style>
