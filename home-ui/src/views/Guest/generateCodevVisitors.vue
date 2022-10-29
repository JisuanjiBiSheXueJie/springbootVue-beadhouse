<template>
  <div>
    <h1 style="text-align: center">
      入院记录
    </h1>
    <n-form ref="formRef" :model="form" :rules="rules">
      <n-form-item path="name" label="姓名">
        <n-input v-model:value="form.name"/>
      </n-form-item>
      <n-form-item path="phone" label="手机号">
        <n-input v-model:value="form.phone"/>
      </n-form-item>
      <n-form-item path="email" label="邮箱">
        <n-input v-model:value="form.email"/>
      </n-form-item>
      <n-form-item path="startTime" label="访问时间">
        <n-date-picker v-model:value="form.startTime" type="datetime" />
      </n-form-item>
      <n-form-item path="endTime" label="结束时间">
        <n-date-picker v-model:value="form.endTime" type="datetime" />
      </n-form-item>
      <n-form-item  >
        <button @click="submitForm()"> 提交
        </button>
      </n-form-item>
    </n-form>
<!--    <el-form :model="form" :rules="rules" ref="form" label-width="2 00px">-->
<!--      <el-form-item label="姓名" prop="name">-->
<!--        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="手机号" prop="phone">-->
<!--        <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="邮箱" prop="email">-->
<!--        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="访问时间" prop="time">-->
<!--        <el-date-picker v-model="form.time" type="date" placeholder="选择日期时间"></el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束时间" prop="endtime">-->
<!--        <el-date-picker v-model="form.endtime" type="date" placeholder="选择日期时间"></el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="submitForm()" style="text-align: center">提交</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
  </div>
</template>

<script>
import httpRequest, {isSuccess, successMessage} from "@/config/requesturl";

export default {
  name: "generateCodevVisitors",
  data() {
    return {
      form: {
        name: '',
        phone: '',
        email: '',
        startTime: null,
        endTime: null
      },
    }
  },
  methods: {
    submitForm() {
      httpRequest.prototype.saveVisitor(this.form).then(res => {
        if (isSuccess(res)) {
          successMessage("申请成功，请等待审核");
        }
      })
    }
  },
}
</script>

<style  >
/* From uiverse.io by @hannahyockel */
button {
  margin: 0 auto;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 15px 38px;
  border-radius: 16px;
  border: 1px solid transparent;
  color: #FFFFFF;
  background-color: #1DC9A0;
  font-size: 16px;
  letter-spacing: 1px;
  transition: all 0.15s linear;
}

button:hover {
  background-color: rgba(29, 201, 160, 0.08);
  border-color: #1DC9A0;
  color: #1DC9A0;
  transform: translateY(-5px) scale(1.05);
}

button:active {
  background-color: transparent;
  border-color: #1DC9A0;
  color: #1DC9A0;
  transform: translateY(5px) scale(0.95);
}

button:disabled {
  background-color: rgba(255, 255, 255, 0.16);
  color: #8E8E93;
  border-color: #8E8E93;
}
</style>
