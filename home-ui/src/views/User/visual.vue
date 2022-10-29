<template>
  <div class="Echarts">
    <el-date-picker
        v-model="date"
        type="week"
        format="[第] ww [周]"
        placeholder="选择周"
        style="width: 140px"
        @change="initView"
    />
    <div style="width: 600px; height: 400px" id="echarts"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import {defineComponent, h, ref} from "vue";
import {NIcon} from "naive-ui";
import {
  BookOutline as BookIcon,
  PersonOutline as PersonIcon,
  WineOutline as WineIcon
} from "@vicons/ionicons5";
import httpRequest from "@/config/requesturl";
import dayjs from "dayjs";

function renderIcon(icon) {
  return () => h(NIcon, null, {default: () => h(icon)});
}

const menuOptions = [];
let myChart1;
export default {
  name: "visual",
  props: ['UserId'],
  data() {
    return {
      activeKey: ref(null),
      collapsed: ref(true),
      personId: this.UserId,
      date: null,
      close,
      myecharts: null,
      menuOptions
    }
  },
  mounted() {
  },
  methods: {
    initView() {
      let self = this;
      if (self.date == null) {
        return;
      }
      let startTime = dayjs(self.date).startOf('week').format('YYYY-MM-DD HH:mm:ss');
      let endTime = dayjs(self.date).endOf('week').format('YYYY-MM-DD HH:mm:ss');
      let data = {
        "startTime": startTime,
        "endTime": endTime,
        "isUpdate":false,
        "id": this.personId
      }
      httpRequest.prototype.getHealthpunchById(data).then(res => {
        self.myEcharts(res.data);
      });
    },
    myEcharts(data) {
      if (myChart1 != null && myChart1 != "" && myChart1 != undefined) {
        myChart1.dispose();//销毁
      }
      myChart1 = null;
      let list1 = [];
      let list2 = [];
      let list3 = [];
      let list4 = [];
      let list5 = [];
      data.forEach(item => {
        list1.push(parseFloat(item.temperature).toFixed(1));
        list2.push(item.maxBloodPre);
        list3.push(item.minBloodPre);
        list4.push(item.breathingRate);
        list5.push(item.pulse);
      });
      myChart1 = echarts.init(document.getElementById("echarts"));
      myChart1.setOption(
          {
            title: {
              text: ''
            },
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross',
                label: {
                  backgroundColor: '#6a7985'
                }
              }
            },
            legend: {
              data: ['呼吸频率', '体温', '心率', '血压(低)', '血压(高)']
            },
            toolbox: {
              feature: {
                saveAsImage: {}
              }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: [
              {
                type: 'category',
                boundaryGap: false,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
              }
            ],
            yAxis: [
              {
                type: 'value'
              }
            ],
            series: [
              {
                name: '呼吸频率',
                type: 'line',
                stack: 'Total',
                areaStyle: {},
                emphasis: {
                  focus: 'series'
                },
                data: list4
              },
              {
                name: '体温',
                type: 'line',
                stack: 'Total',
                areaStyle: {},
                emphasis: {
                  focus: 'series'
                },
                data: list1
              },
              {
                name: '心率',
                type: 'line',
                stack: 'Total',
                areaStyle: {},
                emphasis: {
                  focus: 'series'
                },
                data: list5
              },
              {
                name: '血压(低)',
                type: 'line',
                stack: 'Total',
                areaStyle: {},
                emphasis: {
                  focus: 'series'
                },
                data: list3
              },
              {
                name: '血压(高)',
                type: 'line',
                stack: 'Total',
                label: {
                  show: true,
                  position: 'top'
                },
                areaStyle: {},
                emphasis: {
                  focus: 'series'
                },
                data: list2
              }
            ]
          }
      );
    },
    close() {
      if (myChart1 != null && myChart1 != "" && myChart1 != undefined) {
        myChart1.dispose();//销毁
      }
    }
  }
}
</script>

<style scoped>
.flexBox {
  position: fixed; /* 设置fixed固定定位 */
  bottom: 20px; /* 距离浏览器窗口下边框20px */
  right: 20px; /* 距离浏览器窗口右边框20px */
  width: 200px;
  height: 200px;
}
</style>
