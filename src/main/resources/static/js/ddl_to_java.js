new Vue({
    el: '#app',
    data() {
        return {
            ddlEditor: null,
            javaCodeEditor: null,
            ddlText: '',
            javaCode: '// 点击左侧预览代码',
            formVisible: false,
            codeEditMode: 'text/x-java',
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            fileTree: [],
        };
    },
    mounted() {
        // 初始化ddl编辑插件
        this.ddlEditorInit();
        // 初始化java编辑插件
        this.javaCodeEditorInit();
    },
    methods: {
        onSubmit() {
            console.log('submit!');
        },
        codeChange(val) {
            if (val.includes("xml")) {
                this.codeEditMode = "application/xml";
            } else {
                this.codeEditMode = "text/x-java";
            }
            this.javaCodeEditorInit();
        },
        javaCodeEditorInit() {

            // 销毁已有实例（避免重复）
            if (this.javaCodeEditor) {
                this.javaCodeEditor.toTextArea();
            }

            // 初始化 CodeMirror
            this.javaCodeEditor = CodeMirror.fromTextArea(document.getElementById("javaCode"), {
                mode: this.codeEditMode,
                theme: "eclipse",
                lineNumbers: true,
                readOnly: true,
                extraKeys: {
                    "Ctrl-Space": "autocomplete"
                }
            });

            // 让编辑器高度自适应父容器
            this.javaCodeEditor.setSize("100%", "100%");

            // 输入时自动触发 SQL 提示
            this.javaCodeEditor.on("inputRead", function (cm, change) {
                if (change.text[0].match(/[\w\.]/)) {
                    cm.showHint({hint: CodeMirror.hint.sql});
                }
            });
        },
        ddlEditorInit() {
            // 初始化 CodeMirror
            this.ddlEditor = CodeMirror.fromTextArea(document.getElementById("code"), {
                mode: "text/x-sql",
                theme: "eclipse",
                lineNumbers: true,
                extraKeys: {
                    "Ctrl-Space": "autocomplete"
                }
            });

            // 让编辑器高度自适应父容器
            this.ddlEditor.setSize("100%", "100%");

            // 输入时自动触发 SQL 提示
            this.ddlEditor.on("inputRead", function (cm, change) {
                if (change.text[0].match(/[\w\.]/)) {
                    cm.showHint({hint: CodeMirror.hint.sql});
                }
            });
        },
        toggle(node) {
            if (node.type === 'folder') {
                node.open = !node.open;
            } else {
                this.handleFileClick(node);
            }
        },
        handleFileClick(fileNode) {
            this.clearSelection(this.fileTree);
            fileNode.selected = true;
            this.javaCode = fileNode.javaCode;

            const ext = fileNode.label.split('.').pop();
            const mode = ext === 'xml' ? 'application/xml' : 'text/x-java';
            this.javaCodeEditor.setOption("mode", mode);
            this.javaCodeEditor.setValue(fileNode.javaCode);
        },
        clearSelection(nodes) {
            nodes.forEach(node => {
                node.selected = false;
                if (node.children) this.clearSelection(node.children);
            });
        },
        buildTree(data) {
            const tree = [];
            const map = {};

            data.forEach(file => {
                const parts = file.packagePath.split('/');
                let current = tree;

                parts.forEach((part, index) => {
                    const pathSoFar = parts.slice(0, index + 1).join('/');
                    if (!map[pathSoFar]) {
                        const isFile = index === parts.length - 1;
                        const node = {
                            id: pathSoFar,
                            label: part,
                            type: isFile ? 'file' : 'folder',
                            open: !isFile,
                            selected: false,
                            children: [],
                            ...(isFile ? {javaCode: file.javaCode} : {})
                        };
                        map[pathSoFar] = node;
                        current.push(node);
                    }
                    current = map[pathSoFar].children;
                });
            });

            return tree;
        },
        generateHandler() {
            this.formVisible = true;

        },
        copyCode() {

        },
        generateFormSubmit(){
            $.ajax({
                type: "POST",
                url: "/generate/ddlToJava",
                data: JSON.stringify(this.form),
                contentType:"application/json",
                success: result => {
                    if (result.code === 0){
                        console.log(JSON.stringify(result.data))
                        this.fileTree = this.buildTree(result.data);
                    }
                },
                error: function (e) {
                    console.log(e.message);
                }
            });

            // 关闭弹窗
            this.formVisible = false;
        }
    }
});