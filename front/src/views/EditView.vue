<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const router = useRouter();

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true,
  }
});

const post = ref({
  title: "",
  content: ""
});

axios.get(`/api/post/${props.postId}`).then((response) => {
  post.value = response.data;
});

const edit = () => {
  axios.put(`/api/post/${props.postId}`, post.value).then(() => {
    router.replace({name: "home"})
  });
}
</script>

<template>
  <h1>Toy Project</h1>

  <div>
    <el-input v-model="post.title"/>
  </div>

  <div class="mt-2">
    <el-input v-model="post.content" type="textarea" rows="5"/>
  </div>

  <div class="mt-2">
    <button type="button" class="btn btn-info" @click="edit()">수정</button>
  </div>

</template>

<style scoped>

</style>