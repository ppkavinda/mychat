<template>
  <div class="tab-content">
    <div class="tab-pane message-body active" id="inbox-message-1">
      <panel-top />
      <div class="message-chat">
        <div ref="messageBox" class="chat-body">
          <div :key="i" v-for="(msg, i) in messages">
            <default-bubble v-if="msg.created_by === userName" :message="msg" />
            <info-bubble v-else :message="msg" />
          </div>
        </div>
        <panel-bottom />
      </div>
    </div>
    <!-- <other-boxes /> -->
  </div>
</template>

<script>
import PanelTop from "./panel-top";
import InfoBubble from "../../components/bubble/info-bubble";
import DefaultBubble from "../../components/bubble/default-bubble";
import PanelBottom from "./panel-bottom";

export default {
  components: { PanelTop, InfoBubble, DefaultBubble, PanelBottom },
  computed: {
    messages() {
      return this.$store.state.public.messages;
    },
    userName() {
      return sessionStorage.getItem('username');
    }
  },
  updated() {
    const messsageBox = this.$refs.messageBox;
      messsageBox.scrollTop = messsageBox.scrollHeight;
  }
  
};
</script>