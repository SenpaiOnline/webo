import { computed, readonly, ref, Ref, watch } from 'vue'
import { useMediaControls, useStorage } from '@vueuse/core'
import { defineStore } from 'pinia'

export const useAudioPlayer = defineStore('audioPlayerStore', () => {
  const _mediaTarget = ref(undefined) as Ref<HTMLMediaElement | undefined>
  const _loop = ref(false)
  const _volume = useStorage('audio.volume', 0.8, undefined, { flush: 'post' })
  const {
    playing: _playing,
    currentTime: _currentTime,
    duration: _duration,
    volume: _mediaTargetVolume
  } = useMediaControls(_mediaTarget)

  const playbackIcon = computed(() => _playing.value ? 'pause' : 'play_arrow')

  const volumeIcon = computed(() =>
    _volume.value >= 0.5
      ? 'volume_up'
      : _volume.value < 0.5 && _volume.value > 0
        ? 'volume_down'
        : 'volume_off'
  )

  const readyToPlay = computed(() => _duration.value > 0.0)

  watch(_volume, (volume: number) => _mediaTargetVolume.value = volume)

  const _mediaTargetUndefined: () => never = () => {
    throw new Error('A HTMLMediaElement hasn\'t been defined yet. Make sure you\'ve initialized the store.')
  }

  const initialize = (mediaTarget: HTMLMediaElement) => {
    if (_mediaTarget.value) {
      console.warn('A new HTMLMediaElement has been passed without terminating the old one.')
      _mediaTarget.value = undefined
    }
    _mediaTarget.value = mediaTarget
    _mediaTargetVolume.value = _volume.value
    // sometimes gets reset after reloading the page regardless of its value provided by vueuse
    if (_mediaTarget.value.volume !== _volume.value) {
      _mediaTarget.value.volume = _volume.value
    }
  }

  const terminate = () => {
    _mediaTarget.value = undefined
  }

  const pauseOrResume = async () => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    _playing.value
      ? _mediaTarget.value.pause()
      : await _mediaTarget.value.play()
  }

  const load = (audioSrc: string) => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    if (_playing.value) {
      _mediaTarget.value.pause()
    }
    _mediaTarget.value.src = audioSrc
    _mediaTarget.value.load()
  }

  const loadAndPlay = async (audioSrc: string) => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    load(audioSrc)
    await _mediaTarget.value.play()
  }

  const rewindToBeginning = () => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    _mediaTarget.value.currentTime = 0
  }

  const synchronizeVolumeLevel = () => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    _mediaTargetVolume.value = _volume.value
  }

  const toggleLoop = () => {
    if (!_mediaTarget.value) {
      _mediaTargetUndefined()
    }
    _loop.value = !_loop.value
  }

  return {
    playing: readonly(_playing),
    currentTime: readonly(_currentTime),
    duration: readonly(_duration),
    loop: readonly(_loop),
    volume: _volume,

    playbackIcon,
    volumeIcon,
    readyToPlay,

    initialize,
    terminate,
    pauseOrResume,
    load,
    loadAndPlay,
    rewindToBeginning,
    synchronizeVolumeLevel,
    toggleLoop
  }
})
