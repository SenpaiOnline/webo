<template lang='pug'>
audio.hidden(ref='audioElementRef' :loop='loop')
q-toolbar.justify-center.bg-blue-grey-10
  .col-1.flex.no-wrap.items-center.justify-end.q-pa-sm
    q-btn(icon='fast_rewind' @click='rewindToBeginning' :disable='!readyToPlay' unelevated)
    q-btn(:icon='playbackIcon' @click='pauseOrResume' :disable='!readyToPlay' unelevated)
    q-btn(icon='replay' @click='toggleLoop' :class='{ active: loop }' unelevated)
  .col-3.flex.no-wrap.content-center.items-center.justify-evenly.q-pa-sm.q-gutter-x-sm
    span {{ currentTime }}
    q-linear-progress(:value='progress' :animation-speed='500' stripe size='md' color='grey-5' rounded)
    span {{ duration }}
  .col-1.flex.no-wrap.items-center.justify-start.q-pa-sm.q-gutter-x-md
    q-icon(:name='volumeIcon' size='large')
    q-slider#volume-slider(:min='0' :max='1' :step='0.01' v-model='volume' snap color='grey-2')
</template>

<script lang='ts'>
import { computed, defineComponent, onMounted, onUnmounted, Ref, ref, unref } from 'vue'
import { useAudioPlayer } from 'src/stores/useAudioPlayer'
import { storeToRefs } from 'pinia'
import { secondsToHumanReadable } from 'src/utils/secondsToHumanReadable'

export default defineComponent({
  name: 'AudioPlayer',

  setup() {
    const audioElementRef = ref<HTMLAudioElement>() as Ref<HTMLAudioElement>
    const audioPlayerStore = useAudioPlayer()
    const {
      loop,
      playbackIcon,
      playing,
      volumeIcon,
      volume,
      currentTime: _currentTime,
      duration: _duration,
      readyToPlay
    } = storeToRefs(audioPlayerStore)

    const currentTime = computed(() => secondsToHumanReadable(_currentTime.value))

    const duration = computed(() => secondsToHumanReadable(_duration.value))

    const progress = computed(() => _currentTime.value / _duration.value)

    onMounted(() => audioPlayerStore.initialize(unref(audioElementRef)))

    onUnmounted(() => audioPlayerStore.terminate())

    return {
      audioElementRef,
      loop,
      playbackIcon,
      playing,
      volumeIcon,
      volume,

      currentTime,
      duration,
      progress,
      readyToPlay,

      pauseOrResume: audioPlayerStore.pauseOrResume,
      load: audioPlayerStore.load,
      loadAndPlay: audioPlayerStore.loadAndPlay,
      rewindToBeginning: audioPlayerStore.rewindToBeginning,
      toggleLoop: audioPlayerStore.toggleLoop
    }
  }
})
</script>

<style lang='sass' scoped>
.active
  color: rgba(25, 118, 210, 0.73)
</style>
