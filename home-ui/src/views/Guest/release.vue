<template>
  <el-container>
    <el-header>
      <img src="https://cmsimg01.71360.com/data/20200821/11u6144/public/2020082119115460427.jpg" alt=""
           style="display: block; width: 100%; height: 100%;">
    </el-header>
    <el-main>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="分院分步" name="first">分院分步</el-tab-pane>
        <el-tab-pane label="郑州总院" name="second"><span class="title">{{title}}</span> </el-tab-pane>
        <el-tab-pane label="洛阳分院" name="third"><span class="title">{{title}}</span></el-tab-pane>
        <el-tab-pane label="焦作分院" name="fourth"><span class="title">{{title}}</span></el-tab-pane>
        <el-tab-pane label="周口分院" name="fifth"><span class="title">{{title}}</span></el-tab-pane>
        <el-tab-pane label="漯河分院" name="sixth"><span class="title">{{title}}</span></el-tab-pane>
      </el-tabs>
      <iframe v-show="baidumap" src="/static/baidumap.html" width="100%" height="600" style="border: none"></iframe>
      <el-cardL v-show="wangeditor">
        <div v-html="detail.content" style="min-height: 100px"></div>
      </el-cardL>
    </el-main>
    <el-footer><Footer/></el-footer>
  </el-container>
</template>

<script>
import Footer from "@/components/Guest/Footer";
import httpRequest from "@/config/requesturl";
export default {
  name: "release",
  components: {Footer},
  data() {
    return {
      detail: {
        content: "<p>444444</p>"
      },
      baidumap:true,
      wangeditor:false,
      title:""
    }
  },
  created() {

  },
  methods: {
    handleClick(tab) {
      this.detail.content = "";
      this.title = "";
      let name = tab.props.name;
      if (name == "first") {
        this.baidumap = true;
        this.wangeditor = false;
      }
      if (name == "second") {
        this.baidumap = false;
        httpRequest.prototype.getIndexIntroduceByType("zz").then(res=>{
          this.detail.content = res.data.data.body;
          this.title = res.data.data.name;
          this.wangeditor = true;
        })
      }
      if (name == "third") {
        this.baidumap = false;
        httpRequest.prototype.getIndexIntroduceByType("ly").then(res=>{
          this.detail.content = res.data.data.body;
          this.title = res.data.data.name;
          this.wangeditor = true;
        })
      }
      if (name == "fourth") {
        this.baidumap = false;
        httpRequest.prototype.getIndexIntroduceByType("jz").then(res=>{
          this.detail.content = res.data.data.body;
          this.title = res.data.data.name;
          this.wangeditor = true;
        })
      }
      if (name == "fifth") {
        this.baidumap = false;
        httpRequest.prototype.getIndexIntroduceByType("zk").then(res=>{
          this.detail.content = res.data.data.body;
          this.title = res.data.data.name;
          this.wangeditor = true;
        })
      }
      if (name == "sixth") {
        this.baidumap = false;
        httpRequest.prototype.getIndexIntroduceByType("lh").then(res=>{
          this.detail.content = res.data.data.body;
          this.title = res.data.data.name;
          this.wangeditor = true;
        })
      }
      // console.log(event)
    },
  }
}
</script>

<style scoped>
.el-header,
.el-footer {
  color: var(--el-text-color-primary);
  text-align: center;
  padding: 0;
  height: auto;
}

.el-aside {
  background-color: #d3dce6;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.title{
  font-size: 20px;
  font-weight: bold;
  color: #0034f6;
}
</style>
