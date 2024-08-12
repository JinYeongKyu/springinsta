<template>
    <div class="login-container">
      <div class="login-box">
        <h1 class="logo">Instagram</h1>
        <form @submit.prevent="handleLogin">
          <div class="form-group">
            <input type="text" v-model="id" placeholder="아이디" required />
          </div>
          <div class="form-group">
            <input type="password" v-model="password" placeholder="비밀번호" required />
          </div>
          <button type="submit" class="login-button">로그인</button>
        </form>
        <div class="or-divider">
          <span></span>
          <span class="or-text">또는</span>
          <span></span>
        </div>
        <div class="forgot-password">
          <a href="#">비밀번호를 잊으셨나요?</a>
        </div>
      </div>
      <div class="signup-box">
        <p>계정이 없으신가요? <router-link to="/sign">가입하기</router-link></p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  const id = ref('');
  const password = ref('');
  const router = useRouter();
  
  const handleLogin = async () => {
    try {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ id: id.value, password: password.value }),
      });
  
      if (response.ok) {
        const message = await response.text();
        alert(message);  // 환영 메시지를 알림으로 표시
        router.push('/');  // 로그인 성공 후 메인 페이지로 이동
      } else {
        alert('로그인 실패: 아이디 또는 비밀번호를 확인하세요.');
      }
    } catch (error) {
      console.error('Error during login:', error);
      alert('로그인 중 오류가 발생했습니다.');
    }
  };
  </script>
  
  <style scoped>
  body {
    background-color: #fafafa;
    font-family: Arial, sans-serif;
  }
  
  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #fafafa;
  }
  
  .login-box {
    width: 350px;
    padding: 40px;
    border: 1px solid #dbdbdb;
    background-color: #fff;
    text-align: center;
    margin-bottom: 10px;
  }
  
  .logo {
    font-family: 'Billabong', cursive;
    font-size: 2.5em;
    margin-bottom: 30px;
  }
  
  .form-group {
    margin-bottom: 10px;
  }
  
  input[type="text"], input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #dbdbdb;
    border-radius: 3px;
    background-color: #fafafa;
    font-size: 14px;
  }
  
  .login-button {
    width: 100%;
    padding: 10px;
    background-color: #0095f6;
    border: none;
    border-radius: 4px;
    color: #fff;
    font-weight: bold;
    cursor: pointer;
    font-size: 14px;
  }
  
  .login-button:hover {
    background-color: #007ac1;
  }
  
  .or-divider {
    display: flex;
    align-items: center;
    text-align: center;
    margin: 20px 0;
  }
  
  .or-divider span:first-child, .or-divider span:last-child {
    flex: 1;
    height: 1px;
    background-color: #dbdbdb;
  }
  
  .or-text {
    padding: 0 10px;
    font-size: 12px;
    color: #8e8e8e;
  }
  
  .social-login {
    display: flex;
    align-items: center;
    justify-content: center;
    color: #385185;
    font-weight: bold;
    cursor: pointer;
  }
  
  .social-login i {
    margin-right: 8px;
  }
  
  .forgot-password {
    margin-top: 20px;
  }
  
  .forgot-password a {
    color: #00376b;
    font-size: 12px;
    text-decoration: none;
  }
  
  .signup-box {
    width: 350px;
    padding: 20px;
    border: 1px solid #dbdbdb;
    background-color: #fff;
    text-align: center;
  }
  
  .signup-box a {
    color: #0095f6;
    font-weight: bold;
    text-decoration: none;
  }
  </style>
  