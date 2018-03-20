<template>
    <el-container class="wrapper">
      <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width='400px'>
        <el-form :model="form">
          <el-form-item label="新密码" label-width='100px'>
            <el-input v-model="form.newPwd" type="password"  auto-complete="off"></el-input>
          </el-form-item>
        </el-form>

        <el-form :model="form">
          <el-form-item label="确认新密码" label-width='100px'>
            <el-input v-model="form.newAgain"  type="password" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="doOperate">确 定</el-button>
        </div>
      </el-dialog>

        <el-header class="header" height="80px">
            任务管理系统

            <el-dropdown size="medium" class="el-dropdown-logout">
                  <span class="el-dropdown-link">
                    欢迎,{{currentUserName}}<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                        <el-dropdown-menu slot="dropdown">
                            <a href="/logout">
                                <el-dropdown-item>退出</el-dropdown-item>
                            </a>
                          <el-dropdown-item><span @click="dialogFormVisible=true">修改密码</span></el-dropdown-item>

                        </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <el-container :style="{height: '100%'}">
            <el-aside width="140px">
                <el-menu default-active="1" background-color="#324157" text-color="#fff">
                    <router-link to="/orderProgress">
                        <el-menu-item index="1">订单进度</el-menu-item>
                    </router-link>
                    <router-link to="/users" v-if="accessUserRole">
                        <el-menu-item index="2">
                            用户管理
                        </el-menu-item>
                    </router-link>
                  <router-link to="/roles" v-if="accessUserRole">
                    <el-menu-item index="3">
                      角色管理
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
    import request from '@/api/users';
    export default {
        name: 'App',
        data(){
            return {
                currentUserName:'xxx',
                accessUserRole:false,
              dialogFormVisible:false,
              form:{
                newPwd:'',
                newAgain:''
              }
            }
        },
        created() {
            this.$router.beforeEach((to, from, next) => {
                if(to.path === '/roles' || to.path === '/users') {
                    if(!this.accessUserRole){
                      next("orderProgress")
                    }else {
                        next()
                    }
                } else {
                    next()
                }
            })

            request.getCurrentUserName().then(res => {
                if(res.data.code == 200) {
                    this.currentUserName = res.data.result
                }
            }).catch(err => {
                // 处理请求错误的情况
            });

          request.getRole().then(res => {
            if(res.data.code == 200) {
              this.accessUserRole = res.data.result.userRole
            }
          }).catch(err => {
            // 处理请求错误的情况
          });
        },
      methods:{
        closeDialog() {
          this.dialogFormVisible = false;
          this.form={
            newPwd:'',
              newAgain:''
          }
        },
        doOperate() {
          request.updatePwd(this.form).then(res => {
          if (res.data.code == 200 && res.data.result) {
            this.$message({
              message: '密码修改成功!',
              type: 'success',
              onClose: () => {
                location.href = '/logout';
              }
            });

          } else {
            this.$message.error('密码修改失败!' + res.data.msg);
          }
        }).catch(err => {
      // 处理请求错误的情况
        })
          this.dialogFormVisible = false;
          this.form={
            newPwd:'',
              newAgain:''
          }
        }
      }
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
        position:relative;
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
    .el-dropdown-link {
        cursor: pointer;
        color: #fff;
        font-size: 16px;

    }
    .el-icon-arrow-down {
        font-size: 16px;
    }
    .el-dropdown-logout{
        float: right;
    }
</style>
