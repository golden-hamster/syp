import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    nickname: '',
    isLoggedIn: false
  }),
  actions: {
    login(nickname: string) {
      this.nickname = nickname
      this.isLoggedIn = true
    },
    logout() {
      this.nickname = ''
      this.isLoggedIn = false
    }
  }
})
