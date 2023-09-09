<template>
  <div id="app">
    <Screen msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
import Screen from "./components/Screen.vue";

export default {
  name: "App",
  components: {
    Screen,
  },
};
</script>

<style>
body {
  padding: 0;
  margin: 0;
}
</style>
