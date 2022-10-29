<template>
  <div class="main">
    <h1 style="float: left;color:#e9eef3">留言管理</h1>
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
      <el-button type="danger" circle size="default" @click="deleteById">
        <el-icon size="default">
          <delete/>
        </el-icon>
      </el-button>
    </el-row>
    <n-divider/>
    <el-table v-loading="loading" :data="tableData" style="width: 100%" :row-class-name="tableRowClassName"
              :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
              :row-style="{ background: 'rgba(14,25,42,0.2)' }"
              @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column fixed type="index" width="50"/>
      <el-table-column prop="name" label="名字" width="120"/>
      <el-table-column prop="phone" label="手机号" width="120"/>
      <el-table-column prop="mess" label="说明" width="400"/>
      <el-table-column prop="createTime" label="创建时间" width="180"/>
      <el-table-column fixed="right" label="管理" width="200">
        <template #default="id">
          <el-button type="success" plain size="small" @click="contact(id.row.id)" v-show="id.row.status!==2">已联系</el-button>
          <el-button type="primary" plain size="small" @click="registration(id.row.id,false)" v-show="id.row.status!==2">登记</el-button>
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
        v-model="dialogShowPerson"
        title="登记信息"
        width="30%"
    >
      <el-input
          v-model="mess"
          :rows="2"
          type="textarea"
          placeholder="请输入登记信息"
      />
        <el-button type="primary" @click="registration(nowId,true)">确定</el-button>
    </el-dialog>
  </div>
</template>

<script>
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";
import {
  Delete,
  Edit, Plus,
} from '@element-plus/icons-vue'
import search from "@element-plus/icons-vue/dist/es/search.mjs";
import {ref} from "vue";

export default {
  name: "MessageManagement",
  data() {
    return {
      tableData: null,
      loading: ref(true),
      tablePerson: null,
      searchInfo: '',
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
      dialogModifyUser: false,
      dialogShowPerson: false,
      idList: [],
      mess: '',
      nowId: null,
    }
  },
  created() {
    this.getPersonList();
  },
  components: {
    Delete,
    Edit,
    Plus,
    search
  },
  methods: {
    getPersonList() {
      let self = this;
      httpRequest.prototype.getMessageList(self.page).then(res => {
        if (isSuccess(res)) {
          self.tableData = res.data.list;
          self.page.total = res.data.total;
          self.page.pages = res.data.pages;
          self.loading = ref(false);
        }
      })
    },
    handleSelectionChange(data) {
      let idList = [];
      data.forEach(item => {
        idList.push(item.id);
      });
      this.idList = idList;
    },
    contact(id) {
      let self = this;
      let data = {
        id: id,
        mess:"已联系",
        status: 2
      }
      httpRequest.prototype.updateMessage(data).then(res => {
        if (isSuccess(res)) {
          successMessage('操作成功');
          self.getPersonList();
        }
      });
    },
    registration(id,isMess) {
      let self = this;
      if (!isMess) {
        self.nowId = id;
        self.dialogShowPerson = true;
        return;
      }
      let data = {
        id: id,
        mess: self.mess,
        status: 3
      }
      httpRequest.prototype.updateMessage(data).then(res => {
        if (isSuccess(res)) {
          successMessage('操作成功');
          self.getPersonList();
          self.dialogShowPerson = false;
        }
      });
    },
    deleteById() {
      let self = this;
      if (self.idList.length === 0) {
        return errorMessage('请选择要删除的数据');
      }
      self.idList.forEach(item => {
        let data = {
          id: item,
          status: 0
        }
        httpRequest.prototype.updateMessage(data);
      });
      self.getPersonList();
    }
  }
}
</script>

<style>
.btn_list {
  float: right;
  margin-right: 30vh;
}

.main {
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

.statistics-warning-row {
  background-color: rgba(153, 250, 250, 0.2);
}

.statistics-susses-row {
  background-color: rgba(189, 236, 185, 0.2);
}

.el-table {
  background-color: rgba(255, 255, 255, 0.52);
}

.el-table__body-wrapper tr td.el-table-fixed-column--left, .el-table__body-wrapper tr td.el-table-fixed-column--right, .el-table__body-wrapper tr th.el-table-fixed-column--left, .el-table__body-wrapper tr th.el-table-fixed-column--right, .el-table__footer-wrapper tr td.el-table-fixed-column--left, .el-table__footer-wrapper tr td.el-table-fixed-column--right, .el-table__footer-wrapper tr th.el-table-fixed-column--left, .el-table__footer-wrapper tr th.el-table-fixed-column--right, .el-table__header-wrapper tr td.el-table-fixed-column--left, .el-table__header-wrapper tr td.el-table-fixed-column--right, .el-table__header-wrapper tr th.el-table-fixed-column--left, .el-table__header-wrapper tr th.el-table-fixed-column--right {
  background-color: rgba(255, 255, 255, 0);

}
</style>
