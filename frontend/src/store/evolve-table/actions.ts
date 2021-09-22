import { ActionTree } from 'vuex'
import { Store } from 'src/store'
import { EvolveTableState } from 'src/store/evolve-table/state'
import Axios, { AxiosResponse } from 'axios'
import { EvolveJsonLines, EvolveJsonRowsNumber, EvolveLinesModel, JsonResponse } from 'components/models'
import { FetchDataPayload } from 'src/store/evolve-table/payloads'
import { Notify } from 'quasar'
import { api } from 'webo/packages/common'
import EvolveRowsNumberModel = api.model.EvolveRowsNumberModel

function showErrorMessage(): void {
  let response: AxiosResponse<EvolveRowsNumberModel>
  Notify.create({
    timeout: 10000,
    multiLine: false,
    type: 'negative',
    closeBtn: true,
    message: 'An error occurred while trying to fetch the data from the server! Very not nice of the server! ):<'
  })
}

const actions: ActionTree<EvolveTableState, Store> = {
  initialize: async function({ commit, dispatch }, payload) { // TODO
    let response: AxiosResponse<JsonResponse<EvolveJsonRowsNumber>>
    try {
      response = await Axios.get('/api/evolve/rowsNumberOf/' + payload.name)
    } catch (error) {
      console.log(error)
      showErrorMessage()
      throw error
    }
    let rowsNumber: number
    if (response.data.data) {
      rowsNumber = response.data.data.rowsNumber
    } else {
      showErrorMessage()
      return
    }
    commit('initialize', {
      characterName: payload.name,
      rowsNumber
    })
    await dispatch('fetchData', { page: payload.page })
  },
  terminate({ commit }) {
    commit('terminate')
  },
  async fetchData({ commit, state }, payload: FetchDataPayload) {
    const currentPage = state.pagination.page
    const startRow: number = (payload.page - 1) * state.pagination.rowsPerPage
    commit('fetching', payload.page)
    try {
      const response: AxiosResponse<JsonResponse<EvolveJsonLines>> = await Axios.get(
        '/api/evolve/linesOf/' + state.characterName,
        {
          params: {
            startRow,
            count: state.pagination.rowsPerPage,
            sortBy: state.pagination.sortBy,
            descending: state.pagination.descending
          }
        }
      )
      let lines: Array<EvolveLinesModel>
      if (response.data.data) {
        lines = response.data.data.lines
      } else {
        console.log(response.data.data)
        showErrorMessage()
        return
      }
      commit('fillTable', lines)
      commit('fetched')
    } catch (error) {
      console.log(error)
      showErrorMessage()
      commit('fetched', currentPage)
      throw error
    }
  }
}

export default actions
