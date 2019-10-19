<template>
  <div class="home">
    <div class="container">
      <div class="panel messages-panel">
        <contact-panel />
        <message-panel />
      </div>
    </div>
  </div>
</template>

<script>
import ContactPanel from "@/views/navigation-panel/navigation-panel";
import MessagePanel from "@/views/message-panel/message-panel";

export default {
  name: "home",
  components: {
    ContactPanel,
    MessagePanel
  }
};
</script>
