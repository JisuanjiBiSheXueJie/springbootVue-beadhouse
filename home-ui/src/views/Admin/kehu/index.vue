<template>

  <div class="main">
    <h1 style="float: left;color:#e9eef3">客户管理</h1>
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
          <el-button type="primary" size="small" @click="getPersonList">
            <el-icon size="small">
              <search/>
            </el-icon>
          </el-button>
        </el-row>
      </el-popover>
      <el-button type="primary" circle size="default" @click="addUser">
        <el-icon size="default">
          <plus/>
        </el-icon>
      </el-button>
    </el-row>
    <n-divider/>
    <el-table v-loading="loading" :data="tableData" style="width: 100%"
              :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
              :row-style="{ background: 'rgba(14,25,42,0.2)' }"
    >
      <el-table-column fixed type="index" width="40"/>
      <el-table-column prop="name" label="名字" width="60"/>
      <el-table-column prop="uuid" label="身份证号码" width="150"/>
      <el-table-column prop="phone" label="联系方式" width="100"/>
      <el-table-column prop="familyPhone1" label="紧急联系人一" width="100"/>
      <el-table-column prop="familyPhone2" label="紧急联系人二" width="100"/>
      <el-table-column prop="address" label="通讯地址" width="240"/>
      <el-table-column prop="createUser" label="创建者" width="80"/>
      <el-table-column prop="createTime" label="创建时间" width="100"/>
      <el-table-column prop="userName" label="护工" width="80"/>
      <el-table-column fixed="right" label="管理" width="200">
        <template #default="id">
          <el-button type="primary" size="small" @click="handleClick(id.row.id)">
            更多
          </el-button>
          <el-popconfirm
              confirm-button-text="Yes"
              cancel-button-text="No"
              icon-color="red"
              title="你确认要删除该客户吗?"
              @confirm="setUserNotUse(id.row.id)"
          >
            <template #reference>
              <el-button type="danger" size="small">注销</el-button>
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
        v-model="dialogAddUser"
        title="添加用户"
        width="30%"
    >
      <el-form
          label-width="100px"
          :model="form"
          style="max-width: 460px"
      >
        <el-form-item label="护工：">
          <el-select v-model="form.userId" class="m-2" placeholder="选择人员" size="large" filterable>
            <el-option
                v-for="item in UserOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="客户姓名：">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="身份证件号：">
          <el-input v-model="form.uuid"/>
        </el-form-item>
        <el-form-item label="手机号：">
          <el-input v-model="form.phone"/>
        </el-form-item>
        <el-form-item label="家庭地址：">
          <el-input v-model="form.address"/>
        </el-form-item>
        <el-form-item label="紧急联系人1：">
          <el-input v-model="form.familyPhone1"/>
        </el-form-item>
        <el-form-item label="紧急联系人2：">
          <el-input v-model="form.familyPhone2"/>
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
        v-model="dialogModifyUser"
        title="修改客户"
        width="50%"
    >
      <el-form
          label-width="100px"
          :model="form1"
          style="max-width: 460px"
      >
        <el-form-item label="护工：">
          <el-select v-model="form1.userId" class="m-2" placeholder="选择人员" size="large" filterable>
            <el-option
                v-for="item in UserOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="客户姓名：">
          <el-input v-model="form1.name"/>
        </el-form-item>
        <el-form-item label="id：" style="display: none">
          <el-input v-model="form1.id"/>
        </el-form-item>
        <el-form-item label="身份证件号：">
          <el-input v-model="form1.uuid"/>
        </el-form-item>
        <el-form-item label="手机号：">
          <el-input v-model="form1.phone"/>
        </el-form-item>
        <el-form-item label="家庭地址：">
          <el-input v-model="form1.address"/>
        </el-form-item>
        <el-form-item label="紧急联系人1：">
          <el-input v-model="form1.familyPhone1"/>
        </el-form-item>
        <el-form-item label="紧急联系人2：">
          <el-input v-model="form1.familyPhone2"/>
        </el-form-item>
        <el-form-item label="注意事项：">
          <el-input v-model="form1.warning"/>
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
      <div class="demo-collapse">
        <el-collapse>
          <el-collapse-item title="食品详细" name="1">
            <el-table
                :data="Person.foods"
                style="width: 100%"
                :row-class-name="tableRowClassName"
                :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
                :row-style="{ background: 'rgba(14,25,42,0.2)' }"
            >
              <el-table-column fixed type="index" width="40"/>
              <el-table-column prop="dose" label="备注" width="180"/>
              <el-table-column prop="foodName" label="名称" width="180"/>
              <el-table-column prop="fRole" label="剂量"/>
              <el-table-column prop="fWaring" label="注意"/>
            </el-table>
          </el-collapse-item>
        </el-collapse>
        <el-collapse>
          <el-collapse-item title="使用药品" name="2">
            <el-table
                :data="Person.drugs"
                style="width: 100%"
                :row-class-name="tableRowClassName"
            >
              <el-table-column fixed type="index" width="40"/>
              <el-table-column prop="dDose" label="备注" width="180"/>
              <el-table-column prop="dRole" label="名称" width="180"/>
              <el-table-column prop="dName" label="剂量"/>
              <el-table-column prop="dWarning" label="注意"/>
            </el-table>
          </el-collapse-item>
        </el-collapse>
      </div>
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
  name: "index",
  data() {
    return {
      tableData: null,
      tablePerson: null,
      dialogAddUser: false,
      loading: ref(true),
      form: {},
      form1: {},
      password: '',
      rePassword: '',
      page: {
        current: null,
        size: null,
        total: null,
        pages: null,
        like: null
      },
      dialogModifyUser: false,
      dialogShowPerson: false,
      Person: {},
      UserOptions: [],
      nowUserId: null,
      nowId: null,
    }
  },
  created() {
    this.initView();
    this.getPersonList();
  },
  components: {
    Edit,
    Plus,
    search
  },
  methods: {
    initView() {
      httpRequest.prototype.getAllUser().then(res => {
        if (isSuccess(res)) {
          let data = res.data;
          let options = [];
          data.forEach(item => {
            options.push({
              label: item.name,
              value: item.id
            })
          });
          this.UserOptions = options;
        }
      });
    },
    getPersonList() {
      let self = this;
      httpRequest.prototype.getPersonPage(self.page).then(res => {
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
      httpRequest.prototype.getPersonMessById(id).then(res => {
        if (isSuccess(res)) {
          this.Person = res.data;
        }
      })
    },
    setUserNotUse(id) {
      httpRequest.prototype.delPersonById(id).then(res => {
        if (isSuccess(res)) {
          successMessage("操作成功");
        } else {
          errorMessage(res);
        }
      })
    },
    addUser() {
      let self = this;
      self.dialogAddUser = true;
    },
    onSubmit() {
      let self = this;
      httpRequest.prototype.addPerson(self.form).then(res => {
        if (isSuccess(res)) {
          successMessage("添加成功");
          self.dialogAddUser = false;
          this.getPersonList();
          this.form = {}; //清空表单
        }
      })
    },
    Modify(id) {
      let self = this;
      self.form1.userId = null;
      self.nowId = id;
      self.initView();
      httpRequest.prototype.getPersonById(id).then(res => {
        if (isSuccess(res)) {
          self.UserOptions.forEach(item => {
            if (item.value === res.data.userId) {
              self.form.userId = item.value;
              self.nowUserId = item.value;
            }
          });
          self.form1 = res.data;
          self.dialogModifyUser = true;
        }
      })
    },
    modifyUpdate() {
      let self = this;
      self.form1.userId === null ? self.form1.userId = self.nowUserId : null;
      //如果没有选择用户，则使用当前用户
      httpRequest.prototype.addPerson(self.form1).then(res => {
        if (isSuccess(res)) {
          successMessage("修改成功");
          self.dialogModifyUser = false;
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

.search {
  width: 120px;
  margin-right: 10px;
}

.demo-pagination-block {
  float: right;
  margin-top: 4vh;
  margin-right: 10vh;
}

.el-table {
  background-color: rgba(255, 255, 255, 0.52);
}

.el-table__body-wrapper tr td.el-table-fixed-column--left, .el-table__body-wrapper tr td.el-table-fixed-column--right, .el-table__body-wrapper tr th.el-table-fixed-column--left, .el-table__body-wrapper tr th.el-table-fixed-column--right, .el-table__footer-wrapper tr td.el-table-fixed-column--left, .el-table__footer-wrapper tr td.el-table-fixed-column--right, .el-table__footer-wrapper tr th.el-table-fixed-column--left, .el-table__footer-wrapper tr th.el-table-fixed-column--right, .el-table__header-wrapper tr td.el-table-fixed-column--left, .el-table__header-wrapper tr td.el-table-fixed-column--right, .el-table__header-wrapper tr th.el-table-fixed-column--left, .el-table__header-wrapper tr th.el-table-fixed-column--right {
  background-color: rgba(255, 255, 255, 0);

}

.el-table tr, .el-table__row {
  background-color: rgba(255, 255, 255, 0);
}

.main {
  width: 100%;
  height: 87vh;
}
</style>
