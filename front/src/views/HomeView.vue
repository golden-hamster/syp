<script setup lang="ts">
import axios from "axios";
import {ref} from "vue";

const articles = ref([]);

axios.get("http://localhost:8080/api/articles").then((response) => {
  const data = response.data.articleResponses.content;
  data.forEach((r) => {
    articles.value.push(r);
  });
});

</script>

<template>
  <ul>
    <li v-for="article in articles" :key="article.id">
      <div class="title">
        <router-link :to="{name : 'read', params: {articleId: article.id}}">{{ article.title }}</router-link>
      </div>

      <div class="content">
        {{ article.content }}
      </div>

      <div class="d-flex sub">
        <div class="category">개발</div>
        <div class="regDate">2024-05-19</div>
      </div>
    </li>

  </ul>
</template>

<style scoped lang="scss">
ul{
  list-style: none;
  padding: 0;

  li{
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

    &:last-child{
      margin-bottom: 0;
    }

    .sub {
      margin-top: 4px;
      font-size: 0.78rem;
       .regDate{
         margin-left: 10px;
         color: #5d5d5d;
       }
    }
  }
}
</style>