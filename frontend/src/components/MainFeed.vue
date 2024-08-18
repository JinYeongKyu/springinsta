<template>
  <div class="main-feed-wrapper">
    <div class="main-feed">
      <div class="post" v-for="post in posts" :key="post.id">
        <div class="post-header">
          <h5>{{ post.userId }}</h5>
          <p>{{ post.createdAt }}</p>
        </div>
        <p>{{ post.location }}</p>
        <img :src="post.imgUrl" alt="Post Image" class="post-image" />
        <div class="post-actions">
          <i class='far fa-heart' style='font-size:24px'></i>
          <i class='far fa-comment' style='font-size:24px'></i>
          <i class='far fa-paper-plane' style='font-size:24px'></i>
        </div>
        <div class="post-like">
          <p>좋아요 {{ post.likesCount }}개</p>
        </div>
        <div class="post-content">
          <p>{{ post.content }}</p>
        </div>
        <div class="post-hashtag">
          <p>{{ post.hashtag }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const posts = ref([]);

const fetchPosts = async () => {
  try {
    const response = await axios.get('/api/post');
    posts.value = response.data;
    console.log(posts.value)
  } catch (error) {
    console.error('Error fetching posts:', error);
  }
};

onMounted(() => {
  fetchPosts();
});
</script>

<style scoped>
.main-feed-wrapper {
  flex: 1;
  overflow-y: auto; /* 스크롤이 필요한 경우 스크롤 가능 */
  margin-left: 250px; /* 사이드바의 너비만큼 왼쪽 여백 설정 */
  margin-right: 300px; /* 오른쪽 사이드바의 너비만큼 오른쪽 여백 설정 */
}

.main-feed {
  display: flex;
  flex-direction: column;
  align-items: center; /* 중앙 정렬 */
  padding: 20px;
}

.post {
  background: white;
  border: 1px solid #dbdbdb;
  margin-bottom: 20px;
  padding: 15px;
  width: 50%;
  display: flex;
  flex-direction: column;
}

.post-header {
  display: flex;
  justify-content: flex-start; /* 이름과 날짜를 양쪽 끝으로 배치 */
}

.post-header h5 {
  margin-right: 15px;
}

.post-image {
  width: 100%; /* 이미지의 너비를 포스트 컨테이너의 너비에 맞추기 */
  height: auto; /* 이미지의 비율 유지 */
  margin-bottom: 10px; /* 이미지와 텍스트 사이의 여백 */
}

.post-like {
  display: flex;
  flex-direction: column;
  align-items: left;
}

.post-hashtag {
  display: flex;
  flex-direction: column;
  align-items: left;
}

.post-actions {
  display: flex;
  justify-content: flex-start; /* 아이콘을 왼쪽 정렬 */
  margin-bottom: 10px; /* 아이콘과 포스트 내용 사이의 여백 */
}

.post-actions i {
  margin-right: 15px; /* 아이콘 사이의 여백을 늘림 */
}
</style>
