<template>
  <div class="message info">
    <img alt class="img-circle medium-image" src="https://bootdey.com/img/Content/avatar/avatar1.png" />
    <div class="message-body">
      <div class="message-info">
        <h4>{{ message ? message.created_by : '' }}</h4>
        <h5> <i class="fa fa-clock-o"></i> 2:25 PM </h5>
      </div>
      <hr />
      <div class="message-text">{{ message ? message.body : '' }}</div>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  props: ['message']
}
</script>