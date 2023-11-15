<template>
  <div class="container">
    <h2>{{ dialogTitle }}</h2>
    <el-card>
      <el-form :model="ruleForm" :rules="rules" ref="ruleform">

        <el-form-item label="标题" :label-width="formLabelWidth">
          <el-input v-model="ruleForm.title" style="width: 50%;"></el-input>
        </el-form-item>
        <el-form-item label="分类" :label-width="formLabelWidth">
          <el-select v-model="ruleForm.categoryId" placeholder="请选择分类">
            <el-option v-for="item in typeData" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="时长" :label-width="formLabelWidth">
          <el-input-number v-model="ruleForm.duration" :min="1" :max="1000"></el-input-number>
        </el-form-item>

        <el-form-item label="时长单位" :label-width="formLabelWidth">
          <el-select v-model="ruleForm.timeUnit" placeholder="请选择时长单位">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="原来的价格" :label-width="formLabelWidth">
          <el-input-number v-model="ruleForm.oriPrice" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth">
          <el-input-number v-model="ruleForm.price" :min="1"></el-input-number>
        </el-form-item>

        <el-form-item label="排序(倒叙)" :label-width="formLabelWidth">
          <el-input-number v-model="ruleForm.sort" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="人员列表" :label-width="formLabelWidth" v-if="personshow">
          <el-checkbox-group v-model="personIds">
            <el-checkbox v-for="item in persondata" :key="item.id" :label="item.id"
              :value="item.id">{{ item.name }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="预览图" :label-width="formLabelWidth">
          <el-upload action="/reservationdevapi/file/upload" list-type="picture-card" :on-preview="handlePictureCardPreview1"
            :on-remove="handleRemove1" :file-list="spuImageList" :on-success="handlerSuccess1" :limit="1">
            <i class="el-icon-plus"></i>
          </el-upload>

          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl1" alt="" />
          </el-dialog>
        </el-form-item>
        <el-form-item label="主图" :label-width="formLabelWidth">
          <el-upload action="/reservationdevapi/file/upload" list-type="picture-card" :on-preview="handlePictureCardPreview2"
            :on-remove="handleRemove2" :file-list="mainImageList" :on-success="handlerSuccess2" :limit="1">
            <i class="el-icon-plus"></i>
          </el-upload>

          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl1" alt="" />
          </el-dialog>
        </el-form-item>
        <el-form-item label="详情图" :label-width="formLabelWidth">
          <el-upload action="/reservationdevapi/file/upload" list-type="picture-card" :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove" :file-list="ImageList" :on-success="handlerSuccess">
            <i class="el-icon-plus"></i>
          </el-upload>

          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="" />
          </el-dialog>
        </el-form-item>

        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="5" v-model="ruleForm.introduction"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <div slot="footer" style="margin-top: 40px">
      <el-button type="primary" @click="saveInfo" size="medium">确定</el-button>
      <el-button type="primary" @click="close" size="medium">取消</el-button>
    </div>
  </div>
</template>

<script>
  import {
    reqProductedit,
    reqProductaddPerson,
    reqProductInfo,
  } from "@/api/product/product";
  import {
    reqTypeAllList
  } from "@/api/product/type";
  import {
    reqPersonAll
  } from "@/api/product/person";
  export default {
    name: "imageList",
    data() {
      return {
        ruleForm: {
          categoryId: null,
          duration: 0,
          info: [], // 富文本内容
          introduction: "",
          oriPrice: 0,
          preImg: "",
          mainImg: "",
          price: 0,
          sort: 0,
          timeUnit: "",
          title: "",
        },
        personshow: false,
        typeData: [],
        dialogTitle: "",
        ImageList: [],
        spuImageList: [],
        mainImageList: [],
        persondata: [],
        personIds: [],
        dialogImageUrl: "",
        dialogImageUrl1: "",
        dialogImageUrl2: "",
        dialogVisible: false,
        formLabelWidth: "120px",
        options: [{
            label: "分钟",
            value: "MINUTE",
          },
          {
            label: "小时",
            value: "HOUR",
          },
          {
            label: "天",
            value: "DAY",
          }
        ],
        rules: {
          categoryId: [{
            required: true,
            message: "请输入分类id",
            trigger: "blur"
          }, ],
          preImg: [{
            required: true,
            message: "请输入图片",
            trigger: "blur"
          }],
          title: [{
            required: true,
            message: "请输入标题",
            trigger: "blur"
          }],
        },
      };
    },
    methods: {
      saveInfo() {
        //   this.$router.push({ name: "product", params: { imageList: this.imageList } });

        this.$refs.ruleform.validate(async (success) => {
          if (success) {
            const personIds = Object.values(this.personIds);
            const intKeysArray = personIds.map((key) => parseInt(key));
            console.log(this.ruleForm);
            let result = await reqProductedit(this.ruleForm);
            if (this.ruleForm.id) {
              let result1 = await reqProductaddPerson(
                intKeysArray,
                this.ruleForm.id
              );
            }
            if (result.code == 200) {
              this.$message({
                type: "success",
                message: "提交成功!",
              });
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
                personIds: [],
                title: "",
              }),
              this.personIds = [];
              this.spuImageList = [];
              this.$router.push({
                name: "product"
              });
            }
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      },
      //照片墙删除某一个图片的时候会触发
      handleRemove(file, fileList) {
        console.log(fileList);
        this.ImageList = fileList.map((response) => response.url)
        this.ruleForm.info = fileList.map((response) => response.url)
      },
      handleRemove1(file, fileList) {
        console.log(fileList);
        this.spuImageList = fileList.map((response) => response.url)
        this.ruleForm.preImg = fileList.map((response) => response.url)
      },
      handleRemove2(file, fileList) {
        console.log(fileList);
        this.mianImageList = fileList.map((response) => response.url)
        this.ruleForm.mainImg = fileList.map((response) => response.url)
      },
      handlePictureCardPreview(file) {
        //将图片地址赋值给这个属性
        this.dialogImageUrl = file.url;
        //对话框显示
        this.dialogVisible = true;
      },
      handlePictureCardPreview1(file) {
        //将图片地址赋值给这个属性
        this.dialogImageUrl1 = file.url;
        //对话框显示
        this.dialogVisible = true;
      },
      handlePictureCardPreview2(file) {
        //将图片地址赋值给这个属性
        this.dialogImageUrl2 = file.url;
        //对话框显示
        this.dialogVisible = true;
      },
      //照片墙图片上传成功的回调
      handlerSuccess(response, file, fileList) {
        debugger
        this.ImageList = [];
        for (var i = 0; i < fileList.length; i++) {
          if (fileList[i].response == null) {
            this.ImageList.push(fileList[i].url)
          } else {
            this.ImageList.push(fileList[i].response.data.url)
          }
        }
        this.ruleForm.info = this.ImageList;
        //收集图片的信息
      },
      handlerSuccess1(response, file, fileList) {
        debugger
        if (fileList) {
          let imgurl = fileList.map(
            (response) => response.response.data.url
          )[0];
          this.ruleForm.preImg = fileList.map(
            (response) => response.response.data.url
          )[0];
        }
        //收集图片的信息
      },
      handlerSuccess2(response, file, fileList) {
        debugger
        if (fileList) {
          this.ruleForm.mainImg = fileList.map(
            (response) => response.response.data.url
          )[0];
        }
        //收集图片的信息
      },
      async getInfo(id) {
        let result = await reqProductInfo(id);
        if (result.code == 200) {
          this.personIds = result.data.personList.map((person) => person.id);
          this.ruleForm = result.data;
          this.spuImageList.push({
            url: this.ruleForm.preImg,
          });
          this.mainImageList.push({
            url: this.ruleForm.mainImg,
          });
          this.ruleForm.info.map((value) => {
            this.ImageList.push({
              url: value
            });
          });
        }
      },
      async getType() {

        let result = await reqTypeAllList();
        if (result.code == 200) {
          this.typeData = result.data;
        }
      },
      async getPerson() {
        let result = await reqPersonAll();
        if (result.code == 200) {
          this.persondata = result.data;
        }
      },

      handleDialogClose() {
        this.spuImageList = [];
      },

      close() {
        this.imageList = [];
        this.$router.push({
          name: "product"
        });
      },
    },
    mounted() {
      this.getPerson();
      this.getType();
    },
    created() {
      let pid = this.$route.query.id;
      this.dialogTitle = (pid != null) ? "修改产品" : "新增产品";
      if (pid != null) {
        /* this.ruleForm = this.$route.params.data || {};
         this.spuImageList.push({
           url: this.ruleForm.preImg,
         });
        */
        this.personshow = true;
        this.getInfo(pid)
        console.log('修改')
      } else {
        this.personshow = false;
        console.log('新增')
      }
    },
  };
</script>

<style>
  .container {
    margin: 10px auto;
    width: 100%;
  }

  .el-card {
    margin-bottom: 30px;
  }
</style>
