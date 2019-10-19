<template>
  <form class="chat-footer">
    <input v-model="message.body" @keydown.enter="send()" class="send-message-text" type="text" />
    <label class="upload-file">
      <input type="file" required />
      <i class="fa fa-paperclip"></i>
    </label>
    <button :disabled="message.body.trim() === ''" @click="send()" class="send-message-button btn-info">
      <i class="fa fa-send"></i>
    </button>
  </form>
</template>

<script>
import Message from '../../model/Message';

export default {
  data() {
    return {
      message: {
        body: ""
      }
    };
  },
  methods: {
    send() {
      if (this.message.body.trim() === '') {
        this.message.body = '';
        return;
      };

      const msg = new Message(this.message.body)
      this.stompClient.send("/message/public", JSON.stringify(msg));
      this.message.body = '';

    },},
    computed: {
      stompClient() {
        return this.$store.state.stompClient;
      }
    },

  }
</script>