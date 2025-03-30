<template>
    <form @submit.prevent="onSubmit">
        <input type="text" placeholder="아이디" v-model="email">
        <input type="password" placeholder="비밀번호" v-model="password">
        <button>{{ props.buttonText }}</button>
    </form>
  </template>

<script setup>
import { ref, defineEmits, defineProps } from 'vue'

const email = ref('')
const password = ref('')

const emit = defineEmits(['login-submit'])
const props = defineProps({
  buttonText: { type: String, default: '로그인' }
})

const onSubmit = () => {
  emit('login-submit', {
    email: email.value,
    password: password.value
  })
}//onSubmit

</script>