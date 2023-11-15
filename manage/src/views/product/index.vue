<template>
  <div>
    <el-card>
      <div slot="header" style="font-size: 18px">信息查询</div>
      <div>
        <el-form ref="form" :model="projectForm" label-width="80px" :inline="true">
          <el-form-item label="分类">
            <el-select v-model="projectForm.categoryId" placeholder="请选择分类">
              <el-option v-for="item in typeData" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="标题">
            <el-input v-model="projectForm.title" placeholder="标题"></el-input>
          </el-form-item>

          <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="search">搜索</el-button>
        </el-form>
      </div>
    </el-card>

    <el-card>
      <div>
        <el-button v-if="usertype=='TOURIST'" size="medium" type="primary" icon="el-icon-plus" disabled style="margin-bottom: 20px">
          增加产品</el-button>
          <el-button v-else size="medium" type="primary" icon="el-icon-plus" @click="AddProduct()" style="margin-bottom: 20px">
            增加产品</el-button>

        <div>
          <el-table :data="ProductData" style="width: 100%" border :header-cell-style="headClass">

            <el-table-column prop="title" label="标题" width="200" align="center">
            </el-table-column>
            <el-table-column prop="categoryName" label="分类" width="width" align="center">
            </el-table-column>

            <el-table-column prop="duration" label="时长" width="100" align="center">
              <template slot-scope="{ row }">
                <el-tag
                  type="info">{{ row.duration }}{{ options.find(item => item.value === row.timeUnit).label }}</el-tag>
              </template>
            </el-table-column>

            <el-table-column prop="oriPrice" label="原价" width="100" align="center">
            </el-table-column>
            <el-table-column prop="price" label="价格" width="100" align="center">
            </el-table-column>
            <el-table-column prop="preImg" label="预览图" width="200" align="center">
              <template slot-scope="{ row }">
                <el-image fit="cover" style="width: 100px; height: 100px" :src="row.preImg"
                  :preview-src-list="[row.preImg]"></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="sort" label="排序" width="width" align="center">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100" align="center">
              <template slot-scope="{ row }">
                <el-tag v-if="row.status=='UP'" type="success">上架</el-tag>
                <el-tag v-if="row.status=='DOWN'" type="info">下架</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="" label="操作" width="250" align="center">

              <template slot-scope="{ row }">
                <el-button v-if="usertype=='TOURIST'" type="primary" disabled size="small">修改</el-button>
                <el-button v-else type="primary" @click="changeInfo(row)" size="small">修改</el-button>
                <el-button v-if="usertype=='TOURIST'" type="danger" disabled size="small">删除</el-button>
                 <el-button v-else type="danger" @click="deleteInfo(row)" size="small">删除</el-button>
                <el-button v-if="usertype=='TOURIST'" type="warning" disabled size="small">
                  {{row.status=='UP'?"下架":"上架"}}
                </el-button>
                <el-button v-else type="warning" @click="changeStatus(row)" size="small">
                  {{row.status=='UP'?"下架":"上架"}}
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 页码 -->
        <el-pagination background style="text-align: center;margin-top: 20px;" @current-change="getProduct"
          @size-change="handleSizeChange" :current-page="page" :page-sizes="[3, 5, 10]" :page-size="5"
          layout="->,total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>

  </div>
</template>

<script>
  import {
    reqTypeAllList
  } from "@/api/product/type";
  import {
    reqProductList,
    reqProductdelete,
    reqProductStatus
  } from "@/api/product/product";
import Cookies from 'js-cookie'
  export default {
    name: "product",

    data() {
      return {
        ProductData: [],
        typeData: [{
          "id": null,
          "name": "全部"
        }],
        ruleForm: {
          categoryId: 1,
          duration: 0,
          info: [], // 富文本内容
          introduction: "",
          oriPrice: 0,
          preImg: "",
          price: 0,
          sort: 0,
          timeUnit: "",
          title: "",
        },
        options: [{
            label: "天",
            value: "DAY",
          },
          {
            label: "小时",
            value: "HOUR",
          },
          {
            label: "分钟",
            value: "MINUTE",
          },
        ],

        editorOptions: {
          // 富文本编辑器的配置选项
          placeholder: "请输入内容",
        },
        projectForm: {
          categoryId: "",
          title: "",
        },
        page: 1,
        limit: 5,
        total: 0,
        //表格头部样式
        headClass: {
          "background-color": "#f8f8f8",
        },
        formLabelWidth: "120px",
        usertype:""
      };
    },
    methods: {
      async getProduct(pages = 1) {
        this.page = pages;
        const {
          page,
          limit
        } = this;
        let result = await reqProductList(page, limit, this.projectForm);
        if (result.code == 200) {
          this.ProductData = result.data.rows;
          this.total = result.data.totalCount;
        }
      },

      //删除信息
      deleteInfo(row) {
        this.$confirm(`你确定删除${row.title}?`, "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {
            let result = await reqProductdelete(row.id);
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              //再次获取品牌列表数据
              this.getProduct(
                this.ProductData.length > 1 ? this.page : this.page - 1
              );
            }
          })
          .catch(() => {
            //当用户点击取消按钮的时候会触发
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      },

      async getType() {
        let result = await reqTypeAllList();
        if (result.code == 200) {
          this.typeData = this.typeData.concat(result.data);
        }
      },

      //修改信息
      changeInfo(row) {
        /* this.$router.push({
           name: "content",
           params: {
             dialogTitle: '修改产品',
             data: {
               ...row
             }
           }
         }); */
        this.$router.push("/content?id=" + row.id)
      },

      AddProduct() {
        this.$router.push({
          name: "content",
          params: {
            dialogTitle: '新增产品'
          }
        });
        this.personshow = false;

      },
     async changeStatus(row) {

        let statusData={
          "id":row.id,
          "status":row.status=='UP'?"DOWN":"UP"
        };
        let result = await reqProductStatus(statusData);
        if (result.code == 200) {
          this.$message({
            type: "success",
            message: "修改成功!",
          });
          //再次获取品牌列表数据
          this.getProduct(
            this.ProductData.length > 1 ? this.page : this.page - 1
          );
        }
      },
      // //照片墙删除某一个图片的时候会触发
      // handleRemove(file, fileList) {
      //   this.ruleForm.preImg = fileList;
      // },
      // handlePictureCardPreview(file) {
      //   //将图片地址赋值给这个属性
      //   this.dialogImageUrl = file.url;
      //   this.ruleForm.preImg = this.dialogImageUrl;
      //   //对话框显示
      //   this.dialogVisible = true;
      // },
      // //照片墙图片上传成功的回调
      // handlerSuccess(response, file, fileList) {
      //   //收集图片的信息
      //   this.ruleForm.preImg = fileList[0].response.data.url;
      // },

      search() {
        this.getProduct();
      },

      //当分页器的某一个展示数据条数发生变化的回调
      handleSizeChange(limit, page) {
        //修改参数
        this.limit = limit;
        this.page = page;
        //再发请求
        this.getProduct();
      },
      //关闭对话框
      close() {
        this.$refs.ruleform.resetFields();
        (this.ruleForm = {
          categoryId: 0,
          duration: 0,
          info: [],
          introduction: "",
          oriPrice: 0,
          preImg: "",
          price: 0,
          sort: 0,
          timeUnit: "",
          title: "",
        }),
        this.personIds = [];
        this.spuImageList = [];
      },
    },

    mounted() {
      this.usertype = Cookies.get("userinfo");
      this.getProduct();
      this.getType();
    }
    // created(){
    //   this.ruleForm.info = this.$route.params.imageList || []
    // }
  };
</script>

<style>
  .el-image {
    width: 100%;
    height: 120px;
  }

  .quill-editor {
    border: 1px solid #ccc;
    height: 600px;
    width: 100%;
  }

  .el-card {
    margin-bottom: 30px;
  }
</style>
