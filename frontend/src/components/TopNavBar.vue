<template lang='pug'>
q-toolbar.bg-primary.text-white.shadow-2
  q-btn.q-mr-sm(v-show='showLeftDrawerButton' flat round dense icon='menu' @click='toggleLeftDrawer')
  q-separator(dark vertical inset)
  q-space
  q-tabs
    q-route-tab(label='Home' name='home' :to='{ name: "index" }' exact)
    q-route-tab(label='Evolve' name='evolve' :to='{ name: "evolve" }')

    //router-link(to='/' custom v-slot='{ href, route, navigate, isActive, isExactActive }')
    //  //q-btn.stretch.flat(label='homepage' :class='[isExactActive && \'router-link-exact-active\']')
    //router-link(to='/evolve' custom v-slot='{ href, route, navigate, isActive, isExactActive }')
    //  //q-btn.stretch.flat(label='evolve' :class='[isActive && \'router-link-active\', isExactActive && \'router-link-exact-active\']')
    //router-link(to='/vermintide2' custom v-slot='{ href, route, navigate, isActive, isExactActive }')
    //  //q-btn.stretch.flat(label='vermintide2' :class='[isActive && \'router-link-active\', isExactActive && \'router-link-exact-active\']')
    //router-link(to='/' custom v-slot='props')
    //  q-btn(label='homepage' v-bind='buttonProps(props)')
    //router-link(to='/evolve' custom v-slot='props')
    //  q-btn(label='evolve' v-bind='buttonProps(props)')
    //router-link(to='/vermintide2' custom v-slot='props')
    //  q-btn(label='vermintide2' v-bind='buttonProps(props)' disable)
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

    const buttonProps = (route: { href: string, isActive: boolean }) => {
      const props = {
        color: '',
        noCaps: false,
        outline: false,
        to: route.href
      }
      if (route.isActive) {
        props.color = 'deep-purple'
      }
      return props
    }

    return {
      buttonProps,
      toggleLeftDrawer: drawers.toggleLeftDrawer,
      toggleRightDrawer: drawers.toggleRightDrawer
    }
  }
})
</script>

<style lang='sass' scoped>
.router-link-active
  color: red

.router-link-exact-active
  color: blue


.my-menu-link
  background-color: rgb(29, 139, 246)
//background: #F2C037
</style>
