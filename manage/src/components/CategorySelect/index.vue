<template>
  <div>
    <!-- inline:代表的是行内表单，代表一行可以放置多个表单元素 -->
    <el-form :inline="true" class="demo-form-inline" :model="cForm">
      <el-form-item label="一级分类">
        <el-select placeholder="请选择" value="" @change="hander" v-model="cForm.category1Id">
          <el-option :label="c1.name" :value="c1.id" v-for="(c1) in list1" :key="c1.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="二级分类">
        <el-select placeholder="请选择" v-model="cForm.category2Id" @change="hander2">
          <el-option :label="c2.name" :value="c2.id" v-for="(c2) in list2" :key="c2.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="三级分类">
        <el-select placeholder="请选择" v-model="cForm.category3Id" @change="hander3">
          <el-option :label="c3.name" :value="c3.id" v-for="(c3) in list3" :key="c3.id"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "CategorySelect",
  data() {
    return {
      //一级分类的数据
      list1: [],
      //二级分类的数据
      list2: [],
      //三级分类的数据
      list3: [],
      //收集相应的一级二级三级分类id
      cForm:{
        category1Id:'',
        category2Id:'',
        category3Id:'',
      }
    };
  },
  mounted() {
    this.getCategory1List();
  },
  methods: {
    //获取一级分类列表
    async getCategory1List() {
      
      let result = await this.$API.attr.reqCategory1List();
      if (result.code == 200) {
        this.list1 = result.data;
      }
    },
    async hander(){
      //清除二，三级分类
      this.list2 = []
      this.list3 = []
      this.cForm.category2Id = ''
      this.cForm.category3Id = ''
//解构出一级分类id
      const {category1Id} = this.cForm
      //传值给 attr组件 一级id
      this.$emit('getcategoryId',{categoryId:category1Id,level:1})
      let result = await this.$API.attr.reqCategory2List(category1Id)
      if (result.code == 200) {
        this.list2 = result.data;
      }
    },
    async hander2(){
      
//清除三级分类
      this.list3 = []
      this.cForm.category3Id = ''
//解构出一级分类id
      const {category2Id} = this.cForm
      //传值给 attr组件 二级id
      this.$emit('getcategoryId',{categoryId:category2Id,level:2})
      let result = await this.$API.attr.reqCategory3List(category2Id)
      if (result.code == 200) {
        this.list3 = result.data;
      }
    },
    async hander3(){
      //解构出一级分类id
      const {category3Id} = this.cForm
      //传值给 attr组件 三级id
      this.$emit('getcategoryId',{ categoryId:category3Id,level:3 })
      let result = await this.$API.attr.reqCategory3List(category3Id)

    }
  },
};
</script>

<style>
</style>