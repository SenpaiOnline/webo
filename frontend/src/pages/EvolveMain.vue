<template lang='pug'>
q-page.row.items-center.justify-center
  p I'm the main page of that layout!
</template>

<script lang='ts'>
import { defineComponent, onMounted } from 'vue'
import { useDrawersStore } from 'src/stores/useDrawersStore'
import { storeToRefs } from 'pinia'

export default defineComponent({
  name: 'EvolveMain',

  setup() {
    const drawersStore = useDrawersStore()
    const { leftDrawerOpen } = storeToRefs(drawersStore)

    onMounted(() => {
      if (!leftDrawerOpen.value) {
        drawersStore.toggleLeftDrawer()
      }
    })
  }
})
</script>
