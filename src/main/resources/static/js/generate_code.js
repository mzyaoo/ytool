new Vue({
    el: '#app',
    data: {
        form: {
            username: 'root',
            password: 'root1234',
            url: 'jdbc:mysql://127.0.0.1:3306/my-db',
            driverClassName: 'com.mysql.cj.jdbc.Driver',
            dbType: 'mysql',
        },
        genConfigInfo: {
            packagePath: 'com/example/project',
            openLombok: "no",
            openSwagger: "no",
            dal: "Mybatis",
            entityName:"entity",
            mapperName:"mapper",
            serviceName:"service",
            controllerName:"controller",
        },
        tableName: '',
        tables: [],
        tableData: [],
        javaTypeList: [],
        drawer: false,
        direction: 'rtl',
    },
    mounted() {
        this.getJavaTypeList();
    },
    methods: {
        getTables() {
            $.ajax({
                type: "GET",
                url: "/generate/table/list",
                data: this.form,
                dataType: "json",
                success: result => {
                    this.tables = result;
                    this.$message({
                        showClose: true,
                        message: '数据库测试连接成功，请选择对应表获取表信息！',
                        type: 'success',
                        duration: 2000,
                        offset: 40
                    });
                },
                error: function (e) {
                    console.log(e.message);
                }
            });
        },
        handleClose() {
            this.drawer = false;
        },
        getJavaTypeList() {
            $.ajax({
                type: "GET",
                url: "/generate/tool/java/type",
                dataType: "json",
                success: result => {
                    this.javaTypeList = result;
                },
                error: function (e) {
                    console.log(e.message);
                }
            });
        },
        getColumns() {
            $.ajax({
                type: "GET",
                url: "/generate/table/columns",
                data: {
                    ...this.form,
                    tableName: this.tableName
                },
                dataType: "json",
                success: result => {
                    this.tableData = result;
                },
                error: function (e) {
                    console.log(e.message);
                }
            });
        },
        deleteColumn(index) {
            this.tableData.splice(index, 1);
        },
        generateCode() {

            if (this.tableName === '') {
                this.$message.error({
                    message: '请选择要生成的表！',
                    position: 'center',
                    duration: 2000,
                });
                return;
            }

            const requestData = {
                dbConfig: this.form,
                tableName: this.tableName,
                generateConfigRequest: this.genConfigInfo,
                columns: this.tableData
            };

            fetch('/generate/code', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('生成失败');
                    }
                    return response.blob(); // 关键：将返回内容处理为 Blob（zip 文件）
                })
                .then(blob => {
                    const blobUrl = window.URL.createObjectURL(blob);
                    const a = document.createElement('a');
                    a.href = blobUrl;
                    a.download = this.tableName + '.zip'; // 可根据请求自定义文件名
                    document.body.appendChild(a);
                    a.click();
                    a.remove();
                    window.URL.revokeObjectURL(blobUrl);
                })
                .catch(error => {
                    console.error('代码生成失败:', error);
                });
        }

    }
});