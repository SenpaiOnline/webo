<template lang='pug'>
q-page.row.justify-center(padding)
  .col-sm-12.col-md-8.col-lg-6
    div(v-if='post')
      h5.q-ma-none {{ post.title }}
      q-markdown(:src='post.content')
    p(v-else) Nothingness!
    div(v-show='!post && loading').q-gutter-sm
      q-skeleton(type='rect' width='65%')
      q-skeleton(v-for='i in 10' :key='i' type='text')
      q-skeleton(type='text' width='80%')
</template>

<script lang='ts'>
import { defineComponent, ref, readonly, onBeforeMount, watch } from 'vue'
import { BlogApi, BlogPostDto, Configuration } from 'src/openapi'

const blogApi = new BlogApi(new Configuration({ basePath: window.location.origin }))

export default defineComponent({
  name: 'PageBlog',

  props: {
    id: {
      type: Number,
      required: true
    }
  },

  setup(props) {
    const _loading = ref(false)
    const _post = ref<BlogPostDto | undefined>()

    const loadPost = () => {
      if (_post.value) {
        _post.value = undefined
      }
      _loading.value = true
      blogApi
        .findPost({ id: props.id })
        .then((post) => {
          _post.value = post
        })
        .finally(() => {
          _loading.value = false
        })
    }

    watch(() => props.id, () => loadPost())

    onBeforeMount(() => loadPost())

    return {
      loading: readonly(_loading),
      post: readonly(_post)
    }
  }
});
</script>
