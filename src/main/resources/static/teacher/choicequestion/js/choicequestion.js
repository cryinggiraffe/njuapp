
//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            loading: {},
            activeIndex: '3', //默认激活
            username: '',
            cId: '',
            count: 0,

            choicequestions: [{
                cqId: '',
                cqContent: '',
                optionA: '',
                optionB: '',
                optionC: '',
                optionD: '',
                cqCorrectAnswer: ''
            }],

            editor: {
                cqId: '',
                cqContent: '',
                optionA: '',
                optionB: '',
                optionC: '',
                optionD: '',
                cqCorrectAnswer: ''
            },

            multipleSelection: [],
            selectIds: [],
            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false

        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
        }, 2000);

            this.username = sessionStorage.getItem("username");
            this.cId = sessionStorage.getItem("cId");
        },

        //刷新列表
        reloadList() {
            this.findAllChoiceQuestions();
        },

        findAllChoiceQuestions(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/choicequestion/findAllChoiceQuestions',
                {
                    cId: this.cId
                }).then(result => {
                console.log(result);
                this.choicequestions = result.data;
                this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.choicequestions.clearSelection();
        },


        //添加
        save(editor) {
            this.$refs[editor].validate((valid) => {
                if (valid) {
                    //关闭dialog
                    this.showSave = false;
                    //调用保存的接口
                    this.$http.post('/TeachingAssistantSystem/choicequestion/create', {
                        cId: this.cId,
                        cqContent: this.editor.cqContent,
                        optionA: this.editor.optionA,
                        optionB: this.editor.optionB,
                        optionC: this.editor.optionC,
                        optionD: this.editor.optionD,
                        cqCorrectAnswer: this.editor.cqCorrectAnswer
                    }).then(result => {
                        if (result.body.success) {
                        //保存成功
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                        //刷新表格
                        this.reloadList();
                        this.editor = {};
                        this.$refs.editor.resetFields();
                    } else {
                        //保存失败
                        this.$emit(
                            'save',
                            this.$message({
                                type: 'warning',
                                message: result.body.message,
                                duration: 6000
                            }),
                        );
                        //刷新表格
                        this.reloadList();
                        this.editor = {};
                        this.$refs.editor.resetFields();
                    }
                });
                } else {
                    this.$emit(
                    'save',
                    this.$message({
                        message: '输入信息有误！',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            return false;
        }
        });
        },
        //新增按钮
        saveBtn() {
            //打开新增dialog
            this.showSave = true;
            this.editor = {}; //清空表单
            this.fileList = []; //清空文件列表
            //清空原始数据
            if (this.$refs['editor'] !== undefined) {
                this.$refs['editor'].resetFields(); //经查询：可能是由于对象还没有生成，导致误读了空对象而报错
            }
        },


        handleEdit(id) {
            //打开dialog
            this.showEditor = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.post('/TeachingAssistantSystem/choicequestion/findByCqId',
                {
                    cqId: id
                }).then(result => {
                //this.editor = result.body[0];
                console.log(result);
            console.log(result.data);

            this.editor = result.data;
            //移除element-ui表单校验残留
            this.$refs['editor'].resetFields();

            console.log(this.editor)
        });
        },

        //更新
        sureEdit(editor) {
            //关闭对话框
            this.showEditor = false;

            console.log(this.editor)
            //调用更新数据的接口
            this.$http.post('/TeachingAssistantSystem/choicequestion/update', {
                cqId: this.editor.cqId,
                cqContent: this.editor.cqContent,
                optionA: this.editor.optionA,
                optionB: this.editor.optionB,
                optionC: this.editor.optionC,
                optionD: this.editor.optionD,
                cqCorrectAnswer: this.editor.cqCorrectAnswer
            }).then(result => {
                if (result.body.success) {
                //更新成功
                this.$message({
                    type: 'success',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
                this.goods = [];
                this.$refs.editor.resetFields();
            } else {
                //更新失败
                this.$message({
                    type: 'warning',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
                this.$refs.editor.resetFields();
            }
        })
        },

        handleDelete(id) {
            var ids = new Array();
            //var ids = {};
            //ids.id = id;
            //var ids = "";
            //ids = id;
            ids.push(id);
            //var ids = id + "";
            console.log(ids);
            this.sureDelete(ids[0]);
        },

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/TeachingAssistantSystem/choicequestion/delete',{
                cqId: ids
            }).then(result => {
                if (result.body.success) {
                //删除成功
                this.selectIds = []; //清空选项
                this.$message({
                    type: 'success',
                    message: result.body.message,
                    duration: 6000
                });
                this.reloadList();
            } else {
                //删除失败
                this.selectIds = []; //清空选项
                this.$message({
                    type: 'warning',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
            }
        });
        }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消删除',
                duration: 6000
            });
        });
        },

        handle(id){
            sessionStorage.setItem("cId", id);
            window.location.href = "/course";
        },


    },

    // 生命周期函数
    created() {
        //this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.findAllChoiceQuestions();
        this.loadings(); //加载动画
    },

});
