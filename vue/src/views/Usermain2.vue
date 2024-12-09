<template>
  <div class="app-container">
    <el-steps :active="active" finish-status="success">
      <el-step title="确认预约信息"></el-step>
      <el-step title="选择科室"></el-step>
      <el-step title="选择医生"></el-step>
      <el-step title="支付挂号费"></el-step>
      <el-step title="预约成功"></el-step>
    </el-steps>
    <div v-if="active == 0">
      <div style=" height: 100vh; width: 100%;">
        <div>
          <template>
            <div>
              <el-form :model="form">
                <el-form-item label="姓名" label-width="15%">
                  <el-input v-model="form.name" autocomplete="off" style="width:90%" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="15%">
                  <el-radio v-model="form.sex" label="男">男</el-radio>
                  <el-radio v-model="form.sex" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="年龄" label-width="15%">
                  <el-input v-model="form.age" autocomplete="off" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="电话" label-width="15%">
                  <el-input v-model="form.phone" autocomplete="off" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="预约仅明天" label-width="15%">
                  <el-date-picker v-model="form.time" type="date" placeholder="选择日期" style="width:90%" :disabled="true">
                  </el-date-picker>
                </el-form-item>

              </el-form>
              <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click="cancelForm()">重置回您的信息</el-button>
                <el-button type="primary" @click="submitForm()">确定上传信息</el-button>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
    <div v-if="active == 1">
      <div style="display: flex; justify-content: center; align-items: center; height: 100vh; width: 100%;">
        <div>
          <div class="block">
            <span class="demonstration" style="margin-left: 40px;">请选择你需要的科室</span>
            </br>
            <el-form :model="form2">
              <el-form-item label="" label-width="15%">
                <el-select v-model="form2.typeId" placeholder="请选择" style="width:100%">
                  <el-option v-for="item in typeObjs" :key="item.id" :label="item.keshiname" :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </div>
          <el-button style="margin-top:12px;margin-left: 40px;" type="primary" @click="prev">上一步</el-button>
          <el-button style="margin-top: 12px;" type="primary" @click="next">下一步</el-button>
        </div>
      </div>
    </div>
    <div v-if="active == 2">
      <div>
        <el-button style="margin-top:12px;" type="primary" @click="prev">上一步</el-button>
      </div>
      <div v-for="doctor in doctors" :key="doctor.id">
        <div class="doctor-card">

          <div style="margin-top: 10px; ">
            <div style="margin-left: 80px;margin-top: 20px;">
              <el-image style="width: 200px; height: 200px;" :src="'http://localhost:8080/ikun/files/' + doctor.img"
                :preview-src-list="['http://localhost:8080/ikun/files/' + doctor.img]">
              </el-image>
            </div>
            <div style="margin-left: 100px;">姓名：{{ doctor.name }}</div>
            <div style="margin-left: 100px;">所属科室：{{ doctor.typeName }}</div>
            <div style="margin-left: 100px;">职位：{{ doctor.posts }}</div>
            <div style="margin-left: 100px;">预约费：￥{{ doctor.qian }}</div>
            <div style="margin-left: 100px;">位置剩余：{{ doctor.num }}</div>
            <el-popconfirm title="确定预约该医生？" style="margin-left: 90px;" @confirm="selectDoctor(doctor)">
              <el-button slot="reference" style="margin-left: 15px;width: 100px;">预约</el-button>
            </el-popconfirm>
          </div>
        </div>
      </div>
      <div style="display: flex; justify-content: center; align-items: center; height: 20vh; width: 100%;">

      </div>

    </div>

    <div v-if="active == 3">
      <div style="margin-left: 40%;">
        <img src="../assets/wx.jpg" style="width: 400px;height: 400px;text-align: center;">
      </div>
      <div style="margin-left: 50%;">
        <el-button style="margin-top: 12px;" type="primary" @click="next">支付完成</el-button>
      </div>

    </div>
    <div v-if="active == 4">
      <div>
        <div>
          <span style="text-align: center;font-size: 80px;">预约成功</span>
        </div>
        <hr>
        <div>
          <el-form :model="form">
            <el-form-item label="姓名" label-width="15%">
              <el-input v-model="form.name" autocomplete="off" style="width:90%" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="性别" label-width="15%">
              <el-input v-model="form.sex" autocomplete="off" style="width:90%" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="年龄" label-width="15%">
              <el-input v-model="form.age" autocomplete="off" style="width:90%" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="电话" label-width="15%">
              <el-input v-model="form.phone" autocomplete="off" style="width:90%" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="预约时间" label-width="15%">
              <el-date-picker v-model="form.time" type="date" placeholder="选择日期" style="width:90%" :disabled="true">
              </el-date-picker>
            </el-form-item>
            <el-button style="margin-top: 12px;margin-left: 400px;" type="primary" @click="pfe()">完成</el-button>
          </el-form>

        </div>

      </div>

    </div>

  </div>
</template>

<style>
.yuyue {
  display: flex;
  /* 使用flex布局 */
  flex-wrap: wrap;
  /* 防止卡片换行 */
  justify-content: space-between;
  /* 在卡片之间均匀分布空间 */
}

.doctor-card {
  width: 17%;
  /* 设置卡片宽度为容器宽度的23%，留出空间给margin */
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  overflow: hidden;
  margin: 1%;
  /* 设置外边距为1%，确保卡片之间有间隔 */
  box-sizing: border-box;
  /* 确保宽度包括内边距和边框 */
}

.doctor-card img {
  width: 100%;
  /* 图片宽度填满卡片宽度 */
  height: auto;
}

/* 其他样式保持不变 */

.doctor-info {
  padding: 10px;
}

.doctor-name {
  font-weight: bold;
  margin: 5px 0;
}

.doctor-department {
  color: #888;
}

.doctor-intro {
  font-size: 12px;
  color: #555;
}

.doctor-fee {
  color: #ff5722;
  font-weight: bold;
  margin: 10px 0;
}

.doctor-remaining {
  color: #888;
}
</style>


<script>
import request from '@/utils/request';

export default {
  data() {
    return {

      dialogVisible: false,//弹出层
      fileList: [],//图片上传
      active: 0,//步骤条初始化
      formInline: {
        user: '',
        region: ''
      },
      ruleForm: {
        store_name: '',
        cate_id: '',
        keyword: '',
        unit_name: '',
        store_info: '',
        type: [],
        resource: '',
        desc: ''
      },
      form: {
        name: '', // 姓名
        loginname: '', // 登录名
        sex: '', // 性别
        age: '', // 年龄
        phone: '' // 电话
      },
      dialogFormVisible: false,
      tableData: [],

      typeObjs: [],

      form2: {},

      doctors: [],

      originalUser: {},

      tableData2: [],
      

    };
  },
  created() {
    this.fetchReservations();
    this.findTypes();

    const user = localStorage.getItem("user")
      ? JSON.parse(localStorage.getItem("user"))
      : {};
    this.originalUser = { ...user }; // 保存原始用户信息
    this.form = { ...user }; // 初始化表单为用户信息

    // 设置默认预约时间为明天且禁用更改
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 2); // 加一天得到明天的日期
    tomorrow.setHours(0, 0, 0, 0); // 将时间设置为当天的开始，即午夜
    this.form.time = tomorrow.toISOString().split('T')[0]; // 格式化为 YYYY-MM-DD

    this.userId = user.id;
  },

  methods: {

    pfe() {
      this.$router.push("/usermain/usermain3");
    },
    fetchReservations() {
      request.get("/reserve").then(res => {
        if (res.code === '0') {
          this.tableData2 = res.data;
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
    selectDoctor(doctor) {
      console.log("Doctor selected:", doctor); // 用于调试
      // 调用后端接口发送预约请求
      const reservationInfo = {
        name: this.form.name,
        sex: this.form.sex,
        age: parseInt(this.form.age), // 确保年龄是整数
        phone: this.form.phone,
        time: this.form.time,
        keshiname: doctor.typeName,
        doctor: doctor.name,
        doctorId: doctor.id,
        userId: this.userId,
      };
      this.submitReservation(reservationInfo, doctor.id);
    },
    submitReservation(reservationInfo) {
      request.post("/reserve", reservationInfo).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '预约成功',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.next();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      });
    },
    jnum() {

    },
    cancelForm() {
      // 重置表单到原始的用户信息
      this.form = { ...this.originalUser };
    },
    submitForm() {
      // 发送请求更新用户信息
      request.post("/user", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '提交成功',
            type: 'success'
          });
          // 更新localStorage中的用户信息
          localStorage.setItem("user", JSON.stringify(this.form));

          //下一步
          this.next();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      });
    },
    next() {
      if (this.active === 0) {
        this.active++;
      } else if (this.active === 1 && this.form2.typeId) {
        const keshiname = this.typeObjs.find(type => type.id === this.form2.typeId).keshiname;
        if (keshiname) {
          this.findDoctorsByKeshiId(keshiname);
        } else {
          console.error('Selected keshi ID does not exist in typeObjs:', this.form2.typeId);
        }
        this.active++;
      } else if (this.active === 2) {
        this.active++;
      } else if (this.active === 3) {
        // 添加对支付完成步骤的处理
        this.active++;
      }
    },
    findDoctorsByKeshiId(keshiname) {
      request.get("/doctor/findByKeshiname", { params: { keshiname } }).then(res => {
        if (res.code === '0') {
          this.doctors = res.data;
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      });
    },
    // ...
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
      request.get("/doctor").then(res => {
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


    //上一页
    prev() {
      if (this.active > 0) this.active--;
    },
    //文件上传
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //弹出层
    addTemplate() {
      this.dialogVisible = true//弹出层
    },
    onSubmit() {
      console.log('submit!');
    },
    handleSelectionChange(selection) {
      // 处理表格行选择变化的逻辑
      console.log(selection);
    },
  }
}



</script>