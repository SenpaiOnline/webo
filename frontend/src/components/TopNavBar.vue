<template lang='pug'>
q-toolbar.bg-primary.text-white
  q-btn.q-mr-sm(v-show='showLeftDrawerButton' flat round dense icon='menu' @click='toggleLeftDrawer')
  q-separator(dark vertical inset)
  q-space
  q-tabs
    q-route-tab(label='Index' name='index' :to='{ name: "index" }')
    q-route-tab(label='Evolve' name='evolve' :to='{ name: "evolve" }')
  q-space
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import { useDrawersStore } from 'src/stores/useDrawersStore'

export default defineComponent({
  name: 'TopNavBar',

  props: {
    showLeftDrawerButton: {
      type: Boolean,
      required: true
    },
    showRightDrawerButton: {
      type: Boolean,
      required: true
    }
  },

  setup() {
    const drawers = useDrawersStore()

    return {
      toggleLeftDrawer: drawers.toggleLeftDrawer,
      toggleRightDrawer: drawers.toggleRightDrawer
    }
  }
})
</script>
