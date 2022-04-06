<template lang='pug'>
q-item(clickable v-ripple :to='to')
  q-item-section
    q-item-label {{ title }}
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import { RouteLocationRaw } from 'vue-router'

export default defineComponent({
  name: 'BlogLinkItem',

  props: {
    title: {
      type: String,
      required: true
    },

    id: {
      type: Number,
      required: true
    }
  },

  setup(props) {
    const to: RouteLocationRaw = { name: 'blog', params: { id: props.id } }

    return {
      to
    }
  }
})
</script>
