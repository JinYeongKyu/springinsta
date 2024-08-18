<template>
  <SideBar />
  <div class="profile-page">
    <div class="profile-header">
      <div class="profile-picture">
        <img :src="profilePicture" alt="Profile Picture" />
      </div>
      <div class="profile-info">
        <div class="profile-username-edit">
          <h2>{{ username }}</h2>
          <button class="btn btn-primary">프로필 편집</button>
        </div>
        <div class="profile-stats">
          <span><strong>게시물 {{ posts.length }}</strong></span>
          <span><strong>팔로워 {{ followers }}</strong></span>
          <span><strong>팔로우 {{ following }}</strong></span>
        </div>
        <h6>{{ name }}</h6>
      </div>
    </div>
    <hr>
    <button @click="createPage" class="btn btn-primary">게시물 추가</button>
    <br>
    <div class="profile-content">
      <div class="posts-grid">
        <div v-for="post in posts" :key="post.id" class="post-item">
          <img :src="post.imageUrl" alt="Post Image" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import SideBar from '../components/SideBar.vue'
import { useRouter } from 'vue-router';

const router = useRouter();
const username = ref('');
const name = ref('');
const profilePicture = ref('https://via.placeholder.com/800x800');  // 프로필 사진 경로
const followers = ref(120);  // 팔로워 수
const following = ref(150);  // 팔로잉 수
const posts = ref([]);

const fetchCurrentUser = async () => {
  const response = await fetch('/api/current-user');
  if (response.ok) {
    const user = await response.json();
    console.log(user)
    username.value = user.id;
    name.value = user.name;
  } else {
    username.value = 'Guest';
  }
};

const createPage = () => {
  router.push('/create');
};

onMounted(() => {
  // 예시로, 게시물 데이터를 하드코딩
  posts.value = [
    { id: 1, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 2, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 3, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 4, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 5, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 6, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 7, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 8, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 9, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 10, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 11, imageUrl: 'https://via.placeholder.com/800x800' },
    { id: 12, imageUrl: 'https://via.placeholder.com/800x800' },
  ];
  fetchCurrentUser();
});
</script>

<style scoped>
.profile-page {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
  padding-top: 50px;
  margin-left: 400px; /* 사이드바 너비를 고려하여 왼쪽 여백을 설정 */
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.profile-picture img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
}

.profile-info {
  margin-left: 30px;
}

.profile-username-edit {
  display: flex;
  align-items: center;
  gap: 20px;
}

.profile-info h2 {
  margin: 0;
}

.profile-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 10px;
}

.profile-stats span {
  font-size: 16px;
}

.profile-content {
  margin-top: 20px;
}

.posts-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
}

.post-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}
</style>
