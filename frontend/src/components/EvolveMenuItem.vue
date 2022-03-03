<template lang='pug'>
q-item(clickable v-ripple :to='to' :inset-level='insetLevel')
  q-item-section(avatar)
    q-avatar(rounded size='100px' :style='imageStyle')
  q-item-section
    q-item-label {{ characterName }}
</template>

<script lang='ts'>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'EvolveMenuItem',

  props: {
    imageSrc: {
      type: String,
      required: true
    },
    imageStyle: String,
    to: {
      type: String,
      required: true
    },
    characterName: {
      type: String,
      required: true
    },
    insetLevel: {
      type: Number,
      default: 0
    }
  }
})
</script>
