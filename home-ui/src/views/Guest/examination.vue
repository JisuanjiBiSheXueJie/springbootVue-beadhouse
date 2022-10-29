<template>
  <el-container>
    <el-header><img :src=srcurl alt="" style="width: 100%;"></el-header>
    <el-main>
      <div>
        <el-row>
          <el-divider> <span style="background-color: rgba(255,255,255,0);font-size: 22px">环境展示</span> </el-divider>
        </el-row>
        <el-row>
        &nbsp;&nbsp;&nbsp;  爱心之家养老院是一家配套设施完善的民办大型养老院，占地面积27万平方米，专业为需护理、康复老年人提供医疗康复服务，致力于提供一个舒心，安心，舒适的居住环境和服务。养老院采用园林设计，以自然养生为基础，整个院区的绿化面积达97%，为长者提供生活照料，健身、保健养生、心理辅导、内务整理等多层次、高标准的服务，养老院荣获国资委老干部局授予的“生态养生候鸟基地”，国家民政部授予的“全国养老服务和社区服务信息惠民工程试点单位，”中国红十字会授予的"郑州养老服务品牌会员单位”等荣誉称号。
        </el-row>
        <div class="demo-image">
          <div v-for="url in fits" class="block" style="margin-left: 70px">
            <el-image style="width: 300px; height: 300px;float: left;" :src="url"/>
          </div>
        </div>
      </div>
    </el-main>
    <el-footer>
      <Footer/>
    </el-footer>
  </el-container>
</template>

<script>
import Footer from "@/components/Guest/Footer";

export default {
  name: "Examination",
  components: {Footer},
  data() {
    return {
      srcurl: "https://cmsimg01.71360.com/data/20200821/11u6144/public/2020082119132640390.jpg",
      fits:[
        "/static/hjzs/健身房.jpg",
        "/static/hjzs/养老院大堂.jpg",
        "/static/hjzs/居住环境1.jpg",
        "/static/hjzs/居住环境2.jpg",
        "/static/hjzs/居住环境4.jpg",
        "/static/hjzs/服务台.jpg",
        "/static/hjzs/院内环境1.jpg",
        "/static/hjzs/院内环境3.jpg",
      ]
    }
  },
  methods: {
    start() {
      window.location.href = 'http://mp.weixin.qq.com/s/2t5M-Xm2IxljBeuqb4LJWQ';
    }
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
</style>
