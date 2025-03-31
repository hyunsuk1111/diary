<template>
  <div class="main-view">
    <h2>📔 오늘의 감정은 어땠나요?</h2>
    <p>{{ today }}</p>

    <!-- 달력 컴포넌트 -->
    <CalendarView :diaryMap="diaryMap" @select-date="handleDateSelect" />

    <!-- 오늘 쓴 일기 미리보기 -->
    <DiaryPreview v-if="todayDiary" :diary="todayDiary" />

    <!-- 글쓰기 버튼 -->
    <WriteButton @click="goToWrite" />
  </div>
</template>

<script setup>
import { ref, reactive, defineOptions } from 'vue'
import { useRouter } from 'vue-router'

defineOptions({
  name: 'MainView',
})

import CalendarView from '@/components/CalendarView.vue'
/* import DiaryPreview from '@/components/DiaryPreview.vue'
import WriteButton from '@/components/WriteButton.vue' */

const router = useRouter()
const today = new Date().toLocaleDateString()
const todayDiary = ref(null) // 오늘 일기가 있으면 이걸로 보여줌

// 예시 데이터: 날짜별 일기 제목
const diaryMap = reactive({
  '2025-03-29': '힘들었지만 뿌듯한 하루',
  '2025-03-30': '오늘 클라이밍 재밌었다!',
})

const handleDateSelect = (date) => {
  console.log('날짜 클릭됨:', date)
  // 날짜에 해당하는 일기 내용 불러오기 로직 추가 예정
}

const goToWrite = () => {
  router.push('/write')
}
</script>

<style scoped>
.main-view {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}
</style>