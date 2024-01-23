<script setup lang="ts">
import {RouterView, useRoute} from 'vue-router'
import VueHeader from "@/components/VueHeader.vue";
import store from "@/scripts/store";
import axios from "axios";
import {watch} from "vue";

const route = useRoute();
const id = sessionStorage.getItem("id");

if (id) {
  store.commit("setAccount", id);
}

const check = () => {
  axios.get("/api/auth/check").then(({data}) => {
    console.log(data);
    if (data) {
      store.commit("setAccount", data || 0);
    }
  })
};

watch(route, () => {
  check();
})

</script>

<template>
  <VueHeader/>
  <RouterView/>
</template>

<style scoped>

</style>
