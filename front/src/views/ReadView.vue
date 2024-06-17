<script setup lang="ts">
import { defineProps, onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import router from '@/router'
import Article from '@/entity/article/Article'
import { ElMessage, ElMessageBox } from 'element-plus'

const props = defineProps<{ articleId: string }>()

const article = ref(new Article())

function getArticle() {
  axios
    .get(`http://localhost:8080/api/articles/${props.articleId}`)
    .then((response) => {
      article.value = new Article(response.data)
      // console.log(article.value)
      // console.log(article.value.playlistItem)
    })
    .catch((e) => {
      console.error('ERROR', e)
    })
}

function deleteArticle() {
  ElMessageBox.confirm('게시글을 삭제하시겠습니까?', 'Warning', {
    title: '삭제',
    confirmButtonText: '삭제',
    cancelButtonText: '취소',
    type: 'warning'
  }).then(() =>
    axios.delete(`http://localhost:8080/api/articles/${props.articleId}`).then(() => {
      ElMessage({ type: 'success', message: '삭제되었습니다.' })
      router.push({ name: 'edit', params: { articleId: props.articleId } })
    })
  )
}

const moveToEdit = () => {
  router.push({ name: 'edit', params: { articleId: props.articleId } })
}

onMounted(() => {
  getArticle()
})
</script>

<template>
  <el-row :gutter="50">
    <el-col :span="10" :align="'end'" :xs="24">
      <el-image :src="article.playlistItem[0]?.thumbnailUrl" />
    </el-col>

    <el-col :span="14">
      <el-row>
        <h2 class="title">{{ article.title }}</h2>
        <div class="d-flex">
          <div class="category">개발</div>
          <div class="regDate">2024-05-19</div>
        </div>
      </el-row>

      <el-row class="pb-5">
        <div class="content">{{ article.content }}</div>
      </el-row>

      <el-row class="pt-4">
        <el-col :span="12">
          <el-button color="#626aef" @click="moveToEdit()">댓글</el-button>
          <el-button color="#626aef" @click="moveToEdit()">좋아요</el-button>
        </el-col>
        <el-col :span="12">
          <el-button color="#626aef" @click="moveToEdit()">수정</el-button>
          <el-button color="#626aef" @click="deleteArticle()">삭제</el-button>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
  <div class="row align-items-center" v-for="item in article.playlistItem" :key="item.videoId">
    <div class="col-md-4 text-end">
      <el-image :src="item.thumbnailUrl" :fit="'scale-down'" style="width: 200px; height: 200px" />
    </div>
    <div class="col-md-5 text-start">
      {{ item.channelTitle }}
    </div>
    <div class="col-md-3">X</div>
  </div>
</template>

<style scoped lang="scss">
.title {
  font-size: 1.6rem;
  font-weight: 600;
  margin: 0;
}

.sub {
  margin-top: 10px;
  font-size: 0.78rem;

  .regDate {
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
