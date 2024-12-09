<template>
  <div>


    <el-container>

      <el-aside style="overflow: hidden;min-height: 100vh;background-color:#E8E8E8;width: 250px;">

        <el-menu default-active="$route.path:" router class="el-menu-vertical-demo" background-color="#E8E8E8"
          active-text-color="#060270">

          <el-menu-item index="/Admingl/home" default-active>

            <i class="el-icon-menu"></i>
            <span slot="title">系统首页</span>

          </el-menu-item>

          <el-submenu index="1">

            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>人员信息管理</span>
            </template>

            <el-menu-item-group>
              <el-menu-item index="/Admingl/doctor"><i class="el-icon-location"></i>医生信息</el-menu-item>
              <el-menu-item index="/Admingl/user"><i class="el-icon-location"></i>用户信息</el-menu-item>
              <el-menu-item index="/Admingl/admin"><i class="el-icon-location"></i>管理员信息</el-menu-item>
            </el-menu-item-group>



          </el-submenu>


          <el-submenu index="2">

            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>科室信息管理</span>
            </template>

            <el-menu-item-group>
              <el-menu-item index="/Admingl/keshi"><i class="el-icon-location"></i>科室信息</el-menu-item>
              <el-menu-item index="/Admingl/doctorguahao"><i class="el-icon-location"></i>医生挂号</el-menu-item>
            </el-menu-item-group>



          </el-submenu>

        </el-menu>
      </el-aside>

      <el-main>
        <div>
          <el-header>

            <el-dropdown style="float: right;">
              <span class="el-dropdown-link" style="font-size: 20px;">
                <i class="el-icon-s-custom" style="margin-right: 10px;"></i>欢迎你，{{ admin.name }}<i
                  class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><span @click="loginout()">退出登录</span></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

          </el-header>

        </div>

        <div>
          <router-view />
        </div>

      </el-main>
    </el-container>


  </div>
</template>

<style>
.el-menu {
  border-right: none !important;
}
</style>

<script>

export default {
  data() {
    return {
      admin: localStorage.getItem("admin") ? JSON.parse(localStorage.getItem("admin")) : {}
    }
  },

  methods: {
    loginout() {
      localStorage.removeItem("admin");
      this.$router.push("/login");
    },
  }
}
</script>