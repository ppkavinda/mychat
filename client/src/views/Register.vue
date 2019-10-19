<template>
  <div>
    <div class="alert alert-danger" :hidden="!error" >There was a problem logging in. Please try again.</div>
    <form role="form" @submit.prevent="login()">
      <div class="form-group">
        <label for="username">Name:</label>
        <input type="text" class="form-control" id="name" name="name" v-model="credentials.name" />
      </div>
      <div class="form-group">
        <label for="username">Email:</label>
        <input type="emaill" class="form-control" id="email" name="email" v-model="credentials.email" />
      </div>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" id="username" name="username" v-model="credentials.username" />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" name="password" v-model="credentials.password" />
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
        username: "adnivak",
        password: "password",
        email: 'test1@test.com',
        name: 'kavinda gunasekara',
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
        .post("http://localhost:8080/api/auth/signup", {
          username: this.credentials.username,
          password: this.credentials.password,
          email: this.credentials.email,
          name: this.credentials.name,
        })
        .then(response => {
          console.log(response.data);
        //   if (response.data.accessToken) {
            // sessionStorage.setItem("username", this.credentials.username);
            // sessionStorage.setItem("token", response.data.accessToken);
            // this.$store.commit("setUsername", this.credentials.username);
            // this.$store.commit("login");
            // this.$router.push("/");
        //   } else {
            // this.$store.commit("logout");
        //   }
        });
    }
  }
};
</script>