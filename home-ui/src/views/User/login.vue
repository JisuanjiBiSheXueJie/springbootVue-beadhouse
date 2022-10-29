<template>
  <div class="background">
    <img :src="imgSrc" width="100%" height="100%" alt=""/>
  </div>
  <div class="divmain">
    <div class="home">
      <div  class="btnBox">
        <img src="../../../public/static/img/logo.png" alt="" style="width: 80px;float: left;margin-top: -40px;margin-left: -40px ">
        <div style="color: rgb(106,172,239);font-size: 30px;font-weight:bold;padding-bottom: 30px">爱心之家养老院</div>
        <el-form ref="ruleFormRef" :model="form" size="normal" :rules="rules">
          <el-form-item prop="account" label="账号:&nbsp;&nbsp;&nbsp;" >
            <el-input v-model="form.account" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码:&nbsp;&nbsp;&nbsp;">
            <el-input v-model="form.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="验证码:" prop="salt">
            <div style="display: flex;margin-left: 6px">
              <el-input prefix-icon="el-icon-key" v-model="form.salt" style="width: 50%;"
                        placeholder="请输入验证码"></el-input>
              <img :src="verifyCode" style="width: 140px;height: 34px; margin-left: 10px;" @click="save">
            </div>
          </el-form-item>


          <el-form-item>
            <el-row style="margin-left: 30%">
              <el-radio v-model="radio1" label="1" size="large">护工</el-radio>
              <el-radio v-model="radio1" label="2" size="large">管理员</el-radio>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 100%;margin: 0 40px 0 40px" type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
        <div>
          <el-link type="primary" class="footerUrl">忘了密码？</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {ElMessage} from "element-plus"
import request from "@/utils/request";
import {Avatar, CaretBottom} from "@element-plus/icons-vue";
import refresh from "@element-plus/icons-vue/dist/es/refresh.mjs";
import axios from "axios";
import {getCookie, clearCookie, setCookie} from "@/utils/cookie";
import {removeToken, setToken} from "@/utils/auth";
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";

export default {
  name: "login",
  components: {
    refresh
  },
  data() {
    return {
      vedioCanPlay: false,
      fixStyle: '',
      form: {role: 1},
      rules: {
        account: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
          {
            min: 6,
            max: 16,
            message: '6-16位数字的用户名',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
          {
            min: 8,
            max: 16,
            message: '8-16位的密码',
            trigger: 'blur',
          },
        ],
        salt: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
          {
            min: 4,
            max: 4,
            message: '4位的验证码',
            trigger: 'blur',
          },
        ],
      },
      validCode: '',
      uuid: "",
      key: "",
      verifyCode: '',
      radio1: '',
      imgSrc: "static/img/TouchKeyboardThemeLight001.jpg"
    }
  },
  created() {
    this.save();
  },
  methods: {
    save() {
      removeToken();
      axios.post("/api/util/getCaptacha").then(res => {
        if (res.status === 200) {
          if (res.data.status === 200) {
            this.verifyCode = res.data.data.imageBase64;
            this.form.uuid = res.data.data.uuid;
          }
        }
      })
    },
    isNullReturn0(str) {
      if (str == null || str == "") {
        return -1;
      } else {
        return str;
      }
    },
    login() {
      let self = this;
      if (self.form.account == null || self.form.password == null || self.form.salt == null) {
        errorMessage("账号密码---不能为空哦")
        return;
      }
      if (self.form.uuid == null) {
        errorMessage("请重新刷新页面")
        return;
      }
      if (self.radio1 == 1) {
        httpRequest.prototype.login(this.form).then(res => {
          if (!isSuccess(res)) {
            errorMessage(res.message);
            return;
          }
          successMessage("登录成功")
          let token = res.data.token;
          setToken(token)
          this.$router.push('/');
        })
      } else if (self.radio1 == 2) {
        httpRequest.prototype.adminLogin(this.form).then(res => {
          if (!isSuccess(res)) {
            errorMessage(res.message);
            return;
          }
          successMessage("登录成功")
          let token = res.data.token;
          setToken(token)
          this.$router.push('/admin');
        })
      }
    }
  }
}
</script>
<style scoped>
.homepage-hero-module,
.video-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
}

.divmain {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background-size: 100%;
}

.video-container .poster img {
  z-index: 0;
  position: absolute;
}

.video-container .filter {
  z-index: 1;
  position: absolute;
  /*background: rgba(0, 0, 0, 0.4);*/
  width: 100%;
}

.footerUrl {
  margin-left: 160px;
}

.home {
  position: relative;
  left: 400px;
  width: 400px;
  margin: 100px;
  text-align: center;
}

.home:before {
  background: inherit;
  background-attachment: fixed;
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  filter: blur(5px);
}

.home:hover {

}

.background {
  width: 100%;
  height: 100%; /**宽高100%是为了图片铺满屏幕 */
  z-index: -1;
  position: absolute;
}

.divmain {
  background-image: url("../../../public/static/img/aa424c1549976a7abccf447bfd4c341.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.btnBox{
  padding: 80px;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  background: rgba(241, 164, 140, 0.2 );
  box-shadow: 3px 3px 6px 3px rgba(0, 0, 0, .3);
  overflow: hidden;
}
label{
  color: #3f6bff;
}
</style>
