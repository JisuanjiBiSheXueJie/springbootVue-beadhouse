<template>
  <div class="main">
    <h1 style="float: left;color:#e9eef3;color: #e9eef3">访客管理</h1>
    <div>
      <el-row class="btn_list">
        <el-popover placement="bottom" :width="200" trigger="click">
          <template #reference>
            <el-button circle size="default">
              <el-icon size="default">
                <search/>
              </el-icon>
            </el-button>
          </template>
          <el-row>
            <el-input v-model="page.like" placeholder="姓名" class="search"/>
            <el-button type="primary" size="small" @click="initView">
              <el-icon size="small">
                <search/>
              </el-icon>
            </el-button>
          </el-row>
        </el-popover>
        <button class="GenerateCode" @click="GenerateCode">生成二维码
        </button>
      </el-row>
      <el-dialog
          v-model="dialogVisible"
          width="30%"
          title="生成访客登记验证码"
      >
        <div class="block" style="margin-left: 80px">
          <el-input v-model="address" placeholder="二维码粘贴位置" class="search" style="width: 160px;height: 40px;"/>
          <button class="generate" @click="generate"> 生成
          </button>
          <el-image :src="src" v-if="src!=null"/>
        </div>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-link type="primary" :href="src" class="download">下载</el-link>
      </span>
        </template>
      </el-dialog>
      <n-divider/>
      <el-table :data="tableData" style="width: 96%;margin: 0 auto"
                :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
                :row-style="{ background: 'rgba(14,25,42,0.2)' }"
      >
        <el-table-column fixed type="index" label="序号" width="40"/>
        <el-table-column prop="name" label="姓名" width="60"/>
        <el-table-column prop="phone" label="手机号" width="120"/>
        <el-table-column prop="email" label="邮箱" width="200"/>
        <el-table-column prop="startTime" label="访问时间" width="120"/>
        <el-table-column prop="endTime" label="结束时间" width="400"/>
        <el-table-column prop="createTime" label="创建时间" width="400"/>
        <el-table-column fixed="right" label="管理" width="60">
          <template #default="id">
            <el-popconfirm
                confirm-button-text="是"
                cancel-button-text="否"
                icon-color="red"
                title="你确认要删除该记录吗?"
                @confirm="deleteById(id.row.id)"
            >
              <template #reference>
                <el-button type="danger" size="small">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <n-modal
            v-model:show="showModal"
            class="custom-card"
            preset="card"
            title="打回原因"
            size="huge"
            :bordered="false"
            style="width: 600px;"
        >
          <n-input
              placeholder="请写原因"
              type="textarea"
              size="small"
              :disabled="!isClickWhy"
              v-model:value="errorReason"
              :autosize="{
        minRows: 3,
        maxRows: 5
      }"
          />
          <n-button strong secondary type="success" style="margin-top: 20px" @click="rejected()" v-if="isClickWhy">
            确定
          </n-button>
        </n-modal>
      </div>
    </div>
  </div>
</template>

<script>
import httpRequest, {isSuccess, successMessage} from "@/config/requesturl";
import {Edit, Plus} from "@element-plus/icons-vue";
import search from "@element-plus/icons-vue/dist/es/search.mjs";

export default {
  name: "visitor",
  data() {
    return {
      tableData: null,
      form: {
        id: null,
        status: null,
        errorReason: null
      },
      errorReason: '',
      showModal: false,
      isClickWhy: true,
      nowId: null,
      dialogVisible: false,
      address: "",
      src: null,
      page: {
        current: null,
        size: null,
        total: null,
        pages: null,
        like: null
      },
      status: ["驳回", "待审核", "审核通过"]
    }
  },
  created() {
    this.initView();
  },

  components: {
    Edit,
    Plus,
    search
  },
  methods: {
    initView() {
      let self = this;
      httpRequest.prototype.getVisitor(self.page).then(res => {
        if (isSuccess(res)) {
          let data = res.data.list;
          for (let i = 0; i < data.length; i++) {
            let nstatus = data[i].status
            data[i].status = self.status[nstatus];
          }
          self.page.total = res.data.total;
          self.page.pages = res.data.pages;
          self.tableData = data;

        }
      })
    },
    handleClick() {

    },
    ToAllow(id) {
      let self = this;
      self.form.id = id;
      self.form.status = 2;
    },
    rejected(id) {
      if (id != null) {
        this.nowId = id;
        this.showModal = true;
        return;
      }
      let self = this;
      self.form.id = this.nowId;
      self.form.status = 0;
      self.form.errorReason = self.errorReason;
      httpRequest.prototype.setLeaveStatusById(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("操作成功");
          this.showModal = false;
        }
      })
    },
    getStatus(status, num) {
      if (status === this.status[num]) {
        return true;
      }
    },
    why(id) {
      let self = this;
      httpRequest.prototype.getLeaveErrorReasonById(id).then(res => {
        if (isSuccess(res)) {
          self.errorReason = res.data.errorReason;
          self.showModal = true;
          self.isClickWhy = false;
        }
      })
    },
    GenerateCode() {
      let self = this;
      self.dialogVisible = true;
    },

    generate() {
      let self = this;
      httpRequest.prototype.generateCode(this.address).then(res => {
        if (isSuccess(res)) {
          successMessage("生成成功");
          self.src = httpRequest.prototype.getImageFileUrl() + res.data.code;
        }
      })
    },
    deleteById(id) {
      let self = this;
      let data = {
        id: id,
        status: 0
      };
      httpRequest.prototype.updateVisitor(data).then(res => {
        if (isSuccess(res)) {
          successMessage("删除成功");
          self.initView();
        }
      });
    }
  }
}
</script>

<style>
.btn_list {
  float: right;
  margin-right: 30vh;
}

.search {
  width: 120px;
  margin-right: 10px;
}

.demo-pagination-block {
  float: right;
  margin-top: 4vh;
  margin-right: 10vh;
}

.main {
  width: 100%;
  height: 87vh;
}

.el-table {
  background-color: rgba(255, 255, 255, 0.52);
}

.el-table__body-wrapper tr td.el-table-fixed-column--left, .el-table__body-wrapper tr td.el-table-fixed-column--right, .el-table__body-wrapper tr th.el-table-fixed-column--left, .el-table__body-wrapper tr th.el-table-fixed-column--right, .el-table__footer-wrapper tr td.el-table-fixed-column--left, .el-table__footer-wrapper tr td.el-table-fixed-column--right, .el-table__footer-wrapper tr th.el-table-fixed-column--left, .el-table__footer-wrapper tr th.el-table-fixed-column--right, .el-table__header-wrapper tr td.el-table-fixed-column--left, .el-table__header-wrapper tr td.el-table-fixed-column--right, .el-table__header-wrapper tr th.el-table-fixed-column--left, .el-table__header-wrapper tr th.el-table-fixed-column--right {
  background-color: rgba(255, 255, 255, 0);

}

.GenerateCode {
  text-decoration: none;
  position: absolute;
  border: none;
  font-size: 14px;
  font-family: inherit;
  color: #fff;
  width: 9em;
  height: 3em;
  line-height: 2em;
  text-align: center;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 300%;
  border-radius: 30px;
  z-index: 1;
  margin-left: 60px;
}

.GenerateCode:hover {
  animation: ani 8s linear infinite;
  border: none;
}

@keyframes ani {
  0% {
    background-position: 0%;
  }

  100% {
    background-position: 400%;
  }
}

.GenerateCode:before {
  content: '';
  position: absolute;
  top: -5px;
  left: -5px;
  right: -5px;
  bottom: -5px;
  z-index: -1;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  border-radius: 35px;
  transition: 1s;
}

.GenerateCode:hover::before {
  filter: blur(20px);
}

.GenerateCode:active {
  background: linear-gradient(32deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
}

.generate {
  background: linear-gradient(-45deg, #3f00b5, #9f69fe, #27c8b7, #3f00b5);
  background-size: 800% 400%;
  padding: 10px 20px;
  display: inline-block;
  border: none;
  border-radius: 10px;
  font-size: 17px;
  font-weight: 700;
  color: white;
  transition: all .5s ease-in-out;
  animation: gradient 10s infinite cubic-bezier(.62, .28, .23, .99) both;
}

.generate:hover {
  animation: gradient 3s infinite;
  transform: scale(1.05);
}

.generate:active {
  animation: gradient 3s infinite;
  transform: scale(0.8);
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
}

.download {
  width: 58px;
  height: 24px;
  background-color: #4e5ade;
  border-radius: 4px;
  color: #e9eef3;
}
</style>
