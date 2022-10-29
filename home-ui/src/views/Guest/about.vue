<template>
  <el-container>
    <el-header><img :src=imgsrclc alt="" style="width: 100%"></el-header>
    <br><br><br><br><br><br>
    <el-main>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="关于我们" name="first">
          <p>
            &nbsp;&nbsp;&nbsp; 爱心之家养老院实行医养结合，为需护理、康复老年人提供医疗康复服务。全院总占地面积27万平方米，设有生活护理区、理疗康复区、老年人活动中心、生态种植园区、休闲娱乐区、护理员培训区，以及正在建设的老年大学区。
            公司以健康养老产业为重点投资方向，市场立足郑州金水区，着力打造“养老+护理”的医养结合模式，给长者提供精准服务。并由“中华孝亲敬老楷模”、河南省“十大敬老之星”郑州市“劳动模范”、郑州市“最美 家乡人”等荣誉获得者,具有33年养老经验的蔡运娇院长带领团队运营管理。爱心之家是郑州市占地规模和床位数量较大一家养老院，养老院采用园林设计，以自然养生为基础，整个院区的绿化面积达97%，护老院配套设施完善，为长者提供生活照料，健身、保健养生、心理辅导、内务整理等多层次、高标准的服务，为长者提供一个舒心，安心，舒适的居住环境和服务。
          </p>
          <div class="demo-image">
            <div  class="block" style="margin-left: 70px">
              <el-image style="width: 900px; height: 300px;float: left;" src="/static/img/2.png"/>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="品牌介绍" name="second">
          <el-row>
            <el-image style="width: 100px; height: 100px;margin: 0 auto" src="/static/img/logo.png"/>
            <p>
              &nbsp;&nbsp;&nbsp;  “爱心之家”品牌始创于2011年，参照国内、外先进养老理念，定位家门口的养老院，专注于为高龄、半失能、失能、认知症老人，提供颐养、照料、医疗、护理、康乐等专业服务。是中国社区融合式养老模式的开拓者，全国最佳养老院，郑州市样板，是集居家养老、机构养老、社区养老为一体的实体养老机构。
              “有爱心”成立于2016年7月，该品牌由郑州市属大型国有企业郑州开发股份有限公司与养老行业知名品牌“爱心之家”养老机构运营方郑州有爱心管理咨询有限公司、郑州有福科技有限公司三方合资成立。延续“爱心之家”品牌的融合式养老模式，秉承“城市复兴”理念，将首开集团存量房产资源与爱心之家优质养老服务相结合，打造集机构养老、社区养老、居家养老于一体的连锁化、品牌化的专业护理型养老机构，计划于2025年前，在郑州市核心区域建设运营养老机构10-20家。
            </p>

            <img style="width: 600px; height: 300px;margin: 0 auto" src="/static/img/品牌.PNG"/>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="品牌文化" name="third">

          <el-image style="width: 600px; height: 300px;margin: 0 auto" src="/static/img/品牌文化.png"/>
        </el-tab-pane>
        <el-tab-pane label="品牌荣誉" name="fourth">
          <el-row>
            <p>
              民政部养老服务顾问单位 <br>
              全国养老服务的样板单位<br>
              国家养老服务标准体系审评单位<br>
              中国最佳养老机构<br>
              民政部咨询专家单位<br>
              中国社会组织评估等级5A级单位<br>
              郑州市政府养老示范单位<br>
              郑州市敬老爱老为老服务示范单位等多项荣誉
            </p>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="访客登记" name="visitor">
          访客登记
          <el-form :model="form" :rules="rules" ref="form" label-width="100px" style="width: 500px;margin: 0 auto">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item label="访问时间" prop="time">
              <el-date-picker v-model="form.startTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="endtime">
              <el-date-picker v-model="form.endTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm()" style="text-align: center">提交</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
      <el-cardL v-show="wangeditor">
        <div style="min-height: 100px" v-html="detail.content"></div>
      </el-cardL>
    </el-main>
    <el-footer>
      <Footer/>
    </el-footer>
  </el-container>

</template>

<script>
import Footer from "@/components/Guest/Footer";
import httpRequest, {isSuccess, successMessage} from "@/config/requesturl";
import dayjs from "dayjs";

export default {
  name: "about",
  components: {Footer},
  data() {
    return {
      imgsrclc: "https://cmsimg01.71360.com/data/20200821/11u6144/public/2020082119132640390.jpg",
      activeName: 'first',
      wangeditor: true,
      detail: {},
      form: {
        name: '',
        phone: '',
        email: '',
        startTime: '',
        endTime: ''
      },
    }
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event)
    },
    submitForm() {
      //格式化时间
      this.form.startTime= dayjs(this.form.startTime).format('YYYY-MM-DD HH:mm:ss');
      this.form.endTime= dayjs(this.form.endTime).format('YYYY-MM-DD HH:mm:ss');
      httpRequest.prototype.saveVisitor(this.form).then(res => {
        if (isSuccess(res)) {
          successMessage("申请成功，请等待审核");
        }
      })
    }
  },
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
