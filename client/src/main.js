import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false
// Vue.http.headers.common['Authorization'] = sessionStorage.getItem('token');

new Vue({
  http: {
    headers: {
      Authorization: sessionStorage.getItem('token')
    }
  },
  router,
  store,
  render: h => h(App),
  created: function () {
    this.connect();
  },

  methods: {
    connect() {
      this.stompClient.connect({}, frame => {
        this.stompClient.subscribe("/chat/public", (messageOutput) => {
          this.$store.commit('addPublicMessage', JSON.parse(messageOutput.body));
          // console.log("received msg: ", JSON.parse(messageOutput.body));
        });
      });
    },
  },

  computed: {
    stompClient() {
      return this.$store.state.stompClient;
    }
  }
}).$mount('#app')
