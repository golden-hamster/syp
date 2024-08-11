<script setup lang="ts">
import { defineProps, onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import Article from '@/entity/article/Article'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { Comment, Star, StarFilled, UserFilled } from '@element-plus/icons-vue'
import Playlist from '@/entity/playlist/Playlist'
import type Item from '@/entity/article/Item'

const props = defineProps<{ articleId: string }>()

const router = useRouter()

const article = ref(new Article())
const likesCount = ref(0)
const isLiked = ref(false)
const addItemModal = ref(false)
const selectedItem = ref<Item>()

const openAddItemModal = (item: Item) => {
  selectedItem.value = item
  addItemModal.value = true
}

const addItem = (playlistId: number) => {
  if (!selectedItem.value) return

  axios
    .post(`/api/playlists/${playlistId}`, {
      videoId: selectedItem.value?.videoId,
      videoTitle: selectedItem.value?.videoTitle,
      thumbnailUrl: selectedItem.value?.thumbnailUrl
    })
    .then(() => {
      ElMessage({ type: 'success', message: '동영상이 플레이리스트에 추가되었습니다.' })
      addItemModal.value = false
    })
    .catch((error) => {
      console.error('동영상 추가 에러', error)
      ElMessage({ type: 'error', message: '동영상 추가 중 오류가 발생했습니다.' })
    })
}

const getArticle = () => {
  axios
    .get(`/api/articles/${props.articleId}`)
    .then((response) => {
      article.value = new Article(response.data)
      likesCount.value = response.data.likesCount
      isLiked.value = response.data.isLiked
      // console.log(article.value)
      // console.log(article.value.playlistItem)
    })
    .catch((e) => {
      console.error('ERROR', e)
    })
}

const deleteArticle = () => {
  ElMessageBox.confirm('게시글을 삭제하시겠습니까?', 'Warning', {
    title: '게시글 삭제',
    confirmButtonText: '삭제',
    cancelButtonText: '취소',
    type: 'warning'
  }).then(() =>
    axios.delete(`/api/articles/${props.articleId}`).then(() => {
      ElMessage({ type: 'success', message: '게시글이 삭제되었습니다.' })
      router.replace('/')
    })
  )
}

const moveToEdit = () => {
  router.push({ name: 'update', params: { articleId: props.articleId } })
}

const toggleLikeArticle = () => {
  if (isLiked.value) {
    axios
      .delete(`/api/${props.articleId}/likes`)
      .then((response) => {
        likesCount.value = response.data.likesCount
        isLiked.value = false
        ElMessage({ type: 'success', message: '좋아요가 취소되었습니다.' })
      })
      .catch((e) => {
        console.error('좋아요 취소 에러', e)
      })
  } else {
    axios
      .post(`/api/${props.articleId}/likes`)
      .then((response) => {
        likesCount.value = response.data.likesCount
        isLiked.value = true
        ElMessage({ type: 'success', message: '좋아요!' })
      })
      .catch((e) => {
        console.error('좋아요 에러', e)
      })
  }
}

const initials = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']
const value = ref()
const options = Array.from({ length: 10 }).map((_, idx) => ({
  value: `Option ${idx + 1}`,
  label: `${initials[idx % 10]}${idx}`
}))

const myPlaylists = ref<Playlist[]>([])

const getMyPlaylist = () => {
  axios.get('/api/playlists/me').then((response) => {
    myPlaylists.value = response.data.playlistResponses
  })
}

onMounted(() => {
  getArticle()
  getMyPlaylist()
})
</script>

<template>
  <div class="row">
    <div class="col-5 text-end">
      <el-image :src="article.items[0]?.thumbnailUrl" style="width: 90%" />
    </div>

    <div class="col-7">
      <div class="row">
        <div class="col-9">
          <el-text>
            <h2>{{ article.title }}</h2>
            <el-text tag="sub" size="small">{{ article.createdAt }}</el-text>
          </el-text>
        </div>
        <div class="col-3 text-start">
          <el-text>{{ article.createdBy }}</el-text>
        </div>

        <!--        <h2 class="title">{{ article.title }}</h2>-->
        <!--        <div class="d-flex">-->
        <!--          <div class="regDate">{{ article.createdAt }}</div>-->
        <!--        </div>-->
      </div>

      <div class="row mb-4 mt-3">
        <div class="content">{{ article.content }}</div>
      </div>

      <div class="mb-5">
        <span class="badge text-bg-secondary mx-1">#java</span>
        <span class="badge text-bg-secondary mx-1">#java12312</span>
        <span class="badge text-bg-secondary mx-1">#java124124214</span>
        <!--                <el-tag type="info" round>해시태그</el-tag>-->
        <!--        <el-tag type="info" round>해시태그</el-tag>-->
      </div>

      <div class="row">
        <div class="col">
          <el-button color="#626aef" @click="moveToEdit()"
            >1
            <el-icon>
              <Comment />
            </el-icon>
          </el-button>
          <el-button color="#626aef" @click="toggleLikeArticle()"
            >{{ likesCount }}
            <el-icon>
              <component :is="isLiked ? StarFilled : Star" />
            </el-icon>
          </el-button>
          <!--          <el-button color="#626aef" @click="moveToEdit()"><el-icon><StarFilled /></el-icon></el-button>-->
        </div>
        <div class="col">
          <el-button color="#626aef" @click="moveToEdit()">수정</el-button>
          <el-button color="#626aef" @click="deleteArticle()">삭제</el-button>
        </div>
      </div>
    </div>
  </div>

  <div class="row align-items-center" v-for="item in article.items" :key="item.videoId">
    <div class="col-md-4 text-end">
      <el-image :src="item.thumbnailUrl" style="width: 80%" />
    </div>
    <div class="col-md-5 text-start">
      {{ item.videoTitle }}
    </div>
    <div class="col-md-3">
      <!--      <span><el-button color="#626aef" style="margin-right: 10px">저장</el-button></span>-->
      <span class="playlist-button"
        ><el-button color="#626aef" @click="openAddItemModal(item)">저장</el-button></span
      >
      <span><el-button color="#626aef">바로가기</el-button></span>
    </div>
  </div>

  <el-dialog width="400" v-model="addItemModal" title="동영상 저장">
    <div class="row playlist" v-for="playlist in myPlaylists" :key="playlist.id">
      <el-button color="#626aef" @click="addItem(playlist.id)">{{ playlist.title }}</el-button>
    </div>
  </el-dialog>
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

.playlist-button {
  margin-right: 10px;
}

.playlist {
  margin-bottom: 15px;
}

//.content {
//  margin-top: 20px;
//  white-space: break-spaces;
//  line-height: 1.5;
//}
</style>
