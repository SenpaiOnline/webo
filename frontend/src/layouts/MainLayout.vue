<template lang='pug'>
q-layout(view='hHh lpr fFf')
  q-header(elevated)
    top-nav-bar(:show-left-drawer-button='showLeftDrawerButton' :show-right-drawer-button='showRightDrawerButton')
  q-footer(elevated)
    router-view(name='footer')
  // TODO #WEBO-25
  q-drawer(:model-value='leftDrawerOpen' bordered side='left' overlay persistent)
    router-view(name='leftDrawer' ref='leftDrawer')
  q-drawer(:model-value='rightDrawerOpen' bordered side='right' overlay)
    router-view(name='rightDrawer' ref='rightDrawer')
  q-page-container
    router-view
</template>

<script lang='ts'>
import TopNavBar from 'components/TopNavBar.vue'
import { defineComponent, onMounted, ref } from 'vue'
import { RouterView } from 'vue-router'
import { storeToRefs } from 'pinia'
import { useDrawersStore } from 'src/stores/useDrawersStore'

export default defineComponent({
  name: 'MainLayout',

  components: {
    TopNavBar
  },

  setup() {
    const leftDrawer = ref<InstanceType<typeof RouterView> | undefined>()
    const rightDrawer = ref<InstanceType<typeof RouterView>>()
    const showLeftDrawerButton = ref(false)
    const showRightDrawerButton = ref(false)
    const drawersStore = useDrawersStore()
    const { leftDrawerOpen, rightDrawerOpen } = storeToRefs(drawersStore)

    onMounted(() => {
      if (leftDrawer.value !== undefined) {
        showLeftDrawerButton.value = true
      }
      if (rightDrawer.value !== undefined) {
        showRightDrawerButton.value = true
      }
    })

    return {
      leftDrawerOpen,
      rightDrawerOpen,
      leftDrawer,
      rightDrawer,
      showLeftDrawerButton,
      showRightDrawerButton
    }
  }
})
</script>

<style lang='sass'>
body
  overflow-y: scroll
</style>
