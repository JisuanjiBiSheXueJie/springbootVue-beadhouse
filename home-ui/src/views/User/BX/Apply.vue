<template>
  <div style="width: 600px;">
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-form-item label="报修主题：">
        <el-input v-model="form.bxTitle"></el-input>
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
      <el-form-item label="说明：">
        <el-input
            v-model="form.bxBody"
            maxlength="30"
            placeholder="说明故障详细原因"
            show-word-limit
            type="textarea"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import arrowDown from "@element-plus/icons-vue/dist/es/arrow-down.mjs";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import httpRequest from "@/config/requesturl";

export default {
  name: "Apply",

  data() {
    return {
      form: {},
      ssName: null,
      ssHouseNumber: null,
      ssFloor: null,
      list: [],
      floorNum: 0,
      housesList: []
    }
  },
  components: {
    arrowDown
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      let self = this;
      httpRequest.prototype.getSSListInfo().then(res => {
        if (res.status === 200) {
          self.list = res.data;
        }
      })
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
      if (this.ssHouseNumber == null) {
        ElMessage.error("请选择宿舍门牌号");
        return;
      }
      if (this.form.bxTitle == null) {
        ElMessage.error("请输入报修主题");
        return;
      }
      if (this.form.bxBody == null) {
        ElMessage.error("请输入说明");
        return;
      }
      self.form.bxAddresses = self.ssName + "&" + self.ssFloor + "&" + self.ssHouseNumber;
      httpRequest.prototype.bxInsert(this.form).then(res => {
        if (res.status === 200) {
          ElMessage.success("申报成功,请耐心等待")
          this.$router.push('/');
        }else {
          ElMessage.error(res.message);
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
</style>
