<script setup lang="ts">

import {ref} from "vue";
import axios from "axios";

const posts: any = ref([]);

axios.get("http://localhost:8080/api/posts").then((response) => {
  response.data.content.forEach((res: any) => {
    posts.value.push(res);
  })
})
</script>

<template>

  <div class="card" style="width: 18rem;">
    <div class="card-header">
      Main List Page
    </div>
    <ul class="list-group list-group-flush" v-for="post in posts" :key="post.id">
      <li class="list-group-item">{{ post.title }}</li>
      <li class="list-group-item">{{ post.content }}</li>
    </ul>
  </div>

</template>
