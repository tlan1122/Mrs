<template>
    <div>
        <template>
            <el-table ref="multipleTable" :data="tableData2" tooltip-effect="dark" style="width: 100%"
                @selection-change="handleSelectionChange">

                <el-table-column prop="name" label="姓名" width="100">
                </el-table-column>
                <el-table-column prop="sex" label="性别" width="100">
                </el-table-column>
                <el-table-column prop="phone" label="电话号码" width="200" show-overflow-tooltip>
                </el-table-column>

                <el-table-column prop="time" label="预约时间">
                </el-table-column>
                <el-table-column prop="keshiname" label="选择科室">
                </el-table-column>
                <el-table-column prop="doctor" label="选择医生">
                </el-table-column>
                <el-table-column prop="doctor" label="选择医生">
                </el-table-column>
                <el-table-column prop="status" label="就诊状态">
                </el-table-column>
                <el-table-column prop="reason" label="症状以及医嘱">
                </el-table-column>

                <el-table-column fixed="right" label="操作" width="300">
                    <!--template slot-scope="scope" 意思是选中整行的数据  -->
                    <template slot-scope="scope" v-if="scope.row.status !== '已就诊'">
                        <el-popconfirm title="确定取消预约吗？" @confirm="del(scope.row.id)">
                            <el-button slot="reference" style="margin-left: 15px;">取消预约</el-button>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>

        </template>

    </div>
</template>


<script>
import request from '@/utils/request';

export default {
    name: "usermain3",
    data() {
        return {
            params: {
                name: '',
                phone: '',
                pageNum: 1,//默认页码为1
                pageSize: 5,//默认5条为一页
            },
            total: 0,
            tableData2: [],
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
        }
    },
    created() {
        this.fetchReservations();
    },
    methods: {
        del(id) {
            // 查找当前行的数据
            const reserve = this.tableData2.find(item => item.id === id);
            if (reserve && reserve.status === '已就诊') {
                // 如果就诊状态为已就诊，则不执行取消预约操作，并提示用户
                this.$message({
                    message: '已就诊的预约不能取消',
                    type: 'warning'
                });
                return; // 终止方法执行
            }

            // 执行删除请求
            request.delete("/reserve/" + id).then(res => {
                if (res.code === '0') {
                    this.$message({
                        message: '取消预约成功',
                        type: 'success'
                    });

                    this.findby();
                } else {
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });
                }
            });
        },

        //清空搜索框
        removeqi() {
            this.params = {
                name: '',
                phone: '',
                pageNum: 1,//默认页码为1
                pageSize: 5,//默认5条为一页
            }
        },
        //模糊查询
        findby() {
            //get是从后台数据库得到数据，post是从前端发数据给后台数据库接受修改
            request.get("/reserve/search", {
                params: this.params
            }).then(res => {
                if (res.code === '0') {
                    this.tableData = res.data;
                    this.total = res.data.total;
                } else {
                    //ui官网的消息提示组件
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });
                }
            }

            )
        },
        fetchReservations() {
            request.get("/reserve").then(res => {
                if (res.code === '0') {
                    this.tableData2 = res.data.filter(item => item.name === this.user.name);
                } else {
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });
                }
            }).catch(error => {
                console.error("获取预约数据失败：", error);
                this.$message({
                    message: '获取预约数据失败',
                    type: 'error'
                });
            });
        },
        //切换每页的条数（一页多少条数据）
        handleSizeChange(pagesize) {
            this.params.pageSize = pagesize;
            this.findby();
        },

        //切换页码
        handleCurrentChange(pageNum) {
            this.params.pageNum = pageNum;
            this.findby();
        },


    }
}
</script>