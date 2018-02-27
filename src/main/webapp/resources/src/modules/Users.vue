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
                <el-button type="primary" @click="dialogFormVisible = true">创建用户</el-button>
            </el-form-item>
        </el-form>
        <!--</el-row>-->

        <el-dialog title="创建用户" :visible.sync="dialogFormVisible" width='400px'>
            <el-form :model="form">
                <el-form-item label="姓名" label-width='80px'>
                    <el-input v-model="form.userName" auto-complete="off"></el-input>
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
                    uerName: ''
                },
                dialogFormVisible: false,
                form: {
                    userName: '',
                    description: ''
                }
            };
        },
        created() {
            request.queryUsers({}).then(res => {
                this.tableData = res.data.result.data
            }).catch(err => {
                // 处理请求错误的情况
            })
        },
        methods: {
            dateFormatter(row, col, value) {
                return util.dateHandle(value);
            },
            onSubmit() {
                request.queryUsers(this.formInline).then(res => {
                    this.tableData = res.data.result.data
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            closeDialog() {
                this.dialogFormVisible = false;
                this.form = {
                    uerName: '',
                    description: ''
                }
            },

            doOperate() {
                this.dialogFormVisible = false;
                request.createUser(this.form).then(res => {
                    console.info(res);
                }).catch(err => {
                    // 处理请求错误的情况
                })
                request.queryUsers({}).then(res => {
                    this.tableData = res.data.result.data
                }).catch(err => {
                    // 处理请求错误的情况
                })
                this.form = {
                    uerName: '',
                    description: ''
                }
            }
        }
    }
</script>

<style scoped>

</style>
