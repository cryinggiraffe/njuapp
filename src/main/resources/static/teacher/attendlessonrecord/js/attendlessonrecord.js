
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

            attendlessonrecords: [{
                lId : '',
                sId: '',
                sName: '',
                alTime: ''
            }],

            /*
            editor: {
                scId: '',
                score: 0
            },

            */

            //条件查询单独封装的对象
            searchEntity: {
                sId: ''
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
            this.findAllAttendLessonRecordResults();
        },

        findAllAttendLessonRecordResults(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/attendlessonrecord/findAllAttendLessonRecordResults',
                {
                    cId: this.cId
                }).then(result => {
                console.log(result);
            this.attendlessonrecords = result.data;
            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        //搜索
        search(){
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/attendlessonrecord/findAttendLessonRecordResultsBySId',
                {
                    cId: this.cId,
                    sId: this.searchEntity.sId
                }).then(result => {
                console.log(result.data);
            this.attendlessonrecords = [];
            //this.attendlessonrecords.push(result.data);

            this.attendlessonrecords = result.data;
            //this.pageConf.totalPage = result.body.total;
            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.coursecommentrecords.clearSelection();
        },



    },

    // 生命周期函数
    created() {
        //this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.findAllAttendLessonRecordResults();
        this.loadings(); //加载动画
    },

});
