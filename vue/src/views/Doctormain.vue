<template>
  <div>
    <div style="font-size: 30px;width: 100%;text-align: center;">
      我的挂号
    </div>

    <hr>
    <div>
      <el-dropdown style="float: right;">
        <span class="el-dropdown-link" style="font-size: 20px;">
        我的挂号数量剩余：
          {{ doctor.num }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><span @click="numc()">修改挂号数量</span></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown style="float: right;">
        <span class="el-dropdown-link" style="font-size: 20px;">
          <i class="el-icon-s-custom" style="margin-right: 10px;"></i>欢迎你,
          {{ doctor.name }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><span @click="loginout()">退出登录</span></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

      
    </div>
    <div style="margin-top: 15px;margin-bottom: 15px;">
      <el-input v-model="params.name" placeholder="请输入姓名" style="width: 200px;"></el-input>
      <el-button type="warning" style="margin-left: 10px;" @click="removeqi()">清空搜索框</el-button>
      <el-button type="warning" style="margin-left: 10px;" @click="findby()">查询搜索框</el-button>

      <div style="margin-top: 10px;">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="params.pageNum" :page-sizes="[5, 10, 15, 20]" :page-size="params.pageSize"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
    </div>
    <div>
      <template>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="status" label="就诊状态" width="250">
          </el-table-column>
          <el-table-column prop="reason" label="症状以及医嘱" width="250">
          </el-table-column>
          <el-table-column prop="id" label="单号" width="250">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="250">
          </el-table-column>
          <el-table-column prop="sex" label="性别" width="250">
          </el-table-column>
          <el-table-column prop="age" label="年龄" width="250">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="250">
          </el-table-column>
          <el-table-column prop="time" label="预约时间" width="250">
          </el-table-column>
          <el-table-column prop="keshiname" label="科室" width="250">
          </el-table-column>
          <el-table-column prop="doctor" label="医生" width="250">
          </el-table-column>

          <el-table-column fixed="right" label="操作" width="300">
            <!--template slot-scope="scope" 意思是选中整行的数据  -->
            <template slot-scope="scope">
              <!-- @click="edit(scope.row)传整行的数据给这个按钮 -->
              <el-button @click="edit(scope.row)">编辑就诊状态</el-button>
              <!-- ui官网给的组件 confirm也是官方给的点击确定事件-->
              <el-popconfirm title="这段数据确定删除吗？" @confirm="del(scope.row.id)">
                <el-button slot="reference" style="margin-left: 15px;">删除该数据</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>

    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">

          <el-form-item label="就诊状态" label-width="15%">
            <el-radio v-model="form.status" label="已就诊" border>已就诊</el-radio>
          </el-form-item>
          <el-form-item label="症状以及医嘱" label-width="15%">
            <el-input v-model="form.reason" autocomplete="off" style="width:90%"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="back()">取 消</el-button>
          <el-button type="primary" @click="addsu()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>




</template>

<script>
import request from '@/utils/request';

export default {

  data() {
    return {
      params: {
        name: '',
        phone: '',
        pageNum: 1,//默认页码为1
        pageSize: 5,//默认5条为一页
      },
      tableData: [],//这里放表格的数据

      total: 0,

      dialogFormVisible: false,

      form: {},

      typeObjs: [],
      doctor: localStorage.getItem("doctor") ? JSON.parse(localStorage.getItem("doctor")) : {},
    }
  },
  //页面加载的时候，做一些事情，在created里面写
  created() {
    this.Load();
    this.findby();
    this.findTypes();
  },

  //定义一些页面上的控件触发的事件调用的方法，按钮之类的
  methods: {
    loginout(){
            localStorage.removeItem("doctor");
            this.$router.push("/doctorlogin");
        },

    findTypes() {
      request.get("/keshi").then(res => {
        if (res.code === '0') {
          this.typeObjs = res.data;
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    Load() {
      request.get("/reserve").then(res => {
        if (res.code === '0') {
          this.tableData = res.data//res就是后端得到的数据
        } else {
          //ui官网的消息提示组件
          this.$message({
            message: res.msg,
            type: 'error'
          });

        }
      })
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
          this.tableData = res.data.list.filter(item => item.doctor === this.doctor.name);
          this.total = res.data.total
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

    //增加数据
    add() {
      this.form = {};
      this.dialogFormVisible = true;
    },

    //把新增的数据传输到后台数据库
    addsu() {
      //get是从后台数据库得到数据，post是从前端发数据给后台数据库接受修改
      request.post("/reserve", this.form).then(res => {
        if (res.code === '0') {

          //ui官网的消息提示组件
          this.$message({
            message: '操作成功',
            type: 'success'
          });

          this.dialogFormVisible = false;

          this.findby();//刷新一下
        } else {

          //ui官网的消息提示组件
          this.$message({
            message: res.msg,
            type: 'error'
          });

        }
      })
    },

    //修改行内的数据
    edit(obj) {
      this.form = obj;
      this.dialogFormVisible = true;
    },

    //修改数据时取消键的bug
    back() {
      this.findby();
      this.dialogFormVisible = false;
    },

    //删除一行的数据，根据id来删除...
    del(id) {
      //delete请求
      request.delete("/reserve/" + id).then(res => {
        if (res.code === '0') {

          //ui官网的消息提示组件
          this.$message({
            message: '删除成功',
            type: 'success'
          });

          this.findby();

        } else {
          //ui官网的消息提示组件
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },

    
    numc() {
    // 弹出一个对话框让用户输入新的挂号数量
    this.$prompt('请输入新的挂号数量', '修改挂号数量', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      inputPattern: /^\d+$/,
      inputErrorMessage: '挂号数量必须为数字'
    }).then(({ value }) => {
      // value 是用户输入的新挂号数量
      const newNum = parseInt(value, 10);

      // 检查新挂号数量是否有效
      if (isNaN(newNum) || newNum <= 0) {
        this.$message({
          message: '挂号数量必须为正整数',
          type: 'error'
        });
        return;
      }

      // 发送请求到后端更新挂号数量
      request.post("/doctor/updateNum", { id: this.doctor.id, num: newNum }).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '挂号数量更新成功',
            type: 'success'
          });
          // 更新本地存储的医生信息
          this.doctor.num = newNum;
          localStorage.setItem("doctor", JSON.stringify(this.doctor));
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      }).catch(() => {
        // 用户取消操作
        this.$message({
          message: '操作已取消',
          type: 'info'
        });
      });
    }).catch(() => {
      // 用户取消操作
      this.$message({
        message: '操作已取消',
        type: 'info'
      });
    });
  },
  }
}
</script>