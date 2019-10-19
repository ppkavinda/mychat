<template>
  <div class="message my-message">
    <img
      alt
      class="img-circle medium-image"
      src="https://bootdey.com/img/Content/avatar/avatar1.png"
    />

    <div class="message-body">
      <div class="message-body-inner">
        <div class="message-info">
          <h4>{{ message ? message.created_by : ''}}</h4>
          <h5>
            <i class="fa fa-clock-o"></i> 2:28 PM
          </h5>
        </div>
        <hr />
        <div class="message-text">{{ message ? message.body : '' }}</div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  props: ['message']

}
</script>