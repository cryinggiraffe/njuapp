
//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            loading: {},
            activeIndex: '6', //默认激活
            username: '',
            cId: '',
            lId: '',
            count: 0,

            students: [{
                sId: '',
                sName: '',
                sex: ''
            }],

            /*
            editor: {
                scId: '',
                score: 0
            },

            */

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
            this.lId = sessionStorage.getItem("lId");
        },

        //刷新列表
        reloadList() {
            this.findAllAbsentLessonRecords();
        },

        findAllAbsentLessonRecords(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/absentlessonrecord/findAllAbsentLessonStudents',
                {
                    cId: this.cId,
                    lId: this.lId
                }).then(result => {
                console.log(result);
            this.students = result.data;
            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.students.clearSelection();
        },



    },

    // 生命周期函数
    created() {
        //this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.findAllAbsentLessonRecords();
        this.loadings(); //加载动画
    },

});
