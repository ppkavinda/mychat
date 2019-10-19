<template>
  <div>
    <div
      class="alert alert-danger"
      :hidden="!error"
    >There was a problem logging in. Please try again.</div>
    <form role="form" @submit.prevent="login()">
      <div class="form-group">
        <label for="username">Username:</label>
        <input
          type="text"
          class="form-control"
          id="username"
          name="username"
          v-model="credentials.username"
        />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input
          type="password"
          class="form-control"
          id="password"
          name="password"
          v-model="credentials.password"
        />
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      error: null,
      credentials: {
        username: "ppkavinda",
        password: "password"
      }
    };
  },
  methods: {
    login() {
      this.authenticate();
      return false;
    },
    authenticate(credentials, callback) {
      axios
        .post("http://localhost:8080/api/auth/signin", {
          usernameOrEmail: this.credentials.username,
          password: this.credentials.password
        })
        .then(response => {
          // console.log(response.data);
          if (response.data.accessToken) {
            sessionStorage.setItem("username", this.credentials.username);
            sessionStorage.setItem("token", `Bearer ${response.data.accessToken}` );
            this.$store.commit("setUsername", this.credentials.username);
            this.$store.commit("login");
            this.$router.push("/");
          } else {
            this.$store.commit("logout");
          }
        });
    }
  }
};
</script>