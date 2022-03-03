import { defineStore } from 'pinia'
import { Configuration, DefaultApi, EvolveCharacter, EvolveDialogueDto, EvolveDialoguesMetaDto } from 'src/openapi'
import { computed, reactive, readonly, ref, Ref } from 'vue'

const api = new DefaultApi(new Configuration({ basePath: window.location.origin }))

export const useEvolveDialoguesStore = defineStore('evolveDialoguesStore', () => {
  const _pagination = reactive({
    page: 1,
    rowsPerPage: 0,
    rowsNumber: 0
  })
  const _tableColumns = ref([
    {
      name: 'name',
      required: true,
      label: 'Name',
      align: 'left',
      field: 'name',
      sortable: false
    },
    {
      name: 'text',
      required: true,
      label: 'Text',
      align: 'center',
      field: 'text',
      sortable: false
    },
    {
      name: 'audio',
      required: true,
      label: 'Audio',
      align: 'right',
      field: 'audio',
      sortable: false,
    },
  ])
  const _maxPagesAtTime = ref(10)
  const _tableData = ref([] as Array<EvolveDialogueDto>)
  const _loading = ref(false)
  const _character = ref() as Ref<EvolveCharacter | undefined>
  const _rowKey = ref('name')

  const _hasData = computed(() => _tableData.value.length > 0)

  async function initialize(character: EvolveCharacter) {
    const metaDto: EvolveDialoguesMetaDto = await api.characterMeta({ character })
    _pagination.rowsNumber = metaDto.rowsNumber
    _pagination.rowsPerPage = metaDto.rowsPerPage
    _character.value = character
  }

  function terminate() {
    _pagination.page = 1
    _pagination.rowsPerPage = 0
    _pagination.rowsNumber = 0
    _character.value = undefined
    _tableData.value.length = 0
  }

  async function fetchData(requestedPage: number) {
    _loading.value = true
    try {
      const dialogues = await api.findTwentyDialoguesByCharacter({
        character: _character.value as EvolveCharacter,
        offset: (requestedPage - 1) * _pagination.rowsPerPage
      })
      _tableData.value.splice(0, _tableData.value.length, ...dialogues)
      _pagination.page = requestedPage
    } finally {
      _loading.value = false
    }
  }

  return {
    pagination: _pagination,
    maxPagesAtTime: readonly(_maxPagesAtTime),
    tableData: readonly(_tableData),
    loading: readonly(_loading),
    character: readonly(_character),
    tableColumns: readonly(_tableColumns),
    rowKey: readonly(_rowKey),

    hasData: _hasData,

    initialize,
    terminate,
    fetchData
  }
})
