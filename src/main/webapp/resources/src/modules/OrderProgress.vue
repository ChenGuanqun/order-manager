<template>
    <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="客户名称:">
                <el-input v-model="formInline.customerName" placeholder="customerName"></el-input>
            </el-form-item>
            <el-form-item label="产品名称:">
                <el-input v-model="formInline.productName" placeholder="productName"></el-input>
            </el-form-item>
            <el-form-item label="交货时间:">
                <el-date-picker
                        v-model="formInline.deliveryDateRange"
                        type="daterange"
                        value-format="timestamp"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="是否完工:">
                <el-select v-model="formInline.status"  placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreate">创建</el-button>
            </el-form-item>
        </el-form>

        <el-dialog title="订单进步操作" :visible.sync="dialogFormVisible" width='400px'>
            <el-form :model="form">
                <el-form-item label="客户名称" label-width='80px'>
                    <el-input v-model="form.customerName" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="产品名称" label-width='80px'>
                    <el-input v-model="form.productName" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="产品型号" label-width='80px'>
                    <el-input v-model="form.productSeries" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="数量" label-width='80px'>
                    <el-input v-model="form.number" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="订单时间:" label-width='80px'>
                    <el-date-picker
                            v-model="form.orderDate"
                            type="date"
                            value-format="timestamp"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="交货时间:" label-width='80px'>
                    <el-date-picker
                            v-model="form.deliveryDate"
                            type="date"
                            value-format="timestamp"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="计划时间:" label-width='80px'>
                    <el-date-picker
                            v-model="form.planDate"
                            type="date"
                            value-format="timestamp"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </el-form>

            <el-form :model="form">
                <el-form-item label="产品要求" label-width='80px'>
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
                    prop="customerName"
                    label="客户名称"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="productName"
                    label="产品名称"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="productSeries"
                    label="产品型号"
                    width="100">
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

                    label="产品要求"
                    width="200">
                <template slot-scope="scope">
                    <el-tooltip :content="scope.row.description" placement="top">
                        <span class="description_limit">{{scope.row.description}}</span>
                    </el-tooltip>
                </template>
            </el-table-column>

            <el-table-column
                    prop="properties"
                    label="配件一览"
                    width="493">
                <template slot-scope="scope">
                    <el-checkbox-group v-model="scope.row.configArray" @change="configChange(scope.row)">
                        <el-checkbox label="油漆"></el-checkbox>
                        <el-checkbox label="定子"></el-checkbox>
                        <el-checkbox label="转子"></el-checkbox>
                        <el-checkbox label="风罩"></el-checkbox>
                        <el-checkbox label="钢板件"></el-checkbox><br>
                        <el-checkbox label="轴"></el-checkbox>
                        <el-checkbox label="盖头"></el-checkbox>
                        <el-checkbox label="机壳"></el-checkbox>
                        <el-checkbox label="转子总成"></el-checkbox>
                        <el-checkbox label="定子总成"></el-checkbox><br>
                        <el-checkbox label="包装"></el-checkbox>
                        <el-checkbox label="标牌"></el-checkbox>
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
    </div>

</template>

<script>
    import request from '@/api/progress';
    import util from '@/shares/util';
    export default {
        name: "orderProgress",
        data() {
            return {
                tableData: [],
                checkList: [],
                formInline: {
                    productName: '',
                    status: '0',
                    deliveryDateRange: []
                },
                dialogFormVisible: false,
                form: {
                    customerName: '',
                    productName: '',
                    productSeries: '',
                    number: '',
                    planDate: null,
                    orderDate: null,
                    deliveryDate: null,
                    description: ''
                },
                isEdit: false
            }
        },
        created() {
            this.fetchAllOrders({});
        },
        methods: {
            dateFormatter(row, col, value) {
                return util.dateHandle(value);
            },
            fetchAllOrders(params, isSubmit) {
                request.queryOrder(params).then(res => {
                    this.tableData = res.data.result.data
                    if (isSubmit && res.data.code == 200) {
                        this.$message({
                            message: '查询成功!',
                            type: 'success'
                        });
                    }

                    if (res.data.code != 200) {
                        this.$message.error('查询失败,msg:' + res.data.msg);
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            onSubmit() {
                console.info(this.formInline)
                var queryParams = this.formInline;
                if (this.formInline.deliveryDateRange != undefined && this.formInline.deliveryDateRange.length > 0) {
                    queryParams.startTime = this.formInline.deliveryDateRange[0]
                    queryParams.endTime = this.formInline.deliveryDateRange[1]
                } else {
                    queryParams.startTime = -1
                    queryParams.endTime = -1
                }
                this.fetchAllOrders(queryParams, true);
            },
            handleCreate(){
                this.resetFormData();
                this.dialogFormVisible = true;
                this.isEdit = false;
            },
            handleEdit(row) {
                this.resetFormData();
                this.$_currentEditOrder = row;
                this.form.customerName = row.customerName;
                this.form.productName = row.productName;
                this.form.productSeries = row.productSeries;
                this.form.number = row.number;
                this.form.description = row.description;
                if (row.planDate >= 0) {
                    this.form.planDate = row.planDate;
                }

                if (row.orderDate >= 0) {
                    this.form.orderDate = row.orderDate;
                }

                if (row.deliveryDate >= 0) {
                    this.form.deliveryDate = row.deliveryDate;
                }

                this.dialogFormVisible = true;
                this.isEdit = true;
            },
            handleDelete(row) {
                request.deleteOrder(row).then(res => {
                    if(res.data.code == 200 && res.data.result){
                        this.$message({
                            message: '删除成功!',
                            type: 'success'
                        });
                        this.fetchAllOrders({});
                    } else {
                        this.$message.error('删除失败!');
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            statusChange(row) {
                request.updateStatus({status: row.status, id: row.id}).then(res => {
                    if (res.data.code == 200 && res.data.result) {
                        this.$message({
                            message: '状态更新成功!',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('状态更新失败!');
                        if (row.status == 1) {
                            row.status = 2;
                        } else if (row.status == 2) {
                            row.status = 1;
                        }
                    }
                }).catch(err => {
                    // 处理请求错误的情况
                })
            },
            configChange(row) {
                request.updateStatus({configArray: row.configArray, id: row.id}).then(res => {
                    if (res.data.code == 200 && res.data.result) {
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
            },
            closeDialog() {
                this.dialogFormVisible = false;
//                this.form = {
//                    userName: '',
//                    description: ''
//                }
            },
            resetFormData() {
                this.form = {
                    customerName: '',
                    productName: '',
                    productSeries: '',
                    number: '',
                    planDate: null,
                    orderDate: null,
                    deliveryDate: null,
                    description: ''
                }
            },
            doOperate() {
                this.dialogFormVisible = false;
                if (!this.isEdit) {
                    //创建用户
                    request.createOrder(this.form).then(res => {
                        if (res.data.code == 200 && res.data.result) {
                            this.$message({
                                message: '创建成功!',
                                type: 'success'
                            });
                            this.fetchAllOrders({});
                        } else {
                            this.$message.error('创建失败!');
                        }
                    }).catch(err => {
                        // 处理请求错误的情况
                    })
                } else {
                    //编辑用户
                    request.updateOrder({
                        id: this.$_currentEditOrder.id,
                        customerName: this.form.customerName,
                        productName: this.form.productName,
                        productSeries: this.form.productSeries,
                        number: this.form.number,
                        deliveryDate: this.form.deliveryDate,
                        orderDate: this.form.orderDate,
                        planDate: this.form.planDate,
                        description: this.form.description
                    }).then(res => {
                        if (res.data.code == 200 && res.data.result) {
                            this.$message({
                                message: '信息更新成功!',
                                type: 'success'
                            });
                            this.fetchAllOrders({});
                        } else {
                            this.$message.error('信息更新失败!');
                        }
                    }).catch(err => {
                        // 处理请求错误的情况
                    })
                }
            }
        }
    }
</script>

<style>
    .el-checkbox__label {
        width: 33px;
    }
    .el-tooltip__popper{
        width: 400px;
    }
    .description_limit {
        display: inline-block;
        max-width: 175px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>