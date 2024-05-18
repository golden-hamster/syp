<script setup lang="ts">

import {defineProps, onMounted, ref} from "vue";
import axios from "axios";
import router from "@/router";

const props = defineProps({
  articleId: {
    type: [Number, String],
    require: true,
  },
});

const article = ref({
  id: 0,
  title: "",
  content: "",
});

axios.get(`http://localhost:8080/api/articles/${props.articleId}`).then((response) => {
  article.value = response.data;
});

const edit = () => {
  axios.patch(`http://localhost:8080/api/articles/${props.articleId}`, article.value).then(() => {
    router.replace({name: home})
  })
}

</script>

<template>
  <div>
    <el-input v-model="article.title" placeholder="제목을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="article.content" type="textarea" rows="15" placeholder="내용을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="article.apiId" type="text" rows="1" placeholder="플레이리스트의 url 을 입력해주세요"/>
  </div>

  <div class="mt-2 d-flex justify-content-end">
    <el-button type="warning" @click="edit()">글 수정완료</el-button>
  </div>

</template>

<style scoped>

</style>