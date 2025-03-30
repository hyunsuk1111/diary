<template>
  <div id="login-wrapper">
      <LoginForm button-text="로그인" @login-submit="handleLogin"/>
  </div>
</template>

<script setup>
  import axios from 'axios';
  import LoginForm from '@/components/LoginForm.vue';

  const handleLogin = async ({ email, password }) => {
    try {
      const response = await axios.post('/auth/login', { email,password })
      
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