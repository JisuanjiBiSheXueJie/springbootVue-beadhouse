<template>
  <el-table :data="tableData" style="width: 100%" max-height="250" class="table">
    <el-table-column fixed prop="name" label="姓名" width="80"/>
    <el-table-column prop="uuid" label="身份证号码" width="170"/>
    <el-table-column prop="address" label="家庭住址" width="200"/>
    <el-table-column prop="phone" label="联系方式" width="100"/>
    <el-table-column prop="warning" label="注意事项" width="200"/>
    <el-table-column prop="familyPhone1" label="紧急联系人一" width="120"/>
    <el-table-column prop="familyPhone2" label="紧急联系人二" width="120"/>
    <el-table-column prop="createTime" label="入住时间" width="120"/>
    <el-table-column fixed="right" label="操作" width="160">
      <template #default="id">
        <el-button type="text" size="small" @click.prevent="showRow(id.row.id,id.row.name)"
        >药物
        </el-button>
        <el-button type="text" size="small" @click.prevent="showHp(id.row.id,id.row.name)">
          打卡
        </el-button>
        <el-button type="text" size="small" @click.prevent="showFood(id.row.id,id.row.name)">
          餐饮
        </el-button>
        <el-button type="text" size="small" @click.prevent="showView(id.row.id,id.row.name)">
          视图
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog
      v-model="dialogMore"
      :title="nowName+'的用药信息'"
      width="48%"
  >
    <el-row class="btn_list">
      <el-button type="primary" circle size="default" @click="addMoreDiaLog=true">
        <el-icon size="default">
          <plus/>
        </el-icon>
      </el-button>
    </el-row>
    <el-table :data="tableDrug" style="width: 100%" max-height="250" class="table">
      <el-table-column fixed prop="uuid" label="编号" width="80"/>
      <el-table-column prop="name" label="名称" width="80"/>
      <el-table-column prop="dose" label="剂量" width="80"/>
      <el-table-column prop="warning" label="注意事项" width="300"/>
      <el-table-column fixed="right" label="操作" width="80">
        <template #default="id">
          <el-button type="danger" plain size="small" @click="deleteById(id.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
  <el-dialog
      v-model="addMoreDiaLog"
      title="添加药物"
      width="30%"
  >
    <el-form
        label-position="left"
        label-width="100px"
        :model="MoreForm"
        style="max-width: 460px"
    >
      <el-form-item label="药品名称">
        <el-select v-model="MoreForm.drugId" filterable placeholder="请选择">
          <el-option
              v-for="item in Moreoptions"
              :key="item.uuid"
              :label="item.name"
              :value="item.uuid"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="剂量描述">
        <el-input
            v-model="MoreForm.dose"
            :rows="2"
            type="textarea"
            placeholder="请输入"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addMore">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <el-dialog
      v-model="dialogHealth"
      :title="nowName+'的健康打卡'"
      width="42%"
  >
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
        <el-button type="primary" @click="onMdfiy" v-if="isUpdate">修改</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <el-dialog
      v-model="dialogFood"
      :title="nowName+'的餐饮管理'"
      width="51%"
  >
    <el-row class="btn_list">
      <el-button type="primary" circle size="default" @click="addFoodDiaLog=true">
        <el-icon size="default">
          <plus/>
        </el-icon>
      </el-button>
    </el-row>
    <el-table :data="foodTableData" style="width: 100%">
      <el-table-column prop="uuid" label="编号" width="180"/>
      <el-table-column prop="name" label="食品" width="180"/>
      <el-table-column prop="warning" label="禁忌"/>
      <el-table-column prop="dose" label="口味"/>
      <el-table-column fixed="right" label="操作" width="130">
        <template #default="id">
          <el-button type="danger" plain size="small" @click="deleteFoodById(id.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
  <el-dialog
      v-model="addFoodDiaLog"
      title="添加餐饮"
      width="30%"
  >
    <el-form
        label-position="left"
        label-width="100px"
        :model="FoodForm"
        style="max-width: 460px"
    >
      <el-form-item label="食品名称">
        <el-select v-model="FoodForm.foodsId" filterable placeholder="请选择">
          <el-option
              v-for="item in foodOptions"
              :key="item.uuid"
              :label="item.name"
              :value="item.uuid"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="食品说明">
        <el-input
            v-model="FoodForm.dose"
            :rows="2"
            type="textarea"
            placeholder="请输入"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addFood">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <el-dialog
      v-model="dialogView"
      :title="nowName+'的健康视图'"
      :before-close="handleClose"
      width="50%"
  >
    <Visual :UserId="nowid" :key="timer"/>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";
import HealthPunch from "@/views/User/User/HealthPunch";
import DialogFood from "@/views/User/dialog/dialogFood";
import * as echarts from "echarts";
import Visual from "@/views/User/visual";
import dayjs from "dayjs";
import {Edit, Plus} from "@element-plus/icons-vue";
import search from "@element-plus/icons-vue/dist/es/search.mjs";

export default {
  name: "personnelList",
  data() {
    return {
      tableData: null,
      tableDrug: null,
      dialogMore: false,
      nowName: "",
      MoreForm: {},
      FoodForm: {},
      dialogHealth: false,
      nowid: null,
      dialogFood: false,
      addFoodDiaLog: false,
      dialogView: false,
      addMoreDiaLog: false,
      Moreoptions: [],
      timer: null,
      form: {
        "id": null
      },
      nowDate: new Date().toLocaleString(),
      isLeave: false,
      isUpdate: false,
      foodTableData: [],
      foodOptions: [],
    }
  },
  components: {
    Visual,
    DialogFood,
    Plus,
    HealthPunch
  },
  created() {
    this.getPersonList();
    this.getMoreoptions();
  },
  methods: {
    getPersonList() {
      httpRequest.prototype.getPersonListByUser().then(res => {
        if (isSuccess(res)) {
          this.tableData = res.data;
        } else {
          errorMessage(res);
        }
      })
    },
    getMoreoptions() {
      httpRequest.prototype.getDrugTypeList().then(res => {
        if (isSuccess(res)) {
          this.Moreoptions = res.data;
        } else {
          errorMessage(res);
        }
      })
    },
    showRow(id, name) {
      let self = this;
      self.getPersonnelDrug(id);
      self.nowid = id;
      self.nowName = name;
      self.dialogMore = true;
    },
    getPersonnelDrug(id) {
      let self = this;
      httpRequest.prototype.getPersonnelDrug(id).then(res => {
        if (isSuccess(res)) {
          self.tableDrug = res.data;
        }
      })
    },
    onAddItem() {

    },
    addMore() {
      this.MoreForm.personnelId = this.nowid;
      httpRequest.prototype.updatePersonnelDrug(this.MoreForm).then(res => {
        if (isSuccess(res)) {
          successMessage("添加成功");
          this.dialogMore = false;
          this.addMoreDiaLog = false;
        }
      });
    },
    deleteById(id) {
      let self = this;
      let data = {
        "id": id,
        "status": 0
      }
      httpRequest.prototype.updatePersonnelDrug(data).then(res => {
        if (isSuccess(res)) {
          successMessage("删除成功！");
          this.getPersonList();
        }
      });
    },
    getPerFoodsById() {
      httpRequest.prototype.getPerFoodsById(this.nowid).then(res => {
        if (isSuccess(res)) {
          this.foodTableData = res.data;
        }
      })
    },
    getFoodOptions() {
      httpRequest.prototype.getFoodOptions().then(res => {
        if (isSuccess(res)) {
          console.log(res.data);
          this.foodOptions = res.data;
        }
      })
    },
    addFood() {
      this.FoodForm.personnelId = this.nowid;
      httpRequest.prototype.updatePersonnelFood(this.FoodForm).then(res => {
        if (isSuccess(res)) {
          successMessage("添加成功");
          this.dialogFood = false;
          this.addFoodDiaLog = false;
        }
      });
    },
    deleteFoodById(id) {
      debugger;
      let self = this;
      let data = {
        "id": id,
        "status": 0
      }
      httpRequest.prototype.updatePersonnelFood(data).then(res => {
        if (isSuccess(res)) {
          successMessage("删除成功！");
          self.getPerFoodsById();
        }
      });
    },
    showHp(id, name) {
      let self = this;
      self.nowid = id;
      self.nowName = name;
      self.dialogHealth = true;
      let startTime = dayjs(new Date()).startOf('day').format('YYYY-MM-DD HH:mm:ss');
      let endTime = dayjs(new Date()).endOf('day').format('YYYY-MM-DD HH:mm:ss');
      let data = {
        "startTime": startTime,
        "endTime": endTime,
        "id": id,
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
      if (self.form.temperature == null) {
        errorMessage("请输入体温");
        return;
      }
      if (self.form.pulse == null) {
        errorMessage("请输入心率");
        return;
      }
      if (self.form.breathingRate == null) {
        errorMessage("请输入呼吸频率");
        return;
      }
      if (self.form.minBloodPre == null || self.form.maxBloodPre == null) {
        errorMessage("请输入血压");
        return;
      }
      debugger;
      self.form.isLeave = self.isLeave ? 1 : 0;
      self.form.personId = self.nowid;
      httpRequest.prototype.healthpunch(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("打卡成功")
        } else {
          errorMessage(res.message);
        }
        self.dialogHealth = false;
      })
    },
    onMdfiy() {
      let self = this;
      if (self.form.temperature == null) {
        errorMessage("请输入体温");
        return;
      }
      if (self.form.pulse == null) {
        errorMessage("请输入心率");
        return;
      }
      if (self.form.breathingRate == null) {
        errorMessage("请输入呼吸频率");
        return;
      }
      if (self.form.minBloodPre == null || self.form.maxBloodPre == null) {
        errorMessage("请输入血压");
        return;
      }
      self.form.isLeave = self.isLeave ? 1 : 0;
      self.form.personId = self.nowid;
      httpRequest.prototype.updateHealthpunch(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("修改成功")
        } else {
          errorMessage(res.message);
        }
        self.dialogHealth = false;
      })
    },
    showFood(id, name) {
      let self = this;
      self.nowid = id;
      self.nowName = name;
      self.dialogFood = true;
      self.getPerFoodsById();
      self.getFoodOptions();
    },
    showView(id, name) {
      let self = this;
      self.nowid = id;
      self.nowName = name;

      Visual.methods.initView();
      self.dialogView = true;
      self.timer = new Date().getTime();
    },
    handleClose() {
      Visual.methods.close();
      this.dialogView = false;
    }
  }
}
</script>

<style scoped>
.remaining {
  color: #606266;
  font-size: 14px;
  text-align: right;
  float: right;
  margin-right: 10vh;
}

.btn_list {
  float: right;
}
</style>
