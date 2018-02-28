<template>
    <el-container class="wrapper">
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
                        </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <el-container :style="{height: '100%'}">
            <el-aside width="140px">
                <el-menu default-active="1" background-color="#324157" text-color="#fff">
                    <router-link to="/orderProgress">
                        <el-menu-item index="1">订单进度</el-menu-item>
                    </router-link>
                    <router-link to="/users">
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
    import request from '@/api/users';
    export default {
        name: 'App',
        data(){
            return {
                currentUserName:'xxx'
            }
        },
        created() {
            request.getCurrentUserName().then(res => {
                if(res.data.code == 200) {
                    this.currentUserName = res.data.result
                }
            }).catch(err => {
                // 处理请求错误的情况
            })
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
