<template>
  <div id="app">
    <h1>MainView</h1>
    <router-link to="/login">로그인</router-link>
    <router-link to="/register">회원가입</router-link>
  </div>
</template>

<script>
export default {
  name: 'MainView',
}
</script>

<style>
body {
  margin: 0;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f9f9f9;
}
</style>