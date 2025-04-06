<template>
    <div class="diary-write">
      <h2>📅 {{ date }} 오늘의 감정은?</h2>
  
      <input
        v-model="title"
        type="text"
        placeholder="제목을 입력하세요"
        class="diary-input"
      />
  
      <textarea
        v-model="content"
        placeholder="내용을 입력하세요"
        class="diary-textarea"
      />
  
      <button class="save-button" @click="handleSave">💾 저장하기</button>
    </div>
  </template>
  
  <script setup>
  import axios from 'axios';
  import { ref } from 'vue'
  import { useRoute } from 'vue-router'
  
  const router = useRoute();
  const date = router.query.date;
  
  const title = ref('');
  const content = ref('');
  
  const handleSave = async () => {
    try {
      const token = localStorage.getItem('accessToken');

      const response = await axios.post('/diary/write',
      { 
        diaryDate: date, 
        title: title.value, 
        content: content.value 
      }, 
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      console.log('작성 성공 ', response);
    } catch (error) {
      console.error('작성 실패', error);
    }
  }//handleSave
  </script>
  
  <style scoped>
  .diary-write {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  .diary-input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    font-size: 16px;
  }
  .diary-textarea {
    width: 100%;
    height: 200px;
    padding: 10px;
    font-size: 16px;
    resize: none;
  }
  .save-button {
    margin-top: 10px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
  }
  </style>