import { RouteLocationNormalized, RouteRecordRaw } from 'vue-router'

const layoutMainLayout = () => import('layouts/MainLayout.vue')

const pageIndex = () => import('pages/Index.vue')
const pageEvolveData = () => import(/* webpackChunkName: 'evolve' */ 'pages/EvolveData.vue')

const componentMainPageLeftDrawer = () => import('components/MainPageLeftDrawer.vue')
const componentEvolveLeftDrawer = () => import(/* webpackChunkName: 'evolve' */ 'components/EvolveLeftDrawer.vue')
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
          leftDrawer: componentMainPageLeftDrawer
        }
      },
      {
        path: 'evolve/:character?/:page?',
        name: 'evolve',
        components: {
          default: pageEvolveData,
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
    component: () => import('pages/Error404.vue'),
  },
]

export default routes
