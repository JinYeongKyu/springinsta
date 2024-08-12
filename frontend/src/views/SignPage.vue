<template>
  <div class="signup-form">
    <h2>회원가입</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="id">아이디</label>
        <input v-model="form.id" type="text" id="id" required />
        <span v-if="errors.id" class="error">{{ errors.id }}</span>
      </div>    
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input v-model="form.password" type="password" id="password" required />
        <span v-if="errors.password" class="error">{{ errors.password }}</span>
      </div>
      <div class="form-group">
        <label for="confirmPassword">비밀번호 확인</label>
        <input v-model="form.confirmPassword" type="password" id="confirmPassword" required />
        <span v-if="errors.confirmPassword" class="error">{{ errors.confirmPassword }}</span>
      </div>
      <div class="form-group">
        <label for="email">이메일</label>
        <input v-model="form.email" type="email" id="email" required />
        <span v-if="errors.email" class="error">{{ errors.email }}</span>
      </div>
      <div class="form-group">
        <label for="name">성명</label>
        <input v-model="form.name" type="text" id="name" />
      </div>
      <div class="form-group">
        <label for="profilePicture">프로필 사진 URL (선택)</label>
        <input v-model="form.profilePicture" type="text" id="profilePicture" />
      </div>
      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();

const form = ref({
  id: '',
  email: '',
  password: '',
  confirmPassword: '',
  name: '',
  profilePicture: ''
});

const errors = ref({});

const validateForm = () => {
  errors.value = {};

  if (!form.value.id) errors.value.id = '아이디를 입력해주세요.';
  if (!form.value.email) errors.value.email = '이메일을 입력해주세요.';
  if (!form.value.password) errors.value.password = '비밀번호를 입력해주세요.';
  if (form.value.password !== form.value.confirmPassword) errors.value.confirmPassword = '비밀번호가 일치하지 않습니다.';
  return Object.keys(errors.value).length === 0;
};

const handleSubmit = async () => {
  if (!validateForm()) return;

  try {
    const response = await axios.post('/api/signup', form.value);
    console.log(response.data)
    alert('회원가입이 완료되었습니다.');
    router.push('/login');
  } catch (error) {
    if (error.response && error.response.data) {
      console.error('Error:', error.response.data);
      alert('회원가입에 실패했습니다.');
    } else {
      console.error('Unexpected Error:', error);
      alert('알 수 없는 오류가 발생했습니다.');
    }
  }
};

</script>
  
  <style scoped>
  .signup-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input, textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .error {
    color: red;
    font-size: 0.875em;
  }
  </style>
  