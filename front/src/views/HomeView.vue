<script setup lang="ts">
import axios from 'axios'
import { onMounted, ref } from 'vue'
import SimpleArticle from '@/entity/article/SimpleArticle'

const articles = ref<SimpleArticle[]>([])
// const articles = ref([])

const getArticles = () => {
  axios.get('/api/articles').then((response) => {
    const data = response.data.simpleArticleResponses.content
    articles.value = data
    console.log(articles.value)
    // data.forEach((r) => {
    //   articles.value.push(r)
    // })
  })
}
onMounted(() => {
  getArticles()
})
</script>

<template>
  <ul>
    <li v-for="article in articles" :key="article.id">
      <div class="title">
        <router-link :to="{ name: 'article', params: { articleId: article.id } }">{{
          article.title
        }}</router-link>
      </div>

      <div class="content">
        {{ article.thumbnailUrl }}
      </div>

      <div class="d-flex sub">
        <div class="author">{{ article.createdBy }}</div>
        <div class="regDate">{{ article.createdAt }}</div>
      </div>
    </li>
  </ul>
  <ul>
    <li v-for="article in articles" :key="article.id">
      <div class="title">
        <router-link :to="{ name: 'article', params: { articleId: article.id } }">{{
          article.title
        }}</router-link>
      </div>

      <div class="content">
        {{ article.thumbnailUrl }}
      </div>

      <div class="d-flex sub">
        <div class="author">{{ article.createdBy }}</div>
        <div class="regDate">{{ article.createdAt }}</div>
      </div>
    </li>
  </ul>
</template>

<style scoped lang="scss">
ul {
  list-style: none;
  padding: 0;

  li {
    margin-bottom: 1.3rem;

    .title {
      a {
        font-size: 1.2rem;
        color: #303030;
        text-decoration: none;
      }

      &:hover {
        text-decoration: underline;
      }
    }

    .content {
      font-size: 0.95rem;
      color: #5d5d5d;
    }

    &:last-child {
      margin-bottom: 0;
    }

    .sub {
      margin-top: 4px;
      font-size: 0.78rem;
      .regDate {
        margin-left: 10px;
        color: #5d5d5d;
      }
    }
  }
}
</style>
