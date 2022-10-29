<template>
  <el-container>
    <Header/>
    <el-header>用户:郭帆
      <br>
      <div class="time">
        <span>剩余时间:{{ minute }}:{{ seconds }}</span>
      </div>
    </el-header>
    <el-main v-if="this.list!=null">
      <div style="margin-left: 20%;width: 600px;" v-for="item in list" :key="item">
        <p>{{ count += 1 }}、{{ item.title }}</p>
        <span style="float: right"><img :src=item.url alt="" height="140"></span>
        <span v-if="item.optionA!=''">
      <ul style="list-style: none">
        <p>{{ item.optionA }}</p>
        <p>{{ item.optionB }}</p>
        <p>{{ item.optionC }}</p>
        <p>{{ item.optionD }}</p>
      </ul>
          <p v-show="false">
          {{ item.analysis = count }}
          </p>
        <p>
      <el-button @click="postResult(item.analysis,item.id,'A')" :id="'btnA'+item.id">A</el-button>
      <el-button @click="postResult(item.analysis,item.id,'B')" :id="'btnB'+item.id">B</el-button>
      <el-button @click="postResult(item.analysis,item.id,'C')" :id="'btnC'+item.id">C</el-button>
      <el-button @click="postResult(item.analysis,item.id,'D')" :id="'btnD'+item.id">D</el-button>
        </p>
          </span>
        <span v-if="item.optionA==''">
        <p>
      <el-button @click="postResult(item.analysis,item.id,'T')" :id="'btnT'+item.id">正确</el-button>
      <el-button @click="postResult(item.analysis,item.id,'F')" :id="'btnF'+item.id">错误</el-button>
        </p>
          </span>
        <hr/>
      </div>
    </el-main>
    <el-footer>
      <el-popconfirm
          confirm-button-text="是的"
          cancel-button-text="不，我点错了"
          :icon="InfoFilled"
          icon-color="#f90"
          title="是否现在交卷啊！"
          @confirm="put"
      >
        <template #reference>
          <el-button>交卷</el-button>
        </template>
      </el-popconfirm>
    </el-footer>
  </el-container>
</template>

<script>
import request from "@/utils/request";
import {ElMessageBox} from "element-plus";
import Header from "@/components/Guest/Header";

export default {
  name: "MockExamination",
  components: {Header},
  data() {
    return {
      time: "",
      list: [],
      count: -10000,
      result: [],
      hour: "", //倒计时  小时
      minute: "", // 倒计时 分钟
      seconds: "", //倒计时 秒
      interval: "",
      countdownTime: 45,
    }
  },
  created() {
    this.save();
    this.setTimer()
    this.setTimeData(this.countdownTime * 60 * 1000);
    window.setInterval(() => {
      setTimeout(() => {
        this.getTime();
      }, 0)
    }, 1)
  },
  methods: {
    save() {
      request.post("/getSubject/ExaminationQuestions").then(res => {
        // console.log(res.data)
        this.list = res.data;
      })
    },
    postResult(row, id, result) {
      // console.log(row);
      if (row > 90 && row <= 100) {
        document.getElementById("btn" + result + id).style.backgroundColor = "cornflowerblue"
        let list = ["A", "B", "C", "D"];
        let str = ""
        for (let i = 0; i < list.length; i++) {
          if (document.getElementById("btn" + list[i] + id).style.backgroundColor === "cornflowerblue") {
            str += list[i]
          }
        }
        this.result[row] = {id, str}
        // console.log(this.result);
        return;
      }
      this.setBtnColor(id, result);
      let pd = result;
      result = this.setValue(result);
      // console.log(row, id, result)
      this.result[row] = {id, result}
      // console.log(result);
      document.getElementById("btn" + pd + id).style.backgroundColor = "cornflowerblue"
      // console.log(this.result[row]);
    },
    getTime() {
      let date = new Date();
      this.time = date.toLocaleString()
    },
    setValue(result) {
      if (result == "T") {
        return "对"
      } else if (result == "F") {
        return "错"
      } else {
        return result;
      }
    },
    setBtnColor(id, result) {
      let list = ["A", "B", "C", "D"];
      if (result == "T" || result == "F") {
        list = ["T", "F"]
      }
      for (let i = 0; i < list.length; i++) {
        document.getElementById("btn" + list[i] + id).style.backgroundColor = "white"
      }
    },
    elmess(result) {
      ElMessageBox.confirm(
          '你的成绩为:' + result,
          '考试成绩',
          {
            confirmButtonText: '确定',
            cancelButtonText: '再来一次',
            type: 'warning',
          }
      )
          .then(() => {
            this.$router.push("/mock")
          })
          .catch(() => {
            location.reload();
          })
    },
    put() {
      request.post("/getResult/getMyachievement", this.result).then(res => {
        this.elmess(res.data);
      })
    },
    setTimeData(cm) {
      console.log(cm, '--cm');
      this.hour = Math.floor((cm / 1000 / 60 / 60) % 24);
      this.hour =
          this.hour.toString().length == 1 ? "0" + this.hour : this.hour;
      this.minute = Math.floor((cm / 1000 / 60) % 60);
      this.minute =
          this.minute.toString().length == 1 ? "0" + this.minute : this.minute;
      this.seconds = Math.floor((cm / 1000) % 60);
      this.seconds =
          this.seconds.toString().length == 1
              ? "0" + this.seconds
              : this.seconds;
    },
    setTimer() {
      let _this = this;
      this.interval = setInterval(function () {
        _this.payCountdown(_this.countdownTime * 60 * 1000);
        clearInterval(_this.interval);
      }, 1000);
    },
    /**
     * 支付倒计时
     */
    payCountdown(cm) {
      let _this = this;
      if (cm >= 0) {
        this.hour = Math.floor((cm / 1000 / 60 / 60) % 24);
        this.hour =
            this.hour.toString().length == 1 ? "0" + this.hour : this.hour;
        this.minute = Math.floor((cm / 1000 / 60) % 60);
        this.minute =
            this.minute.toString().length == 1
                ? "0" + this.minute
                : this.minute;
        this.seconds = Math.floor((cm / 1000) % 60);
        this.seconds =
            this.seconds.toString().length == 1
                ? "0" + this.seconds
                : this.seconds;
        let _msThis = this;
        setTimeout(function () {
          cm -= 1000;
          _msThis.payCountdown(cm);
        }, 1000);
      }else {
        this.put();
      }
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

.time {
  position: fixed; /* 设置fixed固定定位 */
  bottom: 20px; /* 距离浏览器窗口下边框20px */
  right: 20px; /* 距离浏览器窗口右边框20px */
  float: right;
  margin-right: 40px;
  font-size: 26px;
}
</style>
