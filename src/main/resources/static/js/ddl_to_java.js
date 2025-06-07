let ddlEditor, javaEditor;

layui.use(['layer', 'form', 'tree', 'util'], function () {
    const layer = layui.layer;
    const form = layui.form;
    const tree = layui.tree;

    // 加载编辑框
    initDdlEditor();
    initJavaEditor();

    $("#generateBtn").click(function () {
        const html = document.getElementById("generateForm").innerHTML;

        // 在此处输入 layer 的任意代码
        layer.open({
            type: 1, // page 层类型
            area: ['65%', '70%'],
            title: '代码生成配置',
            shade: 0.6, // 遮罩透明度
            shadeClose: true, // 点击遮罩区域，关闭弹层
            maxmin: true, // 允许全屏最小化
            anim: 0, // 0-6 的动画形式，-1 不开启
            content: html,
            btn: ['提交', '取消'], // 这里是按钮
            yes: function (index, layero) {

                let formData = {};
                $.ajax({
                    type: "POST",
                    url: "/generate/ddlToJava",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    success: function (res) {
                        if (res.code === 0) {
                            const {treeData, codeMap} = buildTree(res.data);
                            // 渲染树
                            tree.render({
                                elem: '#fileTree',
                                data: treeData,
                                click: function (obj) {
                                    const code = codeMap[obj.data.id];
                                    if (code !== undefined) {
                                        const ext = obj.data.id.split('.').pop();
                                        let mode = 'text/plain';
                                        if (ext === 'java') mode = 'text/x-java';
                                        else if (ext === 'xml') mode = 'application/xml';
                                        javaEditor.setOption('mode', mode);
                                        javaEditor.setValue(code);
                                    }
                                }
                            });
                        } else {
                            layer.msg("生成失败：" + res.msg);
                        }
                        layer.close(index);
                    },
                    error: function () {
                        layer.msg("请求失败！");
                        layer.close(index);
                    }
                });
            },
            btn2: function (index) {
                layer.close(index); // 取消按钮关闭
            },
            success: function () {
                form.render();
            }
        });
    });

    form.on('submit(submitConfig)', function (data) {
        return false;
    });

});

// 将扁平路径转为树结构
function buildTree(data) {
    const root = {};
    const codeMap = {};

    data.forEach(item => {
        const parts = item.packagePath.split('/');
        codeMap[item.packagePath] = item.javaCode;

        let current = root;
        parts.forEach((part, idx) => {
            if (!current[part]) {
                current[part] = {__children: {}, __isFile: idx === parts.length - 1};
            }
            current = current[part].__children;
        });
    });

    function toLayuiTree(node, path = '') {
        return Object.entries(node).map(([key, value]) => {
            const fullPath = path ? `${path}/${key}` : key;
            const isFile = value.__isFile;
            return {
                title: key,
                id: fullPath,
                spread: true,
                icon: isFile ? 'layui-icon-file' : 'layui-icon-folder',
                children: isFile ? [] : toLayuiTree(value.__children, fullPath)
            };
        });
    }

    return {
        treeData: toLayuiTree(root),
        codeMap
    };
}

function initDdlEditor() {
    ddlEditor = CodeMirror.fromTextArea(document.getElementById("code"), {
        mode: "text/x-sql",
        theme: "eclipse",
        lineNumbers: true,
        extraKeys: {"Ctrl-Space": "autocomplete"}
    });

    ddlEditor.setSize("100%", "300px");

    ddlEditor.on("inputRead", function (cm, change) {
        if (change.text[0].match(/[\w\.]/)) {
            cm.showHint({hint: CodeMirror.hint.sql});
        }
    });
}

function initJavaEditor() {
    javaEditor = CodeMirror.fromTextArea(document.getElementById("javaCode"), {
        mode: "text/x-java",
        theme: "eclipse",
        lineNumbers: true,
        readOnly: true
    });
    javaEditor.setSize("100%", "300px");

}
