import { login, logout } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { set } from 'nprogress'
import Cookies from 'js-cookie'
const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    imageList:[]
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_IMAGE: (state, imageList) =>{
    state.imageList = imageList
  },
  COLSE_IMAGE:(state) => {
    state.imageList = []
  }
}

const actions = {
  // user login
  async login({ commit }, userInfo) {
    const { username, password } = userInfo
    let result = await login({ username: username.trim(), password: password });
    if(result.code==20000 || result.code==200){
      commit('SET_TOKEN',result.data.token);
      Cookies.set("userinfo", result.data.info.userType)
      setToken(result.data.token);
      return 'ok'
    }else{
      return Promise.reject(new Error('faile'))
    }
  //   return new Promise((resolve, reject) => {
  //     login({ username: username.trim(), password: password }).then(response => {
  //       const { data } = response
  //       commit('SET_TOKEN', data.token)
  //       setToken(data.token)
  //       resolve()
  //     }).catch(error => {
  //       reject(error)
  //     })
  //   })
  },


  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        const { name, avatar } = data

        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  },

  setImageList({ commit },imageList){
    commit('SET_IMAGE',imageList)
  },

  closeImageList({ commit }){
    commit('COLSE_IMAGE')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

