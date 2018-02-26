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
  import request from '@/api/users'
  export default {
    name: "users",
    data() {
        return {
            tableData: []
        }
    },
    created() {
      request.getXXXData().then(res => {
        this.tableData = res.xxxx
      }).catch(err => {
        // 处理请求错误的情况
      })
    }
  }
</script>

<style scoped>

</style>
