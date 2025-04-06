import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/auth/LoginView.vue';
import RegisterView from '@/views/auth/RegisterView.vue';
import DiaryMainView from '@/views/diary/DiaryMainView.vue';
import DiaryWriteView from '@/views/diary/DiaryWriteView.vue';

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: LoginView },
  { path: '/register', component: RegisterView },
  { path: '/diary', component: DiaryMainView, meta: { requiresAuth: true }}, // 로그인된 사용자만 접근  
  { path: '/diary/write', component: DiaryWriteView }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('accessToken')
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login');
  } else {
    next();
  }
})

export default router