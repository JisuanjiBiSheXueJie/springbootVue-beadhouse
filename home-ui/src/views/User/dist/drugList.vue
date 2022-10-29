<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="uuid" label="编号" width="180"/>
    <el-table-column prop="name" label="药物名称" width="180"/>
    <el-table-column prop="role" label="作用"/>
    <el-table-column prop="warning" label="警告"/>
  </el-table>
</template>

<script>
import httpRequest, {isSuccess} from "@/config/requesturl";

export default {
  name: "drugList",
  data() {
    return {
      tableData: null,
    }
  },
  created() {
    this.getDruglist();
  },
  methods: {
    getDruglist() {
        httpRequest.prototype.getInitViewDrug().then(res=>{
          if (isSuccess(res)) {
              this.tableData=res.data;
          }
        })
    }
  }
}
</script>

<style scoped>
</style>
