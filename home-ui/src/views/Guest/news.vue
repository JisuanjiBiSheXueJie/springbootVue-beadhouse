<template>
    <div class="news">
      <div ref='editor'>
        <h1 v-html="form.title" style="text-align: center"></h1>
        <el-card>
          <div v-html="form.body" style="min-height: 100px"></div>
        </el-card>
      </div>
    </div>
</template>

<script>
import Footer from "@/components/Guest/Footer";
import httpRequest, {isSuccess} from "@/config/requesturl";

export default {
  name: "news",
  components: {Footer},
  data() {
    return {
      form: {
        title: "",
        body: ""
      }
    };
  },
  created() {
    this.initView();
  },
  methods: {
    initView() {
      let id = this.$route.query.id;
      httpRequest.prototype.getNewsById(id).then(res => {
        if (isSuccess(res)) {
          this.form = res.data;
        }
      })
    }
  }
}
</script>

