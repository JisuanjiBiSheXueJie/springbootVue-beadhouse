<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
      >
        <el-menu-item index="0" style="border-left: 10px solid rgba(5,80,154,0.59);
">
          <i class="el-icon-menu"></i>
          <template #title>投诉建议</template>
        </el-menu-item>
        <el-menu-item index="1" @click="contact">
          <i class="el-icon-document"></i>
          <template #title>联系我们</template>
        </el-menu-item>
        <el-menu-item index="2" @click="JoinWe">
          <i class="el-icon-setting"></i>
          <template #title>加入我们</template>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header><h1>投诉建议</h1></el-header>
      <el-main>
        <div class="mainDiv">
          <p>您的问题:&nbsp;
            <el-select
                v-model="value"
                multiple
                filterable
                allow-create
                default-first-option
                placeholder="请选择你的问题"
            >
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              >
              </el-option>
            </el-select>
          </p>
          <el-input
              v-model="textarea"
              :rows="10"
              type="textarea"
              placeholder="详细问题"
          />
        </div>
        <div style="text-align: center;margin-top: 6vh;">
          <el-button type="primary" plain>提交</el-button>
        </div>
        <div>
          <el-dialog v-model="dialogTableVisible" :title="title">
            <el-table :data="gridData" v-if="isJoinOrContact">
              <el-table-column
                  property="id"
                  label="编号"
                  width="50"
              ></el-table-column>
              <el-table-column
                  property="name"
                  label="名称"
                  width="120"
              ></el-table-column>
              <el-table-column property="address" label="联系地址"></el-table-column>
              <el-table-column property="phone" label="联系电话"></el-table-column>
              <el-table-column property="charge" label="负责人"></el-table-column>
            </el-table>
            <div v-if="!isJoinOrContact">
              <el-form ref="form" :model="form"  size="normal" :rules="rules" >
                <el-form-item label="名  称:" :label-width="formLabelWidth" prop="name">
                  <el-input placeholder="加入名称" v-model="form.name" autocomplete="off" minlength="0" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="地  址:" :label-width="formLabelWidth" prop="address">
                  <el-input v-model="form.address" autocomplete="off"  placeholder="地址" clearable></el-input>
                </el-form-item>
                <el-form-item label="联系号码:" :label-width="formLabelWidth" prop="phone">
                  <el-input v-model="form.phone" autocomplete="off" placeholder="请填写练习号码" clearable></el-input>
                </el-form-item>
                <el-form-item label=" 负责人:" :label-width="formLabelWidth" prop="charge">
                  <el-input v-model="form.charge" autocomplete="off" placeholder="负责人" clearable></el-input>
                </el-form-item>
              </el-form>
              <div style="text-align: center;">
                <el-button type="primary" @click="JoinPut" plain>加 入</el-button>
              </div>
            </div>

          </el-dialog>
        </div>
      </el-main>
      <el-footer>
        <Footer/>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import Footer from "@/components/Guest/Footer";
import {Check} from "@element-plus/icons-vue";
import axios from "axios";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "ContactUs",
  components: {Footer, Check},
  methods: {
    contact() {
      let that = this;
      that.gridData = [];
      request.post("/ContactUs/GetContactFeedbackText").then(res => {
        if (res.status == 200) {
          let data = res.data;
          for (let i = 0; i < data.length; i++) {
            that.grids.name = data[i].name;
            that.grids.address = data[i].address;
            that.grids.id = i + 1;
            that.grids.phone = data[i].phone;
            that.grids.charge = data[i].charge;
            that.gridData.push(that.grids)
          }
        }
      })
      that.title = "联系我们";
      that.isJoinOrContact = true;
      that.dialogTableVisible = true;
    },
    dialogFormVisible() {
    },
    JoinPut() {
      let that = this;
      let data= that.form;
      console.log(data);
      request.post("/ContactUs/PutContactFeedbackText",data).then(res => {
          if(res.status==200){
            ElMessage.success({
              message: '添加成功!',
              type: 'success',
            })
          }else {
            ElMessage.error('添加失败!')
          }
          that.isJoinOrContact=false;
      })
    },
    JoinWe() {
      let that = this;
      that.title = "加入我们";
      that.isJoinOrContact = false;
      that.dialogTableVisible = true;
    }
  },
  data() {
    return {
      textarea: '',
      dialogTableVisible: false,
      title: "联系我们",
      formLabelWidth: '160px',
      grids: {
        id: null,
        name: null,
        address: null,
        phone: null,
        charge: null
      },
      gridData: [],
      isJoinOrContact: true,
      form: {
        name: '',
        address: '',
        phone: '',
        charge: ''
      },
      rules: {
        name: [
          {
            required: true,
            message: '请输入加入名称',
            trigger: 'blur',
          },
          {
            min: 0,
            max: 20,
            message: '名称不能为空哦',
            trigger: 'blur',
          },
        ],
        address: [
          {
            required: true,
            message: '请输入参与地址',
            trigger: 'blur',
          },
          {
            min: 0,
            max: 20,
            message: '地址不可以为空哦',
            trigger: 'blur',
          },
        ],
        phone: [
          {
            required: true,
            message: '请输入手机号',
            trigger: 'blur',
          },
          {
            min: 0,
            max: 20,
            message: '手机号不能为空哦',
            trigger: 'blur',
          },
        ],
        charge: [
          {
            required: true,
            message: '请输入负责人',
            trigger: 'blur',
          },
          {
            min: 0,
            max: 20,
            message: '负责人不可以为空',
            trigger: 'blur',
          },
        ],
      },
      options: [
        {
          value: '0',
          label: '报名问题',
        },
        {
          value: '1',
          label: '投诉建议',
        },
        {
          value: '2',
          label: '交易问题',
        },
        {
          value: '3',
          label: '考试问题',
        },
        {
          value: '4',
          label: '练习问题',
        },
        {
          value: '5',
          label: '发现一个bug',
        },
      ],
      value: [],
    }
  }
}
</script>

<style scoped>
.el-header,
.el-footer {
  text-align: center;
  line-height: 60px;
}

.el-main {
  width: 100%;
  height: 60vh;
}

.el-menu-vertical-demo {
  border-right: none;
}

.el-menu {
  border-left: 2px solid #0b7def;
}

.el-select {
  width: 20%;
}
</style>
