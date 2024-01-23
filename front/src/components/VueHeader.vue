<script setup lang="ts">
import store from "@/scripts/store";
import router from "@/router";
import axios from "axios";

const logout = () => {
  axios.post("/api/auth/logout").then(() => {
    store.commit('setAccount', 0);
    router.push({path: "/"});
    alert("로그아웃 성공");
  })
}
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">땡떤</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/write" class="nav-link">게시글 등록</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/signIn" class="nav-link" v-if="!$store.state.account.id">회원가입</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/login" class="nav-link" v-if="!$store.state.account.id">로그인</router-link>
            <a class="nav-link" @click="logout()" v-else>로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>