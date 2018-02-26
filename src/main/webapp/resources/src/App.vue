<template>
    <el-container class="wrapper">
        <el-header class="header" height="80px">

            <el-row>
                <el-col :span="23"><div class="grid-content bg-purple"></div>任务管理系统</el-col>
                <el-col :span="1">
                    <el-dropdown trigger="click">
                        <a href="javascript:;" class="el-dropdown-link"  style="color:white">点击退出</a>
                        <el-dropdown-menu slot="dropdown">
                            <a href="/logout">
                                <el-dropdown-item>退出</el-dropdown-item>
                            </a>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
            </el-row>

        </el-header>
        <el-container :style="{height: '100%'}">
            <el-aside  width="140px">
                <el-menu default-active="1" background-color="#324157" text-color="#fff">
                    <router-link to="/page1">
                        <el-menu-item index="1">订单进度</el-menu-item>
                    </router-link>
                    <router-link to="/page2">
                        <el-menu-item index="2">
                            用户管理
                        </el-menu-item>
                    </router-link>
                </el-menu>
            </el-aside>
            <el-main class="content">
                <router-view></router-view>
            </el-main>
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
      position: relative;
      padding: 0 15px;
      box-sizing: border-box;
      width: 100%;
      height: 80px;
      font-size: 20px;
      line-height: 80px;
      color: #fff;
      background-color: #242f42;
  }
  .wrapper {
    height: 100%;
  }
  .menu {
    height: 100%;
  }

  .hd_name {
      display: inline-block;
  }

  .hd_operate {
      float: right;
      margin-right: 40px;
      font-size: 14px;
  }

  .content {
    padding: 20px;
  }
</style>
