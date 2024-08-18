<template>
    <SideBar />
    <div class="add-post-page">
      <h2>새 게시물</h2>
      <form @submit.prevent="submitPost">
        <div class="form-group">
          <label for="imageUpload">사진 업로드</label>
          <input type="file" id="imageUpload" @change="handleImageUpload" accept="image/*" />
          <div v-if="imagePreview" class="image-preview">
            <img :src="imagePreview" alt="Preview Image" />
          </div>
        </div>
        <div class="form-group">
          <label for="location">위치</label>
          <textarea id="location" v-model="location" rows="1" placeholder="위치를 입력하세요"></textarea>
        </div>
        <div class="form-group">
          <label for="content">문구</label>
          <textarea id="content" v-model="content" rows="3" placeholder="문구를 입력하세요"></textarea>
        </div>
        <div class="form-group">
          <label for="hashtag">해시태그</label>
          <textarea id="hashtag" v-model="hashtag" rows="2" placeholder="해시태그를 입력하세요"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">게시물 올리기</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import SideBar from '../components/SideBar.vue';
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const imagePreview = ref(null);
  const content = ref('');
  const location = ref('');
  const hashtag = ref('');
  const imageFile = ref(null);
  
  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      imageFile.value = file;
      const reader = new FileReader();
      reader.onload = (e) => {
        imagePreview.value = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  };
  
  const submitPost = async () => {
    if (!imageFile.value) {
      alert("사진을 업로드해주세요!");
      return;
    }
  
    const formData = new FormData();
    formData.append('imageFile', imageFile.value);
    formData.append('content', content.value);
    formData.append('location', location.value);
    formData.append('hashtag', hashtag.value);
  
    try {
      const response = await fetch('/api/create', {
        method: 'POST',
        body: formData,
      });
  
      if (response.ok) {
        alert("게시물이 성공적으로 업로드되었습니다!");
        // 업로드 후 초기화
        imagePreview.value = null;
        content.value = '';
        location.value = '';
        hashtag.value = '';
        imageFile.value = null;
        router.push('/');
      } else {
        const errorText = await response.text();
        alert("게시물 업로드 중 오류 발생: " + errorText);
      }
    } catch (error) {
      console.error("게시물 업로드 중 오류 발생:", error);
      alert("게시물 업로드 중 오류 발생: " + error.message);
    }
  };
  </script>

  
  <style scoped>
  .add-post-page {
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    padding-top: 50px;
    margin-left: 600px; /* 사이드바 너비를 고려하여 왼쪽 여백을 설정 */
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
  }
  
  input[type="file"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    resize: none;
  }
  
  .image-preview {
    margin-top: 10px;
  }
  
  .image-preview img {
    max-width: 100%;
    border-radius: 10px;
    object-fit: cover;
  }
  
  button {
    width: 100%;
    padding: 10px;
    border: none;
    background-color: #0095f6;
    color: white;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #007bb5;
  }
  </style>
  