<template lang='pug'>
q-list
  q-item-label(header) Assaults
    evolve-menu-item(
      v-for='chr in evolveMenuAssaultsData'
      :character-name='chr.character'
      :to='chr.page',
      :image-src='chr.imageSrc'
      :image-style='characterDataToCss(chr.imageSrc, chr.width, chr.height, chr.coordinatesOnSprite)'
    )
  q-item-label(header) Medics
    evolve-menu-item(
      v-for='chr in evolveMenuMedicsData'
      :character-name='chr.character'
      :to='chr.page',
      :image-src='chr.imageSrc'
      :image-style='characterDataToCss(chr.imageSrc, chr.width, chr.height, chr.coordinatesOnSprite)'
    )
  q-item-label(header) Trappers
    evolve-menu-item(
      v-for='chr in evolveMenuTrappersData'
      :character-name='chr.character'
      :to='chr.page',
      :image-src='chr.imageSrc'
      :image-style='characterDataToCss(chr.imageSrc, chr.width, chr.height, chr.coordinatesOnSprite)'
    )
  q-item-label(header) Supports
    evolve-menu-item(
      v-for='chr in evolveMenuSupportsData'
      :character-name='chr.character'
      :to='chr.page',
      :image-src='chr.imageSrc'
      :image-style='characterDataToCss(chr.imageSrc, chr.width, chr.height, chr.coordinatesOnSprite)'
    )
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import EvolveMenuItem from 'components/EvolveMenuItem.vue'
import {
  evolveMenuAssaultsData,
  evolveMenuMedicsData,
  evolveMenuSupportsData,
  evolveMenuTrappersData
} from 'src/composables/useEvolveMenuData'

export default defineComponent({
  name: 'EvolveLeftDrawer',

  components: {
    EvolveMenuItem
  },

  setup() {
    const characterDataToCss = (url: string, width = 100, height = 100, coords?: [number, number]): string => { // TODO
      if (coords) {
        return `width: ${width}px; height: ${height}px; background: url(${url}) ${coords[0]}px ${coords[1]}px;`
      } else {
        return `width: ${width}px; height: ${height}px; background: url(${url})`
      }
    }

    return {
      evolveMenuAssaultsData,
      evolveMenuMedicsData,
      evolveMenuTrappersData,
      evolveMenuSupportsData,
      characterDataToCss
    }
  }
})
</script>
