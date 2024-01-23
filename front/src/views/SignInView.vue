<script setup lang="ts">
import {reactive} from "vue";
import axios from "axios";

const stateUser = reactive({
  email: "",
  password: ""
})

const submit = () => {
  axios.post("/api/auth/signup", stateUser)
      .then((res) => {
        console.log(res);
        alert("회원가입 성공!");
      });
}
</script>

<template>
  <div style="width: 400px; height: 400px; margin-left: 20px; margin-top: 10px;">
    <h1>회원가입 페이지</h1>
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Email</label>
      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
             v-model="stateUser.email">
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword1" v-model="stateUser.password">
    </div>
    <button @click="submit()" class="btn btn-primary">Submit</button>
  </div>
</template>
