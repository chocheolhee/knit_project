<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import WriteView from "@/views/WriteView.vue";
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/write">게시글 작성</RouterLink>
    </nav>
  </header>

  <RouterView/>
</template>

<style scoped>

</style>
