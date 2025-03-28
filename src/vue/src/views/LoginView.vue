<template>
  <div id="login-wrapper">
    <form @submit.prevent="handleLogin">
      <input type="text" placeholder="아이디" v-model="email">
      <input type="password" placeholder="비밀번호" v-model="password">
      <button>로그인</button>
    </form>
  </div>
</template>

<script setup>
  import axios from 'axios';
  import { ref } from 'vue'

  const email = ref('');
  const password = ref('');

  const handleLogin = async () => {
    try {
      const response = await axios.post('/auth/login', {
        email: email.value,
        password: password.value
      })
      
      console.log('로그인 성공 ', response.data);
    } catch (error) {
      console.error('로그인 실패', error);
    }
  }//handleLogin
</script>

<style>
body {
  margin: 0;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f9f9f9;
}
</style>