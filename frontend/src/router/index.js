import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../views/LoginPage.vue'; 
import MainPage from '@/views/MainPage.vue';
import SignPage from '../views/SignPage.vue';
import ProfilePage from '../views/ProfilePage.vue';
import CreatePost from '../views/CreatePost.vue';

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
  { path: '/profile',
    name: 'profile',
    component: ProfilePage },
  { path: '/create',
    name: 'create',
    component: CreatePost },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  });

export default router;
