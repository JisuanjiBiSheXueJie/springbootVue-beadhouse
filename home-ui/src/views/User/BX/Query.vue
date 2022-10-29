<template>
  <el-dialog
      v-model="infoDialog"
      width="30%"
      title="详细信息"
      :before-close="closeDialog"
  >
    <el-form ref="formRef" :model="detailedInfos">
      <el-form-item label="主题">
        <el-input v-model="detailedInfos.bxTitle" placeholder="Please input" :disabled="disabled"/>
      </el-form-item>
      <el-form-item label="内容">
        <el-input
            v-model="detailedInfos.bxBody"
            :rows="4"
            type="textarea"
            :disabled="disabled"
            placeholder="Please input"
        />
      </el-form-item>
      <el-form-item label="" style="padding-left: 40px;">
        <el-row style="padding-top: 20px;">
          <el-button type="primary" plain @click="disabled=false">修改</el-button>
          <el-button type="success" plain @click="save">保存</el-button>
        </el-row>
      </el-form-item>
    </el-form>
  </el-dialog>
  <div v-for="(item,i) in applylist">
    <el-link @click="detailedInfo(item.id)" type="primary" :title="item.bxBody">
      {{ i + 1 }}、{{ item.bxTitle }}
    </el-link>
    <el-steps :active="item.status" :finish-status="status" simple v-if="item.status!==4">
      <el-step title="申请"></el-step>
      <el-step title="审批"></el-step>
      <el-step title="上门维修"></el-step>
    </el-steps>
    <p v-if="item.status===4"><span>申请被驳回：</span> <span style="color:red;">{{item.status}}</span></p>
    <hr>
  </div>
</template>

<script>
import httpRequest, {isSuccess} from '@/config/requesturl.js'

export default {
  name: "Query",
  data() {
    return {
      messNum: 0,
      status: "success",
      applylist: [],
      detailedInfos: {},
      form: {},
      infoDialog: false,
      disabled: true
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      httpRequest.prototype.getBxapply().then(res => {
        if (isSuccess(res)) {
          this.applylist = res.data
        }
      })
    },
    save() {
      let self = this;
      let data = {
        "id": self.detailedInfos.id,
        "bxTitle": self.detailedInfos.bxTitle,
        "bxBody": self.detailedInfos.bxBody
      }
      httpRequest.prototype.updateBx(data).then(res => {
        if (isSuccess(res)) {
          self.closeDialog();
        }
      })
    },
    closeDialog() {
      let self = this;
      self.disabled = true;
      self.infoDialog = false;
    },
    detailedInfo(id) {
      let self = this;
      httpRequest.prototype.findDetailedById(id).then(res => {
        if (isSuccess(res)) {
          self.detailedInfos = res.data;
          self.infoDialog = true;
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
