<template>
    <div>
        <el-form :model="form">

            <el-form-item label="账号" label-width="15%">
                <el-input v-model="form.loginname" autocomplete="off" style="width:90%"></el-input>
            </el-form-item>

            <el-form-item label="密码" label-width="15%">
                <el-input v-model="form.password" autocomplete="off" style="width:90%"></el-input>
            </el-form-item>
            

        </el-form>
        <div slot="footer" class="dialog-footer" style="text-align: center;">
            <el-button @click="back()">取 消</el-button>
            <el-button type="primary" @click="addsu()">确 定</el-button>
        </div>
    </div>
</template>


<script>
import request from '@/utils/request';

export default {
    name: "usermain4",
    data() {
        return {
            form: {
                id: null,
                name: '', // 姓名
                loginname: '', // 登录名
                password:'',
                sex: '', // 性别
                age: '', // 年龄
                phone: '' // 电话
            },
            dialogFormVisible: false
        }
    },
    created() {
        // 假设用户信息存储在localStorage中
        const user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {};
        // 将用户信息赋值给表单
        this.form = {
            id: user.id,
            name: user.name,
            loginname: user.loginname,
            sex: user.sex,
            age: user.age,
            phone: user.phone
        };
    },
    methods: {
        //把修改的数据传输到后台数据库
        addsu() {
            //get是从后台数据库得到数据，post是从前端发数据给后台数据库接受修改
            request.post("/user", this.form).then(res => {
                if (res.code === '0') {

                    //ui官网的消息提示组件
                    this.$message({
                        message: '操作成功',
                        type: 'success'
                    });

                    this.dialogFormVisible = true;

                } else {

                    //ui官网的消息提示组件
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });

                }
            })
        },
        findby() {
            //get是从后台数据库得到数据，post是从前端发数据给后台数据库接受修改
            request.get("/user/search", {
                params: this.params
            }).then(res => {
                if (res.code === '0') {
                    // 假设后端返回了完整的用户信息，更新form对象
                    this.form = res.data; // 请根据实际返回的数据结构调整
                    this.dialogFormVisible = true;
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
    }
}
</script>