<template>
    <div class="login-container" style="max-width: 400px; margin: 100px auto;">
      <h2 style="text-align: center; margin-bottom: 20px;">로그인</h2>
      <form @submit.prevent="handleLogin">
        <div style="margin-bottom: 16px;">
          <label for="email">이메일</label>
          <input
            id="email"
            v-model="email"
            type="email"
            required
            style="width: 100%; padding: 8px;"
          />
        </div>
  
        <div style="margin-bottom: 16px;">
          <label for="password">비밀번호</label>
          <input
            id="password"
            v-model="password"
            type="password"
            required
            style="width: 100%; padding: 8px;"
          />
        </div>
  
        <button
          type="submit"
          style="width: 100%; padding: 10px; background-color: #42b983; color: white; border: none; cursor: pointer;"
        >
          로그인
        </button>
  
        <p v-if="errorMessage" style="color: red; margin-top: 12px;">
          {{ errorMessage }}
        </p>
      </form>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  import axios from '@/api/axios'  // 너가 만든 axios 인스턴스를 여기에 import 하면 좋아
  
  const email = ref('')
  const password = ref('')
  const errorMessage = ref('')
  const router = useRouter()
  
  const handleLogin = async () => {
    errorMessage.value = ''
    try {
      const response = await axios.post('/api/auth/login', {
        email: email.value,
        password: password.value,
      })
  
      const token = response.data.token
      localStorage.setItem('accessToken', token)
  
      // 로그인 성공 시 메인 페이지로 이동
      router.push('/diaries')
    } catch (error: any) {
      errorMessage.value = '이메일 또는 비밀번호가 올바르지 않습니다.'
      console.error('로그인 실패:', error)
    }
  }
  </script>