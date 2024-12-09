<template>
  <div>
    <div style="font-size: 30px;width: 100%;text-align: center;">
      科室信息
    </div>
    <hr>
    <div style="margin-top: 15px;margin-bottom: 15px;">
      <el-input v-model="params.keshiname" placeholder="请输入姓名" style="width: 200px;"></el-input>
      <el-button type="warning" style="margin-left: 10px;" @click="removeqi()">清空搜索框</el-button>
      <el-button type="warning" style="margin-left: 10px;" @click="findby()">查询搜索框</el-button>
      <el-button type="" style="margin-left: 10px;" @click="add()">新增表数据</el-button>

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
          <el-table-column prop="id" label="id" width="250">
          </el-table-column>
          <el-table-column prop="keshiname" label="科室名称" width="250">
          </el-table-column>
          <el-table-column prop="keshimiaoshu" label="科室描述" width="700">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="300">
          <!--template slot-scope="scope" 意思是选中整行的数据  -->
          <template slot-scope="scope">
            <!-- @click="edit(scope.row)传整行的数据给这个按钮 -->
            <el-button @click="edit(scope.row)">编辑该数据</el-button>

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

          <el-form-item label="科室名称" label-width="15%">
            <el-input v-model="form.keshiname" autocomplete="off" style="width:90%"></el-input>
          </el-form-item>

          <el-form-item label="科室描述" label-width="15%">
            <el-input v-model="form.keshimiaoshu" autocomplete="off" style="width:90%"></el-input>
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
        keshiname: '',
        keshimiaoshu: '',
        pageNum: 1,//默认页码为1
        pageSize: 5,//默认5条为一页
      },
      tableData: [],//这里放表格的数据

      total: 0,

      dialogFormVisible: false,

      form: {},
    }
  },
  //页面加载的时候，做一些事情，在created里面写
  created() {
    this.findby();
  },

  //定义一些页面上的控件触发的事件调用的方法，按钮之类的
  methods: {
    Load() {
      request.get("/keshi").then(res => {
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
        keshiname: '',
        keshimiaoshu: '',
        pageNum: 1,//默认页码为1
        pageSize: 5,//默认5条为一页
      }
    },

    //模糊查询
    findby() {
      //get是从后台数据库得到数据，post是从前端发数据给后台数据库接受修改
      request.get("/keshi/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list//这里的赋值要看网页端请求的网络返回的值
          this.total = res.data.total
          console.log(res.data.list)
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
      request.post("/keshi", this.form).then(res => {
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
      request.delete("/keshi/" + id).then(res => {
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
    }
  }
}
</script>