<template>
  <el-header class="header">
    <!--        <RouterLink to="/">Home</RouterLink>-->
    <!--        <RouterLink to="/write">글작성</RouterLink>-->
    <el-menu mode="horizontal" class="menu-left" :ellipsis="false" router>
      <el-menu-item index="/"><h1>SYP</h1></el-menu-item>
      <el-menu-item index="/all">All</el-menu-item>
      <el-menu-item index="/1">Weekly best</el-menu-item>
      <el-menu-item index="/2">Hashtags</el-menu-item>
      <el-menu-item index="/3">Random</el-menu-item>
      <div class="flex-grow" />
      <el-menu-item index="/write">Share</el-menu-item>
      <el-menu-item v-if="!isLoggedIn" index="/login">Login</el-menu-item>
      <el-sub-menu v-if="isLoggedIn">
        <template #title>My Page</template>
        <el-menu-item index="/My Playlist">My Playlist</el-menu-item>
        <el-menu-item index="/likes">Likes</el-menu-item>
        <el-menu-item index="/setting">Setting</el-menu-item>
        <el-menu-item v-if="isLoggedIn" @click="logout()">Logout</el-menu-item>
      </el-sub-menu>
    </el-menu>
    <!--    <el-menu-->
    <!--      mode="horizontal"-->
    <!--      class="menu-right"-->
    <!--      :ellipsis="false"-->
    <!--      :default-active="rightMenuActive"-->
    <!--      router-->
    <!--    >-->

    <!--    </el-menu>-->
  </el-header>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const isLoggedIn = computed(() => authStore.isLoggedIn)

function logout() {
  ElMessageBox.confirm('로그아웃을 하시겠습니까??', 'Warning', {
    title: '로그아웃',
    confirmButtonText: '로그아웃',
    cancelButtonText: '취소',
    type: 'warning'
  }).then(() => {
    axios.post('/logout').then(() => {
      authStore.logout()
      ElMessage({ type: 'success', message: '로그아웃되었습니다.' })
    })
  })
}
</script>

<style scoped>
.header {
  padding: 0;
}
.flex-grow {
  flex-grow: 1;
}
</style>
