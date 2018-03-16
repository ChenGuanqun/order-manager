<template>
    <div>

      <el-form :inline="true"  class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="handleCreate">创建权限</el-button>
        </el-form-item>
      </el-form>

      <el-dialog title="操作用户" :visible.sync="dialogFormVisible" width='400px'>
        <el-form :model="form">
          <el-form-item label="权限名" label-width='80px'>
            <el-input v-model="form.roleName" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>

        <el-form :model="form">
          <el-form-item label="权限" label-width='80px'>
            <el-checkbox-group v-model="form.roleArray">
              <el-checkbox label="生产序号"></el-checkbox>
              <el-checkbox label="客户名称"></el-checkbox>
              <el-checkbox label="产品名称"></el-checkbox>
              <el-checkbox label="产品型号"></el-checkbox>
              <el-checkbox label="数量"></el-checkbox>
              <el-checkbox label="交货时间"></el-checkbox>
              <el-checkbox label="订单时间"></el-checkbox>
              <el-checkbox label="计划时间"></el-checkbox>
              <el-checkbox label="产品要求"></el-checkbox>
              <el-checkbox label="定子总成"></el-checkbox>
              <el-checkbox label="配件一览"></el-checkbox>
              <el-checkbox label="完工"></el-checkbox>
              <el-checkbox label="交货"></el-checkbox>
              <el-checkbox label="操作"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>

        <el-form :model="form">
          <el-form-item label="备注" label-width='80px'>
            <el-input v-model="form.description" type="textarea" :rows="3" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="doOperate">确 定</el-button>
        </div>
      </el-dialog>

        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="roleName"
                    label="角色名">
            </el-table-column>
            <el-table-column
                    prop="insertTime"
                    label="创建日期"
                    :formatter="dateFormatter">
            </el-table-column>
            <el-table-column
                    prop="lastModifyTime"
                    label="修改日期"
                    :formatter="dateFormatter">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="备注">
            </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.row)" type="text" size="mini">编辑</el-button>
                    <el-button @click="handleDelete(scope.row)" type="text" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>

</template>

<script>
    import request from '@/api/roles';
    import util from '@/shares/util';
    export default {
        name: "roles",
        data() {
            return {
                tableData: [],
              dialogFormVisible: false,
              form: {
                roleName: '',
                description: '',
                orderId:true,
                roleArray:[]
              },
              isEdit:false
            };
        },
        created() {
            this.fetchAllRoles();
        },
        methods: {
          dateFormatter(row, col, value) {
            return util.dateHandle(value, true);
          },
          closeDialog() {
            this.dialogFormVisible = false;
            this.form = {
              roleName: '',
              description: ''
            }
          },
          handleCreate(){
            this.form = {
              roleName: '',
              description: ''
            }
            this.dialogFormVisible = true;
            this.isEdit = false;
          },
          doOperate() {
            this.dialogFormVisible = false;
            if(!this.isEdit){
              //创建用户
              request.createRole(this.form).then(res => {
                if(res.data.code == 200 && res.data.result){
                  this.$message({
                    message: '创建成功!',
                    type: 'success'
                  });
                  this.fetchAllRoles({});
                } else {
                  this.$message.error('创建失败:' + res.data.msg);
                }
              }).catch(err => {
                // 处理请求错误的情况
              })
            }else {
              //编辑用户

            }
          },
          handleEdit(row) {
            this.$_currentEditRole = row;
            this.form.roleName = this.$_currentEditRole.roleName;
            this.form.orderId = this.$_currentEditRole.orderId;
            this.form.description = this.$_currentEditRole.description;
            this.dialogFormVisible = true ;
            this.isEdit = true;
          },
            fetchAllRoles() {
              request.queryRoles().then(res => {
                this.tableData = res.data.result.data;
                if(res.data.code != 200) {
                  this.$message.error('查询失败,msg:' + res.data.msg);
                }
              }).catch(err => {
                // 处理请求错误的情况
              })
            }
        }
    }
</script>

<style scoped>

</style>
