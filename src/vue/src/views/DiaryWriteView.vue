<template>
    <div class="diary-write">
      <h2>📅 {{ date }} 일기 쓰기</h2>
  
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
  import { ref } from 'vue'
  import { useRoute } from 'vue-router'
  
  const route = useRoute()
  const date = route.query.date || '날짜 없음'
  
  const title = ref('')
  const content = ref('')
  
  const handleSave = () => {
    console.log('저장할 데이터:', {
      date,
      title: title.value,
      content: content.value,
    })
  
    // 추후 axios.post('/api/diary', { diaryDate: date, title, content }) 추가 예정
  }
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