import { defineStore } from 'pinia'
import { readonly, ref } from 'vue'

export const useDrawersStore = defineStore('drawersStore', () => {
  const _leftDrawerOpen = ref(false)
  const _rightDrawerOpen = ref(false)

  const toggleLeftDrawer = () => {
    _leftDrawerOpen.value = !_leftDrawerOpen.value
  }

  const toggleRightDrawer = () => {
    _rightDrawerOpen.value = !_rightDrawerOpen.value
  }

  return {
    leftDrawerOpen: readonly(_leftDrawerOpen),
    rightDrawerOpen: readonly(_rightDrawerOpen),

    toggleLeftDrawer,
    toggleRightDrawer
  }
})
