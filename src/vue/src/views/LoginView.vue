<template>
  <div id="login-wrapper">
      <LoginForm button-text="로그인" @login-submit="handleLogin"/>
  </div>
</template>

<script setup>
  import axios from 'axios';
  import LoginForm from '@/components/LoginForm.vue';
  import router from '@/router';

  const handleLogin = async ({ email, password }) => {
    try {
      const response = await axios.post('/auth/login', { email,password });

      const token = response.data.token;
      localStorage.setItem('accessToken', token);

      alert('로그인 성공');
      router.push('/diary');
    } catch (error) {
      alert('로그인 실패');
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