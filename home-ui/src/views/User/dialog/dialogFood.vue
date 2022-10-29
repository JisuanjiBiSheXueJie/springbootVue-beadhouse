<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="uuid" label="编号" width="180"/>
    <el-table-column prop="name" label="食品" width="180"/>
    <el-table-column prop="warning" label="禁忌"/>
    <el-table-column prop="dose" label="口味"/>
    <el-table-column fixed="right" label="操作" width="130">
      <template #default="id">
        <el-button type="danger" plain size="small" @click="deleteById(id.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import httpRequest, {isSuccess} from "@/config/requesturl";

export default {
  name: "dialogFood",
  props: ['UserId'],
  data() {
    return {
      tableData: null,
      personId: this.UserId,
      foodOptions: [],
    }
  },
  created() {
    this.getPerFoodsById();
    this.getFoodOptions();
  },
  methods: {
    getPerFoodsById() {
      httpRequest.prototype.getPerFoodsById(this.personId).then(res => {
        if (isSuccess(res)) {
          this.tableData = res.data;
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
    }
  }
}
</script>

<style scoped>

</style>
