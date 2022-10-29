<template>
  <div style="width: 600px; ">
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-form-item label="标题：">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="起始/结束时间：">
        <el-col :span="11">
          <el-date-picker
              v-model="form.startTime"
              type="datetime"
              placeholder="选择日期"
              style="width: 100%"
          ></el-date-picker>
        </el-col>
        &nbsp;&nbsp;-&nbsp;&nbsp;
        <el-col :span="11">
          <el-date-picker
              v-model="form.endTime"
              type="datetime"
              placeholder="选择日期"
              style="width: 100%"
          ></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="身份">
        <el-select v-model="form.identity" placeholder="请选择身份" @change="form.identity==='护工'?isUser=true:isUser=false">
          <el-option label="护工" value="护工"></el-option>
          <el-option label="客户" value="客户"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否调休" v-if="isUser">
        <el-switch v-model="isCompensatory"/>
      </el-form-item>
      <el-form-item label="请假原因说明：" v-if="!isCompensatory">
        <el-input
            v-model="form.body"
            maxlength="30"
            placeholder="Please input"
            show-word-limit
            type="textarea"
        />
      </el-form-item>
      <el-form-item label="宿舍门牌号：">
        <el-select v-model="ssName" placeholder="宿舍楼" @change="cahngeSsName()">
          <el-option v-for="item in list " :label="item.ssName" :value="item.ssNameId"></el-option>
        </el-select>
        <el-select v-model="ssFloor" placeholder="楼层" @change="cahngeSsFloor()">
          <el-option v-for="item in floorNum" :label="item+'楼'" :value="item"></el-option>
        </el-select>
        <el-select v-model="ssHouseNumber" placeholder="门牌号">
          <el-option v-for="item in housesList " :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="margin: 0 auto">申请</el-button>
      </el-form-item>
    </el-form>
  </div>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed type="index" label=" " width="50"/>
      <el-table-column fixed type="title" label="标题" width="200"/>
      <el-table-column fixed prop="createTime" label="请假时间" width="150"/>
      <el-table-column prop="startTime" label="开始时间" width="100"/>
      <el-table-column prop="endTime" label="结束时间" width="100"/>
      <el-table-column prop="errorReason" label="驳回原因" width="120"/>
      <el-table-column prop="modifiedTime" label="审批时间" width="150"/>
      <el-table-column prop="status" label="状态" width="120"/>
      <el-table-column fixed="right" label="操作" width="140">
        <template #default="id">
          <el-button type="primary" plain size="small" @click="melt(id.row.id)" v-show="id.row.status===2">销假</el-button>
          <el-button type="danger" plain size="small" @click="deleteById(id.row.id)" v-show="id.row.status===4">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";

export default {
  name: "ApplyFor",
  data() {
    return {
      form: {},
      list: [],
      ssName: null,
      ssHouseNumber: null,
      ssFloor: null,
      floorNum: 0,
      isCompensatory: false,
      housesList: [],
      range: null,
      tableData: [],
      status: ["驳回", "待审核", "审核通过","销假审核中","已销假"],
      isUser: true
    }
  }, created() {
    this.init();
  },
  methods: {
    init() {
      let self = this;
      httpRequest.prototype.getSSListInfo().then(res => {
        if (res.status === 200) {
          self.list = res.data;
        }
      });
      httpRequest.prototype.getLeaveList().then(res => {
        if (res.status === 200) {
          let data = res.data;
          data.forEach(item => {
            item.status = self.status[item.status];
          })
          self.tableData = data;
        }
      });
    },
    melt(id) {
      let self = this;
      let data = {
        id: id,
        status: 3
      };
      httpRequest.prototype.setLeaveStatusById(data).then(res => {
        this.init();
      });
    },
    deleteById(id) {
      let self = this;
      let data = {
        id: id,
        status: -1
      };
      httpRequest.prototype.setLeaveStatusById(data).then(res => {
        this.init();
      });
    },
    FloorNum(id) {
      let self = this;
      let list = self.list;
      for (let i = 0; i < list.length; i++) {
        if (list[i].ssNameId == id) {
          let num = list[i].ssFloorNum;
          self.floorNum = num;
        }
      }
    },
    Houses(id) {
      let self = this;
      let list = self.list;
      let arrylist = [];
      for (let i = 0; i < list.length; i++) {
        arrylist = [];
        let houses = list[i].ssHouses;
        for (let j = 1; j <= houses; j++) {
          if (j < 10) {
            arrylist.push(id.toString() + "0" + j.toString())
          } else {
            arrylist.push(id.toString() + j.toString())
          }
        }
      }
      this.housesList = arrylist;
    },
    cahngeSsName() {
      let self = this;
      self.FloorNum(self.ssName);
    },
    cahngeSsFloor() {
      let self = this;
      this.Houses(self.ssFloor);
    },
    onSubmit() {
      let self = this;
      if (self.form.startTime === null) {
        errorMessage("请选择起始时间");
        return;
      }
      if (self.form.endTime === null) {
        errorMessage("请选择结束时间");
        return;
      }
      if (self.form.title === null) {
        errorMessage("请输入标题");
        return;
      }
      if (self.form.identity === null) {
        errorMessage("请选择身份");
        return;
      }
      if (self.form.body === null) {
        errorMessage("请输入请假原因说明");
        return;
      }
      self.form.addresses = self.ssName + "&" + self.ssFloor + "&" + self.ssHouseNumber;
      httpRequest.prototype.applyFor(this.form).then(res => {
        if (isSuccess(res)) {
          successMessage("申请成功")
        } else {
          errorMessage(res.message);
        }
      })
    }
  }
}
</script>

<style>
.el-select {
  width: 120px;
  margin-right: 10px;
}
</style>
