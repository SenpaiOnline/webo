<template lang='pug'>
q-list(separator)
  q-item-label.text-grey-8(header) Posts
  blog-link-item(v-for='post in postsPreview' :key='post.title' v-bind='post')
  q-item(v-show='loading' v-for='i in 5' :key='i')
    q-item-section
      q-item-label
        q-skeleton(type='text')
      q-item-label(caption)
        q-skeleton(type='text' width='65%')
</template>

<script lang='ts'>
import { defineComponent, readonly, ref, onBeforeMount } from 'vue'
import { BlogApi, BlogPostPreviewDto, Configuration } from 'src/openapi'
import BlogLinkItem from 'components/blog/BlogLinkItem.vue'

const blogApi = new BlogApi(new Configuration({ basePath: window.location.origin }))

export default defineComponent({
  name: 'BlogLeftDrawer',

  components: {
    BlogLinkItem
  },

  setup() {
    const _postsPreview = ref<Array<BlogPostPreviewDto>>([])
    const _loading = ref(false)

    const loadPreview = () => {
      _loading.value = true
      blogApi
        .findPublishedPostsIds()
        .then(preview => _postsPreview.value.splice(0, _postsPreview.value.length, ...preview))
        .finally(() => _loading.value = false)
    }

    onBeforeMount(() => {
      void loadPreview()
    })

    return {
      postsPreview: readonly(_postsPreview),
      loading: readonly(_loading)
    }
  }
})
</script>
