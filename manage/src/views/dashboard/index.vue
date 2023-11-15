<template>
  <div>
    <h2>欢迎您：{{username}} </h2>
    <h3 v-if="usertype=='TOURIST'">
      您登陆的账号为演示账号，为了保障数据安全，只有浏览权限，相关编辑按钮已隐藏
    </h3>
    <h3 v-if="usertype=='TOURIST'">
      联系微信：MMRWXM
    </h3>
  </div>

</template>

<script>
  import Cookies from 'js-cookie'
  export default {
    name: 'dashboard',
    data() {
      return {
        username: "",
        usertype: ""
      }
    },
    mounted() {
      this.usertype = Cookies.get("userinfo");
      if (this.usertype == 'TOURIST') {
        this.username = "游客";
      } else {
        this.username = "超级管理员";
      }
    },
  }
</script>

<style>

</style>
