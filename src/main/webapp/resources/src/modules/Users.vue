<template>
    <div>
        <!--<el-container>-->
        <!--<el-row>-->
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="姓名">
                <el-input v-model="formInline.userName" placeholder="userName"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreate">创建用户</el-button>
            </el-form-item>
        </el-form>
        <!--</el-row>-->

        <el-dialog title="操作用户" :visible.sync="dialogFormVisible" width='400px'>
            <el-form :model="form">
                <el-form-item label="姓名" label-width='80px'>
                    <el-input v-model="form.userName" auto-complete="off" :disabled="isEdit"></el-input>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="权限" label-width='80px'>
                    <el-input v-model="form.securityRole" auto-complete="off"></el-input>
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

        <!--<el-row>-->
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="userName"
                    label="姓名">
            </el-table-column>
            <el-table-column
                    prop="securityRole"
                    label="权限">
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
            <el-table-column
                    label="启用">
                <template slot-scope="scope">
                    <el-switch
                            v-model="scope.row.status"
                            :inactive-value="2"
                            :active-value="1"
                            @change="statusChange(scope.row)"></el-switch>
                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.row)" type="text" size="mini">编辑</el-button>
                    <el-button @click="handleResetKey(scope.row)" type="text" size="mini">密码重置</el-button>
                    <el-button @click="handleDelete(scope.row)" type="text" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--</el-row>-->
        <!--</el-container>-->
    </div>

</template>

<script>
    import request from '@/api/users';
    import util from '@/shares/util';
    export default {
        name: "users",
        data() {
            return {
                tableData: [],
                formInline: {
                    userName: ''
                },
                dialogFormVisible: false,
                form: {
                    userName: '',
                    description: '',
                    //todo 用户权限
                    securityRole:0
                },
                isEdit: false
            };
        },
        created() {
            this.fetchAllUsers({});
        },
        methods: {
            dateFormatter(row, col, value) {
                return util.dateHandle(value, true);
            },
            fetchAllUsers(params, isSubmit) {
                request.queryUsers(params).then(res => {
                    this.tableData = res.data.result.data
                    if(isSubmit && res.data.code == 200) {
                        this.$message({
                            message: '查询成功!',
                            type: 'success'
                        });
                    }

                    if(res.data.code != 200) {
                        this.$message.error('查询失败,msg:' + res.data.msg);
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            onSubmit() {
                this.fetchAllUsers(this.formInline, true);
            },
            closeDialog() {
                this.dialogFormVisible = false;
                this.form = {
                    userName: '',
                    description: ''
                }
            },

            handleCreate(){
                this.form = {
                    userName: '',
                    description: ''
                }
                this.dialogFormVisible = true;
                this.isEdit = false;
            },
            doOperate() {
                this.dialogFormVisible = false;
                if(!this.isEdit){
                    //创建用户
                    request.createUser(this.form).then(res => {
                        if(res.data.code == 200 && res.data.result){
                            this.$message({
                                message: '创建用户成功!',
                                type: 'success'
                            });
                            this.fetchAllUsers({});
                        } else {
                            this.$message.error('创建用户失败!');
                        }
                    }).catch(err => {
                        // 处理请求错误的情况
                    })
                }else {
                    //编辑用户
                    request.updateUser({id:this.$_currentEditUser.id, description:this.form.description}).then(res => {
                        if(res.data.code == 200 && res.data.result){
                            this.$message({
                                message: '用户信息更新成功!',
                                type: 'success'
                            });
                            this.fetchAllUsers({});
                        } else {
                            this.$message.error('用户信息更新失败!');
                        }
                    }).catch(err => {
                        // 处理请求错误的情况
                    })
                }
            },
            handleEdit(row) {
                this.$_currentEditUser = row;
                this.form.userName = this.$_currentEditUser.userName;
                this.form.description = this.$_currentEditUser.description;
                this.dialogFormVisible = true ;
                this.isEdit = true;
            },
            handleDelete(row) {
                request.deleteUser(row).then(res => {
                    if(res.data.code == 200 && res.data.result){
                        this.$message({
                            message: '删除用户:"' + row.userName+ '"成功!',
                            type: 'success'
                        });
                        this.fetchAllUsers({});
                    } else {
                        this.$message.error('删除用户:"' + row.userName+ '"失败!');
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })

            },
            handleResetKey(row) {
                //todo
                console.info(row)
            },
            statusChange(row) {
                request.updateUser({status:row.status,id:row.id}).then(res => {
                    if(res.data.code == 200 && res.data.result){
                        this.$message({
                            message: '用户状态更新成功!',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('用户状态更新失败!');
                        if(row.status == 1) {
                            row.status = 2;
                        } else if (row.status == 2){
                            row.status = 1;
                        }
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
