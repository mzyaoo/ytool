# 使用官方 OpenJDK 8 JRE 基础镜像（体积小，适合部署）
FROM openjdk:8-jre-alpine

# 设置容器中的工作目录
WORKDIR /app

# 复制你构建好的 jar 包到容器中（注意修改文件名为实际 jar 包名）
COPY target/code-generator.jar app.jar

# 暴露应用端口（根据你项目设置）
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
