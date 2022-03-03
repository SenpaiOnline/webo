<template lang='pug'>
q-page(padding)
  q-table(
    v-if='character'
    :pagination='pagination'
    :rows='tableData'
    :columns='tableColumns'
    :row-key='rowKey'
    :loading='loading'
    @request="onRequest",
    wrap-cells
    flat
    :rows-per-page-options='[]'
  )
    template(v-slot:loading)
      q-inner-loading(showing color='primary')
    template(v-slot:body-cell-audio='props')
      q-td(:props='props')
        q-btn(v-for='audio in props.row.audio' icon='play_arrow' @click='loadAndPlay(audio)' padding='xs')
    template(v-slot:top-right='props')
      q-pagination(
        v-model='pagination.page'
        :max='lastPage'
        :max-pages='maxPagesAtTime'
        :to-fn='createPaginationLink'
        boundary-numbers
      )
    template(v-slot:pagination='props')
      q-pagination(
        v-model='pagination.page'
        :max='lastPage'
        :max-pages='maxPagesAtTime'
        :to-fn='createPaginationLink'
        boundary-numbers
      )
  .row.items-center.justify-center(v-else)
    p I'm the main page of that layout!
</template>

<script lang='ts'>
import { useEvolveDialoguesStore } from 'src/stores/useEvolveDialoguesStore'
import { computed, defineComponent, onMounted, onUnmounted, ref, watch } from 'vue'
import { onBeforeRouteUpdate, useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'
import { EvolveCharacter } from 'src/openapi'
import { useQuasar } from 'quasar'
import AudioPlayer from 'components/AudioPlayer.vue'
import { useAudioPlayer } from 'src/stores/useAudioPlayer'
import { useDrawersStore } from 'src/stores/useDrawersStore'

export default defineComponent({
  name: 'EvolveData',

  components: {
    AudioPlayer
  },

  props: {
    character: {
      type: String,
      required: false
    },
    page: {
      type: Number,
      required: true
    }
  },

  setup(props) {
    const q = useQuasar()
    const volume = ref(80) // TODO
    const evolveDialoguesStore = useEvolveDialoguesStore()
    const audioPlayerStore = useAudioPlayer()
    const router = useRouter()
    const {
      hasData,
      maxPagesAtTime,
      tableData,
      tableColumns,
      loading,
      pagination,
      rowKey
    } = storeToRefs(evolveDialoguesStore)
    const drawersStore = useDrawersStore()
    const { leftDrawerOpen } = storeToRefs(drawersStore)

    const lastPage = computed(() => Math.ceil(pagination.value.rowsNumber / pagination.value.rowsPerPage))

    watch(
      () => props.character,
      async (character) => {
        if (!character) {
          return
        }
        try {
          await evolveDialoguesStore.initialize(<EvolveCharacter> character)
          await evolveDialoguesStore.fetchData(props.page)
        } catch (e) {
          console.error(e)
          q.notify({
            type: 'negative',
            message: 'Internal server error. The error is mine.',
            timeout: 10000,
            actions: [
              { label: 'Close', icon: 'close', color: 'white' },
              {
                label: 'Retry', icon: 'restart_alt', color: 'white', handler: () => {
                  router.go(0)
                }
              } // FIXME
            ],
          })
        }
      },
      { immediate: true })

    watch(() => props.page, (page) => onRequest(page))

    onMounted(() => {
      if (!leftDrawerOpen.value) {
        drawersStore.toggleLeftDrawer()
      }
    })

    onUnmounted(() => evolveDialoguesStore.terminate())

    onBeforeRouteUpdate((to, from, next) => {
      if (to.params.character !== from.params.character) {
        evolveDialoguesStore.terminate()
      }
      next()
    })

    const onRequest = async (page: number) => {
      await evolveDialoguesStore.fetchData(page)
    }

    const createPaginationLink = (page: number) => ({
      params: {
        character: props.character,
        page: page
      }
    })

    return {
      volume,
      hasData,
      maxPagesAtTime,
      tableData,
      tableColumns,
      pagination,
      loading,
      rowKey,
      lastPage,

      createPaginationLink,
      onRequest,
      loadAndPlay: audioPlayerStore.loadAndPlay
    }
  }
})
</script>
