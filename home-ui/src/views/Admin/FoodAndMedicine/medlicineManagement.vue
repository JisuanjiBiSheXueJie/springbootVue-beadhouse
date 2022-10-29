<template>
  <div class="main">
    <h1 style="float: left;color:#e9eef3">药品管理</h1>
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
          <el-input v-model="page.like" placeholder="名称" class="search"/>
          <el-button type="primary" size="small" @click="getPersonList">
            <el-icon size="small">
              <search/>
            </el-icon>
          </el-button>
        </el-row>
      </el-popover>
      <el-button type="primary" circle size="default" @click="addMedlicine">
        <el-icon size="default">
          <plus/>
        </el-icon>
      </el-button>
    </el-row>
    <n-divider/>
    <el-table v-loading="loading" :data="tableData" style="width: 100%" :row-class-name="tableRowClassName"
              :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
              :row-style="{ background: 'rgba(14,25,42,0.2)' }"
    >
      <el-table-column fixed type="index" width="50"/>
      <el-table-column prop="uuid" label="药物编号" width="80"/>
      <el-table-column prop="name" label="名称" width="120"/>
      <el-table-column prop="dose" label="剂量说明" width="100"/>
      <el-table-column prop="role" label="作用" width="260"/>
      <el-table-column prop="warning" label="注意事项" width="240"/>
      <el-table-column prop="createUser" label="创建者" width="120"/>
      <el-table-column prop="createTime" label="创建时间" width="120"/>
      <el-table-column fixed="right" label="管理" width="200">
        <template #default="id">
<!--          <el-button type="primary" size="small" @click="handleClick(id.row.id)">-->
<!--            详细-->
<!--          </el-button>-->
          <el-popconfirm
              confirm-button-text="Yes"
              cancel-button-text="No"
              icon-color="red"
              title="你确认要删除该药品吗?"
              @confirm="setMedlicineNotUse(id.row.id)"
          >
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button type="info" size="small" @click="Modify(id.row.id)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="demo-pagination-block">
      <el-pagination
          v-model:currentPage="page.current"
          v-model:page-size="page.size"
          :page-sizes="[5, 10, 15, 20]"
          layout="sizes, prev, pager, next"
          :total="page.total"
          @size-change="getPersonList"
          @current-change="getPersonList"
      />
    </div>
    <el-dialog
        v-model="dialogAddMedlicine"
        title="添加药品"
        width="30%"
    >
      <el-form
          label-width="100px"
          :model="form"
          style="max-width: 460px"
      >
        <el-form-item label="名称：">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="编号：">
          <el-input v-model="form.uuid"/>
        </el-form-item>
        <el-form-item label="剂量说明：">
          <el-input v-model="form.dose"/>
        </el-form-item>
        <el-form-item label="作用：">
          <el-input v-model="form.role"/>
        </el-form-item>
        <el-form-item label="注意事项：">
          <el-input v-model="form.warning"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" style="margin-left: 10vh"> 提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
        v-model="dialogModifyMedlicine"
        title="修改药品"
        width="30%"
    >
      <el-form
          label-width="100px"
          :model="form"
          style="max-width: 460px"
      >
        <el-form-item label="名称：">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="编号：">
          <el-input v-model="form.uuid"/>
        </el-form-item>
        <el-form-item label="剂量说明：">
          <el-input v-model="form.dose"/>
        </el-form-item>
        <el-form-item label="作用：">
          <el-input v-model="form.role"/>
        </el-form-item>
        <el-form-item label="注意事项：">
          <el-input v-model="form.warning"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="modifyUpdate" style="margin-left: 10vh"> 修改</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
        v-model="dialogShowPerson"
        title="详细信息"
        width="50%"
    >
      <el-table :data="tablePerson" style="width: 100%;margin: 0 auto;"
                :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
                :row-style="{ background: 'rgba(14,25,42,0.2)' }"
      >
        <el-table-column fixed type="index" width="50"/>
        <el-table-column prop="name" label="名字" width="120"/>
        <el-table-column prop="uuid" label="身份证号码" width="180"/>
        <el-table-column prop="phone" label="联系方式" width="120"/>
        <el-table-column prop="address" label="通讯地址" width="220"/>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";
import {
  Edit, Plus,
} from '@element-plus/icons-vue'
import search from "@element-plus/icons-vue/dist/es/search.mjs";
import {ref} from "vue";

export default {
  name: "medlicineManagement",
  data() {
    return {
      tableData: null,
      loading: ref(true),
      tablePerson: null,
      searchInfo: '',
      dialogAddMedlicine: false,
      form: {},
      password: '',
      rePassword: '',
      page: {
        current: null,
        size: null,
        total: null,
        pages: null,
        like: null
      },
      dialogModifyMedlicine: false,
      dialogShowPerson: false
    }
  },
  created() {
    this.getPersonList();
  },
  components: {
    Edit,
    Plus,
    search
  },
  methods: {
    getPersonList() {
      let self = this;
      httpRequest.prototype.getInitViewDrugPage(self.page).then(res => {
        if (isSuccess(res)) {
          self.tableData = res.data.list;
          self.page.total = res.data.total;
          self.page.pages = res.data.pages;
          self.loading = ref(false);
        }
      })
    },
    handleClick(id) {
      let self = this;
      self.dialogShowPerson = true;
      httpRequest.prototype.getPersonnelMedlicine(id).then(res => {
        if (isSuccess(res)) {
          this.tablePerson = res.data;
        }
      })
    },
    setMedlicineNotUse(id) {
      let self = this;
      let data = {
        id: id,
        status: 0
      }
      httpRequest.prototype.updateMedlicine(data).then(res => {
        if (isSuccess(res)) {
          successMessage("操作成功");
          this.getPersonList();
        } else {
          errorMessage(res);
        }
      })
    },
    searchLikeNmae() {
      let self = this;
      httpRequest.prototype.getListByName(self.searchInfo).then(res => {
        if (isSuccess(res)) {
          self.tableData = res.data;
        } else {
          errorMessage(res)
        }
      })
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 == 0) {
        return 'statistics-warning-row';
      } else if (rowIndex % 2 === 1) {
        return 'statistics-susses-row';
      } else {
        return 'tableTitle';
      }
    },
    addMedlicine() {
      let self = this;
      self.dialogAddMedlicine = true;
    },
    onSubmit() {
      let self = this; // 获取当前vue实例
      httpRequest.prototype.addDrug(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("添加成功");
          self.dialogAddMedlicine = false;
          this.getPersonList();
          this.form = {};
          this.password = '';
          this.rePassword = '';
        }
      })
    },
    Modify(id) {
      let self = this;
      httpRequest.prototype.findMedlicineById(id).then(res => {
        if (isSuccess(res)) {
          this.form = res.data;
          self.dialogModifyMedlicine = true;
        }
      })
    },
    modifyUpdate() {
      let self = this;
      httpRequest.prototype.updateMedlicine(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("修改成功");
          self.dialogModifyMedlicine = false;
          this.getPersonList();
        }
      })
    }
  }
}
</script>

<style>
.btn_list {
  float: right;
  margin-right: 30vh;
}
.main{
  width: 100%;
  height: 87vh;
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
.statistics-warning-row{
  background-color: rgba(153, 250, 250, 0.2);
}
.statistics-susses-row{
  background-color: rgba(189, 236, 185, 0.2);
}
.el-table{
  background-color: rgba(255, 255, 255, 0.52);
}
.el-table__body-wrapper tr td.el-table-fixed-column--left, .el-table__body-wrapper tr td.el-table-fixed-column--right, .el-table__body-wrapper tr th.el-table-fixed-column--left, .el-table__body-wrapper tr th.el-table-fixed-column--right, .el-table__footer-wrapper tr td.el-table-fixed-column--left, .el-table__footer-wrapper tr td.el-table-fixed-column--right, .el-table__footer-wrapper tr th.el-table-fixed-column--left, .el-table__footer-wrapper tr th.el-table-fixed-column--right, .el-table__header-wrapper tr td.el-table-fixed-column--left, .el-table__header-wrapper tr td.el-table-fixed-column--right, .el-table__header-wrapper tr th.el-table-fixed-column--left, .el-table__header-wrapper tr th.el-table-fixed-column--right{
  background-color: rgba(255, 255, 255, 0);

}
</style>
