<template>
    <div id="app" class="d-flex">
      <SideBar />
      <div class="content-container">
        <MainFeed />
        <RightSideBar />
      </div>
    </div>
  </template>
  
  <script setup>
  import SideBar from '../components/SideBar.vue'
  import MainFeed from '../components/MainFeed.vue'
  import RightSideBar from '../components/RightSideBar.vue'
  </script>
  
  <style scoped>
  #app {
    height: 100vh;
    overflow: hidden; /* 페이지의 스크롤을 방지 */
  }
  
  .d-flex {
    display: flex;
    flex-direction: row;
  }
  
  .content-container {
    display: flex;
    flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
    overflow: hidden; /* 내용이 넘칠 경우 스크롤을 방지 */
  }
  
  .main-feed-wrapper {
    flex: 1;
    overflow-y: auto; /* 스크롤이 필요한 경우 스크롤 가능 */
  }
  </style>
  