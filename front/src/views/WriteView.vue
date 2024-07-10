<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import Item from '@/entity/article/Item'
import { ElMessage } from 'element-plus'

const title = ref('')
const content = ref('')
const videoUrl = ref('')
const item = ref(new Item())
const items = ref<Item[]>([])

const router = useRouter()

const youtubeAxios = axios.create({
  withCredentials: false // Youtube API 호출에서는 인증 정보 필요 없음
})

const write = () => {
  axios
    .post('/api/articles', {
      title: title.value,
      content: content.value,
      thumbnailUrl: items.value[0].thumbnailUrl,
      itemDtoList: items.value
    })
    .then(() => {
      ElMessage({ type: 'success', message: '게시글이 작성되었습니다.' })
      router.replace({ name: 'home' })
    })
}

function extractVideoId(url: string): string | null {
  const regex =
    /(?:https?:\/\/)?(?:www\.)?youtube\.com\/.*v=([a-zA-Z0-9_-]+)|(?:https?:\/\/)?(?:www\.)?youtu\.be\/([a-zA-Z0-9_-]+)/
  const match = url.match(regex)
  return match ? match[1] || match[2] : null
}

function addItem() {
  const videoId = extractVideoId(videoUrl.value)
  if (!videoId) {
    ElMessage({ type: 'error', message: '유효하지 않은 url 입니다.' })
    return
  }

  const isDuplicate = items.value.some((item) => item.videoId === videoId)
  if (isDuplicate) {
    ElMessage({ type: 'error', message: '중복 url 입니다.' })
    return
  }
  youtubeAxios
    .get(`https://www.googleapis.com/youtube/v3/videos`, {
      params: {
        part: 'snippet',
        id: videoId,
        key: 'AIzaSyAOGhPJk8EoxaWYwmuQUPUMCnJ_bugn6Xs'
      }
    })
    .then((response) => {
      const newItem = new Item({
        thumbnailUrl: response.data.items[0].snippet.thumbnails.medium.url,
        videoId: response.data.items[0].id,
        videoTitle: response.data.items[0].snippet.title
      })
      items.value.push(newItem)
      videoUrl.value = '' // 인풋 필드 초기화
    })
}

function deleteItem(videoId: string) {
  items.value = items.value.filter((item) => item.videoId !== videoId)
  ElMessage({ type: 'success', message: '플레이리스트 항목이 삭제되었습니다.' })
}
</script>

<template>
  <div class="container">
    <el-col :offset="15" :pull="8">
      <el-input
        v-model="title"
        maxlength="30"
        show-word-limit
        style="font-size: large"
        placeholder="제목을 입력해주세요"
      />
    </el-col>

    <div class="mt-2">
      <el-col :offset="15" :pull="8">
        <el-input
          v-model="content"
          type="textarea"
          rows="2"
          maxlength="70"
          show-word-limit
          style="font-size: large"
          placeholder="간단하게 내용을 입력해주세요"
        />
      </el-col>
    </div>

    <div class="row mt-5">
      <div class="col-8 text-end">
        <el-input
          v-model="videoUrl"
          type="text"
          rows="1"
          style="width: 28rem"
          placeholder="플레이리스트에 추가할 영상의 url 을 입력해주세요"
        />
      </div>

      <div class="col-4 text-start">
        <div>
          <el-button color="#626aef" @click="addItem()">영상 추가하기</el-button>
        </div>
      </div>
    </div>

    <div class="row align-items-center" v-for="item in items" :key="item.videoId">
      <div class="col-md-4 text-end">
        <el-image
          :src="item.thumbnailUrl"
          :fit="'scale-down'"
          style="width: 200px; height: 200px"
        />
      </div>
      <div class="col-md-5 text-start">
        {{ item.videoTitle }}
      </div>
      <div class="col-md-3">
        <el-button color="#626aef" @click="deleteItem(item.videoId)">X</el-button>
      </div>
    </div>

    <div class="mt-5">
      <el-col :offset="20" :pull="10">
        <div class="d-flex justify-content-center">
          <el-button color="#626aef" @click="write()">플레이리스트 게시하기</el-button>
        </div>
      </el-col>
    </div>
  </div>
</template>

<style scoped></style>
