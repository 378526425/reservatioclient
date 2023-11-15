<template>
  <div>
    <el-card>
      <div slot="header" style="font-size: 18px">信息查询</div>
      <div>
        <el-form ref="form" :model="projectForm" label-width="80px" :inline="true">
          <el-form-item label="姓名">
            <el-input v-model="projectForm.name" placeholder="姓名"></el-input>
          </el-form-item>
          <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="search">搜索</el-button>
        </el-form>
      </div>
    </el-card>
    <el-card>
      <div>
        <el-button v-if="usertype=='TOURIST'" type="primary" size="medium" icon="el-icon-plus" disabled
          style="margin-bottom: 20px;">
          增加人员</el-button>
        <el-button v-else type="primary" size="medium" icon="el-icon-plus" @click="Addperson()"
          style="margin-bottom: 20px;">
          增加人员</el-button>

        <div>
          <el-table :data="persondata" style="width: 100%" border :header-cell-style="headClass">
            <el-table-column prop="name" label="姓名" width="120" align="center">
            </el-table-column>
            <el-table-column prop="preImg" label="预览图片" width="200" align="center">
              <template slot-scope="{ row }">
                <el-image fit="cover" style="width: 100px; height: 100px" :src="row.preImg"
                  :preview-src-list="[row.preImg]"></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="introduction" label="介绍" width="width" align="center">
            </el-table-column>

            <el-table-column prop="phone" label="手机" width="150" align="center">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="200" align="center">
            </el-table-column>


            <el-table-column prop="" label="操作" width="200" align="center">
              <template  slot-scope="{ row }">
                <el-button v-if="usertype=='TOURIST'" type="primary" disabled size="medium">修改</el-button>
                <el-button v-else type="primary" @click="changeInfo(row)" size="medium">修改</el-button>
                <el-button v-if="usertype=='TOURIST'" type="danger" disabled size="medium">删除</el-button>
                <el-button v-else type="danger" @click="deleteInfo(row)" size="medium">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 页码 -->
        <el-pagination background style="text-align: center;margin-top: 20px;" @current-change="getPerson"
          @size-change="handleSizeChange" :current-page="page" :page-sizes="[3, 5, 10]" :page-size="5"
          layout="->,total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>

    <!-- 弹出框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="handleDialogClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleform">

        <el-form-item label="人员姓名" :label-width="formLabelWidth">
          <el-input v-model="ruleForm.name" placeholder="人员姓名"></el-input>
        </el-form-item>
        <el-form-item label="人员介绍" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="ruleForm.introduction"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="ruleForm.phone" placeholder="多个手机号空格隔开"></el-input>
        </el-form-item>
        <el-form-item label="预览图片" :label-width="formLabelWidth">
          <el-upload action="/reservationdevapi/file/upload" list-type="picture-card"
            :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :file-list="spuImageList"
            :on-success="handlerSuccess" :multiple="false" :limit="1">
            <i class="el-icon-plus"></i>
          </el-upload>

          <el-dialog :visible.sync="dialogVisible" append-to-body>
            <img width="100%" :src="dialogImageUrl" alt="" fit="contain" />
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="Personsave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    reqPersonList,
    PersonAddorUpdate,
    reqPersonDelete,
  } from "@/api/product/person";
  import Cookies from 'js-cookie'
  export default {
    name: "Person",
    data() {
      return {
        persondata: [],
        ruleForm: {
          introduction: "",
          name: "",
          preImg: "",
          phone: "",
        },
        dialogFormVisible: false,
        projectForm: {
          name: "",
        },
        spuImageList: [],
        dialogTitle: "",
        page: 1,
        limit: 5,
        total: 0,
        //表格头部样式
        headClass: {
          "background-color": "#f8f8f8",
        },
        formLabelWidth: "120px",
        dialogImageUrl: "",
        dialogVisible: false,
        rules: {
          name: [{
            required: true,
            message: "请输入姓名",
            trigger: "blur"
          }],
          preImg: [{
            required: true,
            message: "请输入图片",
            trigger: "blur"
          }],
        },
        srcList: [],
        usertype: ""
      };
    },
    methods: {
      async getPerson(pages = 1) {
        this.page = pages;
        const {
          page,
          limit,
          projectForm
        } = this;
        let result = await reqPersonList(page, limit, projectForm);
        if (result.code == 200) {
          this.persondata = result.data.rows;
          this.total = result.data.totalCount;
        }
      },

      //新增学生
      async Personsave() {
        this.$refs.ruleform.validate(async (success) => {
          if (success) {
            let result = await PersonAddorUpdate(this.ruleForm);
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "提交成功!",
              });

              this.$refs.ruleform.resetFields();
              (this.ruleForm = {
                createTime: "",
                introduction: "",
                name: "",
                preImg: "",
              }),
              (this.dialogFormVisible = false);
              this.spuImageList = []
              //重新请求
              this.getPerson();
            }
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      },

      handleDialogClose() {
        this.spuImageList = []
      },
      //修改信息
      changeInfo(row) {
        this.dialogFormVisible = true;
        this.dialogTitle = "修改人员";
        this.ruleForm = {
          ...row
        };
        console.log(this.ruleForm)
        this.dialogImageUrl = this.ruleForm.preImg;
        this.spuImageList.push({
          'url': this.ruleForm.preImg
        })

      },

      //删除信息
      deleteInfo(row) {
        this.$confirm(`你确定删除${row.name}?`, "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {
            let result = await reqPersonDelete(row.id);
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              //再次获取品牌列表数据
              this.getPerson(
                this.persondata.length > 1 ? this.page : this.page - 1
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

      //照片墙删除某一个图片的时候会触发
      handleRemove(file, fileList) {
        // this.ruleForm.preImg = fileList;
      },
      handlePictureCardPreview(file) {
        console.log(file.url);
        //将图片地址赋值给这个属性
        this.dialogImageUrl = file.url;
        // this.ruleForm.preImg = this.dialogImageUrl;
        //对话框显示
        this.dialogVisible = true;
      },
      //照片墙图片上传成功的回调
      handlerSuccess(response, file, fileList) {
        //收集图片的信息
        this.ruleForm.preImg = fileList[0].response.data.url;
        this.dialogImageUrl = fileList[0].response.data.url;
      },

      //当分页器的某一个展示数据条数发生变化的回调
      handleSizeChange(limit, page) {
        //修改参数
        this.limit = limit;
        this.page = page;
        //再发请求
        this.getPerson();
      },

      search() {
        this.getPerson();
      },

      //关闭对话框
      close() {
        this.$refs.ruleform.resetFields();
        (this.ruleForm = {
          createTime: "",
          introduction: "",
          name: "",
          preImg: "",
        }),
        (this.dialogFormVisible = false);
        this.spuImageList = []
      },

      //打开添加对话框
      Addperson() {
        this.dialogFormVisible = true;
        this.dialogTitle = "新增人员";
      },

      save() {},
    },
    mounted() {
      this.usertype = Cookies.get("userinfo");
      this.getPerson();
    }
  };
</script>

<style lang="scss" scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .el-image {
    width: 100%;
    max-height: 160px;
  }

  .el-card {
    margin-bottom: 30px;
  }
</style>
