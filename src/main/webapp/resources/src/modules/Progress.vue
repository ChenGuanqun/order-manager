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
                width="500">
        </el-table-column>

        <el-table-column
                prop="properties"
                label="配件一览"
                width="193">
            <template slot-scope="scope">
                <el-checkbox-group v-model="checkList">
                    <el-checkbox :label="scope.row.id.toString()+(1)">油漆</el-checkbox>
                    <el-checkbox :label="scope.row.id.toString()+(2)">定子</el-checkbox>
                    <br>
                    <el-checkbox :label="scope.row.id.toString()+(3)">转子</el-checkbox>
                    <el-checkbox :label="scope.row.id.toString()+(4)">风罩</el-checkbox>
                    <br>
                    <el-checkbox :label="scope.row.id.toString()+(5)">钢板件</el-checkbox>
                    <el-checkbox :label="scope.row.id.toString()+(6)">轴</el-checkbox>
                    <br>
                    <el-checkbox :label="scope.row.id.toString()+(7)">盖头</el-checkbox>
                    <el-checkbox :label="scope.row.id.toString()+(8)">机壳</el-checkbox>
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
                        :width='40'></el-switch>
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
    import orderRequest from '@/api/progress';
    import util from '@/shares/util';
    export default {
        name: "progress",
        data() {
            return {
                tableData: [],
                checkList: []
            }
        },
        created() {
            orderRequest.queryOrder({}).then(res => {
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
            }
        }
    }
</script>

<style>
    .el-checkbox__label {
        width: 33px;
    }
</style>