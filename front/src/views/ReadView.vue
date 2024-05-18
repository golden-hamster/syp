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

const moveToEdit = () => {
  router.push({name: "edit", params: {articleId: props.articleId}});
}

onMounted(() => {
  axios.get(`http://localhost:8080/api/articles/${props.articleId}`).then((response) => {
    article.value = response.data;
  });
});

</script>


<template>
  <el-row>
    <el-col>
      <h2 class="title">{{ article.title }}</h2>

      <div class="d-flex sub">
        <div class="category">개발</div>
        <div class="regDate">2024-05-19</div>
      </div>
    </el-col>
  </el-row>

  <el-row>
    <el-col>
      <div class="content">{{ article.content }}</div>
    </el-col>
  </el-row>

  <el-row>
    <el-col>
      <div class="d-flex justify-content-end">
        <el-button type="warning" @click="moveToEdit()">수정</el-button>
      </div>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">

.title {
  font-size: 1.6rem;
  font-weight: 600;
  margin:0;
}

.sub {
  margin-top: 10px;
  font-size: 0.78rem;
  .regDate{
    margin-left: 10px;
    color: #5d5d5d;
  }
}

.content {
  margin-top: 20px;
  white-space: break-spaces;
  line-height: 1.5;
}
</style>