<template>
    <div class="calendar">
      <h3>📅 3월</h3>
      <div class="calendar-grid">
          <div
            v-for="day in days"
            :key="day"
            class="day"
            :class="{ hasDiary: props.diaryMap[day] }"
            @click="handleDateClick(day)"
          >
          <div class="date">
            {{ day.split('-')[2] }}
          </div>
          <div class="title">
            {{ day }} - {{ props.diaryMap[day]}}
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { computed, defineOptions, defineProps, defineEmits } from 'vue'

  defineOptions({ name: 'CalendarView' });
  const emit = defineEmits(['select-date']);
  const props = defineProps({
    diaryMap: Object
  }); 

  //실제 달력 불러오기 수정
  const days = computed(() => {
    const arr = []
    for (let i = 1; i <= 31; i++) {
      const day = i.toString().padStart(2, '0')
      arr.push(`2025-03-${day}`)
    }
    return arr
  })//days

  const handleDateClick = (day) => {
    emit('select-date', day);
  }//handleDateClick

  </script>
  
  <style scoped>
  .calendar-grid {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    gap: 6px;
  }

  .day {
    padding: 10px;
    text-align: center;
    background-color: #f4f4f4;
    border-radius: 6px;
    cursor: pointer;
  }

  .day.hasDiary {
    background-color: #ffe4b5;
    font-weight: bold;
  }

  .title {
  font-size: 12px;
  margin-top: 4px;
  color: #444;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
  </style>
  