<script setup lang="ts">
import { onMounted, ref } from 'vue'
import SimpleArticle from '@/entity/article/SimpleArticle'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const articles = ref<SimpleArticle[]>([])
const pagingData = ref({})

const getArticles = (page: number) => {
  axios.get(`/api/articles?page=${page}`).then((response) => {
    const responseData = response.data.simpleArticleResponses
    articles.value = responseData.content
    pagingData.value = {
      totalPages: responseData.totalPages,
      totalElements: responseData.totalElements,
      size: responseData.size,
      number: responseData.number + 1,
      first: responseData.first,
      last: responseData.last
    }
  })
}

const goToArticle = (articleId: number) => {
  router.push({ name: 'article', params: { articleId } })
}
onMounted(() => {
  getArticles()
})
</script>

<template>
  <div class="row">
    <div class="col-md-4 mb-4 text-center" v-for="article in articles" :key="article.id">
      <el-card shadow="hover" @click="goToArticle(article.id)">
        <el-image style="width: 22rem; height: 18rem" :src="article.thumbnailUrl" :fit="'cover'" />
        <div>
          <span>{{ article.title }}</span>
          <span
            ><el-text type="info"> [{{ article.commentCount }}]</el-text></span
          >
        </div>
        <span class="badge text-bg-secondary mx-1">#java</span>
        <span class="badge text-bg-secondary mx-1">#java12312</span>
        <span class="badge text-bg-secondary mx-1">#java124124214</span>
        <div>{{ article.createdBy }}</div>
        <el-text>{{ article.createdAt }}</el-text>
      </el-card>
    </div>
  </div>
  <div class="d-flex justify-content-center">
    <el-pagination
      v-model:current-page="pagingData.number"
      :total="pagingData.totalElements"
      :default-page-size="9"
      :background="true"
      layout="prev, pager, next"
      @current-change="(page: number) => getArticles(page - 1)"
    />
  </div>
</template>

<style scoped>
.el-card {
  cursor: pointer;
}
</style>
