<template>
  <a :title="title">
    <el-button v-bind="$attrs" v-on="$">123</el-button>
  </a>
</template>

<script>
export default {
    name:'HinButton',
    props:['title']
}
</script>

<style>

</style>