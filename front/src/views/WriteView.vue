<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const title = ref("")
const content = ref("")
const apiId = ref("")

const router = useRouter();

const write = () => {
  axios.post("http://localhost:8080/api/articles", {
    title: title.value,
    content: content.value,
    apiId: apiId.value
  })
      .then(() => {
        router.replace({name: "home"});
      });
}
</script>

<template>
  <div>
    <el-input v-model="title" placeholder="제목을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="content" type="textarea" rows="15" placeholder="내용을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="apiId" type="text" rows="1" placeholder="플레이리스트의 url 을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <div class="d-flex justify-content-end">
    <el-button type="primary" @click="write()">글 작성완료</el-button>
    </div>
  </div>

</template>

<style scoped>

</style>