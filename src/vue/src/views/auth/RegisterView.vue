<template>
    <div id="register-wrapper">
        <form @submit.prevent="handleRegister">
        <div class="form-group">
            <input type="text" v-model="email" placeholder="이메일" />
            <button type="button" @click="checkEmail">중복 확인</button>
            <p class="validation-msg">{{ emailMessage }}</p>
        </div>
        <div class="form-group">
            <input type="password" v-model="password" placeholder="비밀번호" />
            <input type="password" v-model="passwordCheck" placeholder="비밀번호 확인" />
            <p class="validation-msg" v-if="passwordMismatch">비밀번호가 일치하지 않습니다.</p>
        </div>
        <div class="form-group">
            <input type="text" v-model="nickname" placeholder="닉네임" />
        </div>
        <div class="form-group">
            <button type="submit">회원가입</button>
        </div>
    </form>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, computed, watch } from 'vue'
import router from '@/router';

const email = ref('');
const password = ref('');
const passwordCheck = ref('');
const nickname = ref('');

const emailMessage = ref('');
const isEmailValid = ref(true);

watch(email, () => {
  emailMessage.value = ''
  isEmailValid.value = false
})

const passwordMismatch = computed(() => {
  return password.value && passwordCheck.value && password.value !== passwordCheck.value;
});//passwordMismatch

const isFormValid = computed(() => {
  return email.value && password.value && passwordCheck.value && nickname.value &&
         //isEmailValid.value && !passwordMismatch.value;
         !isEmailValid.value && !passwordMismatch.value;
});//isFormValid

const checkEmail = async () => {
  if (!email.value) {
    emailMessage.value = '이메일을 입력해주세요.';
    isEmailValid.value = false;
    return;
  }

  try {
    const response = await axios.get('/auth/checkEmail', {
      params: { email: email.value }
    })

    if (response.data === true) {
      emailMessage.value = '이미 사용 중인 이메일입니다.';
      isEmailValid.value = false;
    } else {
      emailMessage.value = '사용 가능한 이메일입니다.';
      isEmailValid.value = true;
    }
  } catch (error) {
    emailMessage.value = '확인 중 오류 발생';
    isEmailValid.value = false;
  }
}//checkEmail

const handleRegister = async () => {
  if (!isFormValid.value) {
    alert('중복확인 및 입력값들을 확인해주세요');
    return;
  }

  try {
      const response = await axios.post('/auth/register', {
        email: email.value,
        password: password.value,
        nickName: nickname.value
      });

      alert('회원가입이 완료되었습니다.')
      console.log('회원가입 성공 : ', response.data);

      router.push('/login');
  } catch (error) {
    console.error('회원가입 실패', error);
  }
}//handleRegister
</script>

<style>

</style>