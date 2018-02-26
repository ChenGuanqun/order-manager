<template>
    <el-table
            :data="tableData"
            style="width: 100%">
        <el-table-column
                prop="username"
                label="姓名"
                >
        </el-table-column>
        <el-table-column
                prop="securityRole"
                label="权限"
                >
        </el-table-column>
        <el-table-column
                prop="insertTime"
                label="创建日期"
                >
        </el-table-column>
        <el-table-column
                prop="lastModifyTime"
                label="修改日期"
                >
        </el-table-column>
        <el-table-column
                prop="description"
                label="备注"
                >
        </el-table-column>
    </el-table>
</template>

<script>
  export default {
    name: "users",
      data() {
          return {
              tableData: []
          }
      }
  }
</script>

<style scoped>

</style>