<template>
  <div class="right-sidebar">
    <div class="suggestions bg-white p-3">
      <h6>회원님들의 친구</h6>
      <ul class="list-unstyled">
        <li><a href="#">User 1</a></li>
        <li><a href="#">User 2</a></li>
        <li><a href="#">User 3</a></li>
        <li><a href="#">User 4</a></li>
      </ul>
    </div>
    <div class="profile bg-white p-3 mt-4">
      <h6>{{ username }}</h6>
      <p>Username</p>
      <button @click="handleAuthAction" class="btn btn-primary mt-3">{{ isLoggedIn ? '로그아웃' : '로그인' }}</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const username = ref('');  // 사용자 이름을 저장할 ref
const isLoggedIn = ref(false);  // 로그인 상태를 저장할 ref

const fetchCurrentUser = async () => {
  try {
    const response = await fetch('/api/current-user');
    if (response.ok) {
      const user = await response.json();
      username.value = user.id;  // 서버에서 받은 사용자 ID를 저장
      isLoggedIn.value = true;  // 로그인 상태로 설정
    } else {
      username.value = 'Guest';  // 로그인하지 않은 경우 기본값 설정
      isLoggedIn.value = false;  // 로그인되지 않은 상태로 설정
    }
  } catch (error) {
    console.error('Failed to fetch user:', error);
    username.value = 'Guest';  // 에러가 발생한 경우 기본값 설정
    isLoggedIn.value = false;  // 로그인되지 않은 상태로 설정
  }
};

const handleAuthAction = async () => {
  if (isLoggedIn.value) {
    try {
      const response = await fetch('/api/logout', { method: 'POST' });
      if (response.ok) {
        isLoggedIn.value = false;
        username.value = 'Guest';
        alert('로그아웃 되었습니다.');
        router.push('/login');
      }
    } 
    catch (error) {
      console.error('Failed to logout:', error);
    }
  } 
  else {
    router.push('/login');
  }
};

onMounted(() => {
  fetchCurrentUser();  // 컴포넌트가 마운트되면 현재 사용자 정보를 가져옴
});
</script>

<style scoped>
.right-sidebar {
  position: fixed; /* 사이드바를 화면에 고정 */
  top: 0;
  right: 0;
  height: 100vh;
  width: 300px; /* 사이드바 너비 설정 */
  padding: 20px 0;
  overflow-y: auto; /* 내용이 길어지면 스크롤 가능 */
}
</style>
