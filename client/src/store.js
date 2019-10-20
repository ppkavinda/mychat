import Vue from 'vue'
import Vuex from 'vuex'

import webstomp from 'webstomp-client';
import SockJS from "sockjs-client";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    stompClient: webstomp.over(new SockJS("http://localhost:8080/ws-connect?access_token=" + sessionStorage.getItem('token'))),
    user: {},
    public: {
      messages: [],
      users: {},
    }
  },

  mutations: {
    login(state) {
      state.authenticated = true;
    },
    logout(state) {
      state.authenticated = false;
    },
    setUsername(state, username) {
      state.user.username = username;
    },
    addPublicMessage(state, message) {
      if (message.meta.type == 'sub') {
        Vue.set(state.public.users, message.data.username, 1);
        console.log('sub', message);
      } else if (message.meta.type == 'unsub') {
        Vue.delete(state.public.users, message.data.username);
        console.log('unsub', message);
        
      } else if (message.meta.type == 'offline') {
        console.log(message);
      } else if (message.meta.type == 'online') {
        console.log(message);
      } else {
        state.public.messages.push(message);
      }
    }

  },
  actions: { }
})
