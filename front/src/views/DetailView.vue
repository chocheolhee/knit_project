<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true,
  }
});

const post = ref({
  id: 0,
  title: "",
  content: ""
});

const router = useRouter();

const edit = () => {
  router.push({name: "edit", params: {postId: props.postId}})
}

onMounted(() => {
  axios.get(`/api/post/${props.postId}`).then((response) => {
    post.value = response.data;
  })
})
</script>

<template>
  <div>
    <h2>{{ post.title }}</h2>
    <div>{{ post.content }}</div>
  </div>
  <button type="button" class="btn btn-info" @click="edit()">게시글 수정</button>

</template>

<style scoped>

</style>