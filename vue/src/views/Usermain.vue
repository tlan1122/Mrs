<template>

    <el-container>
        <el-header style="height: 20%;width: 100%;">
            <div>


            </div>
            <div style="margin-top: 10px;background-color: aliceblue; height: 60px;">
                <div style="padding: 10px;">
                    <img src="../assets/ikunlogo.png" style="height: 30px;width: 30px;">
                    <span style="font-size: 30px;color:#409EFF;font-family:STXingkai">
                        XX人民医院
                    </span>

                    <div style="float: right;" v-if="user.name == null">
                        <span>
                            您的账号未登录，
                        </span>
                        <el-link :underline="false"
                            style="font-size: 20px;padding: 10px;text-decoration: none;color: #409EFF;font-family:STXingkai"
                            @click="loginindex()">账号登录</el-link>
                        <span style="font-size: 20px;">？</span>
                        <el-link :underline="false"
                            style="font-size: 20px;padding: 10px;text-decoration: none;color:#409EFF;font-family:STXingkai"
                            @click="zhuceindex()">账号注册</el-link>
                    </div>

                    <el-dropdown style="float: right;" v-if="user.name != null">
                        <span class="el-dropdown-link" style="font-size: 20px;">
                            <i class="el-icon-s-custom" style="margin-right: 10px;"></i>欢迎你,
                            {{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item><span @click="loginout()">退出登录</span></el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                </div>
            </div>
        </el-header>
        <el-divider></el-divider>
        <el-main style="height: 60%;">
            <div>
                <div>
                    <el-carousel :interval="4000" type="card" height="400px">
                        <el-carousel-item v-for="item in imgList" :key="item.id">
                            <img :src="item.idView" class="image" style="width: 100%;height: 400px;">
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <el-divider></el-divider>
                <div>


                    <el-menu default-active="$route.path:" router class="el-menu-demo" background-color="#E8E8E8"
                        active-text-color="#060270" mode="horizontal">

                        <el-menu-item index="/usermain/usermain1">
                            预约指南
                        </el-menu-item>
                        <el-menu-item index="/usermain/usermain2">
                            预约挂号
                        </el-menu-item>
                        <el-menu-item index="/usermain/usermain3">
                            我的挂号
                        </el-menu-item>
                        <el-menu-item index="/usermain/usermain4">
                            修改账号密码
                        </el-menu-item>

                    </el-menu>
                </div>
                <div>
                    <el-card class="box-card" style="height: 100vh;">
                        <div class="text item">
                            <router-view></router-view>
                        </div>
                    </el-card>

                </div>
            </div>
        </el-main>

        <el-footer style="height: 40px;background-color: aliceblue;">
            <div style="padding: 10px; text-align: center;">
                版权声明：谭磊爸爸，李麻花，麻麻鱼
            </div>

        </el-footer>
    </el-container>


</template>


<style>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>

<script>

export default {
    name: "usermain",
    data() {
        return {
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            imgList: [
            { id: 0, idView: require('../assets/shouye.png') },
                { id: 1, idView: require('@/assets/shouye2.jpg') },
                { id: 2, idView: require('@/assets/shouye3.jpg') },
            ]

        }
    },
    methods: {
        loginout() {
            localStorage.removeItem("user");
            this.$router.push("/userlogin");
        },

        zhuceindex() {
            this.$router.push("/userregister");
        },

        loginindex() {
            this.$router.push("/userlogin");
        },

    }
}
</script>