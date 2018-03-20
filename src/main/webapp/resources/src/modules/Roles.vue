<template>
    <div>

      <el-form :inline="true"  class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="handleCreate">创建角色</el-button>
        </el-form-item>
      </el-form>

      <el-dialog title="操作用户" :visible.sync="dialogFormVisible" width='400px'>
        <el-form :model="form">
          <el-form-item label="权限名" label-width='80px'>
            <el-input v-model="form.roleName" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>


        <el-form :model="form">
            <el-switch
              v-model="form.orderId"
              :width='40'
              active-text="生产序号"
            >
            </el-switch>
            <el-switch
              v-model="form.customerName"
              :width='40'
              active-text="客户名称"
            >
            </el-switch>
          <el-switch
            v-model="form.productName"
            :width='40'
            active-text="产品名称"
          >
          </el-switch>
          <el-switch
            v-model="form.productSeries"
            :width='40'
            active-text="产品型号"
          >
          </el-switch>
          <el-switch
            v-model="form.productRequires"
            :width='40'
            active-text="产品要求"
          >
          </el-switch>
          <el-switch
            v-model="form.number"
            :width='40'
            active-text="数量"
          >
          </el-switch>
          <el-switch
            v-model="form.deliveryDate"
            :width='40'
            active-text="交货时间"
          >
          </el-switch>
          <el-switch
            v-model="form.orderDate"
            :width='40'
            active-text="订单时间"
          >
          </el-switch>
          <el-switch
            v-model="form.planDate"
            :width='40'
            active-text="计划时间"
          >
          </el-switch>
          <el-switch
            v-model="form.config"
            :width='40'
            active-text="配件一览"
          >
          </el-switch>
          <el-switch
            v-model="form.status"
            :width='40'
            active-text="完工"
          >
          </el-switch>
          <el-switch
            v-model="form.deliveryStatus"
            :width='40'
            active-text="交货"
          >
          </el-switch>
          <el-switch
            v-model="form.operate"
            :width='40'
            active-text="操作"
          >
          </el-switch>
          <el-switch
            v-model="form.userRole"
            :width='40'
            active-text="用户权限"
          >
          </el-switch>


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
                customerName:false,
                productName:false,
                productSeries:false,
                number:false,
                deliveryDate:false,
                orderDate:false,
                planDate:false,
                status:false,
                deliveryStatus:false,
                config:false,
                orderId:false,
                operate:false,
                userRole:false,
                productRequires:false
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
//            this.form = {
//              roleName: '',
//              description: ''
//            }
          },
          handleCreate(){
            this.form = {
              roleName: '',
              description: '',
              customerName:false,
              productName:false,
              productSeries:false,
              number:false,
              deliveryDate:false,
              orderDate:false,
              planDate:false,
              status:false,
              deliveryStatus:false,
              config:false,
              orderId:false,
              operate:false,
              userRole:false,
              productRequires:false
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
              request.updateRole(this.form).then(res => {
                if(res.data.code == 200 && res.data.result){
                  this.$message({
                    message: '更新成功!',
                    type: 'success'
                  });
                  this.fetchAllRoles({});
                } else {
                  this.$message.error('更新失败:' + res.data.msg);
                }
              }).catch(err => {
                // 处理请求错误的情况
              })
            }
          },
          handleEdit(row) {
            this.$_currentEditRole = row;
            this.form.id = this.$_currentEditRole.id;
            this.form.roleName = this.$_currentEditRole.roleName;
            this.form.customerName = this.$_currentEditRole.customerName;
            this.form.productName = this.$_currentEditRole.productName;
            this.form.productSeries = this.$_currentEditRole.productSeries;
            this.form.number = this.$_currentEditRole.number;
            this.form.deliveryDate = this.$_currentEditRole.deliveryDate;
            this.form.orderDate = this.$_currentEditRole.orderDate;
            this.form.planDate = this.$_currentEditRole.planDate;
            this.form.status = this.$_currentEditRole.status;
            this.form.deliveryStatus = this.$_currentEditRole.deliveryStatus;
            this.form.config = this.$_currentEditRole.config;
            this.form.orderId = this.$_currentEditRole.orderId;
            this.form.operate = this.$_currentEditRole.operate;
            this.form.userRole = this.$_currentEditRole.userRole;
            this.form.productRequires = this.$_currentEditRole.productRequires;

            this.form.description = this.$_currentEditRole.description;
            this.dialogFormVisible = true ;
            this.isEdit = true;
          },
          handleDelete(row) {
            request.deleteRole(row).then(res => {
              if(res.data.code == 200 && res.data.result){
                this.$message({
                  message: '删除权限:"' + row.roleName+ '"成功!',
                  type: 'success'
                });
                this.fetchAllRoles();
              } else {
                this.$message.error('删除权限:"' + row.roleName+ '"失败!' + res.data.msg);
              }
            }).catch(err => {
              // 处理请求错误的情况
            })

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
