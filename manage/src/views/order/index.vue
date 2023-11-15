<template>
  <div>
    <el-card>
      <div slot="header" style="font-size: 18px">信息查询</div>
      <div>
        <el-form ref="form" :model="projectForm" label-width="90px" :inline="true">
          <el-form-item label="项目名称">
            <el-input v-model="projectForm.title" placeholder="标题"></el-input>
          </el-form-item>
          <el-form-item label="预约人">
            <el-input v-model="projectForm.contacts" placeholder="预约人姓名"></el-input>
          </el-form-item>
          <el-form-item label="联系手机">
            <el-input v-model="projectForm.phone" placeholder="手机号"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="projectForm.status" placeholder="请选择状态">
              <el-option v-for="item in statusList" :key="item.value" :label="item.value" :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>

          <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="search">搜索</el-button>
        </el-form>
      </div>
    </el-card>
    <el-card>
      <div>
        <div>
          <el-table :data="orderData" style="width: 100%" border :header-cell-style="headClass">
            <el-table-column prop="title" label="项目名称" width="width" align="center">
            </el-table-column>
            <el-table-column prop="preImg" label="预览图片" width="200" align="center">
              <template slot-scope="{ row }">
                <el-image fit="cover" style="width: 100px; height: 100px" :src="row.preImg"
                  :preview-src-list="[row.preImg]"></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="contacts" label="预约人" width="width" align="center">
            </el-table-column>
            <el-table-column prop="personName" label="服务人员" width="width" align="center">
            </el-table-column>
            <el-table-column prop="startTime" label="预约时间" width="width" align="center">
            </el-table-column>
            <el-table-column prop="createTime" label="提交时间" width="width" align="center">
            </el-table-column>

            <el-table-column prop="phone" label="联系手机" width="width" align="center">
            </el-table-column>

            <el-table-column prop="remark" label="备注" width="width" align="center">
            </el-table-column>

            <el-table-column prop="status" label="状态" width="width" align="center">
              <template slot-scope="{ row }">
                <el-tag>{{ statusList.find(item => item.name === row.status).value }}</el-tag>
              </template>
            </el-table-column>


            <el-table-column prop="" label="操作" width="width" align="center">
              <template slot-scope="{ row }">
                <el-button v-if="usertype=='TOURIST'" type="primary" disabled size="medium">完成</el-button>
                <el-button v-else type="primary" @click="changeInfo(row)" size="medium">完成</el-button>
              </template>

            </el-table-column>
          </el-table>
        </div>
        <!-- 页码 -->
        <el-pagination background style="text-align: center;margin-top: 20px;" @current-change="getOrder"
          @size-change="handleSizeChange" :current-page="page" :page-sizes="[3, 5, 10]" :page-size="5"
          layout="->,total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {
    reqOrderList,
    reqOrderedit
  } from "@/api/product/order";
  import Cookies from 'js-cookie'
  export default {
    name: "order",
    data() {
      return {
        orderData: [],
        projectForm: {
          contacts: "",
          phone: "",
          status: "",
          title: "",
        },
        statusList: [{
            name: '',
            value: "全部"
          },
          {
            name: 'CANCELED',
            value: "已取消"
          },
          {
            name: 'WAIT',
            value: "待服务"
          },
          {
            name: 'SERVEB',
            value: "已服务"
          },
          {
            name: 'EXPIRED',
            value: "已过期"
          }
        ],
        dialogFormVisible: false,
        page: 1,
        limit: 5,
        total: 0,
        //表格头部样式
        headClass: {
          "background-color": "#f8f8f8",
        },
        formLabelWidth: "120px",
        usertype: ""
      };
    },
    methods: {
      async getOrder(pages = 1) {
        this.page = pages;
        const {
          page,
          limit,
          projectForm
        } = this;
        let result = await reqOrderList(page, limit, projectForm);
        if (result.code == 200) {
          this.orderData = result.data.rows;
          this.total = result.data.totalCount;
        }
      },

      search() {
        this.getOrder();
      },

      //修改信息
      changeInfo(row) {
        this.$confirm(`你确定完成服务：${row.title}?`, "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {

            let result = await reqOrderedit(row.id);
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "服务完成!",
              });
              //再次获取品牌列表数据
              this.getOrder(
                this.orderData.length > 1 ? this.page : this.page - 1
              );
            }
          })
          .catch(() => {
            //当用户点击取消按钮的时候会触发
            this.$message({
              type: "info",
              message: "已取消",
            });
          });

      },

      //当分页器的某一个展示数据条数发生变化的回调
      handleSizeChange(limit, page) {
        //修改参数
        this.limit = limit;
        this.page = page;
        //再发请求
        this.getOrder();
      }
    },
    mounted() {
      this.usertype = Cookies.get("userinfo");
      this.getOrder();
    }
  };
</script>

<style>
  .el-card {
    margin-bottom: 30px;
  }
</style>
