<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view/>
  </div>
</template>


<script>  
  import SockJS from  'sockjs-client';
  import  Stomp from 'stompjs';
  export default {
      data(){
        return{
          stompClient:null
        }
      },
      methods:{
        initWsClient:function(){
            this.wsConn();
        },
        wsConn:function(){
           // 建立连接对象
            let socket = new SockJS('http://localhost:10010/ws');
            // 获取STOMP子协议的客户端对象
            this.stompClient = Stomp.over(socket);
            // 定义客户端的认证信息,按需求配置
            let headers = {
                Authorization:''
            }
            // 向服务器发起websocket连接
            this.stompClient.connect(headers,() => {
                
            }, (err) => {
                console.log('失败')
                console.log(err);
            });
        }
      },
      mounted(){
        this.initWsClient();
      }
  }
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
