<template>
  <div>
    <div>
      <h1>系统设置</h1>
    </div>
    <div class="demo-collapse">
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item title="MySql数据库信息" name="1">
          <el-descriptions
              class="margin-top"
              title="MySql数据库信息"
              :column="3"
              :size="size"
              border
          >
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <user/>
                  </el-icon>
                  数据库地址:
                </div>
              </template>
              {{ databaseMess.database }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <iphone/>
                  </el-icon>
                  用户
                </div>
              </template>
              {{ databaseMess.databaseUsername }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  数据库密码
                </div>
              </template>
              {{ databaseMess.databasePassword }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <tickets/>
                  </el-icon>
                  数据库端口号
                </div>
              </template>
              <el-tag size="small">{{ databaseMess.databasePort }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <office-building/>
                  </el-icon>
                  数据库版本号
                </div>
              </template>
              {{ databaseMess.databaseVersion }}
            </el-descriptions-item>
          </el-descriptions>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="demo-collapse">
      <el-collapse v-model="redis" @change="handleChange">
        <el-collapse-item title="缓存数据库信息" name="1">
          <el-descriptions
              class="margin-top"
              title="缓存数据库信息"
              :column="3"
              :size="size"
              border
          >
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <user/>
                  </el-icon>
                  Redis版本:
                </div>
              </template>
              <p v-if="cache.info">
                {{ cache.info.redis_version }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <iphone/>
                  </el-icon>
                  运行模式
                </div>
              </template>

              <p v-if="cache.info">
                {{
                  cache.info.redis_mode == "standalone" ? "单机" : "集群"
                }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  客户端数
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.info.connected_clients }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <tickets/>
                  </el-icon>
                  运行时间(天)
                </div>
              </template>
              <el-tag size="small" v-if="cache.info">{{ cache.info.uptime_in_days }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <office-building/>
                  </el-icon>
                  使用内存
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.info.used_memory_human }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  使用CPU
                </div>
              </template>

              <p v-if="cache.info">
                {{
                  parseFloat(cache.info.used_cpu_user_children).toFixed(2)
                }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  内存配置
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.info.maxmemory_human }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  AOF是否开启
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.info.aof_enabled == "0" ? "否" : "是" }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  RDB是否成功
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.info.rdb_last_bgsave_status }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  Key数量
                </div>
              </template>

              <p v-if="cache.info">
                {{ cache.dbSize }}
              </p>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">
                  <el-icon :style="iconStyle">
                    <location/>
                  </el-icon>
                  网络入口/出口
                </div>
              </template>

              <p v-if="cache.info">
                {{
                  cache.info.instantaneous_input_kbps
                }}kps/{{ cache.info.instantaneous_output_kbps }}kps
              </p>
            </el-descriptions-item>
          </el-descriptions>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
import httpRequest from "@/config/requesturl";
import {
  Iphone,
  Location,
  OfficeBuilding,
  Tickets,
  User,
} from "@element-plus/icons-vue";

export default {
  name: "index",
  data() {
    return {
      activeNames: null,
      databaseMess: {},
      // 统计命令信息
      commandstats: null,
      // 使用内存
      usedmemory: null,
      // cache信息
      cache: [],
      cacheList: null,
      redis: null
    }
  },
  components: {
    Iphone,
    Location,
    OfficeBuilding,
    Tickets,
    User,
  },
  created() {
    this.initView();
  },
  methods: {
    getDatabaseMess() {
      httpRequest.prototype.getDatabaseMess().then(res => {
        this.databaseMess = res.data;
      })
    },

    initView() {
      this.getDatabaseMess();
      this.getRedis();
    },
    /** 查缓存询信息 */
    getRedis() {
      httpRequest.prototype.getCache().then((response) => {
        console.log(response);
        this.cache = response.data;
      });
    },
  }
}
</script>

<style scoped>

</style>
