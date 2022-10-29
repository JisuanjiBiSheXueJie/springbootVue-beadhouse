<template>
  <div v-for="(item,i) in data">
    <el-link type="primary" :title="item.title" @click="show(item.id)" v-if="item.status===1">
      {{ i + 1 }}、{{ item.title }}
    </el-link>
    <el-link type="info" :title="item.title" @click="show(item.id)" v-if="item.status===2">
      {{ i + 1 }}、{{ item.title }}
    </el-link>
    <span style="float: right;font-size: 6px">
      {{ item.createTime.split(" ")[0] }}
    </span>
  </div>
  <el-dialog v-model="dialogNews" :before-close="handleClose" title="查看邮件">
    <div ref='editor'>
        <span style="float: right;font-size: 6px">
       发布时间: {{ form.createTime }}
          </span>
      <h1 v-html="form.title"></h1>
      <el-card>
        <div v-html="form.body" style="min-height: 100px"></div>
      </el-card>
    </div>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";
import httpRequest, {isSuccess} from "@/config/requesturl";
import Wangeditor from 'wangeditor'

export default {
  name: "Journalist",

  ...Wangeditor,
  data() {
    return {
      data: null,
      form: {},
      dialogNews: false
    }
  },
  created() {
    this.initJournalism();
  },
  methods: {
    initJournalism() {
      httpRequest.prototype.getNews().then(res => {
        this.data = res.data;
      })
    },
    show(id) {
      httpRequest.prototype.getNewsById(id).then(res => {
        if (isSuccess(res)) {
          this.form = res.data;
          this.dialogNews = true;
        }
      })
    },
    handleClose() {
      this.initJournalism();
      this.dialogNews = false;
    }
  }
}
</script>

<style scoped>
ul li {
  list-style: none;
  text-align: left;
}
</style>
