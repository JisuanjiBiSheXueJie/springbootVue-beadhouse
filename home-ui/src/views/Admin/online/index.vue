<template>
  <div class="main">
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
    <el-table
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
    >
      <el-table-column fixed type="index" label="顺序" width="100"/>
      <el-table-column fixed prop="createTime" label="登录时间" width="240"/>
      <el-table-column fixed prop="name" label="用户名" width="240"/>
      <el-table-column prop="title" label="用户账号" width="240"/>
      <el-table-column label="是否启用" width="200">
        <template #default="scope">
          <el-switch
              v-model="scope.row.status"
              class="ml-2"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @click="changeSwitch(scope.row.id,scope.row.status)"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="offline(scope.row.id)">下线
          </el-button>
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
          @size-change="initView"
          @current-change="initView"
      />
    </div>
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
  name: "userManagement",
  data() {
    return {
      page: {
        current: null,
        size: null,
        total: null,
        pages: null,
        like: null
      },
      tableData: [],
      loading: ref(true)
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
      this.loading = false;
    },
    offline(id) {

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

.main{
  width: 100%;
  height: 87vh;
}
.el-table{
  background-color: rgba(255, 255, 255, 0.52);
}
.el-table__body-wrapper tr td.el-table-fixed-column--left, .el-table__body-wrapper tr td.el-table-fixed-column--right, .el-table__body-wrapper tr th.el-table-fixed-column--left, .el-table__body-wrapper tr th.el-table-fixed-column--right, .el-table__footer-wrapper tr td.el-table-fixed-column--left, .el-table__footer-wrapper tr td.el-table-fixed-column--right, .el-table__footer-wrapper tr th.el-table-fixed-column--left, .el-table__footer-wrapper tr th.el-table-fixed-column--right, .el-table__header-wrapper tr td.el-table-fixed-column--left, .el-table__header-wrapper tr td.el-table-fixed-column--right, .el-table__header-wrapper tr th.el-table-fixed-column--left, .el-table__header-wrapper tr th.el-table-fixed-column--right{
  background-color: rgba(255, 255, 255, 0);

}
</style>
