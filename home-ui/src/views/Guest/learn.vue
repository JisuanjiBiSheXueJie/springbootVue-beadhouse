<template>
  <el-container>
    <el-header><img :src=imgsrclc alt="" style="width: 100%"></el-header>
    <el-main>

      <el-row>
        <el-divider> <span style="background-color: rgba(255,255,255,0);font-size: 22px">服务内容</span> </el-divider>
      </el-row>
      <div class="demo-image">
        <div v-for="url in fits" class="block" style="margin-left: 70px">
          <el-image style="width: 300px; height: 300px;float: left;" :src="url"/>
        </div>
      </div>
      <!--      <n-card title="服务项目" style="margin-bottom: 16px">-->
      <!--        <n-tabs type="line" animated>-->
      <!--          <n-tab-pane name="oasis" tab="餐饮列表">-->
      <!--            <el-table :data="dataForm" style="width: 100%">-->
      <!--              <el-table-column prop="uuid" label="编号" width="180" />-->
      <!--              <el-table-column prop="name" label="名称" width="180" />-->
      <!--              <el-table-column prop="dose" label="用量/备注" />-->
      <!--              <el-table-column prop="role" label="用途/其他" />-->
      <!--              <el-table-column prop="warning" label="注意事项" />-->
      <!--            </el-table>-->
      <!--          </n-tab-pane>-->
      <!--          <n-tab-pane name="the beatles" tab="用药列表">-->
      <!--            <el-table :data="dataForm2" style="width: 100%">-->
      <!--              <el-table-column prop="uuid" label="编号" width="180" />-->
      <!--              <el-table-column prop="name" label="名称" width="180" />-->
      <!--              <el-table-column prop="dose" label="用量/备注" />-->
      <!--              <el-table-column prop="role" label="用途/其他" />-->
      <!--              <el-table-column prop="warning" label="注意事项" />-->
      <!--            </el-table>-->
      <!--          </n-tab-pane>-->
      <!--          <n-tab-pane name="jay chou" tab="其他">-->
      <!--            七里香-->
      <!--          </n-tab-pane>-->
      <!--        </n-tabs>-->
      <!--      </n-card>-->
    </el-main>
    <el-footer>
      <Footer/>
    </el-footer>
  </el-container>
</template>

<script>
import Footer from "@/components/Guest/Footer";
import request from "@/utils/request";
import httpRequest from "@/config/requesturl";

export default {
  name: "learn",
  components: {Footer},
  data() {
    return {
      imgsrc: "http://www.zhengzhi.com.cn/uploads/180102/1-1P102095Q43T.jpg",
      imgsrclc: "https://cmsimg01.71360.com/data/20200821/11u6144/public/2020082119142956537.jpg",
      processShow: true,
      wangeditor: false,
      detail: {},
      context: true,
      dataForm: [],
      dataForm2: [],
      fits:[
          "/static/fwnr/健康检查.jpg",
          "/static/fwnr/安全管理.jpg",
          "/static/fwnr/心理支持.jpg",
          "/static/fwnr/文娱活动.jpg",
          "/static/fwnr/生活照料.jpg",
          "/static/fwnr/社工服务.jpg",
          "/static/fwnr/老年大学.jpg",
          "/static/fwnr/营养膳食.jpg",
      ]
    };
  },
  created() {
    // this.initView();
  },
  mounted() {
    this.initView();
  },
  methods: {
    initView() {
      httpRequest.prototype.getFoodList().then(res => {
        this.dataForm = res.data;
      });
      httpRequest.prototype.getInitViewDrug().then(res => {
        this.dataForm2 = res.data;
      });
    },
    //初始化页面
    getMess() {
      request.get("/learn/Mess").then(res => {
      })
    },
    process() {
      if (this.context) {
        this.wangeditor = true;
        this.processShow = false;
        this.context = false;
      } else {
        this.wangeditor = false;
        this.processShow = true;
        this.context = true;
      }
    }
  }
}
</script>

<style scoped>
.el-header {
  height: auto;
}

.process {
  width: 100px;
  background-color: rgba(132, 218, 192, 0.7);
  text-align: center;
  font-size: 20px;
  border-radius: 20px;
  margin: 10px auto;
}

.process:hover {
  cursor: pointer;
  background-color: rgba(62, 108, 62, 0.83);
}

.el-aside {
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 160px;
}

</style>
