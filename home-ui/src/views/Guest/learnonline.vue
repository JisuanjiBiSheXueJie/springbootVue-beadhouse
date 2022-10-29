<template>
<!--  <el-container>-->
<!--    <el-header><br>-->
<!--      当前用户为:郭帆-->
<!--    </el-header>-->
<!--    <el-main>-->
<!--      <ul class="boxs">-->
<!--        <li @click="Subject1">科目一理论考试</li>-->
<!--        <li @click="Subject2">科目二场地考试</li>-->
<!--        <li @click="Subject3">科目三上路考试</li>-->
<!--        <li @click="Subject4">科目三理论考试</li>-->
<!--      </ul>-->
<!--    </el-main>-->
<!--    <el-footer>-->
<!--      <Footer/>-->
<!--    </el-footer>-->
<!--  </el-container>-->
</template>

<script>
import Footer from "@/components/Guest/Footer";

export default {
  name: "learnonline",
  components: {Footer},
  methods: {
    Subject1() {
      this.$router.push("/subject1?id=1");
    },
    Subject2() {
      this.$router.push("/subject2?id=2");
    },
    Subject3() {
      this.$router.push("/subject2?id=3");
    },
    Subject4() {
      this.$router.push("/subject1?id=4");
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

.boxs {
  margin-left: 5%;
}

.boxs li {
  word-wrap: break-word;
  text-align: center;
  color: aliceblue;
  line-height: 300px;
  list-style: none;
  font-size: 40px;
  width: 300px;
  height: 300px;
  border-radius: 20px;
  background-color: #60aada;
  float: left;
  margin-left: 20px;
  margin-top: 20px;
}

.boxs li:hover {
  background-color: #89d9f5;
  color: black;
}
</style>
