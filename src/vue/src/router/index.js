import { createRouter, createWebHistory } from 'vue-router';
import Main from '@/views/Main.vue';
import LoginView from '@/views/LoginView.vue';

const routes = [
  { path: '/', component: Main },
  { path: '/login', component: LoginView },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router