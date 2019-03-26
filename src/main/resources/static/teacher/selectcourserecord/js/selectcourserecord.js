
//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            loading: {},
            activeIndex: '5', //默认激活
            username: '',
            cId: '',
            count: 0,

            selectcourserecords: [{
                scId : '',
                cId: '',
                sId: '',
                sName: '',
                score: 0
            }],

            editor: {
                scId: '',
                score: 0
            },

            multipleSelection: [],
            selectIds: [],
            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,



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
            this.findAllSelectCourseRecords();
        },

        findAllSelectCourseRecords(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/selectcourserecord/findAllSelectCourseRecords',
                {
                    cId: this.cId
                }).then(result => {
                console.log(result);
            this.selectcourserecords = result.data;
            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.selectcourserecords.clearSelection();
        },



        handleEdit(id) {
            //打开dialog
            this.showEditor = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.post('/TeachingAssistantSystem/selectcourserecord/findByScId',
                {
                    scId: id
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
            this.$http.post('/TeachingAssistantSystem/selectcourserecord/updateGrade', {
                scId: this.editor.scId,
                score: this.editor.score
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



    },

    // 生命周期函数
    created() {
        //this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.findAllSelectCourseRecords();
        this.loadings(); //加载动画
    },

});
