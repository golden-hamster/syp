<script setup lang="ts">
import { reactive, ref } from 'vue'
import Login from '@/entity/member/Login'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import HttpError from '@/http/HttpError'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const state = ref(new Login())
const authStore = useAuthStore()
const router = useRouter()

const doLogin = () => {
  axios
    .post('/api/login', state.value)
    .then(() => {
      console.log(authStore.isLoggedIn)
      ElMessage({ type: 'success', message: '환영합니다 :)' })

      axios.get('/api/members/me').then((response) => authStore.login(response.data.nickname))
      console.log(authStore)
      router.replace({ name: 'home' })
    })
    .catch(() => {
      ElMessage({ type: 'error', message: '로그인 실패' })
    })
}
</script>
<template>
  <el-row>
    <el-col :span="5" :offset="9">
      <el-form label-position="top">
        <el-form-item label="아이디">
          <el-input v-model="state.username"></el-input>
        </el-form-item>

        <el-form-item label="비밀번호">
          <el-input type="password" v-model="state.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="doLogin()">로그인</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style scoped></style>
