<template>
  <div class="block">
    <div class="title">
      <span class="demonstration">真诚服务 用心照顾</span>
<!--      <a href="#" @click="ContactUs" style="line-height: 30px;"><el-icon><message /></el-icon> 联系我们 </a>-->
    </div>
    <el-carousel height="50vh">
      <el-carousel-item v-for="item in imagesListL" :key="item">
        <img :src="item" alt="" style="width: 100%"/>
      </el-carousel-item>
    </el-carousel>
    <div class="subject">
      <p style="font-size: 30px; margin-left: 18%; padding: 30px">
        <span class="texthr"></span>
        <span style="float: left">&nbsp;关于我们&nbsp;</span>
        <span class="texthr"></span>
      </p>
      <div class="main_text" style="width: 100%;height: 200px;">
        <n-image
            style="
              width: 200px;
              float: left;
            "
            :src=imgtitle1
        />
        <div style="padding-top: 30px; padding-left: 320px">
          <el-link @click="openNews(titleList.id)">{{ titleList.title }}</el-link>
          <p style="width: 180px; word-break: break-word; line-height: 20px" @click="openNews(titleList.id)">
            {{titleList.content}}
          </p>
        </div>
      </div>

      <a href="#" style="float: right;padding-right: 60px">>>更多资讯</a>
      <br>
      <div v-if="newBodyList!=null" class="main_text" v-for="item in newBodyList" :key="item">

        <div class="listbox">
          <n-image
              style="
              width: 120px;
              height: 120px;
              float: left;
              margin-right: 40px;
            "
              :src=item.fileName
          />
          <p style="float: left; padding-top: 10px;width:370px;word-wrap:break-word;line-height: 20px;font-size: 14px" @click="openNews(item.id)">
            {{ item.title }}
          </p>
          <p style="width: 500px; word-break: break-word; line-height: 20px;font-size: 12px" @click="openNews(item.id)">
            {{ item.content}}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

import message from "@element-plus/icons-vue/dist/es/message.mjs";
import {Avatar, CaretBottom} from "@element-plus/icons-vue";
import httpRequest from "@/config/requesturl";
export default {
  name: "index",
  data() {
    return {
      content:"",
      title:"",
      newBodyList:[],
      titleList:[],
      // bobytitileId:[],
      // bobytitileDate:[],
      imagesListL: [
        "https://jmy-pic.baidu.com/0/pic/13feb2988e5d1f3a2ea11a99e78debb7.jpg",
        "https://jmy-pic.baidu.com/0/pic/70ac02df64c5f9b5adf5e657cc95339b.jpg",
        "https://jmy-pic.baidu.com/0/pic/db9a45dbf0dcd888f61ce3b2f058b5e9.jpg"
      ],
      imgtitle1:
          "https://jmy-pic.baidu.com/0/pic/8c2beb9568673d08f7a8d958dd00ceb6.jpg",
    };
  },
  created() {
    this.intiView();
  },
  components: {
    message
  },
  methods: {
    intiView() {
      this.setTileone();
      this.setTileDell();
    },
    openNews(id){
      this.$router.push({
        path: "/guest/news",
        query: {
          id: id
        }
      });
    },
    setTileone(){
      request.post("/guest/index/" + 1).then(res => {
        // console.log(res.data);
        this.titleList=res.data[0];
        if (this.titleList.fileName != null && this.titleList.fileName != "" && this.titleList.fileName != undefined) {
          this.imgtitle1= httpRequest.prototype.getImageFileUrl()+ this.titleList.fileName;
        }else {
          this.imgtitle1= "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.hjunkel.com%2Fimages%2Fnopic2.gif&refer=http%3A%2F%2Fwww.hjunkel.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1653309171&t=c9aafee799a6ef20e58f0cfcb7c3d3b3";
        }
      })
    },
    ContactUs(){
      this.$router.push("/guest/ContactUs");
    },
    setTileDell(){
      request.post("/guest/index/" + 0).then(res => {
        // console.log(res.data);
        let data = res.data;
        data.forEach(item=>{
          if (item.fileName != null && item.fileName != "" && item.fileName != undefined) {
            item.fileName = httpRequest.prototype.getImageFileUrl()+ item.fileName;
          }else {
            item.fileName = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.hjunkel.com%2Fimages%2Fnopic2.gif&refer=http%3A%2F%2Fwww.hjunkel.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1653309171&t=c9aafee799a6ef20e58f0cfcb7c3d3b3";
          }
        })
        this.newBodyList= res.data;
        // for (let i = 0; i < datas.length; i++) {
        //   this.bodytitle1.push(datas[i].title);
        //   this.bobytitileList.push(datas[i].content);
        //   this.bobytitileId.push(datas[i].id);
        //   this.bobytitileDate.push(datas[i].createTime);
        // }
      })
    }
  },
};
</script>

<style>
a{
  text-decoration:none;
}
.listbox {
  margin-right: 30px;
  float: left;
}

.main_text {
  padding-left: 10%;
}

.texthr {
  width: 30%;
  border: 1px solid #000;
  float: left;
}

.subject {
  width: 90%;
  margin: 0 auto;
  line-height: 30%;
  height: 60%;
}

.bottom_box {
  background-color: red;
  padding-left: 20vh;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.title {
  width: 100%;
  text-align: left;
}

.demonstration {
  margin-right: 70%;
}
</style>
