<template>
  <div class="main-view">
    <h2>📔 오늘의 감정은 어땠나요?</h2>
    <p>{{ today }}</p>

    <!-- 달력 컴포넌트 -->
    <CalendarView :diaryMap="diaryMap" @select-date="handleDateSelect" />

  </div>
</template>

<script setup>
import { reactive, defineOptions } from 'vue'
import { useRouter } from 'vue-router'

defineOptions({
  name: 'MainView',
})

import CalendarView from '@/components/CalendarView.vue'

const router = useRouter()
const today = new Date().toLocaleDateString()

// 예시 데이터: 날짜별 일기 제목
const diaryMap = reactive({});

const handleDateSelect = (date) => {
  console.log('날짜 클릭됨:', date);
  router.push({ path: '/diary/write', query: { date } });
}

</script>

<style scoped>
.main-view {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}
</style>