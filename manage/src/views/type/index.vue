<template>
  <div>
    <el-card>
      <div>
        <el-button v-if="usertype=='TOURIST'" size="medium" type="primary" icon="el-icon-plus" disabled style="margin-bottom: 20px;">
          增加分类</el-button>
          <el-button v-else size="medium" type="primary" icon="el-icon-plus" @click="AddType" style="margin-bottom: 20px;">
            增加分类</el-button>

        <div>
          <el-table :data="typeData" style="width: 100%" border :header-cell-style="headClass">

            <el-table-column prop="name" label="名称" width="width" align="center">
            </el-table-column>
            <el-table-column prop="sort" label="排序" width="width" align="center">
            </el-table-column>

            <el-table-column prop="" label="操作" width="width" align="center">
              <template slot-scope="{ row }">
                <el-button v-if="usertype=='TOURIST'" type="primary" disabled size="medium">修改</el-button>
                <el-button v-else type="primary" @click="changeInfo(row)" size="medium">修改</el-button>
                
                <el-button v-if="usertype=='TOURIST'" type="danger" disabled size="medium">删除</el-button>
                <el-button v-else type="danger" @click="deleteInfo(row)" size="medium">删除</el-button>
              </template>

            </el-table-column>
          </el-table>
        </div>
        <!-- 页码 -->
        <el-pagination background style="text-align: center;text-align: center;margin-top: 20px;"
          @current-change="getType" @size-change="handleSizeChange" :current-page="page" :page-sizes="[3, 5, 10]"
          :page-size="5" layout="->,total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>

    <!-- 弹出框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
      <el-form :model="ruleForm" :rules="rules" ref="ruleform">

        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="ruleForm.name" placeholder="名称" style="width: 70%;"></el-input>
        </el-form-item>
        <el-form-item label="排序" :label-width="formLabelWidth">
          <el-input v-model="ruleForm.sort" placeholder="排序 正序" style="width: 70%;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="saveorUpdate">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    reqTypeList,
    reqTypedelete,
    reqTypeeditorAdd
  } from '@/api/product/type'
import Cookies from 'js-cookie'
  export default {
    name: "type",
    data() {
      return {
        typeData: [],
        ruleForm: {
          name: '',
          sort: ''
        },
        dialogFormVisible: false,
        page: 1,
        limit: 5,
        total: 0,
        dialogTitle: "",
        //表格头部样式
        headClass: {
          "background-color": "#f8f8f8",
        },
        formLabelWidth: "120px",
        rules: {
          type: [{
            required: true,
            message: "请输入分类",
            trigger: "blur"
          }],

        },
        usertype:""
      };
    },
    methods: {
      async getType(pages = 1) {
        this.page = pages;
        const {
          page,
          limit
        } = this
        let result = await reqTypeList(page, limit)
        if (result.code == 200) {
          this.typeData = result.data.rows
          this.total = result.data.totalCount
        }
      },

      //修改信息
      changeInfo(row) {

        this.dialogFormVisible = true;
        this.ruleForm = {
          ...row
        };
        this.dialogTitle = '修改分类'
      },

      //删除信息
      deleteInfo(row) {
        this.$confirm(`你确定删除${row.name}?`, '提示', {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          }).then(async () => {
            let result = await reqTypedelete(row.id)
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              //再次获取品牌列表数据
              this.getType(this.typeData.length > 1 ? this.page : this.page - 1);
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
      AddType() {
        this.dialogFormVisible = true;
        this.dialogTitle = '新增分类'
      },
      //当分页器的某一个展示数据条数发生变化的回调
      handleSizeChange(limit, page) {
        //修改参数
        this.limit = limit;
        this.page = page
        //再发请求
        this.getType();
      },
      //关闭对话框
      close() {
        this.$refs.ruleform.resetFields()
        this.ruleForm = {
            type: ''
          },
          this.dialogFormVisible = false
      },
      saveorUpdate() {
        this.$refs.ruleform.validate(async (success) => {
          if (success) {
            let result = await reqTypeeditorAdd(this.ruleForm)
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "提交成功!",
              });
              this.$refs.ruleform.resetFields()
              this.ruleForm = {
                  type: ''
                },
                this.dialogFormVisible = false
              //重新请求
              this.getType()
            }
          } else {
            console.log("error submit!!");
            return false;
          }
        })
      }


    },
    mounted() {
      this.usertype = Cookies.get("userinfo");
      this.getType();
    }
  };
</script>

<style>
  .el-card {
    margin-bottom: 30px;
  }
</style>
