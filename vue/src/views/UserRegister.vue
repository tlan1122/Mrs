<template>
    <div class="background" style="height: 100vh;">
        <el-container>
            <el-aside width="50%">
                <img src="@/assets/login.png" alt="" style="width: 900px;position: relative;top:10px;">
            </el-aside>
            <el-main width="50%">
                <div
                    style="width: 400px; height: 600px; margin: 150px ;margin-top: 200px ; background: linear-gradient(to top,  #76aaf3, #ffffff); border-radius: 10px">
                    <div style="margin-top: 20px;text-align: center;">
                        <el-link :underline="false"style="font-size: 20px;padding: 10px;text-decoration: none;color: #CECECE;font-family:STXingkai" @click="loginindex()">账号登录</el-link>
                        <span style="font-size: 20px;">|</span>
                        <el-link :underline="false"style="font-size: 20px;padding: 10px;text-decoration: none;color: #409EFF;font-family:STXingkai" @click="zhuceindex()">账号注册</el-link>
                       
                    </div>
                    <div style="margin-top: 40px;">
                        <el-button  type="primary" style="margin-left: 40px;">用户注册</el-button>
                        <el-button  @click="doctorregisterindex()" style="margin-left: 38px;">医者注册</el-button>
                        <el-button @click="adimnregisterindex()" style="margin-left: 39px;">管理注册</el-button>
                    </div>
                    <div style="margin-top: 25px;text-align: center; height: 320px;">
                        <el-form :model="user">
                            <el-form-item>
                                <el-input v-model="user.name" prefix-icon="el-icon-user" style="width: 80%"
                                    placeholder="清输入您的姓名"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.loginname" prefix-icon="el-icon-user" style="width: 80%"
                                    placeholder="清输入账号"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.password" prefix-icon="el-icon-lock" style="width: 80%"
                                    placeholder="清输入密码">
                                </el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.sex" prefix-icon="el-icon-user" style="width: 80%"
                                    placeholder="清输入您的性别"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.age" prefix-icon="el-icon-user" style="width: 80%"
                                    placeholder="清输入您的年龄"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.phone" prefix-icon="el-icon-user" style="width: 80%"
                                    placeholder="清输入您的电话号码"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button style="width: 80%; margin-top: 10px" type="primary"
                                    @click="register()">注册</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </el-main>
        </el-container>

    </div>
</template>
<script>
import request from '@/utils/request';

export default {
    name: "UserRegister",
    data() {
        return {

            user: {
            }
        }
    },
    methods: {
        register() {
            request.post("/user/register", this.user).then(res => {
                if (res.code === '0') {
                    //成功跳转到首页
                    this.$router.push("/userlogin");

                    //ui官网的消息提示组件
                    this.$message({
                        message: '注册成功',
                        type: 'success'
                    });


                } else {

                    //ui官网的消息提示组件
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });
                }
            })
        },

        loginindex(){
            this.$router.push("/userlogin");
        },

        
        doctorregisterindex(){
            this.$router.push("/doctorregister");
        },

        adimnregisterindex(){
            this.$router.push("/register");
        }

    }
}
</script>