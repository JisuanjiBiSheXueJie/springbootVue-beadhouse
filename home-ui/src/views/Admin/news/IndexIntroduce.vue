<template>
  <div class="main">
    <h1>分院管理</h1>
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
      <el-button type="primary" circle size="default" @click="$router.push('/admin/news/index?type=1')">
        <el-icon size="default">
          <plus/>
        </el-icon>
      </el-button>
    </el-row>
    <n-divider/>
    <el-table
        :data="tableData"
        style="width: 80%;margin: 0 auto;"
        v-loading="loading"
        :header-cell-style="{ background: 'rgba(14,25,42,0.2)', color: '#606266' }"
        :row-style="{ background: 'rgba(14,25,42,0.2)' }"
    >
      <el-table-column fixed type="index" label="顺序" width="100"/>
      <el-table-column fixed prop="createTime" label="时间" width="240"/>
      <el-table-column fixed prop="type" label="分类" width="120"/>
      <el-table-column prop="name" label="标题" width="235"/>
      <el-table-column label="是否启用" width="100">
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
      <el-table-column label="操作" width="240">
        <template #default="scope">
          <el-button size="small" @click="show(scope.row.id)"
          >查看
          </el-button
          >
          <el-button size="small" @click="handleEdit(scope.$index, scope.row.id)"
          >修改
          </el-button
          >
          <el-popconfirm
              confirm-button-text="是的"
              cancel-button-text="不，我点错了"
              icon-color="red"
              title="确认要删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <template #reference>
              <el-button
                  size="small"
                  type="danger"
              >删除
              </el-button
              >
            </template>
          </el-popconfirm>

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
    <el-dialog v-model="showDrawer" title="查看内容">
      <div ref='editor'>
        <h1 v-html="form.title"></h1>
        <el-card>
          <div v-html="form.body" style="min-height: 100px"></div>
        </el-card>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {Edit, Plus} from "@element-plus/icons-vue";
import search from "@element-plus/icons-vue/dist/es/search.mjs";
import httpRequest, {errorMessage, isSuccess, successMessage} from "@/config/requesturl";
import {ref} from "vue";
import Journalist from "@/views/User/Journalist";
import infoFilled from "@element-plus/icons-vue/dist/es/info-filled.mjs";

export default {
  name: "IndexIntroduce",
  components: {
    Edit,
    Plus,
    search,
    Journalist,
    infoFilled
  },
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
      scopea: null,
      form: {},
      showDrawer: ref(false),
      loading: ref(true),
      options: [
        {
          value: '0',
          label: '全部可见',
        },
        {
          value: '1',
          label: '仅内部可见',
        }
      ]
    }
  }
  ,
  created() {
    this.initView();
  }
  ,
  methods: {
    initView() {
      let self = this;
      httpRequest.prototype.getIndexIntroduce(self.page).then(res => {
        if (isSuccess(res)) {
          let data = res.data.list;
          let options = [
            {
              "typeId": 'zz',
              "typeName":'郑州分院'
            },{
              "typeId": 'ly',
              "typeName":'洛阳分院'
            },{
              "typeId": 'jz',
              "typeName":'焦作分院'
            },{
              "typeId": 'zk',
              "typeName":'周口分院'
            },{
              "typeId": 'lh',
              "typeName":'漯河分院'
            }
          ];
          for (let i = 0; i < data.length; i++) {
            data[i].status = data[i].status !== 0;
            data[i].type = options.find(item => item.typeId === data[i].type).typeName;
          }
          self.tableData = data;
          self.page.total = res.data.total;
          self.page.pages = res.data.pages;
          self.loading = false;
        }
      })
    }
    ,
    handleEdit(index, row) {
      this.$router.push('/admin/news/index?id=' + row+'&type=1');
    }
    ,
    handleDelete(row) {
      let data = {
        "id": row,
        "status": -1
      };
      httpRequest.prototype.changeIndexIntroduce(data).then(res => {
        if (isSuccess(res)) {
          successMessage("删除成功！");
          this.initView();
        } else {
          errorMessage(res.message);
        }
      })
    }
    ,
    changeSwitch(row, status) {
      let data = {
        "id": row,
        "status": status ? 1 : 0
      }
      httpRequest.prototype.changeIndexIntroduce(data).then(res => {
        if (isSuccess(res)) {
          return;
        } else {
          errorMessage(res.message);
        }
      })
    }
    ,
    show(id) {
      httpRequest.prototype.getIndexIntroduceById(id).then(res => {
        if (isSuccess(res)) {
          this.form = res.data;
          this.showDrawer = true;
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
