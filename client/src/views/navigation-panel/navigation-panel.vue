<template>
  <div class="contacts-list">
    <div class="private-categories">
      <div
        data-toggle="tab"
        data-target="#private"
        @click="goToPrivateTab()"
        :class="{ 'active': privateTab }"
      >Private</div>
      <div
        data-toggle="tab"
        data-target="#public"
        @click="goToPublicTab()"
        :class="{ 'active': publicTab }"
      >Public</div>
      <!-- <div data-toggle="tab" data-target="#marked">Marked</div> -->
      <!-- <div data-toggle="tab" data-target="#drafts">Drafts</div> -->
    </div>
    <div class="tab-content">
      <div id="private" class="contacts-outter-wrapper tab-pane" :class="{ 'active': privateTab }">
        <form class="panel-search-form info form-group has-feedback no-margin-bottom">
          <input type="text" class="form-control" name="search" placeholder="Search" />
          <span class="fa fa-search form-control-feedback"></span>
        </form>
        <div class="contacts-outter">
          <ul class="list-unstyled contacts">
            <contact-card />
            <contact-card />
          </ul>
        </div>
      </div>
      <div id="sent" class="contacts-outter-wrapper tab-pane" :class="{ 'active': publicTab }">
        <form class="panel-search-form success form-group has-feedback no-margin-bottom">
          <input type="text" class="form-control" name="search" placeholder="Search" />
          <span class="fa fa-search form-control-feedback"></span>
        </form>
        <div class="contacts-outter">
          <ul class="list-unstyled contacts success">
            <contact-card :contact="contact" :key="i" v-for="(contact, i) in contacts" />
            <!-- <li data-toggle="tab" data-target="#sent-message-1">
              <img
                alt
                class="img-circle medium-image"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"
              />

              <div class="vcentered info-combo">
                <h3 class="no-margin-bottom name">David Beckham</h3>
                <h5>I would like to take a look at it this evening, is it possible ?</h5>
              </div>
              <div class="contacts-add">
                <span class="message-time">
                  2:24
                  <sup>AM</sup>
                </span>
                <i class="fa fa-trash-o"></i>
                <i class="fa fa-paperclip"></i>
              </div>
            </li>
            <li data-toggle="tab" data-target="#sent-message-2">
              <div class="message-count">7</div>
              <img
                alt
                class="img-circle medium-image"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"
              />

              <div class="vcentered info-combo">
                <h3 class="no-margin-bottom name">Jeff Williams</h3>
                <h5>Hello, Dennis. I will take a look at it tomorrow, because I'm</h5>
              </div>
              <div class="contacts-add">
                <span class="message-time">
                  12:41
                  <sup>AM</sup>
                </span>
                <i class="fa fa-trash-o"></i>
                <i class="fa fa-paperclip"></i>
              </div>
            </li>-->
          </ul>
        </div>
      </div>
      <!--      <div id="marked" class="contacts-outter-wrapper tab-pane">
        <form class="panel-search-form warning form-group has-feedback no-margin-bottom">
          <input type="text" class="form-control" name="search" placeholder="Search" />
          <span class="fa fa-search form-control-feedback"></span>
        </form>
        <div class="contacts-outter">
          <ul class="list-unstyled contacts warning">
            <li data-toggle="tab" data-target="#marked-message-1">
              <div class="message-count">2</div>
              <img
                alt
                class="img-circle medium-image"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"
              />

              <div class="vcentered info-combo">
                <h3 class="no-margin-bottom name">Jessica Franco</h3>
                <h5>Hello, Dennis. I wanted to let you know we reviewed your proposal and decided</h5>
              </div>
              <div class="contacts-add">
                <span class="message-time">
                  1:44
                  <sup>AM</sup>
                </span>
                <i class="fa fa-trash-o"></i>
                <i class="fa fa-paperclip"></i>
              </div>
            </li>
            <li data-toggle="tab" data-target="#marked-message-2">
              <div class="message-count">1</div>
              <img
                alt
                class="img-circle medium-image"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"
              />

              <div class="vcentered info-combo">
                <h3 class="no-margin-bottom name">Pavel Durov</h3>
                <h5>Hey, we need your help for our next Telegram re-design.</h5>
              </div>
              <div class="contacts-add">
                <span class="message-time">
                  3:23
                  <sup>AM</sup>
                </span>
                <i class="fa fa-trash-o"></i>
                <i class="fa fa-paperclip"></i>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div id="drafts" class="contacts-outter-wrapper tab-pane">
        <form class="panel-search-form dark form-group has-feedback no-margin-bottom">
          <input type="text" class="form-control" name="search" placeholder="Search" />
          <span class="fa fa-search form-control-feedback"></span>
        </form>
        <div class="contacts-outter">
          <ul class="list-unstyled contacts dark">
            <li data-toggle="tab" data-target="#draft-message-1">
              <img
                alt
                class="img-circle medium-image"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"
              />

              <div class="vcentered info-combo">
                <h3 class="no-margin-bottom name">Milla Shiffman</h3>
                <h5>Hello, Mila, can you send me the latest pack of icons, I need</h5>
              </div>
              <div class="contacts-add">
                <span class="message-time">
                  4:22
                  <sup>AM</sup>
                </span>
                <i class="fa fa-trash-o"></i>
                <i class="fa fa-paperclip"></i>
              </div>
            </li>
          </ul>
        </div>
      </div>-->
    </div>
  </div>
</template>

<script>
import ContactCard from "./contact-card";
export default {
  components: { ContactCard },
  data() {
    return {
      privateTab: false,
      publicTab: true
    };
  },
  methods: {
    goToPrivateTab() {
      this.privateTab = true;
      this.publicTab = false;
    },
    goToPublicTab() {
      this.publicTab = true;
      this.privateTab = false;
    }
  },
  computed: {
    contacts() {
      console.log(this.$store);
      return Object.keys(this.$store.state.public.users);
    }
  }
};
</script>