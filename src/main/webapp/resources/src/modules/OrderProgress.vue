<template>
    <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="生产序号:">
                <el-input v-model="formInline.orderId" value="number" placeholder="支持模糊搜索"></el-input>
            </el-form-item>
            <el-form-item label="客户名称:">
                <el-input v-model="formInline.customerName" placeholder="支持模糊搜索"></el-input>
            </el-form-item>
            <el-form-item label="产品名称:">
                <el-input v-model="formInline.productName" placeholder="支持模糊搜索"></el-input>
            </el-form-item>
            <el-form-item label="产品型号:">
                <el-input v-model="formInline.productSeries" placeholder="支持模糊搜索"></el-input>
            </el-form-item>
            <el-form-item label="订单时间:">
                <el-date-picker
                        v-model="formInline.orderDateRange"
                        type="daterange"
                        value-format="timestamp"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
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
                <el-select class="super_mini_el_selector" v-model="formInline.status"  placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="是否交货:">
                <el-select class="super_mini_el_selector" v-model="formInline.deliveryStatus" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="交货" value="2"></el-option>
                    <el-option label="未交货" value="1"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="油漆:">
                <el-select class="super_mini_el_selector" v-model="formInline.yq" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="定子:">
                <el-select class="super_mini_el_selector" v-model="formInline.dz" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="转子:">
                <el-select class="super_mini_el_selector" v-model="formInline.zz" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="风罩:">
                <el-select class="super_mini_el_selector" v-model="formInline.fz" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="钢板件:">
                <el-select class="super_mini_el_selector" v-model="formInline.hbj" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="轴:">
                <el-select class="super_mini_el_selector" v-model="formInline.zc" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="盖头:">
                <el-select class="super_mini_el_selector" v-model="formInline.gttt" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="机壳:">
                <el-select class="super_mini_el_selector" v-model="formInline.jk" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="转子总成:">
                <el-select class="super_mini_el_selector" v-model="formInline.zzzc" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="定子总成:">
                <el-select class="super_mini_el_selector" v-model="formInline.dzzc" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="包装:">
                <el-select class="super_mini_el_selector" v-model="formInline.bz" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="标牌:">
                <el-select class="super_mini_el_selector" v-model="formInline.bp" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="开关:">
                <el-select class="super_mini_el_selector" v-model="formInline.sc" placeholder="请选择">
                    <el-option label="所有" value="0"></el-option>
                    <el-option label="完工" value="2"></el-option>
                    <el-option label="未完工" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="线缆:">
                <el-select class="super_mini_el_selector" v-model="formInline.lc" placeholder="请选择">
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
                            :clearable="false"
                            :editable="false"
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
                :row-class-name="tableRowClassName">
                style="width: 100%">
            <el-table-column
                    prop="orderId"
                    label="生产序号"
                    width="90">
            </el-table-column>
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
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="orderDate"
                    label="订单时间"
                    :formatter="dateFormatter"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="planDate"
                    label="计划时间"
                    :formatter="dateFormatter"
                    width="100">
            </el-table-column>
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
            <el-table-column
                    label="交货"
                    width='60'>
                <template slot-scope="scope">
                    <el-switch
                            v-model="scope.row.deliveryStatus"
                            :active-value="2"
                            :inactive-value="1"
                            :width='40'
                            @change="deliveryStatusChange(scope.row)">
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

        <el-pagination
                background
                layout="total, prev, pager, next"
                @current-change="handleCurrentChange"
                :page-size="10"
                :total="totalCount">
        </el-pagination>
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
                    orderId: null,
                    productName: '',
                    productSeries: '',
                    status: '0',
                    deliveryStatus: '0',
                    deliveryDateRange: [],
                    orderDateRange:[],
                    yq:'0',
                    dz:'0',
                    zz:'0',
                    fz:'0',
                    hbj:'0',
                    zc:'0',
                    gttt:'0',
                    jk:'0',
                    zzzc:'0',
                    dzzc:'0',
                    bz:'0',
                    bp:'0',
                    sc:'0',
                    lc:'0'
                },
                dialogFormVisible: false,
                form: {
                    customerName: '',
                    productName: '',
                    productSeries: '',
                    number: '',
                    planDate: null,
                    orderDate: this.currentDayTime(),
                    deliveryDate: null,
                    description: ''
                },
                isEdit: false,
                totalCount:0,
                currentPage:1
            }
        },
        created() {
            this.fetchAllOrders({pageNum:this.currentPage});
        },
        methods: {
            dateFormatter(row, col, value) {
                return util.dateHandle(value);
            },
            fetchAllOrders(params, isSubmit) {
                request.queryOrder(params).then(res => {
                    this.tableData = res.data.result.data;
                    this.totalCount = res.data.result.count;
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
                /**
                 * 查询按钮
                 * @type {*}
                 */
                if(this.formInline.orderId == "") {
                    this.formInline.orderId = null;
                }

                var queryParams = this.formInline;
                if (this.formInline.deliveryDateRange != undefined && this.formInline.deliveryDateRange.length > 0) {
                    queryParams.startTime = this.formInline.deliveryDateRange[0]
                    queryParams.endTime = this.formInline.deliveryDateRange[1]
                } else {
                    queryParams.startTime = -1
                    queryParams.endTime = -1
                }

                if (this.formInline.orderDateRange != undefined && this.formInline.orderDateRange.length > 0) {
                    queryParams.startOrderTime = this.formInline.orderDateRange[0]
                    queryParams.endOrderTime = this.formInline.orderDateRange[1]
                } else {
                    queryParams.startOrderTime = -1
                    queryParams.endOrderTime = -1
                }
                queryParams.pageNum = this.currentPage;
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
                        this.fetchAllOrders({pageNum:this.currentPage});
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
            deliveryStatusChange(row) {
                request.updateStatus({deliveryStatus: row.deliveryStatus, id: row.id}).then(res => {
                    if (res.data.code == 200 && res.data.result) {
                        this.$message({
                            message: '状态更新成功!',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('状态更新失败!');
                        if (row.deliveryStatus == 1) {
                            row.deliveryStatus = 2;
                        } else if (row.deliveryStatus == 2) {
                            row.deliveryStatus = 1;
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
                    orderDate: this.currentDayTime(),
                    deliveryDate: null,
                    description: ''
                }
            },
            handleCurrentChange(val) {
//                console.log(`当前页: ${val}`);
                this.currentPage  = val;
                this.onSubmit();
            },
            currentDayTime() {
                var currentDayNow = new Date();
                currentDayNow.setHours(0);
                currentDayNow.setMinutes(0);
                currentDayNow.setSeconds(0);
                currentDayNow.setMilliseconds(0);
                return currentDayNow.getTime();
            },
            tableRowClassName({row, rowIndex}) {
//                console.info(row)
                if (row.deliveryStatus === 2) {
                    return 'delivery-row';
                }
                if (row.status === 2) {
                    return 'done-row';
                }

                return '';
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
                            this.fetchAllOrders({pageNum:1});
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
                            this.fetchAllOrders({pageNum:this.currentPage});
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
    .super_mini_el_selector{
        width: 100px;
    }
    .el-table .done-row {
        background: oldlace;
    }

    .el-table .delivery-row {
        background: #f0f9eb;
    }
</style>