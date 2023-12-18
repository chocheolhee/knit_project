<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const title = ref("");
const content = ref("");

const router = useRouter();

const write = () => {
  axios.post("/api/post", {
    title: title.value,
    content: content.value
  }).then(() => {
    router.replace({name: "home"})
  })
}

</script>

<template>
  <h1>Toy Project</h1>

  <div>
    <el-input v-model="title" placeholder="제목을 입력해주세요."/>
  </div>

  <div class="mt-2">
    <el-input v-model="content" type="textarea" rows="5"/>
  </div>

  <div class="mt-2">
    <el-button type="primary" @click="write()">글 작성</el-button>
  </div>

</template>

<style scoped>

</style>