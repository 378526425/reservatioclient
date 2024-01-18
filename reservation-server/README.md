# 技术架构

开发语言： java<br>
开发框架： springboot <br>
数据库：mysql redis <br>
文件存储: minio <br>
短信通知：阿里云短信 <br>

# 基础环境

请确保本地基础环境搭建完毕 <br>
jdk 1.8<br>
Mysql 8.0<br>
redis<br>
minio<br>

# 快速启动

1. 新建数据库 数据库名：fast-reservation，注意设置数据库 字符集为utf8mb4 排序规则为utf8mb4_unicode_ci<br>
2. 执行doc文件夹下fast-reservation.sql脚本<br>
3. 修改application.yml文件中相关配置，启动端口，数据库信息，redis连接信息，minio 连接信息等<br>
4. 构建好项目后，加载maven相关依赖，如果出现依赖包无法下载，请检查maven仓库是否配置正确，例如 配置了阿里云maven镜像仓库时配置了<mirrorOf>central</mirrorOf>或是<mirrorOf>*</mirrorOf> ，如果工程中的jar包都能在阿里镜像中找到，mirrorOf填central还是*都是可以的。central表示覆盖maven中央仓库的默认url，*表示所有的仓库都到我配置的这个url取，所以会导致部分依赖阿里云镜像更新不及时而提示包不存在的错误，请以maven官方中央仓库为准，可以尝试修改为maven默认配置，不使用阿里云仓库
5. 启动命令 nohup java -Dfile.encoding=utf-8  -jar reservation.jar &

# 作者微信

MMRWXM <br>
如果有什么问题咨询或者建议，合作等，都可以添加我的微信，交流学习
