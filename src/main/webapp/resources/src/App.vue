<template>
  <el-container class="wrapper">
    <el-header class="header" height="80px">
      xx后台管理系统
    </el-header>
    <el-container :style="{height: '100%'}">
      <el-aside class="menu">
        <el-menu default-active="1">
          <el-menu-item index="1">页面1</el-menu-item>
          <el-menu-item index="2">页面2</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main class="content"></el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  .header {
    background: rgb(64, 158, 255);
  }
  .wrapper {
    height: 100%;
  }
  .menu {
    height: 100%;
  }

  .content {
    padding: 20px;
  }
</style>
