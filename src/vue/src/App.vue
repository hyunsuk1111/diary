<template>
  <div id="app">
    <!-- 공통 레이아웃 예: Header 등 여기에 추가 가능 -->
    
    <!-- 라우터가 여기에서 화면을 전환해줌 -->
    <router-view />
  </div>
</template>

<script setup lang="ts">
// 전역 컴포넌트(import)나 Composition API 쓸 경우 여기에 작성
</script>

<style>
body {
  margin: 0;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f9f9f9;
}
</style>