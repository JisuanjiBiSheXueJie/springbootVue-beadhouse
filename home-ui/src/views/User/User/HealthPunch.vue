<template>
  <div style="width: 600px;">
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-form-item label="打卡时间：">
        <el-input v-model="form.createTime" disabled placeholder="请输入时间"/>
      </el-form-item>
      <el-form-item label="今日体温：">
        <el-input v-model="form.temperature" placeholder="摄氏度体温"/>
      </el-form-item>
      <el-form-item label="今日心率：">
        <el-input v-model="form.pulse" placeholder="心率数字"/>
      </el-form-item>
      <el-form-item label="呼吸频率：">
        <el-input v-model="form.breathingRate" placeholder="呼吸频率"/>
      </el-form-item>
      <el-form-item label="今日血压；">
        <el-col :span="8">
          <el-input v-model="form.minBloodPre" class="w-50 m-2" placeholder="低压"/>
        </el-col>
        <el-col class="text-center" :span="1" style="margin: 0 0.5rem">-></el-col>
        <el-col :span="8">
          <el-input v-model="form.maxBloodPre" class="w-50 m-2" placeholder="高压"/>
        </el-col>
      </el-form-item>
      <el-form-item label="是否需要上门检查：">
        <el-select v-model="form.adverse" placeholder="请选择">
          <el-option label="是" value="1"></el-option>
          <el-option label="否" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="最近其它状况">
        <el-switch v-model="isLeave"/>
      </el-form-item>
      <el-form-item label="详细状况说明" v-if="isLeave">
        <el-input
            v-model="form.instructions"
            :rows="4"
            type="textarea"
            placeholder="Please input"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" v-if="!isUpdate">提交</el-button>
        <el-button type="primary" @click="onSubmit" v-if="isUpdate">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";
import {StarFilled} from "@element-plus/icons-vue";
import dayjs from "dayjs";

export default {
  name: "HealthPunch",
  props: ['UserId'],
  data() {
    return {
      form: {},
      nowDate: new Date().toLocaleString(),
      isLeave: false,
      personId: this.UserId,
      isUpdate: false
    }
  },
  mounted() {
    StarFilled
  },
  methods: {
    initView(id) {
      this.personId = id;
      let self = this;
      let startTime = dayjs(new Date()).startOf('day').format('YYYY-MM-DD HH:mm:ss');
      let endTime = dayjs(new Date()).endOf('day').format('YYYY-MM-DD HH:mm:ss');
      let data = {
        "startTime": startTime,
        "endTime": endTime,
        "id": self.personId,
        "isUpdate": true
      }
      httpRequest.prototype.getHealthpunchById(data).then(res => {
        if (isSuccess(res)) {
          if (res.data.length === 0) {
            self.form = {};
            self.isUpdate = false;
            return;
          }
          self.form = res.data[0];
          self.isUpdate = true;
        } else {
          self.form = {};
          self.isUpdate = false;
        }
      });
    },
    onSubmit() {
      let self = this;
      self.form.isLeave = self.isLeave ? 1 : 0;
      self.form.personId = self.personId;
      httpRequest.prototype.healthpunch(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("打卡成功")
        } else {
          errorMessage(res.message);
        }
      })
    }
  }
}
</script>

<style scoped>
.el-select {
  width: 120px;
  margin-right: 10px;
}

.input {
  width: 60%;
}

.form-item {
  float: right;
}
</style>
