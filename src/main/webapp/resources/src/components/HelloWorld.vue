<template>
  <div class="hello">
    <h1>{{ msg }}</h1>

    <div class="hd_operate">
      欢迎，<el-dropdown trigger="click">
      <!--<a href="javascript:;" class="el-dropdown-link"><@security.authentication property="details.realName"/></a>-->
      <el-dropdown-menu slot="dropdown">
        <a href="/logout">
          <el-dropdown-item>退出</el-dropdown-item>
        </a>
      </el-dropdown-menu>
    </el-dropdown>
    </div>
  </div>


</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
