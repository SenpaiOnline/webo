import { RouteLocationNormalized, RouteRecordRaw } from 'vue-router'

const layoutMainLayout = () => import('layouts/MainLayout.vue')

const pageIndex = () => import(/* webpackChunkName: 'blog' */'pages/Index.vue')
const pageBlog = () => import(/* webpackChunkName: 'blog' */'pages/Blog.vue')
const pageEvolve = () => import(/* webpackChunkName: 'evolve' */ 'pages/Evolve.vue')

const componentBlogLeftDrawer = () => import(/* webpackChunkName: 'blog' */'components/blog/BlogLeftDrawer.vue')
const componentEvolveLeftDrawer = () => import(/* webpackChunkName: 'evolve' */ 'components/evolve/EvolveLeftDrawer.vue')
const componentAudioPlayer = () => import('components/AudioPlayer.vue')

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: layoutMainLayout,
    props: { showLeftDrawerButton: true },
    children: [
      {
        path: '',
        name: 'index',
        components: {
          default: pageIndex,
          leftDrawer: componentBlogLeftDrawer
        },
      },
      {
        path: 'blog/:id',
        name: 'blog',
        components: {
          default: pageBlog,
          leftDrawer: componentBlogLeftDrawer
        },
        props: {
          default: (route: RouteLocationNormalized) => ({
            id: Number.parseInt(route.params.id as string, 10)
          })
        }
      },
      {
        path: 'evolve/:character?/:page?',
        name: 'evolve',
        components: {
          default: pageEvolve,
          leftDrawer: componentEvolveLeftDrawer,
          footer: componentAudioPlayer
        },
        props: {
          default: (route: RouteLocationNormalized) => ({
            character: route.params.character,
            page: Number.parseInt(route.params.page as string, 10) || 1
          })
        }
      }
    ],
  },
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
]

export default routes
