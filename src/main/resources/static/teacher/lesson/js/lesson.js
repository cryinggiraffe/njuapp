
//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            loading: {},
            activeIndex: '4', //默认激活
            username: '',
            cId: '',
            count: 0,

            lessons: [{
                lId: '',
                lName: '',
                lTime: '',
                actualNumber: 0,
                reachedNumber: 0,
                signCode: '',
                start: '',
                end: ''
            }],

            editor: {
                lId: '',
                lTime: 0,
                signCode: ''
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
            this.updateReachedNumber();
            this.findAllLessons();
        },

        findAllLessons(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/lesson/findAllLessons',
                {
                    cId: this.cId
                }).then(result => {
                console.log(result);
            this.lessons = result.data;

            for (var i=0,len=this.lessons.length; i<len; i++){

                var date = new Date(this.lessons[i].lTime);
                var date_value=date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();

                this.lessons[i].lTime = date_value + '';

                var date_start = new Date(this.lessons[i].start);
                var date_value_start = date_start.getFullYear() + '-' + (date_start.getMonth() + 1) + '-' + date_start.getDate() + ' ' + date_start.getHours() + ':' + date_start.getMinutes() + ':' + date_start.getSeconds();

                this.lessons[i].start = date_value_start + '';

                var date_end = new Date(this.lessons[i].end);
                var date_value_end = date_end.getFullYear() + '-' + (date_end.getMonth() + 1) + '-' + date_end.getDate() + ' ' + date_end.getHours() + ':' + date_end.getMinutes() + ':' + date_end.getSeconds();

                this.lessons[i].end = date_value_end + '';

            }

            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        updateReachedNumber(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/lesson/updateReachedNumber',
                {
                    cId: this.cId
                }).then(result => {
                console.log(result);
                this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.lessons.clearSelection();
        },


        //添加
        save(editor) {
            this.$refs[editor].validate((valid) => {
                if (valid) {
                    //关闭dialog
                    this.showSave = false;

                    var date = new Date(this.editor.lTime + '');
                    //var date_value=date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
                    var date_value = date.getTime();
                    //调用保存的接口
                    this.$http.post('/TeachingAssistantSystem/lesson/create', {
                        cId: this.cId,
                        lTime: date_value,
                        signCode: this.editor.signCode
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
            this.$http.post('/TeachingAssistantSystem/lesson/findByLId',
                {
                    lId: id
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
            this.$http.post('/TeachingAssistantSystem/lesson/update', {
                lId: this.editor.lId,
                signCode: this.editor.signCode
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

        handleList(id){
            sessionStorage.setItem("lId", id);
            window.location.href = "/TeachingAssistantSystem/attendlessonstudent";
        },

        handle(id){
            sessionStorage.setItem("lId", id);
            window.location.href = "/TeachingAssistantSystem/absentlessonstudent";
        },


    },

    // 生命周期函数
    created() {
        //this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.updateReachedNumber();
        this.findAllLessons();
        this.loadings(); //加载动画
    },

});
