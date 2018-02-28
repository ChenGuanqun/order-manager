<template>
    <el-table
            :data="tableData"
            style="width: 100%">
        <el-table-column
                prop="productName"
                label="产品名称"
                width="200">
        </el-table-column>
        <el-table-column
                prop="productSeries"
                label="产品型号"
                width="160">
        </el-table-column>
        <el-table-column
                prop="number"
                label="数量"
                width="60">
        </el-table-column>
        <el-table-column
                prop="deliveryDate"
                label="交货时间"
                :formatter="dateFormatter"
                width="150">
        </el-table-column>
        <el-table-column
                prop="orderDate"
                label="订单时间"
                :formatter="dateFormatter"
                width="150">
        </el-table-column>
        <el-table-column
                prop="planDate"
                label="计划时间"
                :formatter="dateFormatter"
                width="150">
        </el-table-column>
        <!--<el-table-column-->
        <!--prop="insertTime"-->
        <!--label="创建日期"-->
        <!--:formatter="dateFormatter">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
        <!--prop="lastModifyTime"-->
        <!--label="修改日期"-->
        <!--:formatter="dateFormatter">-->
        <!--</el-table-column>-->
        <el-table-column
                prop="description"
                label="产品要求"
                width="400">
        </el-table-column>

        <el-table-column
                prop="properties"
                label="配件一览"
                width="293">
            <template slot-scope="scope">
                <el-checkbox-group v-model="scope.row.configArray" @change="configChange(scope.row)">
                    <el-checkbox label="油漆"></el-checkbox>
                    <el-checkbox label="定子"></el-checkbox>
                    <el-checkbox label="转子"></el-checkbox><br>
                    <el-checkbox label="风罩"></el-checkbox>
                    <el-checkbox label="钢板件"></el-checkbox>
                    <el-checkbox label="轴"></el-checkbox><br>
                    <el-checkbox label="盖头"></el-checkbox>
                    <el-checkbox label="机壳"></el-checkbox>
                    <el-checkbox label="转子总成"></el-checkbox><br>
                    <el-checkbox label="定子总成"></el-checkbox>
                    <el-checkbox label="包装"></el-checkbox>
                    <el-checkbox label="标牌"></el-checkbox><br>
                    <el-checkbox label="开关"></el-checkbox>
                    <el-checkbox label="线缆"></el-checkbox>
                </el-checkbox-group>
            </template>
        </el-table-column>
        <el-table-column
                label="完工"
                width='60'>
            <template slot-scope="scope">
                <el-switch
                        v-model="scope.row.status"
                        :active-value="2"
                        :inactive-value="1"
                        :width='40'
                        @change="statusChange(scope.row)">
                </el-switch>
            </template>
        </el-table-column>
        <el-table-column label="操作" width="100">
            <template slot-scope="scope">
                <el-button @click="handleEdit(scope.row)" type="text" size="mini">编辑</el-button>
                <el-button @click="handleDelete(scope.row)" type="text" size="mini">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    import request from '@/api/progress';
    import util from '@/shares/util';
    export default {
        name: "orderProgress",
        data() {
            return {
                tableData: [],
                checkList: []
            }
        },
        created() {
            request.queryOrder({}).then(res => {
                this.tableData = res.data.result.data
            }).catch(err => {
                // 处理请求错误的情况
            })
        },
        methods: {
            dateFormatter(row, col, value) {
                return util.dateHandle(value, false);
            },
            handleEdit(row) {
                console.log(row);
            },
            handleDelete(row) {
                console.log(row);
            },
            statusChange(row) {
                request.updateOrder({status:row.status,id:row.id}).then(res => {
                    if(res.data.code == 200 && res.data.result){
                        this.$message({
                            message: '订单状态更新成功!',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('订单状态更新失败!');
                        if(row.status == 1) {
                            row.status = 2;
                        } else if (row.status == 2){
                            row.status = 1;
                        }
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            configChange(row) {
                request.updateOrder({configArray:row.configArray,id:row.id}).then(res => {
                    if(res.data.code == 200 && res.data.result){
                        this.$message({
                            message: '配件状态更新成功!',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('配件状态更新失败!');
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            }
        }
    }
</script>

<style>
    .el-checkbox__label {
        width: 33px;
    }
</style>