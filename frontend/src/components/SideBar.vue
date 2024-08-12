<template>
  <div class="sidebar bg-white">
    <ul class="nav flex-column">
      <div class="logo">Instagram</div>
      <li class="nav-item">
        <a class="nav-link active" href="#"><i class='fas fa-home'></i>홈</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class='fas fa-search'></i>검색</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class='fas fa-paper-plane'></i>메시지</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class='fas fa-user'></i>프로필</a>
      </li>
    </ul>
  </div>
</template>

<script setup>
// 사이드바 컴포넌트에 별도 로직은 필요 없습니다.
</script>

<style scoped>
.sidebar {
  position: fixed; /* 사이드바를 화면에 고정 */
  top: 0;
  left: 0;
  height: 100vh;
  width: 250px; /* 사이드바 너비 설정 */
  border-right: 1px solid #dbdbdb; /* 구분선 추가 */
  padding: 20px 0;
  overflow-y: auto; /* 내용이 길어지면 스크롤 가능 */
}

.sidebar a {
  text-decoration: none;
  color: #333;
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 30px;
  margin-left: 10px;
  display: flex;
  align-items: center;
}

.sidebar i {
  margin-right: 10px; /* 아이콘과 텍스트 사이 간격 조정 */
}

.logo {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 50px;
  margin-left: 20px;
}
</style>
