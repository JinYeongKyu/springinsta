import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../views/LoginPage.vue'; 
import MainPage from '@/views/MainPage.vue';
import SignPage from '../views/SignPage.vue'; // 추가된 컴포넌트

const routes = [
  { path: '/login',
    name: 'Login',
    component: LoginPage },

  { path: '/',
    name: 'Main',
    component: MainPage },
  { path: '/sign',
    name: 'sign',
    component: SignPage },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  });

export default router;
