<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import Playlist from '@/entity/playlist/Playlist'

const myPlaylists = ref<Playlist[]>([])

const getMyPlaylist = () => {
  axios.get('/api/playlists/me').then((response) => {
    myPlaylists.value = response.data.playlistResponses
  })
}

onMounted(() => {
  getMyPlaylist()
})
</script>
<template>
  <div class="row">
    <div
      class="align-items-center border rounded-5 bg-white mb-3"
      v-for="playlist in myPlaylists"
      :key="playlist.id"
    >
      <div class="row playlist-title">
        <div class="col-4 text-center">{{ playlist.title }}</div>
        <div class="col-8 text-end">바로가기</div>
      </div>
      <div class="row">
        <el-carousel :interval="5000" trigger="click" type="card" height="300px">
          <el-carousel-item v-for="item in playlist.items" :key="playlist.id">
            <div class="carousel-content">
              <el-image class="carousel-image" :src="item.thumbnailUrl" style="width: 26em" />
              <div class="video-title">{{ item.videoTitle }}</div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<style scoped>
.carousel-image {
  display: block;
  margin: auto;
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.el-carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
}

.video-title {
  font-size: 1em;
}

.playlist-title {
  font-size: 22px;
  font-weight: bold;
}
</style>
